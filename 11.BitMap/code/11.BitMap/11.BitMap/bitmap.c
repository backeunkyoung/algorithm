#pragma warning(disable: 4996)
#include <stdio.h>

struct list {
    int length, capacity;
    int* items;
};

typedef struct list list;

// Make a new list
list* newList() {
    list* al = malloc(sizeof(list));
    int* items = malloc(4 * sizeof(int));
    *al = (list){ 0, 4, items };
    return al;
}

// Expand list
void expand(list* al) {
    al->capacity = al->capacity * 3 / 2;
    al->items = realloc(al->items, al->capacity * sizeof(int));
}

// Add an int to list
void add(list* al, int n) {
    if (al->length >= al->capacity) expand(al);
    al->items[al->length] = n;
    al->length++;
}

// Print list
void print(list* al) {
    printf("[ ");
    for (int i = 0; i < al->length; i++) {
        if (i > 0) printf(", ");
        printf("%d", al->items[i]);
    }
    printf(" ]\n");
}

int get_pisano_period(int M) {
    int a = 0;
    int b = 1;
    int c = a + b;

    for (int i = 0; i < M * M; i++) {
        c = (a + b) % M;
        a = b;
        b = c;

        if (a == 0 && b == 1) {
            return i + 1;
        }
    }
}

void get_fibonacci_pisano(int m, int pisano, list* pisanoList) {

    add(pisanoList, 0);
    add(pisanoList, 1);

    int first = 0;
    int second = 1;

    for (int i = 0; i < pisano - 1; i++) {
        int res = (first + second) % m;
        first = second % m;
        second = res;
        add(pisanoList, res);
        //ret[i + 2] = res;

        //return pisanoList;
    }
}

void main() {
    FILE* inp;
    FILE* out;

    inp = fopen("bitmap.inp", "rt");
    out = fopen("bitmap.out", "wt");

    while (1) {
        char type;
        int row = 0;
        int column = 0;

        if (fscanf(inp, "%c %d %d", &type, &row, &column) == 1) {}
        printf("%c %d %d", &row, &column);
    }

    int numOfCase = 0;
    if (fscanf(inp, "%d", &numOfCase) == 1) {}

    list* pisanoList;
    pisanoList = newList();

    for (int i = 0; i < numOfCase; i++) {
        

        //fprintf(out, "%d\n", result);
    }

    fclose(inp);
    fclose(out);
}
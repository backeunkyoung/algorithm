#pragma warning(disable: 4996)
#include <iostream>

using namespace std;

/*
item = [0,0,0,0]

list 
 length = 3
 capacity = 8
 items = [0, 1, 2, 3, 4, null, null, null]

add(list, 0)
add(list ,1)
add(list, 2)
add(list, 3)
add(list, 4)
*/

struct list {
    int length;
    unsigned long long capacity;
    int* items;
};

typedef struct list list;

// Make a new list
list* newList(unsigned long long pisano) {
    list* al = (list * )malloc(sizeof(list));
    int* items = (int *)malloc(pisano * sizeof(int));
    *al = (list) { 0, pisano, items };
    return al;
}

// Expand list
//void expand(list* al) {
//    al->capacity = al->capacity * 3 / 2;
//    al->items = (int *)realloc(al->items, al->capacity * sizeof(int));
//}

// Add an int to list
void add(list* al, int n) {
    //if (al->length >= al->capacity) expand(al);
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

unsigned long long get_pisano_period(int M) {
    unsigned long long a = 0;
    unsigned long long b = 1;
    unsigned long long c = a + b;

    for (unsigned long long i = 0; i < M * M; i++) {
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

    //printf("pisano : %d\n", pisano);
    for (int i = 0; i < pisano - 1; i++) {
        int res = (first + second) % m;
        first = second % m;
        second = res;
        add(pisanoList, res);
    }
}

//int get_fibonacci_huge(int n, int m, int pisano) {
//
//    if (n < 2) { return n; }
//
//    int remainder = n % pisano;
//
//    int first = 0;
//    int second = 1;
//
//    int res = remainder;
//
//    for (int i = 0; i < remainder - 1; i++) {
//        res = (first + second) % m;
//        first = second % m;
//        second = res % m;
//    }
//
//    return res;
//}

int main() {
    FILE* inp;
    FILE* out;

    inp = fopen("fibonacci.inp", "rt");
    out = fopen("fibonacci.out", "wt");

    int numOfCase = 0;
    if (fscanf(inp, "%d", &numOfCase) == 1) {}

    list* pisanoList;
    

    //printf("%d", numOfCase);
    for (int i = 0; i < numOfCase; i++) {
        //printf("\n---------------\n");

        int fA = 0, fB = 0, fC = 0, fD = 0, M = 0;

        if (fscanf(inp, "%d %d %d %d %d", &fA, &fB, &fC, &fD, &M) == 1) {}
        //printf("%d %d %d %d %d\n", fA, fB, fC, fD, M);

        unsigned long long pisano = get_pisano_period(M);
        //printf("_pisano : %llu\n", pisano);
        pisanoList = newList(pisano);

        /*unsigned long long resultA = (unsigned long long)get_fibonacci_huge(fA, M, pisano);
        unsigned long long resultB = (unsigned long long)get_fibonacci_huge(fB, M, pisano);
        unsigned long long resultC = (unsigned long long)get_fibonacci_huge(fC, M, pisano);
        unsigned long long resultD = (unsigned long long)get_fibonacci_huge(fD, M, pisano);

        printf("result A, B, C, D =>  %llu %llu %llu %llu\n", resultA, resultB, resultC, resultD);*/

        //unsigned long long result = (((resultA * resultB) % M) + ((resultC * resultD) % M)) % M;
        //result = (((resultA * resultB)) % M + ((resultC * resultD)) % M) % M

        //printf("result : %llu\n", result);
        //fprintf(out, "%llu\n", result);

        get_fibonacci_pisano(M, pisano, pisanoList);
        //print(pisanoList);

        unsigned long long resultA = (unsigned long long)(pisanoList->items[fA % pisano]);
        unsigned long long resultB = (unsigned long long)(pisanoList->items[fB % pisano]);
        unsigned long long resultC = (unsigned long long)(pisanoList->items[fC % pisano]);
        unsigned long long resultD = (unsigned long long)(pisanoList->items[fD % pisano]);

        //printf("result A, B, C, D =>  %llu %llu %llu %llu\n", resultA, resultB, resultC, resultD);

        unsigned long long result = (((resultA * resultB)) % M + ((resultC * resultD)) % M) % M;
        //printf("result : %llu\n", result);

        cout >> result >> endl;

        fprintf(out, "%llu\n", result);
    }

    fclose(inp);
    fclose(out);

    return 0;
}
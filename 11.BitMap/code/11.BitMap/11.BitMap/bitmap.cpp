#pragma warning(disable: 4996)
#include <iostream>
#include <string>
using namespace std;

int main() {
    FILE* inp;
    FILE* out;

    inp = fopen("bitmap.inp", "rt");
    out = fopen("bitmap.out", "wt");

    string inputList;

    while (1) {
        char type;
        int row = 0;
        int column = 0;

        if (fscanf(inp, "%c", &type) == 1) {}

        if (type == '#') {
            break;
        }

        if (fscanf(inp, "%d %d", &row, &column) == 1) {}
        if (fscanf(inp, "%d %d", &row, &column) == 1) {}
        printf("%c %d %d", type, row, column);
    }

    fclose(inp);
    fclose(out);

    return 0;
}
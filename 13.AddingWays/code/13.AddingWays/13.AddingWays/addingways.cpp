#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

int sum = 0;

void calc(int n, int k) {   // 10 5
    //cout << "P( " << n << ", " << k << " )" << endl;

    if (n < k) {
        return;
    }

    if (n == k || k == 1) {
        sum++;
        return;
    }
        
    calc(n-1, k-1);
    calc(n - k, k);
}

int main() {

    ifstream readFile;
    ofstream writeFile;

    readFile.open("addingways.inp");
    writeFile.open("addingways.out");

    int size;
    int k;

    while (true) {

        // --- file read ---
        string str;

        getline(readFile, str);

        istringstream ss(str);
        string fragment;

        int input[2];

        int count = 0;
        while (getline(ss, fragment, ' ')) {
            input[count] = (stoi(fragment));
            count++;
        }

        if (input[0] == 0 && input[1] == 0) {
            break;
        }

        //cout << "--------------------" << endl;
        // --- file read end ---

        
        /*for (int i = 0; i < 2; i++) {
            cout << "P( " << input[0] << ", " << input[1] << " )" << endl;
        }*/

        calc(input[0], input[1]);

        //cout << "sum : " << sum << endl;

        writeFile << sum << "\n";

        sum = 0;
    }

    readFile.close();
    writeFile.close();

    return 0;

}
#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <limits.h>
using namespace std;

string game(vector<int> bottleCoin) {
    string result = "";
    int lose = 0;
    int win = 0;

    for (int i = 0; i < 3; i++) {
        int N = bottleCoin[i];

        if (N == 0 || N == 1) {
            lose += 1;
            continue;
        }
        else {
            int R = (N - 1) % 4;
            if (R == 0) {
                lose += 1;
            }
            else {
                win += 1;
            }
        }
    }

    cout << "lose : " << lose << ", win : " << win << endl;

    if (lose == 3 || lose == 1) {
        result = "-1";
    }
    else {
        result = "1";
    }

    cout << result << endl;

    return result;
}

int main() {

    ifstream readFile;
    ofstream writeFile;

    readFile.open("coin.inp");
    writeFile.open("coin.out");

    string str;

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        cout << "\n====================" << endl;

        vector<int> bottleCoin;
        string fragment;

        getline(readFile, str);
        istringstream ss(str);

        while (getline(ss, fragment, ' ')) {
            bottleCoin.push_back(stoi(fragment));
        }

        string result = "(" + to_string(bottleCoin[0]) + " " + to_string(bottleCoin[1]) + " " + to_string(bottleCoin[2]) + ") : ";
        cout << result << endl;

        result += game(bottleCoin);
    
        writeFile << result << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;

}
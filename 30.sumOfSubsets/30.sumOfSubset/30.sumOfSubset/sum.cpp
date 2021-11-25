#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <sstream>
#include <vector>
using namespace std;

int trueSum;
int resultCount;
vector<vector<int>> resultList;

void sum_of_subset(vector<int> weightList, vector<int> checkList, int startIndex, int total) {
    if (total == trueSum) {
        /*for (int i = 0; i < checkList.size(); i++) {
            cout << checkList[i] << " ";
        }
        cout << endl;*/

        resultCount++;

        return;
    }
    else if (total > trueSum) {
        return;
    }

    startIndex++;
    // 1 3 5 7 9 3 6 7 8

    //cout << "startIndex : " << startIndex << endl;

    int weightSize = weightList.size();
    if (startIndex >= weightSize) {
        return;
    }

    int addNode = weightList[startIndex];

    // 1 3 11 29
    sum_of_subset(weightList, checkList, startIndex, total);  // n
    //checkList.push_back(addNode);
    sum_of_subset(weightList, checkList, startIndex, total + addNode);  // y
}

int main() {

    ifstream readFile;
    ofstream writeFile;

    readFile.open("sum.inp");
    writeFile.open("sum.out");

    string str;

    getline(readFile, str);
    int numOfCase = stoi(str);
    //cout << numOfCase << endl;

    for (int i = 0; i < numOfCase; i++) {
        //cout << "\n----------------------------------------" << endl;
        resultCount = 0;

        getline(readFile, str);
        istringstream ss(str);

        string fragment;

        vector<int> data;
        while (getline(ss, fragment, ' ')) {
            data.push_back(stoi(fragment));
        }

        int vectorSize = data[0];
        trueSum = data[1];

        //cout << "trueSum : " << trueSum << endl;

        getline(readFile, str);
        istringstream ss2(str);

        string fragment2;

        vector<int> weightList;
        vector<int> checkList;
        while (getline(ss2, fragment2, ' ')) {
            weightList.push_back(stoi(fragment2));
        }

        sum_of_subset(weightList, checkList, -1, 0);
        //cout << "count : " << resultCount << endl;
        writeFile << resultCount << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;
}
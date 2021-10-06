#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
using namespace std;

string getResult(string remainStr, string matchStr) {
    //cout << "remainStr : " << remainStr << ", matchStr : " << matchStr << endl;

    reverse(remainStr.begin(), remainStr.end());
    reverse(matchStr.begin(), matchStr.end());

    //cout << "remainStr : " << remainStr << ", matchStr : " << matchStr << endl;

    int matchStrSize = matchStr.size();
    int remainStrSize = remainStr.size();

    string copy = "";
    int matchCount = 0;

    for (int i = 0; i < remainStrSize; i++) {
        string nextStr = "";

        char ch = remainStr.at(i);

        if (ch == matchStr.at(matchCount)) {
            copy += ch;
            //cout << "add : " << ch << endl;
            //cout << "copy : " << copy << endl;
            matchCount++;
        }
        else {
            //cout << "skip : " << ch << ", " << remainStr.at(i + 1) << endl;
            i++;
        }

        for (int j = i + 1; j < remainStrSize; j++) {
            nextStr += remainStr.at(j);
        }
        //cout << "nextStr : " << nextStr << endl;


        if (copy.size() + nextStr.size() < matchStr.size()) {
            return "NO";
        }

        if (copy.size() == matchStr.size()) {
            /*cout << "remain str : ";
            for (int j = i+1; j < remainStrSize; j++) {
                cout << remainStr.at(j) << " ";
            }*/
            return "YES";
        }
    }

}

int main() {

    ifstream readFile;
    ofstream writeFile;

    readFile.open("backspace.inp");
    writeFile.open("backspace.out");

    string str = "";

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        //cout << "-----------------" << endl;

        string baseStr = "";
        string remainStr;
        string matchStr;

        getline(readFile, remainStr);
        //cout << remainStr << endl;

        getline(readFile, matchStr);
        //cout << matchStr << endl;

        if (remainStr.size() < matchStr.size()) {
            //cout << "result : NO" << endl;
            writeFile << "NO" << "\n";
            continue;
        }

        string result = getResult(remainStr, matchStr);

        //cout << "\nresult : " << result << endl;
        
        writeFile << result << "\n";


    }

    readFile.close();
    writeFile.close();

    return 0;

}
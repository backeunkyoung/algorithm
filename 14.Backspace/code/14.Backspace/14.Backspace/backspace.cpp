// ababa => ba 를 만들 수 있는가?
// 1). b를 만들 수 있는지를 찾음
// 1-2). a를 만들 수 있는지를 찾음 => 안되면 다시 1)로 돌아감

// 1. a, a+1 삭제
// 1-1. 마지막 인덱스는 a(본인)만 삭제
// 2. 그대로 두기

#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <string_view>
#include <vector>
using namespace std;

string compare(string baseStr, string remainStr, string matchStr) {
    /*cout << "-----------" << endl;
    cout << "baseStr : " << baseStr << " , remainStr : " << remainStr <<  endl;*/

    if (remainStr.size() == 0) {
        //cout << "NO" << endl;
        return "NO";
    }

    // baseStr : ""
    // remainStr : ababa
    // matchStr : ba

    // baseStr : "ab"
    // remainStr : "bb"
    // 1) base + remain.at(0) => ab + b => abb
    // 2) base - base.last ... ab - b => a

    char ch = remainStr.at(0);

    string first = baseStr + ch;

    if (baseStr.size() > 0) {
        reverse(baseStr.begin(), baseStr.end());
        baseStr = baseStr.substr(1);
        reverse(baseStr.begin(), baseStr.end());
    }
    string second = baseStr;

    remainStr = remainStr.substr(1);
    
    //cout << "first : " << first << " , second : " << second << " , remainStr : " << remainStr << ", matchStr : " << matchStr <<  endl;

    if (first == matchStr || second == matchStr || remainStr == matchStr) {
        //cout << "YES" << endl;
        return "YES";
    }

    // match ="AA" , first = "AB"
    // "AA" != "AB" return 


    /*if (matchStr.size() <= first.size() || matchStr.size() <= second.size()) {
        return "NO";
    }*/

    int firstSize = first.size();
    string subMatch1 = matchStr.substr(0, firstSize);

    int secondSize = second.size();
    string subMatch2 = matchStr.substr(0, secondSize);
    
    /*if (subMatch1 != first || subMatch2 != second) {
        return "NO";
    }*/

    
    string firstReturn =  compare(first, remainStr, matchStr);

    if (firstReturn == "NO") {
        return compare(second, remainStr, matchStr);
    }
    else {
        return firstReturn;
    }
    
}

string getResult(string remainStr, string matchStr) {
    //cout << "remainStr : " << remainStr << ", matchStr : " << matchStr << endl;

    reverse(remainStr.begin(), remainStr.end());
    reverse(matchStr.begin(), matchStr.end());

    cout << "remainStr : " << remainStr << ", matchStr : " << matchStr << endl;

    int matchStrSize = matchStr.size();
    int remainStrSize = remainStr.size();

    vector<int> matchVec (matchStr.begin(), matchStr.end());
    vector<int> check;
    vector<int> copy;

    for (int i = 0; i < remainStrSize; i++) {
        bool isPush = false;
        char ch = remainStr.at(i);
        //cout << "\nch : " << ch;

        for (int j = 0; j < matchStrSize; j++) {
            //cout << " | match char : " << matchStr.at(j) << " | ";

            if (ch == matchStr.at(j)) {
                check.push_back(matchStr.at(j));
                //cout << " | push : " << matchStr.at(j);
                isPush = true;
                break;
            }
        }

        if (isPush == false) {
            check.push_back(-1);
            //cout << " | push : " << -1;
        }
    }

    for (int i = 0; i < check.size(); i++) {
        if (check[i] == -1) {
            i++;
        }
        else {
            copy.push_back(check[i]);
        }
    }

    cout << "\nmatchVec : ";
    for (int i = 0; i < matchVec.size(); i++) {
        cout << matchVec[i] << " ";
    }
    cout << endl;

    cout << "check    : ";
    for (int i = 0; i < check.size(); i++) {
        cout << check[i] << " ";
    }
    cout << endl;

    cout << "copy    : ";
    for (int i = 0; i < copy.size(); i++) {
        cout << copy[i] << " ";
    }
    cout << endl;

    if (copy.size() < matchVec.size()) {
        return "NO";
    }

    if (copy.size() == matchVec.size() && copy != matchVec) {
        return "NO";
    }


    vector<int> finalList;

    int copyNum = 0;
    for (int i = 0; i < matchVec.size(); i++) {
        char ch = matchVec.at(i);
        //cout << "ch : " << ch << ", copy :  " << copy[copyNum] << " | ";

        if (ch != copy[copyNum]) {
            copyNum++;
            i--;
        }
        else {
            //cout << copy[copyNum] << " ";
            finalList.push_back(copy[copyNum]);
            copyNum++;
        }
        //cout << endl;
    }

    cout << "final List : ";
    for (int i = 0; i < finalList.size(); i++) {
        cout << finalList[i] << " ";
    }
    cout << endl;


    if (finalList == matchVec) {
        return "YES";
    }
    else {
        return "NO";
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
        cout << "-----------------" << endl;

        string baseStr = "";
        string remainStr;
        string matchStr;

        getline(readFile, remainStr);
        //cout << remainStr << endl;

        getline(readFile, matchStr);
        //cout << matchStr << endl;

        if (remainStr.size() < matchStr.size()) {
            cout << "result : NO" << endl;
            writeFile << "NO" << "\n";
            continue;
        }

        //string result = compare(baseStr, remainStr, matchStr);
        string result = getResult(remainStr, matchStr);

        cout << "\nresult : " << result << endl;
        
        writeFile << result << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;

}
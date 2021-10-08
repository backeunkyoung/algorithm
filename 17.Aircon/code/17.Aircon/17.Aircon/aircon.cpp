#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <limits.h>
using namespace std;

//void printRoomMap(map<int, unsigned long long>& m) {
//    for (map<int, unsigned long long>::iterator itr = m.begin(); itr != m.end(); ++itr) {
//        cout << "key : " << itr->first << " , value : " << itr->second << endl;
//    }
//}

int main() {

    ifstream readFile;
    ofstream writeFile;

    readFile.open("aircon.inp");
    writeFile.open("aircon.out");

    string str;
    string split = " ";

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        //cout << "\n====================" << endl;

        vector<int> roomData;
        map<int, unsigned long long> roomMap;

        vector<int> inputKey;
        vector<unsigned long long> inputVal;
        string fragment;

        // roomData
        getline(readFile, str);
        istringstream ss(str);
        while (getline(ss, fragment, ' ')) {
            roomData.push_back(stoi(fragment));
        }
        fragment = "";

        int roomOfNum = roomData[0];

        // roomMap - key
        getline(readFile, str);
        istringstream ss2(str);
        while (getline(ss2, fragment, ' ')) {
            inputKey.push_back(stoi(fragment));
        }
        // roomMap - value
        getline(readFile, str);
        istringstream ss3(str);
        while (getline(ss3, fragment, ' ')) {
            inputVal.push_back(stoi(fragment));
        }

        int airconNum = inputKey.size();

        for (int j = 0; j < airconNum; j++) {
            roomMap.insert({ inputKey[j] , inputVal[j] });
        }

        /*cout << "roomData : ";
        for (int j = 0; j < roomData.size(); j++) {
            cout << roomData[j] << " ";
        }
        cout << endl;*/

        //cout << "-- roomMap --" << endl;
        //printRoomMap(roomMap);

        vector<unsigned long long> roomList;
        for (int j = 1; j <= roomOfNum; j++) {
            unsigned long long minTemp = LONG_MAX;

            //cout << "---- roomNum : " << j << " ----" << endl;

            for (int t = 0; t < inputKey.size(); t++) {
                int key = inputKey[t];
                //cout << endl;
                //cout << "aircon key : " << key << endl;
                unsigned long long airconTemp = roomMap[key];
                //cout << "aircon Temp : " << airconTemp << endl;

                int diff = key - j;
                diff = abs(diff);
                //cout << "diff : " << diff << endl;

                unsigned long long nowTemp = airconTemp + diff;
                //cout << "nowTemp : " <<  nowTemp << endl;

                if (minTemp > nowTemp) {
                    minTemp = nowTemp;
                }
            }

            roomList.push_back(minTemp);
            //cout << endl;
        }

        int roomListSize = roomList.size();

        //cout << "-- roomList --" << endl;
        for (int j = 0; j < roomListSize; j++) {
            //cout << roomList[j] << " ";
            writeFile << roomList[j] << " ";
        }
        //cout << endl;
        writeFile << "\n";

        //writeFile << maxScore << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;

}
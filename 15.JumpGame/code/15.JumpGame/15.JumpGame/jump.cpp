#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <map>
using namespace std;

void print_map(map<int, int>& m) {
    for (map<int, int>::iterator itr = m.begin(); itr != m.end(); ++itr) {
        cout << "key : " << itr->first << " , value : " << itr->second << endl;
    }
}

void print_map_indexMap(map<int, vector<int>>& m) {
    for (map<int, vector<int>>::iterator itr = m.begin(); itr != m.end(); ++itr) {
        cout << "key : " << itr->first << " , value : ";
        for (int i = 0; i < itr->second.size(); i++) {
            cout << itr->second[i] << " ";
        }
        cout << endl;
    }
}

int main() {

    ifstream readFile;
    ofstream writeFile;

    readFile.open("jump.inp");
    writeFile.open("jump.out");

    string str;

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        cout << "-----------------" << endl;
        
        getline(readFile, str);
        int n = stoi(str);
        //cout << "n : " << n << endl;

        vector<int> arr;

        getline(readFile, str);
        istringstream ss(str);
        string fragment;

        while (getline(ss, fragment, ' ')) {
            arr.push_back(stoi(fragment));
        }

        /*for (int j = 0; j < arr.size(); j++) {
            cout << arr[j] << " ";
        }
        cout << endl;*/

        int maxScore = 0;

        map<int, int> scoreMap;

        map<int, vector<int>> indexMap;

        for (int j = 0; j < n; j++) {
            cout << "----------------------------------------" << endl;

            int index = j;
            int score = 0;
            //bool first = true;

            cout << "index : " << index << endl;

            int nowInt = arr[index];

            int nextIndex = nowInt + j;
            cout << "nextIndex : " << nextIndex << endl;

            score = nowInt;
            cout << "score : " << score << endl;

            scoreMap.insert({ index, score });

            if (nextIndex < n) {

                if (indexMap.count(nextIndex) == 0) {
                    vector<int> addInt = { index };
                    indexMap.insert(make_pair(nextIndex, addInt));
                }
                else {
                    vector<int> addInt = indexMap[nextIndex];
                    addInt.push_back(index);
                    indexMap[nextIndex] = addInt;
                }            
            }

            if (indexMap[index].size() != 0) {
                int newNextIndex = 0;

                int indexMapSize = indexMap[index].size();
                for (int t = 0; t < indexMapSize; t++) {
                    newNextIndex = index;
                    int newScore = 0;
                    int getIndex = indexMap[index][t];

                    newScore += scoreMap[getIndex];
                    newScore += score;

                    scoreMap[getIndex] = newScore;

                    newNextIndex += score;

                    if (indexMap.count(newNextIndex) == 0) {
                        vector<int> addInt = { getIndex };
                        indexMap.insert(make_pair(newNextIndex, addInt));
                    }
                    else {
                        vector<int> addInt = indexMap[newNextIndex];
                        addInt.push_back(getIndex);
                        indexMap[newNextIndex] = addInt;
                    }
                }

                //cout << "scoreMap[" << index << "] : " << newScore << endl;
            }
        }

        cout << "-- scoreMap --" << endl;
        print_map(scoreMap);

        
        cout << "-- indexMap --" << endl;
        print_map_indexMap(indexMap);

        for (map<int, int>::iterator itr = scoreMap.begin(); itr != scoreMap.end(); ++itr) {
            if (maxScore < itr->second) {
                maxScore = itr->second;
            }
        }

        cout << "\nmaxScore : " << maxScore << endl;
        //writeFile << maxScore << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;

}
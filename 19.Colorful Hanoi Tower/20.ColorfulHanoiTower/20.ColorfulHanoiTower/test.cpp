#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

int main() {
    ifstream readFile;
    ofstream writeFile;

    readFile.open("hanoi.inp");
    writeFile.open("hanoi.out");

    string str;

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        cout << "\n--------------------------" << endl;

        getline(readFile, str);
        int floor = stoi(str);

        vector<string> color;   // 색깔을 저장한 배열
        vector<string> count;   // 한 층에 디스크가 몇 개 있는지 저장한 배열
        vector<int> move;       // 한 층의 이동 횟수

        for (int j = 0; j < floor; j++) {
            vector<string> fileData;
            string fragment;

            getline(readFile, str);
            istringstream ss(str);

            while (getline(ss, fragment, ' ')) {
                fileData.push_back(fragment);
            }

            color.push_back(fileData[0]);
            count.push_back(fileData[1]);

            int minus = j+1;
            int input = 1;
            for (int t = minus; t < floor; t++) {
                input *= 2;
            }
            move.push_back(input);
        }

        cout << "-- color --" << endl;
        for (int j = 0; j < color.size(); j++) {
            cout << color[j] << " ";
        }
        cout << endl;

        cout << "-- count --" << endl;
        for (int j = 0; j < count.size(); j++) {
            cout << count[j] << " ";
        }
        cout << endl;

        cout << "-- move --" << endl;
        for (int j = 0; j < move.size(); j++) {
            cout << move[j] << " ";
        }
        cout << endl;

        cout << "floor : " << floor << endl;
        for (int j = 0; j < floor; j++) {

            if (color[j] == "R" && move[j]%2 != 0) {
                for (int t = floor-j-1; t > 0; t--) {
                    move[t] += 1;
                }
            }
            else if (color[j] == "G") {
                continue;
            }
            else if (color[j] == "B" && move[j] % 2 == 0) {

            }
        }

    }

    //writeFile << result << "\n";

    readFile.close();
    writeFile.close();

    return 0;
}
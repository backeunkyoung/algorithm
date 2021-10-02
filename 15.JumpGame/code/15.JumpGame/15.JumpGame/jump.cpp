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

    readFile.open("jump.inp");
    writeFile.open("jump.out");

    string str;

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        //cout << "-----------------" << endl;
        
        getline(readFile, str);
        int n = stoi(str);
        vector<int> arr;

        getline(readFile, str);
        istringstream ss(str);
        string fragment;

        int maxScore = 0;

        while (getline(ss, fragment, ' ')) {
            arr.push_back(stoi(fragment));
        }

        /*for (int j = 0; j < arr.size(); j++) {
            cout << arr[j] << " ";
        }
        cout << endl;*/

        for (int j = 1; j <= n; j++) {
            //cout << "---------------" << endl;
            int index = j-1;
            int moveIndex = 0;
            int score = 0;
            bool first = true;

            while (true) {
                //cout << "index : " << index << endl;

                if (index >= arr.size()) {
                    break;
                }

                if (first) {
                    moveIndex += j + arr[index];
                    first = false;
                }
                else {
                    moveIndex += arr[index];
                }

                score += arr[index];
                index += arr[index];

                //cout << "nextIndex + 1 : " << moveIndex << endl;
                //cout << "score : " << score << endl << endl;

                if (maxScore < score) {
                    maxScore = score;
                }
            }
        }

        //cout << "maxScore : " << maxScore << endl;
        writeFile << maxScore << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;

}
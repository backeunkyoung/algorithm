#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <limits.h>
using namespace std;

int getScore(vector<vector<int>> coinData) {
    vector<int> scoreList;
    vector<int> maxScoreList;

    /*cout << " -- coinData --" << endl;
    for (int j = 0; j < 2; j++) {
        for (int k = 0; k < coinData[j].size(); k++) {
            cout << coinData[j][k] << " ";
        }
        cout << endl;
    }
    cout << endl;*/

    int numOfColumn = coinData[0].size();

    for (int j = 0; j < numOfColumn; j++) {
        vector<vector<int>> coinDataCopy = coinData;
        int maxScore = INT_MIN;

        //cout << "\n----------" << endl;

        for (int k = 0; k < numOfColumn - j; k++) {
            //cout << coinDataCopy[0][k] << " ";
            coinDataCopy[0][k] = 0;
        }
        //cout << " next ";
        for (int k = numOfColumn - 1 - j; k <= numOfColumn - 1; k++) {
            //cout << coinDataCopy[1][k] << " ";
            coinDataCopy[1][k] = 0;
        }
        //cout << endl;

        /*cout << " -- coinDataCopy --" << endl;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < coinDataCopy[j].size(); k++) {
                cout << coinDataCopy[j][k] << " ";
            }
            cout << endl;
        }
        cout << endl;*/

        for (int t = 0; t < numOfColumn; t++) {
            if (j == t) {
                continue;
            }
            int score = 0;

            for (int k = 0; k < numOfColumn - t; k++) {
                //cout << coinDataCopy[0][k] << " ";
                score += coinDataCopy[0][k];
            }
            //cout << " next ";
            for (int k = numOfColumn - 1 - t; k <= numOfColumn - 1; k++) {
                //cout << coinDataCopy[1][k] << " ";
                score += coinDataCopy[1][k];
            }
            //cout << endl;

            //cout << "score : " << score << endl;

            if (maxScore < score) {
                maxScore = score;
            }
        }

        scoreList.push_back(maxScore);
    }

    /*cout << "-- scoreList --" << endl;
    for (int j = 0; j < scoreList.size(); j++) {
        cout << scoreList[j] << " ";
    }
    cout << endl;*/

    int minScore = INT_MAX;
    int scoreListSize = scoreList.size();

    for (int j = 0; j < scoreListSize; j++) {
        if (minScore > scoreList[j]) {
            minScore = scoreList[j];
        }
    }

    return minScore;
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

        vector<vector<int>> coinData;

        getline(readFile, str);
        int numOfColumn = stoi(str);

        if (numOfColumn == 1) {
            cout << "bestScore : " << 0 << endl;
            writeFile << 0 << "\n";
            continue;
        }

        for (int j = 0; j < 2; j++) {
            vector<int> inputCoin;
            string fragment;

            getline(readFile, str);
            istringstream ss(str);

            while (getline(ss, fragment, ' ')) {
                inputCoin.push_back(stoi(fragment));
            }

            coinData.push_back(inputCoin);
        }

        int bestScore = getScore(coinData);
        
        cout << "bestScore : " << bestScore << endl;
        writeFile << bestScore << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;

}
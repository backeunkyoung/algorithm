#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
    int even_b, odd_b;
    int diff = 0;

    ifstream readFile;
    ofstream writeFile;

    readFile.open("jump.inp");
    writeFile.open("jump.out");

    string str;

    getline(readFile, str);
    int numOfCase = stoi(str);

    for (int j = 0; j < numOfCase; j++) {
        vector<int> height;
        int max = 0;

        getline(readFile, str);
        int n = stoi(str);
        
        getline(readFile, str);
        istringstream ss(str);
        string fragment;
        while (getline(ss, fragment, ' ')) {
            height.push_back(stoi(fragment));
        }

        sort(height.begin(), height.end());

        /*cout << " --- sort ----" << endl;
        for (int i = 0; i < height.size(); i++) {
            cout << height[i] << " ";
        }
        cout << endl;*/

        even_b = height[0];
        odd_b = height[0];


        for (int i = 1; i < n - 1; i++) {
            if (i % 2 == 1) {
                diff = height[i] - odd_b;
                odd_b = height[i];
                if (max < diff)
                    max = diff;
            }
            else {
                diff = height[i] - even_b;
                even_b = height[i];
                if (max < diff)
                    max = diff;
            }
        }

        if (max < height[n - 1] - odd_b)
            max = height[n - 1] - odd_b;

        if (max < height[n - 1] - even_b)
            max = height[n - 1] - even_b;

        //cout << max << endl;
        writeFile << max << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;
}
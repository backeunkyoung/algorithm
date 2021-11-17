#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;

int D[3][100001], arr[3][100001];

int DP(int L, int i) {
	if (D[L][i] != -1)
		return D[L][i];
	if (L == 0)
		return D[L][i] = max({ DP(0, i - 1), DP(1, i - 1), DP(2, i - 1) });
	if (L == 1)
		return D[L][i] = max(DP(0, i - 1) + arr[L][i], DP(2, i - 1) + arr[L][i]);
	if (L == 2)
		return D[L][i] = max(DP(0, i - 1) + arr[L][i], DP(1, i - 1) + arr[L][i]);
}

int main() {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("stickers.inp");
	writeFile.open("stickers.out");

	string str;

	getline(readFile, str);
	int numOfCase = stoi(str);
	//cout << numOfCase << endl;

	while (numOfCase--) {
		int result;

		memset(arr, 0, sizeof(arr));
		vector<vector<int>> data;

		getline(readFile, str);
		int num = stoi(str);
		//cout << num << endl;

		for (int i = 1; i <= 2; ++i) {

			getline(readFile, str);
			istringstream ss(str);

			string fragment;

			int count = 1;
			while (getline(ss, fragment, ' ')) {
				arr[i][count] = stoi(fragment);
				//cout << arr[i][count] << " ";
				count++;
			}
			//cout << endl;
		}

		memset(D, -1, sizeof(D));
		D[0][1] = 0;
		D[1][1] = arr[1][1];
		D[2][1] = arr[2][1];

		result = max({ DP(0, num), DP(1, num), DP(2, num) });

		//cout << max({ DP(0, num), DP(1, num), DP(2, num) }) << "\n";
		writeFile << result << "\n";
	}

	readFile.close();
	writeFile.close();

	return 0;
}
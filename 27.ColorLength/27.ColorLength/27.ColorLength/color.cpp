#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <string.h>
#include <stdlib.h>
using namespace std;

int len1, len2;
int dp[5002][5002], acc1[5002][26], acc2[5002][26];
char s1[5002], s2[5002];

inline int min(int a, int b) { return a < b ? a : b; }

void init() {
	s1[0] = s2[0] = '*';
	len1 = strlen(s1), len2 = strlen(s2);
	memset(dp, 0x3f, sizeof(dp));
	memset(acc1, 0, sizeof(acc1));
	memset(acc2, 0, sizeof(acc2));
}

int get_len(int x, int y, int c) {
	int ret1 = 0, ret2 = 0;
	if (acc1[x][c] + acc2[y][c] == acc1[len1 - 1][c] + acc2[len2 - 1][c]) ret1 = x + y;
	if (acc1[x][c] + acc2[y][c] == 1) ret2 = x + y;
	return ret1 - ret2;
}

void solve() {
	for (int i = 1; i < len1; i++) {
		for (int j = 0; j < 26; j++) acc1[i][j] = acc1[i - 1][j];
		acc1[i][s1[i] - 'A']++;
	}
	for (int i = 1; i < len2; i++) {
		for (int j = 0; j < 26; j++) acc2[i][j] = acc2[i - 1][j];
		acc2[i][s2[i] - 'A']++;
	}

	dp[0][0] = 0;
	for (int i = 0; i < len1; i++) {
		for (int j = 0; j < len2; j++) {
			int ret1 = get_len(i + 1, j, s1[i + 1] - 'A');
			int ret2 = get_len(i, j + 1, s2[j + 1] - 'A');
			dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + ret1);
			dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + ret2);
		}
	}
}

int main() {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("color.inp");
	writeFile.open("color.out");

	string str;

	getline(readFile, str);
	int numOfCase = stoi(str);

	for (int i = 0; i < numOfCase; i++) {
		getline(readFile, str);
		string str1 = str;

		string add = str1.substr(0, 1);
		str1 = add + str1;
		const char* c1 = str1.c_str();

		getline(readFile, str);
		string str2 = str;
		const char* c2 = str2.c_str();

		add = str2.substr(0, 1);
		str2 = add + str2;

		strcpy(s1, c1);
		strcpy(s2, c2);

		init();
		solve();

		int result = dp[len1 - 1][len2 - 1];

		//cout << result << endl;
		writeFile << result << "\n";
	}

	readFile.close();
	writeFile.close();

	return 0;
}
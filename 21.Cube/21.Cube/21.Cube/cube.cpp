#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <cstring>
using namespace std;
const int INF = 1987654321;

int T, W, L, H;
int dp[201][201][201];

int returnMin(int a, int b) {
	return (a < b) ? a : b;
}

int min(int a, int b, int c) {
	int result = (a < b) ? a : b;
	return (result < c) ? result : c;
}

int Cut(int w, int l, int h) {
	if (w == l && l == h) return 1;
	if (w == 1 || l == 1 || h == 1) return w * l * h;

	int& ret = dp[w][l][h];
	if (ret != -1) return ret;

	int cut_w = INF;
	int cut_l = INF;
	int cut_h = INF;

	for (int i = 1; i < w / 2 + 1; ++i)
		cut_w = returnMin(cut_w, Cut(i, l, h) + Cut(w - i, l, h));
	for (int i = 1; i < l / 2 + 1; ++i)
		cut_l = returnMin(cut_l, Cut(w, i, h) + Cut(w, l - i, h));
	for (int i = 1; i < h / 2 + 1; ++i)
		cut_h = returnMin(cut_h, Cut(w, l, i) + Cut(w, l, h - i));

	ret = min(cut_w, cut_l, cut_h);
	dp[w][l][h] = ret;
	dp[w][h][l] = ret;
	dp[l][w][h] = ret;
	dp[l][h][w] = ret;
	dp[h][w][l] = ret;
	dp[h][l][w] = ret;

	return ret;
}

int main() {
    ifstream readFile;
    ofstream writeFile;

    readFile.open("cube.inp");
    writeFile.open("cube.out");

    string str;

    getline(readFile, str);

    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        //cout << "\n====================" << endl;
		memset(dp, -1, sizeof(dp));

        vector<int> fileData;
        string fragment;

        getline(readFile, str);
        istringstream ss(str);

        while (getline(ss, fragment, ' ')) {
			fileData.push_back(stoi(fragment));
        }

		int W = fileData[0];
		int L = fileData[1];
		int H = fileData[2];

		//cout << "W : " << W << " " << " , L : " << L << " , H : " << H << endl;

		int result = Cut(W, L, H);

        //cout << "result : " << result << endl;

        writeFile << result << "\n";
    }

	/*memset(dp, -1, sizeof(dp));
	cin >> T;
	while (T--) {
		cin >> W >> L >> H;
		cout << Cut(W, L, H) << '\n';
	}*/

	readFile.close();
	writeFile.close();

	return 0;
}
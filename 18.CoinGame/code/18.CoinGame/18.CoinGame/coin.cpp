// CPP program for the variation
// in nim game
#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <limits.h>
using namespace std;

string misereNim(vector<int> s) {
	/*for (int i = 0; i < s.size(); i++) {
		cout << s[i] << " ";
	}
	cout << endl;*/

	int result = 0;
	int one = 1;
	int size = 0;

	for (int i = 0; i < s.size(); ++i) {
		int x = s[i] % 4;
		result ^= x;
		//one *= x;

		//result ^= s[i];
		if (x != 0) {
			one *= x;
			size++;
		}
	}

	if (one == 1) {
		if ((size % 2) == 1) {
			return "-1";
		}
		else {
			return "1";
		}
	}

	if (result == 0)
		return "-1";
	else
		return "1";
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

		vector<int> bottleCoin;
		string fragment;

		getline(readFile, str);
		istringstream ss(str);

		while (getline(ss, fragment, ' ')) {
			bottleCoin.push_back(stoi(fragment));
		}

		string result = "(" + to_string(bottleCoin[0]) + " " + to_string(bottleCoin[1]) + " " + to_string(bottleCoin[2]) + ") : ";

		result += misereNim(bottleCoin);

		cout << result;

		writeFile << result << "\n";
	}

	readFile.close();
	writeFile.close();

	return 0;
}

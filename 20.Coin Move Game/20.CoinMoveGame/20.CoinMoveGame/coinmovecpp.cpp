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

string cal(vector<int> s) {
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

int playGame(int P, int K, int S, int X) {
	int result = 0;

	for (int i = K; i >= 1; i--) {
		for (int j = K; j >= 1; j--) {
			int Alice = P - i;
			if (i == j || Alice == X) {
				continue;
			}
			cout << "( " << i << " , " << j << " )" << endl;
			cout << "next X : " << (S - (i + j)) << endl;
		}
		//playGame(P, K, S, X);
	}

	return result;
}

int main() {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("coinmove.inp");
	writeFile.open("coinmove.out");

	string str;

	getline(readFile, str);

	int numOfCase = stoi(str);

	for (int i = 0; i < numOfCase; i++) {
		cout << "\n====================" << endl;

		vector<int> fileData;
		string fragment;

		getline(readFile, str);
		istringstream ss(str);

		while (getline(ss, fragment, ' ')) {
			fileData.push_back(stoi(fragment));
		}
		
		int P = fileData[0];
		int K = fileData[1];
		int S = fileData[2];

		int X = P * (S / P);

		/*for (int j = 0; j < fileData.size(); j++) {
			cout << fileData[j] << " ";
		}
		cout << endl;*/

		int result = playGame(P, K, S, X);

		cout << "result : " << result;

		break;

		//writeFile << result << "\n";
	}

	readFile.close();
	writeFile.close();

	return 0;
}

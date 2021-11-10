#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> root;

bool compare(vector<int> a, vector<int> b) { // weight sort
	if (a[3] == b[3]) {
		return a[0] < b[0];
	}

	return a[3] < b[3];
}

vector<vector<int>> asSort(vector<vector<int>> fileData) {
	sort(fileData.begin(), fileData.end(), compare);
	return fileData;
}

vector<int> initialRoot(int nodeCount) {
	vector<int> root;

	for (int i = 0; i < nodeCount; i++) {
		root.push_back(i);
	}

	return root;
}

int findRoot(int x) {
	if (root[x] == x) {
		return x;
	}
	else {
		findRoot(root[x]);
	}
}

void marge(int p, int q) {
	int node1 = findRoot(p);
	int node2 = findRoot(q);
	if (node1 < node2) {
		root[node2] = node1;
	}
	else {
		root[node1] = node2;
	}
}

bool isCycle(int p, int q) {
	int node1 = findRoot(p);
	int node2 = findRoot(q);
	if (node1 == node2) {
		return true;
	}
	else {
		return false;
	}
}

int main() {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("kruskal.inp");
	writeFile.open("kruskal.out");

	string str;

	vector<int> lineData;
	string fragment;

	getline(readFile, str);
	istringstream ss(str);

	while (getline(ss, fragment, ' ')) {
		lineData.push_back(stoi(fragment));
	}

	int nodeCount = lineData[0];
	int numOfCase = lineData[1];

	root = initialRoot(nodeCount);
	
	/*cout << " -- root -- " << endl;
	for (int i = 0; i < nodeCount; i++) {
		cout << root[i] << " ";
	}
	cout << endl;*/

	int result = 0;
	vector<vector<int>> fileData;

	for (int i = 0; i < numOfCase; i++) {
		vector<int> split;

		split.push_back(i);

		string fragment;

		getline(readFile, str);
		istringstream ss(str);

		while (getline(ss, fragment, ' ')) {
			split.push_back(stoi(fragment));
		}

		fileData.push_back(split);
	}

	/*for (int i = 0; i < fileData.size(); i++) {
		for (int j = 0; j < fileData[i].size(); j++) {
			cout << fileData[i][j] << " ";
		}
		cout << endl;
	}*/

	fileData = asSort(fileData);

	/*for (int i = 0; i < fileData.size(); i++) {
		for (int j = 0; j < fileData[i].size(); j++) {
			cout << fileData[i][j] << " ";
		}
		cout << endl;
	}*/

	int mst = 0;
	vector<int> F;

	for (int i = 0; i < numOfCase; i++) {
		if (!isCycle(fileData[i][1], fileData[i][2])) {
			mst += fileData[i][3];
			F.push_back(fileData[i][0]);
			marge(fileData[i][1], fileData[i][2]);
		}
	}

	//cout << mst << endl;
	writeFile << mst << "\n";

	int FSize = F.size();

	for (int i = 0; i < FSize; i++) {
		//cout << F[i] << endl;
		writeFile << F[i] << endl;
	}

	readFile.close();
	writeFile.close();

	return 0;
}
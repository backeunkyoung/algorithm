#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> root;
vector<vector<int>> mstData;
vector<int> mst;

bool compare(vector<int> a, vector<int> b) { // weight sort
	if (a[2] == b[2]) {

	}

	return a[2] < b[2];
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

	int sum = 0;

	for (int i = 0; i < numOfCase; i++) {
		//싸이클이 존재하지 않으면 비용을 더합니다. 
		if (!isCycle(fileData[i][0], fileData[i][1])) {
			sum += fileData[i][2];
			marge(fileData[i][0], fileData[i][1]);
		}

		/*cout << "------" << endl;

		for (int j = 0; j < 3; j++) {
			cout << fileData[i][j] << " ";
		}
		cout << endl;*/

		/*int start = fileData[i][0];
		int end = fileData[i][1];

		int p = findRoot(start);
		int q = findRoot(end);

		if (p != q) {
			marge(p, q);
			mstData.push_back(fileData[i]);
		}*/
	}
	printf("%d\n", sum);
}
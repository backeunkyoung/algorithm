#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

void heap(int* data, int num) {
	for (int i = 1; i < num; i++) {

	}
}

void print_heap() {
	int heapSize = heap.size();
	for (int i = 0; i < heapSize; i++) {
		cout << heap[i] << " ";
	}
	cout << endl;

	//writeFile << result << "\n";
}

void insert_key(int key) {
	heap.push_back(key);
}

void delete_key() {

}

int main(void) {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("queue.inp");
	writeFile.open("queue.out");

	string str;

	while(true) {
		getline(readFile, str);

		//cout << str << endl;

		if (str == "q") {
			return 0;
		}
		else if (str == "d") {
			delete_key();
			return 0;
		}

		else if (str == "r") {
			print_heap();
		}
		else {
			int key = stoi(str);
			insert_key(key);
		}
	}

	readFile.close();
	writeFile.close();

	return 0;
}
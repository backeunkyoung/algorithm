#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

struct Heap {
	int index_id;
	int key;

	Heap(int index_id, int key) :
	index_id(index_id), key(key) {}

	int getParent() {
		return (index_id - 1) / 2;
	}
	int getLeft() {
		return 2 * index_id + 1;
	}
	int getRight() {
		return 2 * index_id + 2;
	}
};

vector<Heap> heap;

void insertion(int start_index) {

	int index_id = start_index;
	do {
		Heap * current = &heap[index_id];
		Heap * parent = &heap[current->getParent()];

		/*cout << "-- be --" << endl;
		cout << parent.index_id << " " << parent.key << endl;
		cout << current.index_id << " " << current.key << endl;
		cout << "----" << endl;*/

		if (current->key > parent->key) {
			int tmp = current->key;
			current->key = parent->key;
			parent->key = tmp;
		}
		else {
			break;
		}

		index_id = parent->index_id;

		/*cout << "-- af --" << endl;
		cout << parent->index_id << " " << parent.key << endl;
		cout << current.index_id << " " << current.key << endl;
		cout << "----" << endl;*/

	} while (index_id != 0);
}

void insert(int index_id, int key) {
	int heapSize = heap.size();

	Heap input =  Heap(index_id, key);
	
	heap.push_back(input);

	insertion(index_id);
	
}

string print_heap() {
	string str = "";

	int heapSize = heap.size();

	int index_id = 0; // start Point

	while (true) {
		Heap current = heap[index_id];

		//cout << current.key << " ";
		
		str += to_string(current.key) + " ";

		index_id = current.getLeft();

		if (index_id > heapSize - 1) {
			break;
		}
	}
	//cout << endl;

	/*for (int i = 0; i < heapSize; i++) {
		cout << heap[i].index_id << " " << heap[i].key << " " << heap[i].getParent() << " " << heap[i].getLeft() << " " << heap[i].getRight() << endl;
	}
	cout << endl;*/

	return str;
}

int* get_max(int root_index) {
	int max_index = 0;
	int max_key = 0;
	int* arr = new int[2];
	arr[0] = -1;
	arr[1] = -1;
	
	if (root_index > heap.size() - 1) {
		return arr;
	}
	Heap root = heap[root_index];
	
	int* left = get_max(root.getLeft());
	int* right = get_max(root.getRight());

	int left_index = left[0];
	int right_index = right[0];

	int cur_key = root.key;
	int left_key = (left_index == -1) ? -1: left[1];
	int right_key = (right_index == -1) ? -1 : right[1];

	
	if (cur_key < left_key) {
		max_key = left_key;
		max_index = left_index;
	}
	else {
		max_key = cur_key;
		max_index = root_index;
	}

	if (max_key < right_key) {
		max_key = right_key;
		max_index = right_index;
	}

	arr[0] = max_index;
	arr[1] = max_key;

	return arr;
}

void adjust(int heapSize) {

	int start_index = 0;
	for (int i = 0; i < heap.size() - 1; i++) {
		int* max_info = get_max(i);
		int max_index = max_info[0];
		int max_key = max_info[1];

		Heap* root = &heap[i];
		Heap* swap = &heap[max_index];

		swap->key = root->key;
		root->key = max_key;

		//cout << "max_index : " << max_index << ", max_key : " << max_key << endl;
	}

}

void del() {
	Heap * root = &heap[0];
	Heap * last = &heap[heap.size() - 1];

	root->key = last->key;
	heap.erase(heap.end()-1);

	adjust(heap.size());
}

int main(void) {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("queue.inp");
	writeFile.open("queue.out");

	string str;

	while(true) {
		getline(readFile, str);

		if (str == "q") {
			break;
		}
		else if (str == "d") {
			del();
		}

		else if (str == "r") {
			string str = print_heap();
			writeFile << str << endl;
		}
		else {
			int key = stoi(str);
			insert(heap.size(), key);
		}

	}

	readFile.close();
	writeFile.close();

	return 0;
}
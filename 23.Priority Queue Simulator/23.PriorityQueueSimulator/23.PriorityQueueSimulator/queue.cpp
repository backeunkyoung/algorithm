#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

struct Heap {
	int index_id;
	int key;

	// 생성자
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

void print_heap() {
	int heapSize = heap.size();

	int index_id = 0; // start Point

	/*while (true) {
		Heap current = heap[index_id];

		cout << current.key << endl;
		index_id = current.getLeft();

		if (index_id >= heapSize - 1) {
			break;
		}
	}*/

	for (int i = 0; i < heapSize; i++) {
		cout << heap[i].index_id << " " << heap[i].key << " " << heap[i].getParent() << " " << heap[i].getLeft() << " " << heap[i].getRight() << endl;
	}
	cout << endl;
}

void adjust(int heapSize) {
	int index_id = heapSize-1;

	while (true) {
		cout << " -------- start -----------" << endl;
		cout << "index_id : " << index_id << endl << endl;

		print_heap();

		if (index_id <= 0) {
			break;
		}

		Heap* current = &heap[index_id];
		Heap* parent = &heap[current->getParent()];

		int max_index = -1;

		int left_index = parent->getLeft();
		int right_index;
		if (2 * index_id + 2 > heapSize-1) {
			right_index = -1;
		}
		else {
			right_index = parent->getRight();
		}		

		/*cout << "parent_index : " << parent->index_id << " , key : " << parent->key << endl;
		cout << "left_index : " << left_index << ", left_key : " << heap[left_index].key << endl;
		cout << "right_index : " << right_index << ", right_index : " << heap[right_index].key << endl << endl;*/
		
		if (right_index == -1) {	// right_index is no exist
			if (heap[left_index].key > parent->key) {
				max_index = left_index;
			}
			else {
				max_index = parent->index_id;
			}
		}
		else {
			if (heap[left_index].key > heap[right_index].key) {
				max_index = left_index;
			}
			else {
				max_index = right_index;
			}

			if (parent->key > heap[max_index].key) {
				max_index = parent->index_id;
			}
		}

		int tmp = parent->key;
		parent->key = heap[max_index].key;
		heap[max_index].key = tmp;

		cout << "--  1차 정렬 완료  --" << endl;
		print_heap();

		index_id = parent->index_id;
	}
}

void del() {
	Heap * root = &heap[0];
	Heap * last = &heap[heap.size() - 1];

	root->key = last->key;
	heap.erase(heap.end()-1);

	adjust(heap.size());

	//cout << " -- del -- " << endl;
	//print_heap();

	// 3. adjust => n/2-1 번째 키

	//adjust(heap.size());
}

int main(void) {
	ifstream readFile;
	ofstream writeFile;

	readFile.open("queue.inp");
	writeFile.open("queue.out");

	//heap = new vector<Heap>;

	string str;

	int index = 0;
	while(true) {
		getline(readFile, str);

		//cout << str << endl;

		if (str == "q") {
			return 0;
		}
		else if (str == "d") {
			cout << " -- D -- " << endl;
			del();
		}

		else if (str == "r") {
			print_heap();
		}
		else {
			int key = stoi(str);
			insert(index++, key);
		}

	}

	readFile.close();
	writeFile.close();

	return 0;
}
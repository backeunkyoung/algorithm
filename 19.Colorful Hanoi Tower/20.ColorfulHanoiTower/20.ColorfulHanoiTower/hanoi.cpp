//#pragma warning(disable: 4996)
//#include <iostream>
//#include <fstream>
//#include <string>
//#include <sstream>
//#include <vector>
//using namespace std;
//
//int main() {
//    ifstream readFile;
//    ofstream writeFile;
//
//    readFile.open("hanoi.inp");
//    writeFile.open("hanoi.out");
//
//    string str;
//
//    getline(readFile, str);
//
//    int numOfCase = stoi(str);
//    cout << "numOfCase : " << numOfCase << endl;
//
//    for (int i = 0; i < numOfCase; i++) {
//        getline(readFile, str);
//        int floor = stoi(str);
//
//        vector<string> color;   // ������ ������ �迭
//        vector<string> count;   // �� ���� ��ũ�� �� �� �ִ��� ������ �迭
//        vector<int> move;       // �� ���� �̵� Ƚ��
//
//        for (int j = 0; j < floor; j++) {
//            vector<string> fileData;
//            string fragment;
//
//            getline(readFile, str);
//            istringstream ss(str);
//
//            while (getline(ss, fragment, ' ')) {
//                fileData.push_back(fragment);
//            }
//
//            color.push_back(fileData[0]);
//            count.push_back(fileData[1]);
//        }
//
//        cout << "-- color --" << endl;
//        for (int j = 0; j < color.size(); j++) {
//            cout << color[j] << " ";
//        }
//        cout << endl;
//
//        cout << "-- count --" << endl;
//        for (int j = 0; j < count.size(); j++) {
//            cout << count[j] << " ";
//        }
//        cout << endl;
//
//        int ans = 0;
//
//        for (int j = floor - 1; j < -1; j--) {
//            if (color[j] == "G") {
//                move.push_back(1);
//                for (int t = j-1; t < -1; t--) {
//                    move.push_back()
//            }
//
//
//
//            move[i] += 1
//                for j in range(i - 1, -1, -1) :
//                    move[j] += 2 * *(i - j - 1)
//                    elif color[i] == 'B' : # ���� move�� ¦���� ��� �� �� �̵��ؾ���.Ȧ���� �� �� �̵��ؾ���
//                    if move[i] % 2 == 0 and val[i] != 1: # ���ܻ��� 1�� ó��
//                        if i == 0: ans -= 1 # ���ܻ��� 2�� ó��
//                            move[i] += 2
//                            for j in range(i - 1, -1, -1) :
//                                move[j] += 2 * *(i - j)
//                        else:
//        
//        }
//                for i in range(m - 1, -1, -1) :
//                    if color[i] == 'G' :
//                        move[i] += 1
//                        for j in range(i - 1, -1, -1) :
//                            move[j] += 2 * *(i - j - 1)
//                            elif color[i] == 'B' : # ���� move�� ¦���� ��� �� �� �̵��ؾ���.Ȧ���� �� �� �̵��ؾ���
//                            if move[i] % 2 == 0 and val[i] != 1: # ���ܻ��� 1�� ó��
//                                if i == 0: ans -= 1 # ���ܻ��� 2�� ó��
//                                    move[i] += 2
//                                    for j in range(i - 1, -1, -1) :
//                                        move[j] += 2 * *(i - j)
//                                else:
//        }
//            
//        move[i] += 1
//            for j in range(i - 1, -1, -1) :
//                move[j] += 2 * *(i - j - 1)
//                        else: # ���� move�� ¦���� ��� �� �� �̵��ؾ���.Ȧ���� �� �� �̵��ؾ���
//                            if move[i] % 2 == 0 or val[i] == 1: # ���ܻ��� 1�� ó��
//                                move[i] += 1
//                                for j in range(i - 1, -1, -1) :
//                                    move[j] += 2 * *(i - j - 1)
//                            else:
//        if i == 0 : ans -= 1 # ���ܻ��� 2�� ó��
//            move[i] += 2
//            for j in range(i - 1, -1, -1) :
//                move[j] += 2 * *(i - j)
//
//                for i in range(m) :
//                    ans += val[i] * move[i]
//
//                    print(ans)
//        
//    }
//
//    //writeFile << result << "\n";
//
//    readFile.close();
//    writeFile.close();
//
//    return 0;
//}
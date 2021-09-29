#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;


void calc(int row, int column, string str) {    // x, y
    const int left = (column / 2) + (column % 2);
    const int right = column - left;
    const int top = (row / 2) + (row % 2);
    const int bottom = row - top;

    printf("left : %d, right : %d, top : %d, bottom : %d\n", left, right, top, bottom);

    // LTx : 0 ~ left, LTy : 0 ~ top
    // RTx : left+1 ~ column.size, LTy : 0 ~ top
    // LBx : 0 ~ left, LTy : top ~ row.size
    // RBx : left+1 ~ column, LTy : top ~ row.size

    vector<int> LTx; vector<int> LTy;
    vector<int> RTx; vector<int> RTy;
    vector<int> LBx; vector<int> LBy;
    vector<int> RBx; vector<int> RBy;

    vector<string> x;
    istringstream ss(str);
    string stringBuffer;

    while (getline(ss, stringBuffer, ' ')) {
        x.push_back(stringBuffer);
        /*x.push_back(stoi(stringBuffer));*/
    }

    printf("xSize : %d\n", x.size());
    //cout << "xSize : " + x.size() << endl;
    cout << "x : ";
    for (int i = 0; i < x.size(); i++) {
        cout << x[i] + " ";
    }
    cout << endl;


    /*for (int i = 0; i < left; i++) {
        vector<int>::iterator it = LTx.begin();
        LTx.insert(it, )
    }*/
}

string s = "";

string imageDivide(int startX, int startY, int height, int width) { // row(5) => y.len, column(4) => x.len
    // divide 4 Section 
    // RT, LT, RB, LB
    // imageDivide(RT)
    // imageDivide(LT)
 
    s.push_back('D');

    cout << " now s : " << s.c_str() << endl;

    cout << "-------------" << endl;

    cout << height << " x " << width << endl;

    cout << "startX : " << startX << endl;
    cout << "startY : " << startY << endl;
    cout << "width : " << width << endl;
    cout << "height : " << height << endl;

    if (width == 1 && height == 1) {
        //cout << "break" << endl;
        return s;
    }


    /*int divideY = column / 2 + column % 2;
    int divideX = row / 2 + row % 2;*/

    int divideX = width / 2 + width % 2;
    int divideY = height / 2 + height % 2;

    cout << "divdeX : " << divideX << endl;
    cout << "divdeY : " << divideY << endl;

    int temp;
    //cin >> temp;

    /*if (divideX == 1 && divideY == 1) {
        return "";
    }*/

    string lt, rt, lb, rb = "0";
    int allCnt = 1;
    lt = imageDivide(startX, startY, divideY, divideX ); // LT

    if (height - divideX != 0) {
        rt =imageDivide(startX + divideX, startY, divideY, height - divideX); // RT
        allCnt++;
    }
    if (width - divideY != 0) {
        lb = imageDivide(startX, startY + divideY, width - divideY, divideX); // LB
        allCnt++;
    }
    if (width - divideY != 0 && height - divideX != 0) {
        rb = imageDivide(startX + divideX, startY + divideY, width - divideY, height - divideX); // RB
        allCnt++;
    }

    /*if (lt + rt + lb + rb == 0) {
        return "0"
    }
    if (lt + rt + lb + rb == allCnt) {
        return "1";
    }
    return "D" + lt + rt + lb + rb;*/

    //imageDivide(startX, startX + divideX - 1, startY, startY + divideY - 1); // LT
    //imageDivide(startX + divideX, column, startY, startY + divideY - 1); // RT
    //imageDivide(startX, startX + divideX - 1, startY + divideY, row); // LB
    //imageDivide(startX + divideX, column, startY + divideY, row); // RB
    return "";

    /*
    1 0  1 

    1 1  1 


    1 1  1 

    */

    //imageDivide(0, 2, 0, 2); // LT
    //imageDivide(2, 3, 0, 2); // RT
    //imageDivide(0, 2, 2, 3); // LB
    //imageDivide(2, 3, 2, 3); // RB
}

string BtoD(int row, int column, string str) {
    string result;

    printf("this type B, row : %d, columns : %d, str : %s\n", row, column, str.c_str());
    int array[3][4] = { { 0,0,1,0 }, { 0, 0, 0, 1 }, { 1, 0, 1, 1 } };


    result = imageDivide(0, 0, 3, 4);    // row, column

    return result;
}

string DtoB(int row, int column, string str) {
    string result;

    printf("this type D, row : %d, columns : %d, str : %s\n", row, column, str.c_str());

    return result;
}

int main() {
    string result;
    result = BtoD(3, 4, "001000011011");
    cout << " ---- result ---- " << endl;
    cout << "result : " << result.c_str() << endl;

    return 0;

    //ifstream readFile;
    //ofstream writeFile;

    //readFile.open("bitmap.inp");
    //writeFile.open("bitmap.out");

    //string result = "";

    //if (readFile.is_open()) {
    //    while (true) {
    //        printf("\n-------------\n");

    //        string str;
    //        vector<string> x;

    //        getline(readFile, str);

    //        if (str.compare("#") == 0) {    // true : 0
    //            break;
    //        }

    //        istringstream ss(str);
    //        string stringBuffer;

    //        while (getline(ss, stringBuffer, ' ')) {
    //            x.push_back(stringBuffer);
    //        }

    //        string type = x[0];
    //        int row = stoi(x[1]);   // string to int
    //        int column = stoi(x[2]);
    //        int size = row * column;
    //        int strSize = 0;

    //        getline(readFile, str);
    //        /*
    //         int** bitmap = int[row][column];
    //         
    //         do {
    //             getline(readFile, str);
    //            
    //             strSize += str.length();
    //         } while (strSize >= size);
    //         */
    //        //cout << str << endl;

    //        printf("type : %s, row : %d , column : %d\n" , type.c_str(), row, column);

    //        if (type.compare("B") == 0) {
    //            result = BtoD(row, column, str);
    //        }
    //        else if (type.compare("D") == 0) {
    //            result = DtoB(row, column, str);
    //        }

    //        /*cout << "x : ";
    //        for (int i = 0; i < x.size(); i++) {
    //            cout << x[i] + " ";
    //        }
    //        cout << endl;*/


    //    }
    //}


    //writeFile.write(result.c_str(), result.size());

    //readFile.close();

    return 0;
}
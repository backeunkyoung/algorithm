#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

vector<vector<int>> arr;

string imageDivide(int startX, int startY, int height, int width) {   // row : height, column : width
    //cout << "----------" << endl;

    //cout << height << " x " << width << endl;
    //for (int i = startY; i < startY + height; i++) {  // print
    //    cout << "{ ";
    //    for (int j = startX; j < startX + width; j++) {
    //        cout << arr[i][j] << " ";
    //    }
    //    cout << " }" << endl;
    //}

    if (height == 1 && width == 1) {    // 1 x 1 이면 해당 값 반환
        return to_string(arr[startY][startX]);
    }

    int nextHeight = height / 2 + height % 2;
    int nextWidth = width / 2 + width % 2;

    string LT = "", RT = "", LB = "", RB = "";
    string allOne = "1";
    string allZero = "0";

    LT = imageDivide(startX, startY, nextHeight, nextWidth);  // row : height, column : width

    if (width - nextWidth != 0) {
        RT = imageDivide(startX + nextWidth, startY, nextHeight, width - nextWidth);
        allOne += "1";
        allZero += "0";
    }

    if (height - nextHeight != 0) {
        LB = imageDivide(startX, startY + nextHeight, height - nextHeight, nextWidth);
        allOne += "1";
        allZero += "0";
    }

    if (height - nextHeight != 0 && width - nextWidth != 0) {
        RB = imageDivide(startX + nextWidth, startY + nextHeight, height - nextHeight, width - nextWidth);
        allOne += "1";
        allZero += "0";
    }

    string allStr = LT + RT + LB + RB;

    if (allZero == allStr) {
        //cout << "0";
        return "0";
    }
    else if (allOne == allStr) {
        //cout << "1";
        return "1";
    }
    else {
        //cout << "D" << allStr;
        return "D" + allStr;
    }

    return allStr;

}

string BtoD(int row, int column) {
    string result = "";

    result = imageDivide(0, 0, row, column);

    return result;
}

string imageInput(int startX, int startY, int height, int width, string str, int position) {

    cout << "startX : " << startX << endl;

    for (int i = startY; i < startY + height; i++) {
        cout << "{ ";
        for (int j = startX; j < startX + width; j++) {
            cout << arr[i][j] << " ";
        }
        cout << " }" << endl;
    }

    if (width == 1 && height == 1) {
        //printf(" -%d-\n", arrayList[startY][startX]);
        //return to_string(arrayList[startY][startX]);
        arr[startY][startX] = str.at(position) - '0';

        cout << "str : " << str << endl;

        return "";
    }

    string LT = "0", RT = "0", LB = "0", RB = "0";
    string fillChar = "X";

    //if (str.at(0) == 'D') {
    //    str = str.substr(1);
    //}
    //else {
    //    fillChar = to_string(str.at(0));
    //    // 0... => 000 + 0...
    //}


    int nextHeight = height / 2 + height % 2;
    int nextWidth = width / 2 + width % 2;

    /*cout << "divdeX : " << divideX << endl;
    cout << "divdeY : " << divideY << endl*/


    // 1 1 1 1 1 1111 1111 1111 1111
    imageInput(startX, startY, nextHeight, nextWidth, str, 0);

    /*if (width - nextWidth != 0 && fillChar != "X") {
        str = fillChar + str;
    }
    if (height - nextHeight != 0 && fillChar != "X") {
        str = fillChar + str;
    }
    if (height - nextHeight != 0 && width - nextWidth != 0 && fillChar != "X") {
        str = fillChar + str;
    }*/

    if (width - nextWidth != 0) {
        imageInput(startX + nextWidth, startY, nextHeight, width - nextWidth, str, 1);
    }

    if (height - nextHeight != 0) {
        imageInput(startX, startY + nextHeight, height - nextHeight, nextWidth, str, 2);
    } 

    if (height - nextHeight != 0 && width - nextWidth != 0) {
        imageInput(startX + nextWidth, startY + nextHeight, height - nextHeight, width - nextWidth, str, 3);
    }

    return "";
}


string DtoB(int row, int column, string str) {
    string result = "";

    imageInput(0, 0, row, column, str, 0);

    /*for (int i = 0; i < row; i++) {
        cout << "{ ";
        for (int j = 0; j < column; j++) {
            cout << arr[i][j] << " ";
        }
        cout << " }" << endl;
    }*/

    return result;
}

int main() {
    string result;

    ifstream readFile;
    ofstream writeFile;

    readFile.open("bitmap.inp");
    writeFile.open("bitmap.out");

    if (readFile.is_open()) {
        while (true) {
            cout << "----------" << endl;
            arr.clear();

            // -- file read --
            string str;
            vector<string> s;

            getline(readFile, str);

            if (str.compare("#") == 0) {    // true : 0
                break;
            }

            istringstream ss(str);
            string stringBuffer;

            while (getline(ss, stringBuffer, ' ')) {
                s.push_back(stringBuffer);
            }

            string type = s[0];
            int row = stoi(s[1]);   // string to int
            int column = stoi(s[2]);

            getline(readFile, str);

            int count = 0;
            for (int i = 0; i < row; i++) { // 3
                vector<int> input;
                for (int j = 0; j < column; j++) {  // 4
                    int num;
                    if (type.compare("B") == 0) {
                        num = str[count] - '0';
                    }
                    else {
                        num = 0;
                    }

                    input.push_back(num);
                    count++;
                }

                arr.push_back(input);
            }

            /*for (int i = 0; i < row; i++) {
                cout << "{ ";
                for (int j = 0; j < column; j++) {
                    cout << arr[i][j] << " ";
                }
                cout << " }" << endl;
            }*/

            if (type.compare("B") == 0) {
                //result = BtoD(row, column);
            }
            else if (type.compare("D") == 0) {
                result = DtoB(row, column, str); // D010101010
            }

            cout << "result : " << result.c_str() << endl;
        }
    }

    //writeFile.write(result.c_str(), result.size());

    readFile.close();
    writeFile.close();

    return 0;
}
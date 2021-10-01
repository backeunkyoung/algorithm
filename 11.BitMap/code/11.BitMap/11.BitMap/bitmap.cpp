#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

int arrayList[3][4] = { { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 1, 0, 1, 1 } };
string s = "";  // return result
vector<vector<int>> arr;
string getStr = ""; // divide list

string imageDivide(int startX, int startY, int height, int width) { // row(5) => y.len, column(4) => x.len
    cout << "-------------" << endl;

    // divide 4 Section 
    // RT, LT, RB, LB
    // imageDivide(RT)
    // imageDivide(LT)
 
    /*
    s.push_back('D');
    cout << "-------------" << endl;
    cout << " now s : " << s.c_str() << endl;

    cout << height << " x " << width << endl;
    */

   /* for (int i = startY; i < startY + height; i++) {
        cout << "{ ";
        for (int j = startX; j < startX + width; j++) {
            cout << arr[i][j] << " ";
        }
        cout << " }" << endl;
    }*/
    
   /* cout << "startX : " << startX << endl;
    cout << "startY : " << startY << endl;
    cout << "width : " << width << endl;
    cout << "height : " << height << endl;*/
    
    if (width == 1 && height == 1) {
       
        //printf(" -%d-\n", arrayList[startY][startX]);
        //return to_string(arrayList[startY][startX]);
        return to_string(arr[startY][startX]);
    }

    int divideX = width / 2 + width % 2;
    int divideY = height / 2 + height % 2;

    /*cout << "divdeX : " << divideX << endl;
    cout << "divdeY : " << divideY << endl*/

    string lt, rt, lb, rb = "";
    string allOne = "1";
    string allZero = "0";

    lt = imageDivide(startX, startY, divideY, divideX);
    if (width - divideX != 0) {
        rt = imageDivide(startX + divideX, startY, divideY, width - divideX);
        allOne += "1";
        allZero += "0";
        // int startX, int startY, int height, int width)
    }    
    
    if (height - divideY != 0) {
        lb = imageDivide(startX, startY + divideY, height - divideY, divideX);
        allOne += "1";
        allZero += "0";
    }
    
    if (height - divideY != 0 && width - divideX != 0) {
        rb = imageDivide(startX + divideX, startY + divideY, height - divideY, width - divideX);
        allOne += "1";
        allZero += "0";
    }
    
    string allStr = lt + rt + lb + rb;
    //cout << allStr << endl;
    /*
    cout << "divdeX : " << divideX << endl;
    cout << "divdeY : " << divideY << endl;
    */

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

void arrayInput(int startX, int startY, int height, int width, string str, int position) {
    // divide 4 Section 
    // RT, LT, RB, LB
    // imageDivide(RT)
    // imageDivide(LT)

    /*
    s.push_back('D');
    cout << "-------------" << endl;
    cout << " now s : " << s.c_str() << endl;

    cout << height << " x " << width << endl;
    */

    /* for (int i = startY; i < startY + height; i++) {
         cout << "{ ";
         for (int j = startX; j < startX + width; j++) {
             cout << arr[i][j] << " ";
         }
         cout << " }" << endl;
     }*/

     /* cout << "startX : " << startX << endl;
      cout << "startY : " << startY << endl;
      cout << "width : " << width << endl;
      cout << "height : " << height << endl;*/

    if (width == 1 && height == 1) {
        //printf(" -%d-\n", arrayList[startY][startX]);
        //return to_string(arrayList[startY][startX]);
        arr[startY][startX] = str.at(position) - '0';   
        
        cout << "str : " << str << endl;

        return;
    }


    
    /*
    
    str = "D 0 D 1 0 0 1 D 1 0 1 "

    D => 0 D 1 0 0 1 D 1 0 1

    RT =>  0  D ~~~~~
    RT => 0 00 000 0000

    1 0
    0 1
    */

    string lt, rt, lb, rb = "0";
    string fillChar = "X";
    
    if (str.at(0) == 'D') {
        str = str.substr(1);
            
    }
    else {
        fillChar = to_string(str.at(0));
        // 0... => 000 + 0...
    }
        

    int divideX = width / 2 + width % 2;
    int divideY = height / 2 + height % 2;

    /*cout << "divdeX : " << divideX << endl;
    cout << "divdeY : " << divideY << endl*/

    
    // 1 1 1 1 1 1111 1111 1111 1111
    arrayInput(startX, startY, divideY, divideX, str, 0);
    
    if (width - divideX != 0 && fillChar != "X") {
        str = fillChar + str;
    }
    if (height - divideY != 0 && fillChar != "X") {
        str = fillChar + str;
    }
    if (height - divideY != 0 && width - divideX != 0 && fillChar != "X") {
        str = fillChar + str;
    }

    if (width - divideX != 0) {
        arrayInput(startX + divideX, startY, divideY, width - divideX, str, 1);
    }

    if (height - divideY != 0) {
        arrayInput(startX, startY + divideY, height - divideY, divideX, str, 2);
    }

    if (height - divideY != 0 && width - divideX != 0) {
        arrayInput(startX + divideX, startY + divideY, height - divideY, width - divideX, str, 3);
    }
    

    //cout << allStr << endl;
    /*
    cout << "divdeX : " << divideX << endl;
    cout << "divdeY : " << divideY << endl;
    */

}

string BtoD(int row, int column) {
    string result;

    printf("this type B, row : %d, columns : %d\n", row, column);

    /*for (int i = 0; i < row; i++) {
        cout << "{ ";
        for (int j = 0; j < column; j++) {
            cout << arr[i][j] << " ";
        }
        cout << " }" << endl;
    }*/

    //imageDivide(0, 0, row, column);

    return imageDivide(0, 0, row, column);    // row, column
 }

string DtoB(int row, int column, string str) {
    string result;

   /* for (int i = 0; i < row; i++) {
        cout << "{ ";
        for (int j = 0; j < column; j++) {
            cout << arr[i][j] << " ";
        }
        cout << " }" << endl;
    }*/

    printf("this type D, row : %d, columns : %d, str : %s\n", row, column, str.c_str());



    arrayInput(0, 0, row, column, str, 0);

    for (int i = 0; i < row; i++) {
        cout << "{ ";
        for (int j = 0; j < column; j++) {
            cout << arr[i][j] << " ";
        }
        cout << " }" << endl;
    }

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


            string str;
            vector<string> x;

            getline(readFile, str);

            if (str.compare("#") == 0) {    // true : 0
                break;
            }

            istringstream ss(str);
            string stringBuffer;

            while (getline(ss, stringBuffer, ' ')) {
                x.push_back(stringBuffer);
            }

            string type = x[0];
            int row = stoi(x[1]);   // string to int
            int column = stoi(x[2]);

            getline(readFile, str);
            //cout << "first str : " << str << endl;

            //while(true) {
            //    string add;
            //    getline(readFile, add);
            //    cout << "next line : " << add << endl;

            //    //cout << "add(1) : " << add.at(1) << "//" << endl;
            //    if (add.at(1) == ' ') {
            //        break;
            //    }
            //    str += add;

            //    //break;
            //}
            //cout << "str : " << str << endl;


            int count = 0;
            for (int i = 0; i < row; i++) {
                vector<int> input;
                for (int j = 0; j < column; j++) {
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

    return 0;
}
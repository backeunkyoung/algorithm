#pragma warning(disable: 4996)
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <climits>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

struct point {
    int x, y, p, q;
    point() {}
    point(int x, int y, int p, int q) : x(x), y(y), p(p), q(q) {}
};
int T;
bool cmpX(const point& e1, const point& e2) {
    if (e1.x != e2.x) return e1.x < e2.x;
    return e1.y < e2.y;
}
bool cmpY(const point& e1, const point& e2) {
    if (e1.y != e2.y) return e1.y < e2.y;
    return e1.x < e2.x;
}
int main() {
    ifstream readFile;
    ofstream writeFile;

    readFile.open("block.inp");
    writeFile.open("block.out");

    string str;

    getline(readFile, str);
    int numOfCase = stoi(str);

    for (int i = 0; i < numOfCase; i++) {
        getline(readFile, str);
        int n = stoi(str);

        vector<point> v;

        for (int j = 0; j < n; j++) {
            getline(readFile, str);
            istringstream ss(str);

            vector<int> lineData;
            string fragment;

            int input[] = { 0, 0, 0, 0 };
            point t;

            int count = 0;
            while (getline(ss, fragment, ' ')) {
                if (!fragment.empty()) {
                    //cout << "fragment : " << fragment << endl;
                    input[count] = stoi(fragment);
                    count++;
                }    
            }
            t.x = input[0];
            t.y = input[1];
            t.p = input[2];
            t.q = input[3];

            //cout << "x : " << t.x << ", y : " << t.y << ", p : " << t.p << ", q : " << t.q;

            v.push_back(t);
        }

        bool ok = true;
        point ans(INT_MAX, INT_MAX, INT_MIN, INT_MIN);
        while (ok) {
            ok = false;
            sort(v.begin(), v.end(), cmpY);
            for (int i = 0; i < n; i++) {
                if (!v[i].y) continue;
                int now = 0;
                for (int j = 0; j < i; j++)
                    if (((v[j].x >= v[i].x && v[j].x < v[i].p) || (v[j].p > v[i].x && v[j].p <= v[i].p)) || (v[j].x <= v[i].x && v[j].p >= v[i].p))
                        now = max(now, v[j].q);
                if (v[i].y == now) continue;
                int diff = v[i].q - v[i].y;
                v[i].y = now;
                v[i].q = now + diff;
                ok = true;
            }
            sort(v.begin(), v.end(), cmpX);
            for (int i = 0; i < n; i++) {
                if (!v[i].x) continue;
                int now = 0;
                for (int j = 0; j < i; j++)
                    if (((v[j].y >= v[i].y && v[j].y < v[i].q) || (v[j].q > v[i].y && v[j].q <= v[i].q)) || (v[j].y <= v[i].y && v[j].q >= v[i].q))
                        now = max(now, v[j].p);
                if (v[i].x == now) continue;
                int diff = v[i].p - v[i].x;
                v[i].x = now;
                v[i].p = now + diff;
                ok = true;
            }
        }
        for (int i = 0; i < n; i++) {
            ans.q = max(ans.q, v[i].q);
            ans.p = max(ans.p, v[i].p);
            ans.x = min(ans.x, v[i].x);
            ans.y = min(ans.y, v[i].y);
        }

        int result1 = ans.p - ans.x;
        int result2 = ans.q - ans.y;

        //cout << result1 << " " << result2 << endl;

        writeFile << result1 << " " << result2 << "\n";
    }

    readFile.close();
    writeFile.close();

    return 0;
}
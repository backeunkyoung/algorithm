#pragma warning(disable: 4996)
#include <stdio.h>

inline int max(int A, int B) { return A > B ? A : B; }
inline int min(int A, int B) { return A < B ? A : B; }

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int N, M, H, ans, z;
int hole[1003][1003][4], tank[1003][1003];

struct Water {
    int height, x, y;
};

bool comp(Water A, Water B) { return A.height < B.height; }
void swap(Water& A, Water& B) { Water t = A; A = B; B = t; }

struct PriorityQueue {
    Water heap[1000 * 1000];
    int heapSize;
    bool(*cmp)(Water, Water);

    void init() {
        heapSize = 0;
        cmp = comp;
    }

    Water top() { return heap[1]; }
    int size() { return heapSize; }

    void push(Water data) {
        heap[++heapSize] = data;
        int c = heapSize;

        for (; c > 1 && cmp(heap[c], heap[c / 2]); c /= 2) {
            swap(heap[c], heap[c / 2]);
        }
    }

    void pop() {
        int p = 1, c = 2;
        swap(heap[1], heap[heapSize--]);

        for (; c <= heapSize; p = c, c *= 2) {

            if (c < heapSize && cmp(heap[c + 1], heap[c])) {
                c++;
            }

            if (!cmp(heap[c], heap[p])) {
                break;
            }

            swap(heap[c], heap[p]);
        }
    }
}pq;


void tryPush(int x, int y, int h) {

    if (tank[x][y] <= h) {
        return;
    }

    tank[x][y] = h;
    pq.push({ h, x, y });
}

void input(FILE* inp) {

    if (fscanf(inp, "%d %d %d", &N, &M, &H) == 1) {}

    for (int i = 1; i <= N + 1; ++i) {
        for (int j = 1; j <= M; ++j) {
            if (fscanf(inp, "%d", &z) == 1) {}
            hole[i - 1][j][2] = z;
            hole[i][j][0] = z;
        }
    }

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M + 1; ++j) {
            if (fscanf(inp, "%d", &z) == 1) {}
            hole[i][j - 1][1] = z;
            hole[i][j][3] = z;
        }
    }

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            tank[i][j] = H;
        }
    }
}

void BFS() {
    register int i, j, nextX, nextY, nextHeight;
    Water cur;

    pq.init();

    for (i = 0; i <= M + 1; ++i) {
        if (hole[0][i][2] != -1) {
            tryPush(1, i, hole[0][i][2]);
        }
        if (hole[N + 1][i][0] != -1) {
            tryPush(N, i, hole[N + 1][i][0]);
        }
    }

    for (i = 0; i <= N + 1; ++i) {
        if (hole[i][0][1] != -1) {
            tryPush(i, 1, hole[i][0][1]);
        }
        if (hole[i][M + 1][3] != -1) {
            tryPush(i, M, hole[i][M + 1][3]);
        }
    }

    while (pq.size()) {
        cur = pq.top(); pq.pop();

        if (cur.height != tank[cur.x][cur.y]) {
            continue;
        }

        for (i = 0; i < 4; i++) {

            if (hole[cur.x][cur.y][i] == -1) {
                continue;
            }

            nextX = cur.x + dx[i];
            nextY = cur.y + dy[i];

            if (nextX < 1 || nextY < 1 || nextX > N || nextY > M) {
                continue;
            }

            nextHeight = max(min(hole[cur.x][cur.y][i], tank[nextX][nextY]), tank[cur.x][cur.y]);
            tryPush(nextX, nextY, nextHeight);
        }
    }

    for (i = 1; i <= N; i++) {
        for (j = 1; j <= M; j++) {
            ans += tank[i][j];
        }
    }
}

int main() {
    FILE* inp;
    FILE* out;

    inp = fopen("watertank.inp", "rt");
    out = fopen("watertank.out", "wt");

    int num = 0;
    if (fscanf(inp, "%d", &num) == 1) {}

    for (int i = 0; i < num; i++) {
        input(inp);
        BFS();
        printf("%d\n", ans);
        fprintf(out, "%d\n", ans);
        ans = 0;
    }

    fclose(inp);
    fclose(out);

}
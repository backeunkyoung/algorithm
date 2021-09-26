//#pragma warning(disable: 4996)
//#include <stdio.h>
//
//inline int max(int A, int B) { return A > B ? A : B; }
//inline int min(int A, int B) { return A < B ? A : B; }
//
//const int dx[4] = { -1, 0, 1, 0 };
//const int dy[4] = { 0, 1, 0, -1 };
//
//int N, M, H, ans, z;
//int hole[1003][1003][4], tank[1003][1003];
//
//struct Water {
//    int height, x, y;
//};
//
//bool comp(Water A, Water B) { return A.height < B.height; }
//void swap(Water& A, Water& B) { Water t = A; A = B; B = t; }
//
//struct PriorityQueue {
//    Water heap[1000 * 1000];
//    int heapSize;
//    bool(*cmp)(Water, Water);
//    void init() {
//        heapSize = 0;
//        cmp = comp;
//    }
//    Water top() { return heap[1]; }
//    int size() { return heapSize; }
//    void push(Water data) {
//        heap[++heapSize] = data;
//        int c = heapSize;
//        for (; c > 1 && cmp(heap[c], heap[c / 2]); c /= 2) {
//            swap(heap[c], heap[c / 2]);
//        }
//    }
//    void pop() {
//        int p = 1, c = 2;
//        swap(heap[1], heap[heapSize--]);
//        for (; c <= heapSize; p = c, c *= 2) {
//            if (c < heapSize && cmp(heap[c + 1], heap[c])) {
//                c++;
//            }
//            if (!cmp(heap[c], heap[p])) break;
//            swap(heap[c], heap[p]);
//        }
//    }
//}pq;
//
//// 구멍에 맞는 높이로 설정되는 경우 pq에 push
//void tryPush(int x, int y, int h) {
//    // 보다 낮은 높이인 경우
//    if (tank[x][y] <= h) return;
//
//    tank[x][y] = h;
//    pq.push({ h, x, y });
//}
//
//void input(FILE *inp) {
//    register int i, j;
//
//    if (fscanf(inp, "%d %d %d", &N, &M, &H) == 1) {}
//
//    // 상하 구멍 표시
//    for (i = 1; i <= N + 1; ++i) {
//        for (j = 1; j <= M; ++j) {
//            if (fscanf(inp, "%d", &z) == 1) {}
//            hole[i - 1][j][2] = z;
//            hole[i][j][0] = z;
//        }
//    }
//    // 좌우 구멍 표시
//    for (i = 1; i <= N; ++i) {
//        for (j = 1; j <= M + 1; ++j) {
//            if (fscanf(inp, "%d", &z) == 1) {}
//            hole[i][j - 1][1] = z;
//            hole[i][j][3] = z;
//        }
//    }
//    // 구멍이 없다고 생각하며 물을 가득 채웁니다.
//    for (i = 1; i <= N; ++i) {
//        for (j = 1; j <= M; ++j) {
//            tank[i][j] = H;
//        }
//    }
//}
//
//void BFS() {
//    register int i, j, nextX, nextY, nextHeight;
//    Water cur;
//    // 우선순위 큐 생성
//    pq.init();
//    // 바깥쪽에 있는 구멍 높이를 기준으로 설정
//    for (i = 0; i <= M + 1; ++i) { // 상, 하
//        if (hole[0][i][2] != -1)
//            tryPush(1, i, hole[0][i][2]);
//        if (hole[N + 1][i][0] != -1)
//            tryPush(N, i, hole[N + 1][i][0]);
//    }
//    for (i = 0; i <= N + 1; ++i) { // 좌, 우
//        if (hole[i][0][1] != -1)
//            tryPush(i, 1, hole[i][0][1]);
//        if (hole[i][M + 1][3] != -1)
//            tryPush(i, M, hole[i][M + 1][3]);
//    }
//    while (pq.size()) {
//        cur = pq.top(); pq.pop();
//        // 이미 갱신처리된 경우
//        if (cur.height != tank[cur.x][cur.y])
//            continue;
//        for (i = 0; i < 4; i++) {
//            // 구멍이 없는 방향인 경우
//            if (hole[cur.x][cur.y][i] == -1)
//                continue;
//            nextX = cur.x + dx[i];
//            nextY = cur.y + dy[i];
//            // 범위를 벗어나는 경우
//            if (nextX < 1 || nextY < 1 || nextX > N || nextY > M)
//                continue;
//            nextHeight = max(min(hole[cur.x][cur.y][i], tank[nextX][nextY]), tank[cur.x][cur.y]);
//            tryPush(nextX, nextY, nextHeight);
//        }
//    }
//
//    // 물의 총량(부피) 구하기
//    for (i = 1; i <= N; i++) {
//        for (j = 1; j <= M; j++) {
//            ans += tank[i][j];
//        }
//    }
//}
//
//int main() {
//    // freopen("input.txt", "r", stdin);
//    FILE* inp;
//
//    inp = fopen("watertank.inp", "rt");
//
//    int num = 0;
//    if (fscanf(inp, "%d", &num) == 1) {}
//
//    for (int i = 0; i < num; i++) {
//        input(inp);
//        BFS();
//        printf("%d\n", ans);
//        ans = 0;
//    }
//
//}
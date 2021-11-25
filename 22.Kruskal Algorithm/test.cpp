*bool shuffle_DC(int* A, int* B, int* C, int n, int m) {

    if (A == B) return true;

    bool same = false;  // 같은지 체크
    int plus = 0;

    for (int k = n + m-1; k >= 0; k--) {
        if (C[k] == A[n] && C[k] == B[m]) {
            if (k == 0) {   // 마지막 인덱스의 경우
                return true;
            }
            n--;
            m--;
            same = true;
            plus++;
        }
        else if (C[k] == A[n] || C[k] == B[m]) {
            if (C[k] == A[n]) {
                n--;
                if (same) {
                    m += plus;
                    same = false;
                }
            }
            else if (C[k] == B[m]) {
                m--;
                if (same) {
                    n += plus;
                    same = false;
                }
            }
            return false;
        }

    }
    return true;
}
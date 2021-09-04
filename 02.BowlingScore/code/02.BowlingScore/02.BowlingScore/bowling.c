#pragma warning(disable: 4996)
#include <stdio.h>
#include <stdlib.h>

// type
#define STRIKE 0     // ��Ʈ����ũ => ���� �� score = score * 3
#define SPARE 1      // ����� => ���� �� score = score * 2
#define OPEN 2       // �⺻ score, 10���� ���� ��� �����߸��� ���� ���

#define MAX_FRAME 10

// ����ü ����
struct score {
    int score1;
    int score2;
    int type;
};

// �Լ� ����
int typeSet(int score1, int score2);

int main() {
    FILE * inp;
    FILE * out;
    int numOfGames = 0;
    struct score s[11]; // s[10] : ������ �����ӿ� 3��° �� ������ ���� ����

    inp = fopen("bowling.inp", "rt");
    out = fopen("bowling.out", "wt");

    // "fscanf ��ȯ���� ���õǾ����ϴ�" ��� �ذ��� ���� if�� ���
    if (fscanf(inp, "%d", &numOfGames) == 1) {} // 10���� ���� 1�� �ޱ�(���� ����)

    for (int count = 0; count < numOfGames; count++) {  // ���� Ƚ����ŭ �ݺ�

        int sum = 0;    // ����

        // ���� �ޱ� �� Ÿ�� ����
        for (int i = 0; i < MAX_FRAME; i++) {
            
            if (fscanf(inp, "%d", &s[i].score1) == 1) {}    // ù��° �� ���� �ֱ�

            if (s[i].score1 == 10 && i < MAX_FRAME-1) {   // ù��° ���� ��Ʈ����ũ�̰�, ������ �������� �ƴϸ�
                s[i].score2 = 0;    // �ι�° ���� 0��
            }
            else {  // ù��° ���� ��Ʈ����ũ�� �ƴϸ�
                if (fscanf(inp, "%d", &s[i].score2) == 1) {}    // �ι�° �� ���� �ֱ�
            }

            if (i == 9 && (s[i].score1 + s[i].score2) >= 10) {  // ������ �������̰�, �� ���� ���� 10���� ũ�� 3��° �� ����
                if (fscanf(inp, "%d", &s[10].score1) == 1) {};
                s[10].score2 = 0;
            }

            // Ÿ�� ����
            s[i].type = typeSet(s[i].score1, s[i].score2);

        }

        // ����(sum) ���
        for (int i = 0; i < MAX_FRAME; i++) {
            switch (s[i].type) {
                case STRIKE:
                    // ��Ʈ����ũ => ( ���� �������� ��1 + ��2 ) + ( ���� �������� ��1, ��2 )
                    sum += s[i].score1 + s[i].score2 + s[i + 1].score1 + s[i + 1].score2;

                    // ������ �������� �ƴϰ�, ���� �����ӵ� ��Ʈ����ũ�̸� �� ���� ���� �������� �ջ�
                    if (i != MAX_FRAME - 1 && s[i + 1].score1 == 10 && s[i + 1].score2 == 0) {
                        sum += s[i + 2].score1;
                    }

                    break;

                case SPARE:
                    // ����� => ( ���� �������� ��1 + ��2 ) + ( ���� �������� ��1 )
                    sum += s[i].score1 + s[i].score2 + s[i + 1].score1;

                    break;

                default:
                    // ���� => ( ���� �������� ��1 + ��2 )
                    sum += s[i].score1 + s[i].score2;

                    break;
            }
        }
        fprintf(out, "%d\n", sum);

    }

    fclose(inp);
    fclose(out);

    return 0;
}

// �Լ� ����
// Ÿ�� ���� �Լ�
int typeSet(int score1, int score2) {
    if (score1 == 10) {
        return STRIKE;
    }
    else if ((score1 + score2) == 10) {
        return SPARE;
    }
    else {
        return OPEN;
    }
}
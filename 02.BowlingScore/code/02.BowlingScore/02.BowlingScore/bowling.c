#pragma warning(disable: 4996)
#include <stdio.h>
#include <stdlib.h>

// type
#define STRIKE 0     // 스트라이크 => 다음 턴 score = score * 3
#define SPARE 1      // 스페어 => 다음 턴 score = score * 2
#define OPEN 2       // 기본 score, 10개의 핀을 모두 쓰러뜨리지 못한 경우

#define MAX_FRAME 10

// 구조체 선언
struct score {
    int score1;
    int score2;
    int type;
};

// 함수 선언
int typeSet(int score1, int score2);

int main() {
    FILE * inp;
    FILE * out;
    int numOfGames = 0;
    struct score s[11]; // s[10] : 마지막 프레임에 3번째 볼 점수를 위해 생성

    inp = fopen("bowling.inp", "rt");
    out = fopen("bowling.out", "wt");

    // "fscanf 반환값이 무시되었습니다" 경고 해결을 위한 if문 사용
    if (fscanf(inp, "%d", &numOfGames) == 1) {} // 10진수 정수 1개 받기(서식 지정)

    for (int count = 0; count < numOfGames; count++) {  // 게임 횟수만큼 반복

        int sum = 0;    // 총점

        // 점수 받기 및 타입 지정
        for (int i = 0; i < MAX_FRAME; i++) {
            
            if (fscanf(inp, "%d", &s[i].score1) == 1) {}    // 첫번째 볼 점수 넣기

            if (s[i].score1 == 10 && i < MAX_FRAME-1) {   // 첫번째 볼이 스트라이크이고, 마지막 프레임이 아니면
                s[i].score2 = 0;    // 두번째 볼은 0점
            }
            else {  // 첫번째 볼이 스트라이크가 아니면
                if (fscanf(inp, "%d", &s[i].score2) == 1) {}    // 두번째 볼 점수 넣기
            }

            if (i == 9 && (s[i].score1 + s[i].score2) >= 10) {  // 마지막 프레임이고, 두 볼의 합이 10보다 크면 3번째 볼 진행
                if (fscanf(inp, "%d", &s[10].score1) == 1) {};
                s[10].score2 = 0;
            }

            // 타입 지정
            s[i].type = typeSet(s[i].score1, s[i].score2);

        }

        // 점수(sum) 계산
        for (int i = 0; i < MAX_FRAME; i++) {
            switch (s[i].type) {
                case STRIKE:
                    // 스트라이크 => ( 현재 프레임의 볼1 + 볼2 ) + ( 다음 프레임의 볼1, 볼2 )
                    sum += s[i].score1 + s[i].score2 + s[i + 1].score1 + s[i + 1].score2;

                    // 마지막 프레임이 아니고, 다음 프레임도 스트라이크이면 그 다음 볼의 점수까지 합산
                    if (i != MAX_FRAME - 1 && s[i + 1].score1 == 10 && s[i + 1].score2 == 0) {
                        sum += s[i + 2].score1;
                    }

                    break;

                case SPARE:
                    // 스페어 => ( 현재 프레임의 볼1 + 볼2 ) + ( 다음 프레임의 볼1 )
                    sum += s[i].score1 + s[i].score2 + s[i + 1].score1;

                    break;

                default:
                    // 오픈 => ( 현재 프레임의 볼1 + 볼2 )
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

// 함수 구현
// 타입 지정 함수
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
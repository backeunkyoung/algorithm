rfile = open("fibonacci.inp", "r")
wfile = open("fibonacci.out", "w")

def get_pisano_period(M) :
    # 피사노 주기 구하기
    M = int(M)

    # 첫 피보나치 값 설정
    a = 0
    b = 1
    c = a + b

    for i in range(M * M) :
        c = (a + b) % M
        a = b
        b = c

        if a == 0 and b == 1 : return i + 1


def get_fibonacci_huge(n, m, pisano) :
    n = int(n)
    m = int(m)
    pisano = int(pisano)

    remainder = n % pisano

    first = 0
    second = 1

    res = remainder

    # no need to think about every calculation as we just need to know the very last digit
    # using pisano period
    for i in range(remainder) :
        res = (first + second) % m
        first = second
        second = res

    return res % m


while True :
    line = str(rfile.readline()).strip()

    if line == "" : break

    for i in range(int(line)) :
        print("-------------")
        line = str(rfile.readline()).strip()

        (fA, fB, fC, fD, M) = line.split(" ")
        # print(fA, " ", fB, " ", fC, " ", fD, " ", M)

        pisano = get_pisano_period(M)
        # print("피사노 주기 : ", pisano)

        resultA = get_fibonacci_huge(fA, M, pisano)
        resultB = get_fibonacci_huge(fB, M, pisano)
        resultC = get_fibonacci_huge(fC, M, pisano)
        resultD = get_fibonacci_huge(fD, M, pisano)

        print(resultA, " ", resultB, " ", resultC, " ", resultD, " ", M)

        M = int(M)
        print(((resultA * resultB) + (resultC * resultD)) % M)
        # ((fa % m * fb % m ) % m + (fc %m * fd % m) % m ) % m
        print("result : ", ( ((resultA * resultB) % M) + ((resultC * resultD) % M)) % M )
        # result = (((resultA * resultB) % M) + ((resultC * resultD) % M)) % M
        # print("result : ", result)
        # wfile.write("%s\n" % result)

rfile.close()
wfile.close()
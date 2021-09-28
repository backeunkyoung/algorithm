rfile = open("fibonacci.inp", "r")
wfile = open("fibonacci.out", "w")

def get_pisano_period(M) :
    M = int(M)

    a = 0
    b = 1
    c = a + b

    for i in range(M * M) :
        c = (a + b) % M
        a = b
        b = c

        if a == 0 and b == 1 : return i + 1


# def get_fibonacci_formula(n, m, pisano):
#     n = int(n)
#     m = int(m)

#     n = n % pisano

#     print(n, m, pisano)
#     sqrt_5 = 5 ** (1/2)
#     ans = 1 / sqrt_5 * ( ((1 + sqrt_5) / 2) ** n  - ((1 - sqrt_5) / 2) ** n )
#     return int(ans) % m

# def get_fibonacci_huge(n, m, pisano) :

#     n = int(n)
#     m = int(m)
#     pisano = int(pisano)

#     if n < 2 : return n
#     remainder = n % pisano

#     first = 0
#     second = 1

#     res = remainder

#     for i in range(remainder - 1) :
#         res = (first + second) % m
#         first = second % m
#         second = res % m

#     return res


def get_fibonacci_pisano(m, pisano) :

    m = int(m)
    pisano = int(pisano)
    ret = {}
    ret[0] = 0
    ret[1] = 1

    first = 0
    second = 1

    for i in range(pisano - 1) :
        res = (first + second) % m
        first = second % m
        second = res
        ret[i+2] = res

    return ret

# pisano_period = get_fibonacci_pisano(3, 8)
# print(pisano_period)

# n = 10000000
# print(pisano_period[n%3])
# import sys
# sys.exit(0)


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
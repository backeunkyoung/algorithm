def get_pisano_period(m) :
    # calculate the pisano period number
    m = int(m)

    a = 0
    b = 1
    c = a + b

    for i in range(0, m * m) :
        c = (a + b) % m
        a = b
        b = c

        if a == 0 and b == 1 : return i + 1


def get_fibonacci_huge(n, m) :
    # using the pisano period makes the calculation faster!
    n = int(n)
    m = int(m)

    remainder = n % (get_pisano_period(m))

    first = 0
    second = 1

    res = remainder

    # no need to think about every calculation as we just need to know the very last digit
    # using pisano period
    for i in range(0, remainder) :
        res = (first + second) % m
        first = second
        second = res

    return res % m


n = 2816213588
m = 239

print(get_fibonacci_huge(n, m))



# def fibo(n, m):
#     if n < 2:
#         return n

#     # a, b = 0, 1
#     a = 0
#     b = 1
#     for i in range(n-1):
#         a = b % m
#         b = (a + b) % m
#         print("b : ", b)

#     return b
# print(fibo(100000000000000 ,3))
# print("실행")
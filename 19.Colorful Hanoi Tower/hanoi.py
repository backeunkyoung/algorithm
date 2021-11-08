rfile = open("hanoi.inp", "r")
wfile = open("hanoi.out", "w")

numOfCount = str(rfile.readline()).strip()

for i in range(int(numOfCount)) :
    floor = int(str(rfile.readline()).strip())

    color = []
    val = []
    move = [0] * floor
    moveCount = 0

    for j in range(floor) :
        line = str(rfile.readline()).strip()
        arr = line.split(" ")

        color.append(arr[0])
        val.append(int(arr[1]))

    for i in range(floor - 1, -1, -1):
        if color[i] == 'G':
            move[i] += 1
            for j in range(i - 1, -1, -1):
                move[j] += 2 ** (i - j - 1)
        elif color[i] == 'B':
            if move[i] % 2 == 0 and val[i] != 1:
                if i == 0: moveCount -= 1
                move[i] += 2
                for j in range(i - 1, -1, -1):
                    move[j] += 2 ** (i - j)
            else:
                move[i] += 1
                for j in range(i - 1, -1, -1):
                    move[j] += 2 ** (i - j - 1)
        else:
            if move[i] % 2 == 0 or val[i] == 1:
                move[i] += 1
                for j in range(i - 1, -1, -1):
                    move[j] += 2 ** (i - j - 1)
            else:
                if i == 0: moveCount -= 1
                move[i] += 2
                for j in range(i - 1, -1, -1):
                    move[j] += 2 ** (i - j)

    for i in range(floor):
        moveCount += val[i] * move[i]

    # print(moveCount)

    wfile.write("%d\n" % moveCount)

rfile.close()
wfile.close()
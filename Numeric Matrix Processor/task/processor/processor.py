def getMatrix(dimen):
    matrix = []
    for i in range(0, dimen[0]):
        matrix.append([int(i) for i in input().split()])
    return matrix


if __name__ == '__main__':
    dA = tuple([int(i) for i in input().split()])
    xA = getMatrix(dA)
    dB = tuple([int(i) for i in input().split()])
    xB = getMatrix(dB)
    if dA[0] != dB[0] or dB[1] != dB[1]:
        print("ERROR")
    else:
        for i in range(0, dA[0]):
            row = [xA[i][j] + xB[i][j] for j in range(0, dA[1])]
            print(*row)

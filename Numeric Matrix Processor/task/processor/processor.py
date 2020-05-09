def get_matrix(dimen):
    matrix = []
    for i in range(0, dimen[0]):
        matrix.append([int(i) for i in input().split()])
    return matrix


def add_matrices(xA, xB):
    matrix = []
    for i in range(0, len(xA)):
        matrix.append([xA[i][j] + xB[i][j] for j in range(0, len(xA[0]))])
    return matrix


if __name__ == '__main__':
    dA = tuple([int(i) for i in input().split()])
    xA = get_matrix(dA)
    c = int(input())
    for i in range(0, dA[0]):
        row = [c * xA[i][j] for j in range(0, dA[1])]
        print(*row)

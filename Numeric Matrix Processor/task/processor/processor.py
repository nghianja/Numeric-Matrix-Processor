def get_size(message):
    return tuple([int(i) for i in input(message).split()])


def get_matrix(size):
    matrix = []
    for i in range(size[0]):
        matrix.append([float(i) for i in input().split()])
    return matrix


def add_matrices():
    xA = get_matrix(get_size("Enter size of first matrix: "))
    xB = get_matrix(get_size("Enter size of second matrix: "))
    if len(xA) != len(xB) or len(xA[0]) != len(xB[0]):
        print("ERROR")
    else:
        print("The addition result is:")
        for i in range(len(xA)):
            row = [xA[i][j] + xB[i][j] for j in range(len(xA[0]))]
            print(*row)


def multiply_matrix_to_constant():
    x = get_matrix(get_size("Enter size of matrix: "))
    c = int(input("Enter constant: "))
    print("The multiplication to a constant result is:")
    for i in range(len(x)):
        row = [c * x[i][j] for j in range(len(x[0]))]
        print(*row)


def multiply_matrices():
    xA = get_matrix(get_size("Enter size of first matrix: "))
    xB = get_matrix(get_size("Enter size of second matrix: "))
    if len(xA[0]) != len(xB):
        print("ERROR")
    else:
        print("The multiplication result is:")
        xR = [[0.0] * len(xB[0]) for _n in range(len(xA))]
        for i in range(len(xA)):
            for j in range(len(xB[0])):
                for k in range(len(xB)):
                    xR[i][j] += xA[i][k] * xB[k][j]
        for r in xR:
            print(*r)


if __name__ == '__main__':
    while True:
        print("1. Add matrices")
        print("2. Multiply matrix to a constant")
        print("3. Multiply matrices")
        print("0. Exit")
        choice = int(input("Your choice: "))
        if choice == 1:
            add_matrices()
        elif choice == 2:
            multiply_matrix_to_constant()
        elif choice == 3:
            multiply_matrices()
        elif choice == 0:
            break

import math


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


def transpose_matrix():
    print()
    print("1. Main diagonal")
    print("2. Side diagonal")
    print("3. Vertical line")
    print("4. Horizontal line")
    choice = int(input("Your choice: "))
    s = get_size("Enter matrix size: ")
    print("Enter matrix: ")
    x = get_matrix(s)
    if choice == 1:
        t = main_diagonal(x)
    elif choice == 2:
        t = side_diagonal(x)
    elif choice == 3:
        t = vertical_line(x)
    elif choice == 4:
        t = horizontal_line(x)
    else:
        t = [[0.0]]
    print("The transpose result is:")
    for r in t:
        print(*r)


def main_diagonal(x):
    t = [[0.0] * len(x[0]) for _n in range(len(x))]
    for i in range(len(x)):
        for j in range(len(x[0])):
            t[j][i] = x[i][j]
    return t


def side_diagonal(x):
    t = [[0.0] * len(x[0]) for _n in range(len(x))]
    for i in range(len(x)):
        for j in range(len(x[0])):
            t[i][j] = x[len(x[0]) - 1 - j][len(x) - 1 - i]
    return t


def vertical_line(x):
    t = [[0.0] * len(x[0]) for _n in range(len(x))]
    for i in range(len(x)):
        for j in range(len(x[0])):
            t[i][j] = x[i][len(x[0]) - 1 - j]
    return t


def horizontal_line(x):
    t = [[0.0] * len(x[0]) for _n in range(len(x))]
    for i in range(len(x)):
        for j in range(len(x[0])):
            t[i][j] = x[len(x) - 1 - i][j]
    return t


def calculate_determinant():
    s = get_size("Enter matrix size: ")
    print("Enter matrix: ")
    x = get_matrix(s)
    if s[1] != s[0]:
        print("ERROR")
    else:
        determinant = laplace_expansion(x)
        print("The result is:")
        print(determinant)


def laplace_expansion(matrix):
    total = 0.0
    if len(matrix) == 1:
        total = matrix[0][0]
    elif len(matrix) == 2:
        total = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    else:
        c = 1
        for j in range(1, len(matrix) + 1):
            m = [[0.0] * (len(matrix) - 1) for _n in range(len(matrix) - 1)]
            for i in range(1, len(matrix)):
                counter = 0
                for index, element in enumerate(matrix[i]):
                    if index != j - 1:
                        m[i - 1][counter] = element
                        counter += 1
            total += c * matrix[0][j - 1] * laplace_expansion(m)
            c *= -1
    return total


def inverse_matrix():
    s = get_size("Enter matrix size: ")
    print("Enter matrix: ")
    x = get_matrix(s)
    if s[1] != s[0]:
        print("ERROR")
    else:
        det = laplace_expansion(x)
        if det == 0.0:
            print("Undefined")
        else:
            cofactors = [[0.0] * s[1] for _n in range(s[0])]
            for i in range(len(x)):
                for j in range(len(x[0])):
                    m = [[0.0] * (s[1] - 1) for _n in range(s[0] - 1)]
                    counter = 0
                    for k in range(len(x)):
                        if i != k:
                            m[counter] = [x[k][n] for n in range(len(x[k])) if n != j]
                            counter += 1
                    cofactors[i][j] = laplace_expansion(m) * math.pow(-1.0, i + j)
            transpose = main_diagonal(cofactors)
            for i in range(len(transpose)):
                transpose[i] = [ele * 1.0 / det for ele in transpose[i]]
                print(*transpose[i])


if __name__ == '__main__':
    while True:
        print("1. Add matrices")
        print("2. Multiply matrix to a constant")
        print("3. Multiply matrices")
        print("4. Transpose matrix")
        print("5. Calculate a determinant")
        print("6. Inverse matrix")
        print("0. Exit")
        choice = int(input("Your choice: "))
        if choice == 1:
            add_matrices()
        elif choice == 2:
            multiply_matrix_to_constant()
        elif choice == 3:
            multiply_matrices()
        elif choice == 4:
            transpose_matrix()
        elif choice == 5:
            calculate_determinant()
        elif choice == 6:
            inverse_matrix()
        elif choice == 0:
            break

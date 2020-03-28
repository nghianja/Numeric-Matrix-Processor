package processor

import kotlin.math.pow

fun main() {
    do {
        println("1. Add matrices")
        println("2. Multiply matrix to a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrix")
        println("5. Calculate a determinant")
        println("6. Inverse matrix")
        println("0. Exit")
        print("Your choice: ")
        val choice = readLine()!!.toInt()
        when (choice) {
            1 -> addMatrices()
            2 -> multiplyMatrixToConstant()
            3 -> multiplyMatrices()
            4 -> transposeMatrix()
            5 -> calculateDeterminant()
            6 -> inverseMatrix()
        }
        println()
    } while (choice != 0)
}

private fun getSize(message: String): Pair<Int, Int> {
    print(message)
    val (n, m) = readLine()!!.split(" ")
    return Pair(n.toInt(), m.toInt())
}

private fun getMatrix(n: Int, m: Int): Array<DoubleArray> {
    val x = Array(n) { DoubleArray(m) { 0.0 } }
    for (i in 1..n) {
        val cols = readLine()!!.split(" ")
        for (j in 1..m) {
            x[i - 1][j - 1] = cols[j - 1].toDouble()
        }
    }
    return x
}

private fun addMatrices() {
    val pA = getSize("Enter size of first matrix: ")
    val xA = getMatrix(pA.first, pA.second)
    val pB = getSize("Enter size of second matrix: ")
    val xB = getMatrix(pB.first, pB.second)
    if (pA.first != pB.first || pA.second != pB.second) {
        println("ERROR")
    } else {
        println("The addition result is:")
        val r = Array(pA.first) { DoubleArray(pA.second) { 0.0 } }
        for (i in 1..pA.first) {
            for (j in 1..pA.second) {
                r[i - 1][j - 1] = xA[i - 1][j - 1] + xB[i - 1][j - 1]
            }
        }
        printMatrix(r)
    }
}

private fun multiplyMatrixToConstant() {
    val p = getSize("Enter size of matrix: ")
    val x = getMatrix(p.first, p.second)
    print("Enter constant: ")
    val c = readLine()!!.toDouble()
    println("The multiplication to a constant result is:")
    for (i in 1..p.first) {
        for (j in 1..p.second) {
            x[i - 1][j - 1] *= c
        }
    }
    printMatrix(x)
}

private fun multiplyMatrices() {
    val pA = getSize("Enter size of first matrix: ")
    val xA = getMatrix(pA.first, pA.second)
    val pB = getSize("Enter size of second matrix: ")
    val xB = getMatrix(pB.first, pB.second)
    if (pA.second != pB.first) {
        println("ERROR")
    } else {
        val r = Array(pA.first) { DoubleArray(pB.second) { 0.0 } }
        for (i in 1..pA.first) {
            for (j in 1..pB.second) {
                for (k in 1..pA.second) {
                    r[i - 1][j - 1] += xA[i - 1][k - 1] * xB[k - 1][j - 1]
                }
            }
        }
        println("The multiplication result is:")
        printMatrix(r)
    }
}

private fun transposeMatrix() {
    println()
    println("1. Main diagonal")
    println("2. Side diagonal")
    println("3. Vertical line")
    println("4. Horizontal line")
    print("Your choice: ")
    val choice = readLine()!!.toInt()
    val p = getSize("Enter matrix size: ")
    println("Enter matrix: ")
    val x = getMatrix(p.first, p.second)
    val t = when (choice) {
        1 -> mainDiagonal(x)
        2 -> sideDiagonal(x)
        3 -> verticalLine(x)
        4 -> horizontalLine(x)
        else -> Array(1) { DoubleArray(1) { 0.0 } }
    }
    println("The transpose result is:")
    printMatrix(t)
}

private fun mainDiagonal(x: Array<DoubleArray>): Array<DoubleArray> {
    val t = Array(x[0].size) { DoubleArray(x.size) { 0.0 } }
    for (i in 1..x.size) {
        for (j in 1..x[0].size) {
            t[j - 1][i - 1] = x[i - 1][j - 1]
        }
    }
    return t
}

private fun sideDiagonal(x: Array<DoubleArray>): Array<DoubleArray> {
    val t = Array(x[0].size) { DoubleArray(x.size) { 0.0 } }
    for (i in 1..x.size) {
        for (j in 1..x[0].size) {
            t[i - 1][j - 1] = x[x[0].size - j][x.size - i]
        }
    }
    return t
}

private fun verticalLine(x: Array<DoubleArray>): Array<DoubleArray> {
    val t = Array(x[0].size) { DoubleArray(x.size) { 0.0 } }
    for (i in 1..x.size) {
        for (j in 1..x[0].size) {
            t[i - 1][j - 1] = x[i - 1][x[0].size - j]
        }
    }
    return t
}

private fun horizontalLine(x: Array<DoubleArray>): Array<DoubleArray> {
    val t = Array(x[0].size) { DoubleArray(x.size) { 0.0 } }
    for (i in 1..x.size) {
        for (j in 1..x[0].size) {
            t[i - 1][j - 1] = x[x.size - i][j - 1]
        }
    }
    return t
}

private fun calculateDeterminant() {
    val p = getSize("Enter matrix size: ")
    println("Enter matrix: ")
    val x = getMatrix(p.first, p.second)
    if (p.second != p.first) {
        println("ERROR")
    } else {
        val determinant = laplaceExpansion(x)
        println("The result is:")
        println(determinant)
    }
}

private fun laplaceExpansion(matrix: Array<DoubleArray>): Double {
    var sum = 0.0
    if (matrix.size == 2) {
        sum =  matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    } else {
        var c = 1
        for (j in 1..matrix.size) {
            val m = Array(matrix.size - 1) { DoubleArray(matrix.size - 1) { 0.0 } }
            for (i in 1 until matrix.size) {
                var counter = 0
                matrix[i].forEachIndexed { index, element ->
                    run {
                        if (index != j - 1) {
                            m[i - 1][counter] = element
                            counter++
                        }
                    }
                }
            }
            sum += c * matrix[0][j - 1] * laplaceExpansion(m)
            c *= -1
        }
    }
    return sum
}

private fun inverseMatrix() {
    val p = getSize("Enter matrix size: ")
    println("Enter matrix: ")
    val x = getMatrix(p.first, p.second)
    if (p.second != p.first) {
        println("ERROR")
    } else {
        val det = laplaceExpansion(x)
        if (det == 0.0) {
            println("Undefined")
        } else {
            val cofactors = Array(p.first) { DoubleArray(p.second) { 0.0 } }
            for (i in x.indices) {
                for (j in x[0].indices) {
                    val m = Array(p.first - 1) { DoubleArray(p.second -1) { 0.0 } }
                    var counter = 0
                    for (k in x.indices) {
                        if (i != k) {
                            m[counter] = x[k].filterIndexed { index, _ -> index != j }.toDoubleArray()
                            counter++
                        }
                    }
                    cofactors[i][j] = laplaceExpansion(m) * (-1.0).pow(i + j)
                }
            }
            val transpose = mainDiagonal(cofactors)
            for (i in 1..transpose.size) {
                for (j in 1..transpose[0].size) {
                    transpose[i - 1][j - 1] *= 1.0 / det
                }
            }
            printMatrix(transpose)
        }
    }
}

private fun printMatrix(matrix: Array<DoubleArray>) {
    val widths = IntArray(matrix[0].size) { 0 }
    matrix.forEach {
        it.forEachIndexed { j, e ->
            run {
                val len = "%.2f".format(e).length
                if (len > widths[j])
                    widths[j] = len
            }
        }
    }
    matrix.forEach {
        it.forEachIndexed { j, e -> print("${"%.2f".format(e).padStart(widths[j])} ") }
        println()
    }
}

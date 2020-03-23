package processor

fun main() {
    var choice = 0
    do {
        println("1. Add matrices")
        println("2. Multiply matrix to a constant")
        println("3. Multiply matrices")
        println("0. Exit")
        print("Your choice: ")
        choice = readLine()!!.toInt()
        when (choice) {
            1 -> addMatrices()
            2 -> multiplyMatrixToConstant()
            3 -> multiplyMatrices()
        }
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
        for (i in 1..pA.first) {
            for (j in 1..pA.second) {
                print("${xA[i - 1][j - 1] + xB[i - 1][j - 1]} ")
            }
            println()
        }
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
            print("${c * x[i - 1][j - 1]} ")
        }
        println()
    }
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
        for (i in 1..pA.first) {
            println(r[i - 1].joinToString(" "))
        }
    }
}

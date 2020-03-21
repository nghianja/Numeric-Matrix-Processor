package processor

fun main() {
    val (nA, mA) = readLine()!!.split(" ")
    val xA = getMatrix(nA, mA)
    val c = readLine()!!.toInt()
    for (i in 1..nA.toInt()) {
        for (j in 1..mA.toInt()) {
            print("${c * xA[i - 1][j - 1]} ")
        }
        println()
    }
}

private fun getMatrix(n: String, m: String): Array<IntArray> {
    val x = Array(n.toInt()) { IntArray(m.toInt()) { 0 } }
    for (i in 1..n.toInt()) {
        val cols = readLine()!!.split(" ")
        for (j in 1..m.toInt()) {
            x[i - 1][j - 1] = cols[j - 1].toInt()
        }
    }
    return x
}

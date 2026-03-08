class Main {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val stack = ArrayDeque<Int>()
        var sum = 0L
        repeat(n) {
            val height = readLine().toInt()
            while (stack.isNotEmpty() && stack.last() <= height) {
                stack.removeLast()
            }
            sum += stack.size
            stack += height
        }
        print(sum)
    }
}

fun main() {
    Main().solution()
}
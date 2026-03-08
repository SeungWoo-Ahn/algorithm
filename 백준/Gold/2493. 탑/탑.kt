import java.util.StringTokenizer

class Main {
    data class Top(val height: Int, val index: Int)

    private val stack = ArrayDeque<Top>()
    private lateinit var result: IntArray

    private fun input() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        result = IntArray(n)
        repeat(n) { index ->
            val height = st.nextToken().toInt()
            stack += Top(height = height, index = index)
        }
    }

    private fun output() {
        val sb = StringBuilder()
        for (n in result) {
            sb.append("$n ")
        }
        print(sb)
    }

    fun solution() {
        input()
        val waitStack = ArrayDeque<Top>()
        waitStack += stack.removeLast()
        while (stack.isNotEmpty()) {
            while (waitStack.isNotEmpty() && waitStack.last().height < stack.last().height) {
                val top = waitStack.removeLast()
                result[top.index] = stack.size
            }
            waitStack += stack.removeLast()
        }
        output()
    }
}

fun main() {
    Main().solution()
}
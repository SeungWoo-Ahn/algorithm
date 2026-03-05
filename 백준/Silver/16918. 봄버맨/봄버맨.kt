class Main {
    private lateinit var map: Array<IntArray>
    private var remain = 0
    private val d = listOf(0 to 0, 1 to 0, -1 to 0, 0 to 1, 0 to -1)

    private fun input() = with(System.`in`.bufferedReader()) {
        val (r, c, n) = readLine().split(" ").map { it.toInt() }
        map = Array(r) {
            val line = readLine()
            IntArray(c) { i ->
                when (line[i]) {
                    'O' -> 3
                    else -> EMPTY
                }
            }

        }
        remain = n
    }

    private fun setBomb(curTime: Int) {
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == EMPTY) {
                    map[x][y] = curTime + 3
                }
            }
        }
    }

    private fun popBomb(curTime: Int) {
        val popPos = mutableListOf<Pair<Int, Int>>()
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == curTime) {
                    popPos += x to y
                }
            }
        }
        for ((x, y) in popPos) {
            for ((dx, dy) in d) {
                val nx = x + dx
                val ny = y + dy
                if (outOfBoundary(nx, ny)) continue
                map[nx][ny] = EMPTY
            }
        }
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun simulation() {
        var time = 1
        while (time <= remain) {
            if (time % 2 == 0) {
                setBomb(time)
            } else {
                popBomb(time)
            }
            time++
        }
    }

    private fun output() {
        val sb = StringBuilder()
        for (row in map) {
            for (num in row) {
                val status = when (num) {
                    EMPTY -> '.'
                    else -> 'O'
                }
                sb.append(status)
            }
            sb.append('\n')
        }
        print(sb)
    }

    fun solution() {
        input()
        simulation()
        output()
    }

    companion object {
        private const val EMPTY = -1
    }
}

fun main() {
    Main().solution()
}
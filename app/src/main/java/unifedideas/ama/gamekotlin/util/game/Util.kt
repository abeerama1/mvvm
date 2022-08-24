package unifedideas.ama.gamekotlin.util.game

class Util {
    companion object {
        fun generateQuestion(): Question? {
            val x = Array(3) {
                arrayOfNulls<String>(
                    3
                )
            }

            var startNumber = (Math.random() * 10).toInt() + 1
            var incStartNumber = (Math.random() * 5).toInt() + 1
            var stredNumber: Int
            var number = -1
            for (i in x.indices) {
                for (j in x[i].indices) {
                    stredNumber = startNumber + incStartNumber
                    if (i == 1 && j == 1) {
                        x[i][j] = "??"
                        number = stredNumber
                    } else {
                        x[i][j] = stredNumber.toString() + ""
                    }
                    incStartNumber += 2
                    startNumber = stredNumber
                }
            }
            return Question(x, number)
        }
    }
}
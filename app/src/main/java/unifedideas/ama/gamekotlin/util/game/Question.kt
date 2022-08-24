package unifedideas.ama.gamekotlin.util.game

class Question(private var data: Array<Array<String?>>, private var hiddenNumber: Int) {

    fun getData(): Array<Array<String?>> {
        return data
    }

    fun setData(data: Array<Array<String?>>) {
        this.data = data
    }

    fun getHiddenNumber(): Int {
        return hiddenNumber
    }

    fun setHiddenNumber(hiddenNumber: Int) {
        this.hiddenNumber = hiddenNumber
    }
}
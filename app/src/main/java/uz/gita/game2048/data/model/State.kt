package uz.gita.game2048.data.model

data class State(
    val swipe: Boolean,
    val score : Int,
    val move: Int,
    val record: Int,
//    val time: Int,
    val array: Array<IntArray>
)
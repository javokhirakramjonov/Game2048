package uz.gita.game2048.mvp.contract

import uz.gita.game2048.data.model.State

interface FinishContract {
    interface Model {
        fun getState(): State
        fun getNewArray(): Array<IntArray>
        fun saveState(state: State)
    }

    interface Presenter {
        fun getState() : State
        fun getNewArray(): Array<IntArray>
        fun saveState(state: State)
    }

    interface View {
        fun loadViews()
        fun loadData()
        fun goBack()
        fun restart()
    }
}
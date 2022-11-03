package uz.gita.game2048.mvp.presenter

import uz.gita.game2048.data.model.State
import uz.gita.game2048.mvp.contract.FinishContract
import uz.gita.game2048.mvp.model.FinishModel

class FinishPresenter: FinishContract.Presenter {
    private val model: FinishContract.Model = FinishModel()

    override fun getState(): State {
        return model.getState()
    }

    override fun getNewArray(): Array<IntArray> {
        return model.getNewArray()
    }

    override fun saveState(state: State) {
        model.saveState(state)
    }
}
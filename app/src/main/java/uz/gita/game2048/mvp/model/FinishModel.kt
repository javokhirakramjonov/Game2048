package uz.gita.game2048.mvp.model

import uz.gita.game2048.data.model.State
import uz.gita.game2048.data.repository.impl.AppRepositoryImpl
import uz.gita.game2048.mvp.contract.FinishContract

class FinishModel : FinishContract.Model {
    private val repository = AppRepositoryImpl.getAppRepository()

    override fun getState(): State {
        return repository.getState()
    }

    override fun getNewArray(): Array<IntArray> {
        return repository.getNewArray()
    }

    override fun saveState(state: State) {
        repository.saveState(state)
    }
}
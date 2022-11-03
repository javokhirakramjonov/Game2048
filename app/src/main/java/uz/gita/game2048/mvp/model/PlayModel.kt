package uz.gita.game2048.mvp.model

import uz.gita.game2048.data.model.State
import uz.gita.game2048.data.repository.AppRepository
import uz.gita.game2048.data.repository.impl.AppRepositoryImpl
import uz.gita.game2048.mvp.contract.PlayContract

class PlayModel : PlayContract.Model {
    private val repository: AppRepository = AppRepositoryImpl.getAppRepository()

    override fun addNewElementToMatrix() {
        repository.addNewElementToMatrix()
    }

    override fun isEmptyCell(): Boolean {
        return repository.isEmptyCell()
    }

    override fun getMatrixData(): Array<IntArray> {
        return repository.getMatrixData()
    }

    override fun moveLeft(): Int {
        return repository.moveLeft()
    }

    override fun moveRight(): Int {
        return repository.moveRight()
    }

    override fun moveUp(): Int {
        return repository.moveUp()
    }

    override fun moveDown(): Int {
        return repository.moveDown()
    }

    override fun saveRecord(rec: Int) {
        repository.saveRecord(rec)
    }

    override fun saveState(state: State) {
        repository.saveState(state)
    }

    override fun getState(): State {
        return repository.getState()
    }

    override fun getNewArray(): Array<IntArray> {
        return repository.getNewArray()
    }
}
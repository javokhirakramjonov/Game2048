package uz.gita.game2048.mvp.presenter

import android.os.CountDownTimer
import uz.gita.game2048.data.model.State
import uz.gita.game2048.mvp.contract.PlayContract
import uz.gita.game2048.mvp.model.PlayModel

class PlayPresenter(private val view: PlayContract.View) : PlayContract.Presenter {
    private val model: PlayContract.Model = PlayModel()

    override fun checkMove(score: Int) {
        if (score >= 0) {
            if (model.isEmptyCell()) {
                model.addNewElementToMatrix()
                view.addMove(score)
                view.illustrateMatrix(model.getMatrixData())
            }
            if (!model.isEmptyCell()) {
                val matrix = model.getMatrixData()
                for (i in 0..2) {
                    for (j in 0..2) {
                        if (matrix[i][j] == matrix[i + 1][j] || matrix[i][j] == matrix[i][j + 1])
                            return
                    }
                }
                for(i in 0..2)
                    if(matrix[3][i] == matrix[3][i + 1])
                        return
                for(i in 0..2)
                    if(matrix[i][3] == matrix[i + 1][3])
                        return
                view.save()
                object: CountDownTimer(2000, 1000) {
                    override fun onTick(p0: Long){}
                    override fun onFinish(){}
                }
                view.finishScreen()
            }
        }
    }

    override fun swipeSideLeft() {
        checkMove(model.moveLeft())
    }

    override fun swipeSideRight() {
        checkMove(model.moveRight())
    }

    override fun swipeSideUp() {
        checkMove(model.moveUp())
    }

    override fun swipeSideDown() {
        checkMove(model.moveDown())
    }

    override fun saveState(state: State) {
        model.saveState(state)
    }

    override fun loadData() = with(model.getState()) {
        view.loadData(this)
    }

    override fun restart() {
        view.loadData(State(model.getState().swipe, 0, 0, model.getState().record, /*0,*/ model.getNewArray()))
    }

    override fun saveRecord(record: Int) {
        model.saveRecord(record)
    }
}
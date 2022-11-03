package uz.gita.game2048.mvp.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import uz.gita.game2048.R
import uz.gita.game2048.data.model.State
import uz.gita.game2048.mvp.contract.FinishContract
import uz.gita.game2048.mvp.presenter.FinishPresenter
import uz.gita.game2048.utils.getBackgroundByAmount
import uz.gita.game2048.utils.openScreen

class FinishScreen : Fragment(R.layout.screen_finish), FinishContract.View {
    private lateinit var screen: View
    private val presenter: FinishContract.Presenter = FinishPresenter()

    private lateinit var table: LinearLayoutCompat
    private lateinit var text: TextView
    private lateinit var restart: AppCompatButton
    private lateinit var undo: AppCompatButton


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        screen = view
        loadViews()
        loadData()
        restart()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadViews() = with(screen) {
        Glide.with(this).load(R.drawable.congrats).into(findViewById(R.id.record))
        table = findViewById(R.id.mainView)
        text = findViewById(R.id.text)
        restart = findViewById(R.id.restart)
        restart.setOnClickListener {
            openScreen(PlayScreen())
        }
        undo = findViewById(R.id.undo)
    }

    @SuppressLint("SetTextI18n")
    override fun loadData() {
        val state = presenter.getState()
        /* with(state) {
             val hour = time / 3600
             val minute = time % 3600 / 60
             val second = time % 60
             val timeX =
                 (if (hour == 0) "" else if (hour < 10) "0$hour:" else "$hour:") + (if (hour > 0) {
                     if (minute < 10) "0$minute" else "$minute"
                 } else "$minute") + ":" + if (second < 10) "0$second" else "$second"
             text.text = "You earned $score points with $move moves in $timeX"
         }*/
        state.apply {
            text.text = "You earned $score points with $move moves"
        }
        for (i in 0 until table.childCount) {
            val line: LinearLayoutCompat = table.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until line.childCount) {
                val temp = line.getChildAt(j) as TextView
                temp.text = state.array[i][j].toString()
                temp.setBackgroundResource(state.array[i][j].getBackgroundByAmount())
                if (state.array[i][j] < 8) {
                    temp.setTextColor(Color.parseColor("#776e65"))
                } else {
                    temp.setTextColor(Color.parseColor("#f9f6f2"))
                }
            }
        }
    }

    override fun goBack() {
        //TODO("Not yet implemented")
    }

    override fun restart() = with(presenter.getState()) {
        presenter.saveState(
            State(
                swipe,
                0,
                0,
                record,
                /*0,*/
                presenter.getNewArray()
            )
        )
    }

}
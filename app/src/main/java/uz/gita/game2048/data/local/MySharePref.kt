package uz.gita.game2048.data.local

import android.content.Context
import android.content.SharedPreferences
import uz.gita.game2048.data.model.State

class MySharePref {
    companion object {
        private lateinit var instance: MySharePref
        private lateinit var sharedPref: SharedPreferences

        fun init(context: Context) {
            if (::instance.isInitialized)
                return
            instance = MySharePref()
            sharedPref = context.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        }

        fun get() = instance
    }

    fun saveRecord(record: Int) {
        sharedPref.edit().putInt("RECORD", record).apply()
    }

    fun isFirstTime(): Boolean {
        val x = sharedPref.getBoolean("isFirst", true)
        sharedPref.edit().putBoolean("isFirst", false).apply()
        return x
    }

    fun saveState(state: State) = with(state) {
        val edit = sharedPref.edit()
        edit.putInt("SCORE", score)
        edit.putBoolean("SWIPE", swipe)
        edit.putInt("MOVE", move)
//        edit.putInt("TIME", time)
        saveRecord(record)

        for (i in array.indices) {
            for (j in array.indices) {
                edit.putInt("Array${i}vs$j", array[i][j])
            }
        }
        edit.apply()
    }

    fun getState(): State = with(sharedPref) {

        val matrix = Array(4) { IntArray(4) }

        for (i in 0..3) {
            for (j in 0..3) {
                matrix[i][j] = getInt("Array${i}vs$j", 0)
            }
        }

        return State(
            getBoolean("SWIPE", false),
            getInt("SCORE", 0),
            getInt("MOVE", 0),
            getInt("RECORD", 0),
//            getInt("TIME", 0),
            matrix
        )
    }
}
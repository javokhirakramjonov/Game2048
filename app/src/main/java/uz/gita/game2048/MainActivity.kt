package uz.gita.game2048

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.gita.game2048.mvp.view.MainScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openScreen(MainScreen())
    }

    fun openScreenAddStack(fm: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fm)
            .addToBackStack(fm.javaClass.name)
            .commit()
    }

    fun openScreen(fm: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fm)
            .commit()
    }

    fun closeScreen() {
        supportFragmentManager.popBackStack()
    }
}
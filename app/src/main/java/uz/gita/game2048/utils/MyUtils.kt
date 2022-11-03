package uz.gita.game2048.utils

import android.util.Log
import androidx.fragment.app.Fragment
import uz.gita.game2048.MainActivity

fun Fragment.openScreenAddStack(fm: Fragment) {
    (requireActivity() as MainActivity).openScreenAddStack(fm)
}

fun Fragment.openScreen(fm: Fragment) {
    (requireActivity() as MainActivity).openScreen(fm)
}

fun Fragment.closeScreen() {
    (requireActivity() as MainActivity).closeScreen()
}

fun Array<IntArray>.showLog(where : String? = null) {
    val tag = "ARRAY"
    Log.d(tag, "begin")
    where?.let {
        Log.d(tag, where)
    }
    for(i in this) {
        Log.d(tag, i.joinToString())
    }
    Log.d(tag, "end")
}
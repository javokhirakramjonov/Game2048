package uz.gita.game2048.mvp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import uz.gita.game2048.R
import uz.gita.game2048.mvp.contract.MainContract
import uz.gita.game2048.utils.openScreen
import java.util.*
import kotlin.concurrent.schedule

class MainScreen : Fragment(R.layout.screen_main), MainContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<LottieAnimationView>(R.id.animation).apply {
            repeatCount = LottieDrawable.INFINITE
            speed = 5f
            playAnimation()
        }
        Timer().schedule(2000) {
            openPlayScreen()
        }
    }

    override fun openPlayScreen() {
        openScreen(PlayScreen())
    }
}
package aih.iikrhia.chaweeiikrhia

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat


class Miiswekef: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.miiswekef)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.setDimAmount(0.40F)

        val kef = intent.getStringExtra("Kiihiikef")
        val kef2 = intent.getStringExtra("Skakef")
        val kef3 = intent.getStringExtra("Tsalii")
        val kef4 = intent.getStringExtra("Laarinak")
        val kef5 = intent.getStringExtra("Shaqatti")
        val kef6 = intent.getStringExtra("Kefskakefai")

        val kiihiikef = findViewById<TextView>(R.id.kiihiikef)
        val skakef = findViewById<TextView>(R.id.skakef)
        val tsalii = findViewById<TextView>(R.id.tsalii)
        val laarinak = findViewById<TextView>(R.id.swelaarinak)
        val shaqatti = findViewById<TextView>(R.id.shaqatti)
        val kefskakefai = findViewById<TextView>(R.id.kefskakefai)

        kiihiikef.text = kef
        skakef.text = kef2
        tsalii.text = kef3
        laarinak.text = kef4
        shaqatti.text = kef5
        kefskakefai.text = kef6
    }
}
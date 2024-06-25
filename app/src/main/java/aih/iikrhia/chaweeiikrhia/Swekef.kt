package aih.iikrhia.chaweeiikrhia

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat


class Swekef: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swekef)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.setDimAmount(0.88F)

        val zopii = applicationContext as ChaweEIikrhia

        val kiihiikef = findViewById<TextView>(R.id.swekiihiikef)
        val skakef = findViewById<TextView>(R.id.sweskakef)
        val tsalii = findViewById<TextView>(R.id.tsalii)
        val laarinak = findViewById<TextView>(R.id.swelaarinak)
        val shaqatti = findViewById<TextView>(R.id.shaqatti)
        val kefskakefai = findViewById<TextView>(R.id.kefskakefai)

        tsalii.setTypeface(null, Typeface.ITALIC)
        laarinak.setTypeface(null, Typeface.ITALIC)
        shaqatti.setTypeface(null, Typeface.ITALIC)
        kefskakefai.setTypeface(null, Typeface.ITALIC)

        kiihiikef.text = zopii.kiihiikef
        skakef.text = zopii.skakef
        tsalii.text = zopii.tsalii
        laarinak.text = zopii.laarinak
        shaqatti.text = zopii.shaqatti
        kefskakefai.text = zopii.kefskakefai
    }
}
package aih.iikrhia.chaweeiikrhia

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.DynamicColors
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream


class ChaweEIikrhia: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
    lateinit var kiihiikef: String
    lateinit var skakef: String
    var tsalii: String? = null
    var laarinak: String? = null
    var shaqatti: String? = null
    var kefskakefai: String? = null
}

class MainActivity : AppCompatActivity(), FefrhiKef.Saswekef {

    private val thalasakef = ArrayList<Kef>()
    private val adapter = FefrhiKef(this, thalasakef, this)
    private val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arakef)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val recyclerView: RecyclerView = findViewById(R.id.sakef)

        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = adapter
        zatseuxakef()

        findViewById<EditText>(R.id.iixakanoi).doOnTextChanged { kiire, _, _, _ ->
            adapter.notifyDataSetChanged()
            adapter.sakaKef(kiire.toString())
        }

        findViewById<FloatingActionButton>(R.id.sakaiixa).setOnClickListener {
            if (findViewById<MaterialCardView>(R.id.kiihiisaka).visibility == View.GONE) {
                findViewById<MaterialCardView>(R.id.kiihiisaka).visibility = View.VISIBLE
            }
            else {
                findViewById<MaterialCardView>(R.id.kiihiisaka).visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun zatseuxakef() {

        //Atle fal kefai
        val myInput: InputStream = assets.open("chaweeiikrhia.xlsx")

        val falsakef = XSSFWorkbook(myInput)
        val tlasakef = falsakef.getSheetAt(0)
        var kiihiikef: String?
        var skakef: String?
        var laarinak: String?
        var tsalii: String?
        var shaqatti: String?
        var kefskakefai: String?

        //Eriiha
        for(kala in 1 .. tlasakef.lastRowNum) {
            if(tlasakef.getRow(kala).getCell(2) != null) {
                kiihiikef = tlasakef.getRow(kala).getCell(2).stringCellValue
                if(tlasakef.getRow(kala).getCell(3) != null) {
                    skakef = tlasakef.getRow(kala).getCell(3).stringCellValue
                    //Tsalii
                    if(tlasakef.getRow(kala).getCell(14) != null) {
                        tsalii = tlasakef.getRow(kala).getCell(14).stringCellValue
                        if(tlasakef.getRow(kala).getCell(17) != null) {
                            laarinak = tlasakef.getRow(kala).getCell(17).stringCellValue
                            if(tlasakef.getRow(kala).getCell(16) != null) {
                                shaqatti = tlasakef.getRow(kala).getCell(16).stringCellValue
                                if(tlasakef.getRow(kala).getCell(18) != null) {
                                    kefskakefai = tlasakef.getRow(kala).getCell(18).stringCellValue
                                    thalasakef.add(Kef(kiihiikef, skakef, tsalii, laarinak, shaqatti, kefskakefai))
                                }
                                else {
                                    thalasakef.add(Kef(kiihiikef, skakef, tsalii, laarinak, shaqatti))
                                }
                            }
                            else {
                                thalasakef.add(Kef(kiihiikef, skakef, tsalii, laarinak))
                            }
                        }
                        else {
                            thalasakef.add(Kef(kiihiikef, skakef, tsalii))
                        }
                    }
                    //Kef Cootasai
                    else if(tlasakef.getRow(kala).getCell(17) != null) {
                        laarinak = tlasakef.getRow(kala).getCell(17).stringCellValue
                        if(tlasakef.getRow(kala).getCell(16) != null) {
                            shaqatti = tlasakef.getRow(kala).getCell(16).stringCellValue
                            if(tlasakef.getRow(kala).getCell(18) != null) {
                                kefskakefai = tlasakef.getRow(kala).getCell(18).stringCellValue
                                thalasakef.add(Kef(kiihiikef, skakef, null, laarinak, shaqatti, kefskakefai))
                            }
                            else {
                                thalasakef.add(Kef(kiihiikef, skakef, null, laarinak, shaqatti))
                            }
                        }
                        else {
                            thalasakef.add(Kef(kiihiikef, skakef, null, laarinak))
                        }
                    }
                    //Shaqatti
                    else if(tlasakef.getRow(kala).getCell(16) != null) {
                        shaqatti = tlasakef.getRow(kala).getCell(16).stringCellValue
                        if(tlasakef.getRow(kala).getCell(18) != null) {
                            kefskakefai = tlasakef.getRow(kala).getCell(18).stringCellValue
                            thalasakef.add(Kef(kiihiikef, skakef, null, null, shaqatti, kefskakefai))
                        }
                        else {
                            thalasakef.add(Kef(kiihiikef, skakef, null, null, shaqatti))
                        }
                    }
                    //Kef Skakefai
                    else if(tlasakef.getRow(kala).getCell(18) != null) {
                        kefskakefai = tlasakef.getRow(kala).getCell(18).stringCellValue
                        thalasakef.add(Kef(kiihiikef, skakef, null, null, null, kefskakefai))
                    }
                    //Nama Kef
                    else {
                        thalasakef.add(Kef(kiihiikef, skakef))
                    }
                }
            }
        }

    }

    override fun tsiinakef(kef: Kef) {
        val intent = Intent(this@MainActivity, Swekef::class.java)
        val zopii = applicationContext as ChaweEIikrhia

        zopii.kiihiikef = sacepai(kef.word)
        zopii.skakef = sacepai(kef.translation)
        zopii.tsalii = kef.note?.replace(", ", " ｡ ")//?.let { sacepai(it) }
        zopii.laarinak = kef.loanword?.replace(", ", " ｡ ")//?.let { sacepai(it) }
        zopii.shaqatti = kef.proto?.replace(", ", " ｡ ")
        zopii.kefskakefai = kef.calque?.replace(", ", " ｡ ")

        startActivity(intent)
    }

    private fun sacepai(kef: String): String {
         return kef.replace(", ", " ｡ ")
             .split(" ").reversed().joinToString()
             .replace("ɭ̀ʃ", "j͑ʃ")
             .replace("ɻʃ", "ɽ͑ʃ'")
             .replace("ⲝʃ", "j͐ʃ")
             .replace("ſ̙ן", "ᶅſ")
             .replace("ƣ", "ƣ̋")
             .replace("ɻ", "п́")
             .replace("ɔ̒", "ͷ̗")
             .replace(",", "!`!")
             .replace("ſɭ!`!", "ſɭ,")
             .replace("ı]!`!", "ı],")
             .replace("!`!", "")
             .replace(" ", "\n")
    }

    override fun sahaktsiinakef(kef: Kef) {
        val intent = Intent(this@MainActivity, Miiswekef::class.java)

        val sefkiihiikef = kef.word
        val sefskakef = kef.translation
        val seftsalii = kef.note
        val seflaarinak = kef.loanword
        val sefshaqatti = kef.proto
        val sefkefskakefai = kef.calque

        intent.putExtra("Kiihiikef", sefkiihiikef)
        intent.putExtra("Skakef", sefskakef)
        intent.putExtra("Tsalii", seftsalii)
        intent.putExtra("Laarinak", seflaarinak)
        intent.putExtra("Shaqatti", sefshaqatti)
        intent.putExtra("Kefskakefai", sefkefskakefai)

        startActivity(intent)
    }
}

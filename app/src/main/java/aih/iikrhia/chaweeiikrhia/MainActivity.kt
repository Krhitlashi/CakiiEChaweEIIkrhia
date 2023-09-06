package aih.iikrhia.chaweeiikrhia

import aih.iikrhia.chaweeiikrhia.databinding.ActivityMainBinding
import android.Manifest
import android.app.Application
import android.app.WallpaperManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
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
}

class MainActivity : AppCompatActivity(), KefRecyclerViewAdapter.Saswekef {

    private lateinit var binding: ActivityMainBinding

    val thalasakef = ArrayList<Kef>()
    val adapter = KefRecyclerViewAdapter(this, thalasakef, this)
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.sakef)


        linearLayoutManager.reverseLayout = true
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

        val sefkiihiikef = sacepai(kef.word)
        val sefskakef = sacepai(kef.translation)
        val seftsalii = kef.note?.replace(", ", " ｡ ")//?.let { sacepai(it) }
        val seflaarinak = kef.loanword?.replace(", ", " ｡ ")//?.let { sacepai(it) }
        val sefshaqatti = kef.proto?.replace(", ", " ｡ ")
        val sefkefskakefai = kef.calque?.replace(", ", " ｡ ")

        intent.putExtra("Kiihiikef", sefkiihiikef)
        intent.putExtra("Skakef", sefskakef)
        intent.putExtra("Tsalii", seftsalii)
        intent.putExtra("Laarinak", seflaarinak)
        intent.putExtra("Shaqatti", sefshaqatti)
        intent.putExtra("Kefskakefai", sefkefskakefai)

        startActivity(intent)
    }

    private fun sacepai(kef: String): String {
         return kef.replace(", ", " ｡ ")
             .split(" ").reversed().joinToString()
             .replace(",", "!`!")
             .replace("ſɭ!`!", "ſɭ,")
             .replace("!`!", "")
             .replace(" ", "\n")
    }

    override fun sahaktsiinakef(araq: Int) {
        val intent = Intent(this@MainActivity, Miiswekef::class.java)

        val sefkiihiikef = thalasakef[araq].word
        val sefskakef = thalasakef[araq].translation
        val seftsalii = thalasakef[araq].note
        val seflaarinak = thalasakef[araq].loanword
        val sefshaqatti = thalasakef[araq].proto
        val sefkefskakefai = thalasakef[araq].calque

        intent.putExtra("Kiihiikef", sefkiihiikef)
        intent.putExtra("Skakef", sefskakef)
        intent.putExtra("Tsalii", seftsalii)
        intent.putExtra("Laarinak", seflaarinak)
        intent.putExtra("Shaqatti", sefshaqatti)
        intent.putExtra("Kefskakefai", sefkefskakefai)

        startActivity(intent)
    }
}

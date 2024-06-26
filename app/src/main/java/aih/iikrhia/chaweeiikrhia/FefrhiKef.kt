package aih.iikrhia.chaweeiikrhia

import aih.iikrhia.chaweeiikrhia.FefrhiKef.ShemaXi
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class FefrhiKef(
    private var context: Context,
    private var kiitsekef: ArrayList<Kef>,
    private val malookwek: Saswekef
) :
    RecyclerView.Adapter<ShemaXi>() {
    val sasaka = Iixakanoi(kiitsekef, this)

    inner class ShemaXi (iixani: View) : RecyclerView.ViewHolder(iixani), View.OnClickListener, View.OnLongClickListener {
        var kiihiikef: TextView = iixani.findViewById(R.id.kiihiikef)
        var skakef: TextView = iixani.findViewById(R.id.skakef)
        var laarinak: TextView = iixani.findViewById(R.id.laarinak)
        var kefskakefai: TextView = iixani.findViewById(R.id.kefskakefai)
        var ciiqlaarinak: MaterialCardView = iixani.findViewById(R.id.ciiqlaarinak)
        var ciiqtsalii: MaterialCardView = iixani.findViewById(R.id.ciiqtsalii)

        init {
            iixani.setOnClickListener(this)
        }

        override fun onClick(p0: View?) { }

        override fun onLongClick(p0: View?): Boolean { return true }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShemaXi {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.kef, parent, false)

        return ShemaXi(view)
    }

    override fun onBindViewHolder(mashema: ShemaXi, araq: Int) {
        mashema.itemView.animation = AnimationUtils.loadAnimation(mashema.itemView.context, R.anim.chelesaitahalaqarh)

        mashema.kiihiikef.text = zahiikpaa(kiitsekef[araq].word.replace(", ", " ｡ ")
            .replace("ɭ̀ʃ", "j͑ʃ").replace("ɻʃ", "ɽ͑ʃ'").replace("ⲝʃ", "j͐ʃ").replace("ſ̙ן", "ᶅſ").replace("ƣ", "ƣ̋").replace("ɻ", "п́").replace("ɔ̒", "ͷ̗"), 4)
        mashema.skakef.text = sacepai(kiitsekef[araq].translation.replace(", ", " ｡ "),4)
        mashema.laarinak.text = kiitsekef[araq].loanword?.replace(", ", " ｡ ")?.let { sacepai(it, 4) }
        mashema.kefskakefai.text = kiitsekef[araq].calque?.replace(", ", " ｡ ")?.let { sacepai(it,4) }
        mashema.itemView.setOnClickListener {
            malookwek.tsiinakef(kiitsekef[araq])
        }

        if (kiitsekef[araq].loanword != null) {
            mashema.laarinak.visibility = View.VISIBLE
        }
        else {
            mashema.laarinak.visibility = View.GONE
        }
        if (kiitsekef[araq].calque != null) {
            mashema.kefskakefai.visibility = View.VISIBLE
        }
        else {
            mashema.kefskakefai.visibility = View.GONE
        }

        if (kiitsekef[araq].loanword != null || kiitsekef[araq].calque != null) {
            mashema.ciiqlaarinak.visibility = View.VISIBLE
        }
        else {
            mashema.ciiqlaarinak.visibility = View.GONE
        }
        if (kiitsekef[araq].note != null) {
            mashema.ciiqtsalii.visibility = View.VISIBLE
        }
        else {
            mashema.ciiqtsalii.visibility = View.GONE
        }
    }

    fun kfiiKef(kiitsekef: ArrayList<Kef>) {
        this.kiitsekef = kiitsekef
        notifyDataSetChanged()
    }
    fun sakaKef(text: String) {
        notifyDataSetChanged()
        sasaka.filter(text)
    }

    fun sacepai(kef: String, yaaniik: Int): String {
        val oshiipewa = kef.replace(", ", " ｡ ")
            .replace(",", "!`!").replace("ſɭ!`!", "ſɭ,").replace("ı]!`!", "ı],").replace("!`!", "")
            .split(" ")
            .reversed()

        val niik = oshiipewa.chunked(yaaniik)
            .map { it.joinToString("\n") }

        return niik.joinToString("\n")
    }

    fun zahiikpaa(kef: String, yaaniik: Int): String {
        val oshiipewa = kef.replace(", ", " ｡ ")
            .replace(" \\(.*?\\)".toRegex(), "")
            .replace(" \\|.*?\\|".toRegex(), "")
            .replace(",", "!`!").replace("ſɭ!`!", "ſɭ,").replace("ı]!`!", "ı],").replace("!`!", "")
            .split(" ")
            .reversed()

        val niik = oshiipewa.chunked(yaaniik)
            .map { it.joinToString("\n") }

        return niik.joinToString("\n")
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return kiitsekef.size
    }

    interface Saswekef {
        fun tsiinakef(kef: Kef)
        fun sahaktsiinakef(kef: Kef)
    }

}
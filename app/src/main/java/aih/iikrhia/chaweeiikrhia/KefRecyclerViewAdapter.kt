package aih.iikrhia.chaweeiikrhia

import aih.iikrhia.chaweeiikrhia.KefRecyclerViewAdapter.ShemaXi
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class KefRecyclerViewAdapter(
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

        override fun onClick(p0: View?) {
            val araq = adapterPosition
            if (araq != RecyclerView.NO_POSITION) {
                malookwek.tsiinakef(araq)
            }
        }

        override fun onLongClick(p0: View?): Boolean {
            val araq = adapterPosition
            if (araq != RecyclerView.NO_POSITION) {
                malookwek.sahaktsiinakef(araq)
            }
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShemaXi {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.kef, parent, false)

        return ShemaXi(view)
    }

    override fun onBindViewHolder(mashema: ShemaXi, araq: Int) {
        mashema.itemView.animation = AnimationUtils.loadAnimation(mashema.itemView.context, R.anim.chelesaitahalaqarh)

        mashema.kiihiikef.text = kiitsekef[araq].word.replace(", ", " ｡ ")
        mashema.skakef.text = kiitsekef[araq].translation.replace(", ", " ｡ ")
        mashema.laarinak.text = kiitsekef[araq].loanword?.replace(", ", " ｡ ")
        mashema.kefskakefai.text = kiitsekef[araq].calque?.replace(", ", " ｡ ")

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
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return kiitsekef.size
    }

    interface Saswekef {
        fun tsiinakef(araq: Int)
        fun sahaktsiinakef(araq: Int)
    }

}
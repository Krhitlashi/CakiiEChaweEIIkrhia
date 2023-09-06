package aih.iikrhia.chaweeiikrhia

import android.widget.Filter
import java.util.Locale

class Iixakanoi(private val hakef: ArrayList<Kef>, private val adapter: KefRecyclerViewAdapter) :
    Filter() {
    private var sakasaiiixakanoi: ArrayList<Kef> = ArrayList()

    override fun performFiltering(charSequence: CharSequence): FilterResults {
        sakasaiiixakanoi.clear()
        val results = FilterResults()
        for (item in hakef) {
            if (item.word.lowercase(Locale.getDefault()).trim { it <= ' ' }
                    .contains(charSequence) || item.translation.lowercase(
                    Locale.getDefault()
                ).trim { it <= ' ' }
                    .contains(charSequence)
            ) {
                sakasaiiixakanoi.add(item)
            }
            if (charSequence === "j͐ʃэƣ̋ ꞁȷ̀ꞇ }ʃᴜƽ") {
                if (item.loanword?.trim { it <= ' ' }?.contains("From") == true) {
                    sakasaiiixakanoi.add(item)
                }
            }
        results.count = sakasaiiixakanoi.size
        results.values = sakasaiiixakanoi
        }

        return results
    }

    override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
        adapter.kfiiKef(sakasaiiixakanoi)
    }

    fun getFilteredList(): ArrayList<Kef> {
        return sakasaiiixakanoi
    }

}
package com.catarsi.catosrs.presentation.old.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.databinding.OldItemRowBinding
import java.util.*
import kotlin.collections.ArrayList

class OldItemsListAdapter(private var oldItemList: ArrayList<OldItem>) :
    RecyclerView.Adapter<OldItemsListAdapter.OldItemHolder>(), Filterable {

    var oldItemFilterList = ArrayList<OldItem>()
    var onItemClick: ((OldItem) -> Unit)? = null

    init {
        oldItemFilterList = oldItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OldItemHolder {
        val itemBinding =
            OldItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OldItemHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return oldItemFilterList.size
    }

    override fun onBindViewHolder(holder: OldItemHolder, position: Int) {
        val oldItem: OldItem = oldItemFilterList[position]
        holder.bind(oldItem)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    oldItemFilterList = oldItemList
                } else {
                    val resultList = ArrayList<OldItem>()
                    for (row in oldItemList) {
                        if (row.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    oldItemFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = oldItemFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                oldItemFilterList = results?.values as ArrayList<OldItem>
                notifyDataSetChanged() //TODO https://iammert.medium.com/using-diffutil-in-android-recyclerview-bdca8e4fbb00#.ehc0gaijt
            }

        }
    }

    inner class OldItemHolder(private val itemBinding: OldItemRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(oldItem: OldItem) {
            itemBinding.oldItemText.text = oldItem.name
            itemBinding.root.setOnClickListener {
                onItemClick?.invoke(oldItem)
                Log.d("Selected:", oldItem.id.toString())
            }
        }
    }
}
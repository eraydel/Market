package com.dev.eraydel.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.holder.ServicesViewHolder
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.model.ServicesModel
import com.dev.eraydel.market.view.ui.fragments.services.ServicesFragment

class ServicesAdapter(private var services: ArrayList<ServicesModel>, val onItemListener: ServicesFragment) : RecyclerView.Adapter<ServicesViewHolder>(), Filterable {

    var servicesFilterList = ArrayList<ServicesModel>()

    init {
        servicesFilterList = services
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServicesViewHolder(layoutInflater.inflate(R.layout.service_item,parent,false), onItemListener)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val item = servicesFilterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return servicesFilterList.size
    }

    //filter ...
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    servicesFilterList = services
                } else {
                    val resultList = ArrayList<ServicesModel>()
                    for (row in services) {
                        if ( row.title.lowercase().contains(charSearch.lowercase()) ) {
                            resultList.add(row)
                        }
                    }
                    servicesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = servicesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                servicesFilterList = results?.values as ArrayList<ServicesModel>
                notifyDataSetChanged()
            }

        }
    }

}
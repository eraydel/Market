package com.dev.eraydel.market.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.model.ServicesModel
import com.dev.eraydel.market.view.ui.fragments.services.ServicesFragment
import com.squareup.picasso.Picasso

class ServicesViewHolder(view: View, onItemListener: ServicesFragment) : RecyclerView.ViewHolder(view) , View.OnClickListener {

    private var ivItem: ImageView = view.findViewById(R.id.ivItem)
    private var tvItemTitle: TextView = view.findViewById(R.id.tvItemTitle)
    private var tvItemDescription: TextView = view.findViewById(R.id.tvItemDescription)

    private val onItemListener = onItemListener
    private lateinit var services: ServicesModel

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onItemListener.clickServicesItem(services)
    }

    fun bind(item: ServicesModel)
    {
        tvItemTitle.text = item.title?.toString()
        Picasso.get().load(item.image).into(ivItem)
        tvItemDescription.text = item.owner?.toString()
        services = item
    }
}
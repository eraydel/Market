package com.dev.eraydel.market.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.view.ui.fragments.products.ProductsFragment
import com.squareup.picasso.Picasso

class ProductsViewHolder(view: View, onItemListener: ProductsFragment) : RecyclerView.ViewHolder(view) , View.OnClickListener {

    private var ivItem: ImageView = view.findViewById(R.id.ivItem)
    private var tvItemTitle: TextView = view.findViewById(R.id.tvItemTitle)
    private var tvItemDescription: TextView = view.findViewById(R.id.tvItemDescription)

    private val onItemListener = onItemListener
    private lateinit var products: ProductsModel

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View){
        onItemListener.clickProductsItem(products)
    }

    fun bind(item: ProductsModel)
    {
        tvItemTitle.text = item.title?.toString()
        Picasso.get().load(item.image).into(ivItem)
        tvItemDescription.text = item.owner?.toString()

        products = item
    }

}
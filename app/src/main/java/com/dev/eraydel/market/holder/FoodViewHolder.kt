package com.dev.eraydel.market.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.view.adapter.FoodAdapter
import com.dev.eraydel.market.view.ui.fragments.food.FoodFragment
import com.squareup.picasso.Picasso

class FoodViewHolder(view: View, onItemListener: FoodFragment) : RecyclerView.ViewHolder(view) , View.OnClickListener {

    private var ivItem: ImageView = view.findViewById(R.id.ivItem)
    private var tvItemTitle: TextView = view.findViewById(R.id.tvItemTitle)
    private var tvItemDescription: TextView = view.findViewById(R.id.tvItemDescription)

    private val onItemListener = onItemListener
    private lateinit var food: FoodModel

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View){
        onItemListener.clickFoodItem(food)
    }

    fun bind(item: FoodModel)
    {
        tvItemTitle.text = item.title?.toString()
        Picasso.get().load(item.image).into(ivItem)
        tvItemDescription.text = item.owner?.toString()
        /*
        Picasso.get().load(item.fotos?.first()).into(ivItem)
        rate.numStars = 5
        rate.stepSize = 0.5F
        rate.rating = item.calificacionPromedio?.toFloat()?.div(2) ?: 0.0F
        tvItemDesde.text = item.anioCreacion.toString()
        tvItemCostoPromedio.text = "$"+item.costoPromedio.toString()
         */
        food = item

    }
}
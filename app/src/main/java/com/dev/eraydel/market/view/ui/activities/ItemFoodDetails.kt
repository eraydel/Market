package com.dev.eraydel.market.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.ActivityItemFoodDetailsBinding
import com.dev.eraydel.market.db.Home
import com.dev.eraydel.market.model.FoodCategoryItems
import com.dev.eraydel.market.view.adapter.FoodCategoryItemsAdapter

class ItemFoodDetails : AppCompatActivity(), FoodCategoryItemsAdapter.OnItemListener {
    lateinit var binding: ActivityItemFoodDetailsBinding
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemFoodDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        


        if(savedInstanceState == null)
        {
            val bundle = intent.extras
            if( bundle != null ){
                id = bundle.getInt("id" , 0)
            }
            else {
                id = savedInstanceState?.getSerializable("id") as Int
            }
        }

        // items food by seller
        val data = Home(applicationContext).getFoodItemsbySeller(id)
        val adapter = FoodCategoryItemsAdapter(applicationContext, data, this)
        with(binding){
            //RecyclerView require a LayoutManager
            val lm = LinearLayoutManager(applicationContext)
            rvBussinessCategory.addItemDecoration(DividerItemDecoration(applicationContext,lm.orientation))
            rvBussinessCategory.layoutManager = lm
            rvBussinessCategory.adapter = adapter
        }
        //goes for food item detail
        //when( id )  {
          //  1 -> { binding.ivProduct.setImageResource(R.drawable.hamburguesa) }
        //}

            //id.toString()
    }


    override fun clickFoodItem(foodItems: FoodCategoryItems) {
        Toast.makeText(this, "Usted seleccionó: ${foodItems.itemTitle}", Toast.LENGTH_SHORT).show()
    }



    fun clickShare(view: View) {
        Toast.makeText(this, "Funciodalidad compartir pendiente", Toast.LENGTH_SHORT).show()
    }
    fun clickFavorite(view: View) {
        Toast.makeText(this, "Funcionalidad favoritos pendiente", Toast.LENGTH_SHORT).show()
    }

    fun clickBack(view: View) {

            onBackPressed()

    }
}
package com.dev.eraydel.market.db

import android.content.Context
import com.dev.eraydel.market.model.Food
import com.dev.eraydel.market.model.FoodCategoryItems
import com.dev.eraydel.market.model.Products
import com.dev.eraydel.market.model.Services

class Home ( context: Context?) {

    val context = context

    fun getTopFood(): ArrayList<Food>
    {
        val tempArrayList = ArrayList<Food>()
        val foodTemp1 = Food(1, "Hamburguesas", "Descripción hamburguesa")
        val foodTemp2 = Food(2, "Tacos", "Descripción tacos")
        val foodTemp3 = Food(3, "Pizzas", "Descripción pizza")
        val foodTemp4 = Food(4, "Sushi", "Descripción sushi")
        val foodTemp5 = Food(5, "Hamburguesas", "Descripción hamburguesa")
        val foodTemp6 = Food(6, "Tacos", "Descripción tacos")
        val foodTemp7 = Food(7, "Pizzas", "Descripción pizza")
        tempArrayList.add(foodTemp1)
        tempArrayList.add(foodTemp2)
        tempArrayList.add(foodTemp3)
        tempArrayList.add(foodTemp4)
        tempArrayList.add(foodTemp5)
        tempArrayList.add(foodTemp6)
        tempArrayList.add(foodTemp7)
        return tempArrayList
    }

    fun getTopProducts(): ArrayList<Products>
    {
        val tempArrayList = ArrayList<Products>()
        val productTemp1 = Products(1, "Playera tipo polo", "Descripción playera",  250 , "Ropa")
        val productTemp2 = Products(2, "Zapatos de vestir", "Descripción calzado", 950 , "calzado")
        val productTemp3 = Products(3, "Pants deportivo", "Descripción blusa", 800, "ropa")
        val productTemp4 = Products(4, "Tenis para correr", "Descripción tenis", 950 , "calzado")
        val productTemp5 = Products(5, "Taza Homero Simpson", "Descripción taza", 200, "personalizados")
        tempArrayList.add(productTemp1)
        tempArrayList.add(productTemp2)
        tempArrayList.add(productTemp3)
        tempArrayList.add(productTemp4)
        tempArrayList.add(productTemp5)
        return tempArrayList
    }

    fun getTopServices(): ArrayList<Services>
    {
        val tempArrayList = ArrayList<Services>()
        val serviceTemp1 = Services(1, "Pediatra", "Descripción pediatra", "profesional")
        val serviceTemp2 = Services(2, "Electricista", "Descripción electricista", "oficio")
        val serviceTemp3 = Services(3, "Albañil", "Descripción albañil","oficio")
        val serviceTemp4 = Services(4, "Jardinero", "Descripción jardinero", "oficio")
        tempArrayList.add(serviceTemp1)
        tempArrayList.add(serviceTemp2)
        tempArrayList.add(serviceTemp3)
        tempArrayList.add(serviceTemp4)
        return tempArrayList
    }

    fun getFoodItemsbySeller(id_seller: Int): ArrayList<FoodCategoryItems>
    {
        val tempArrayList = ArrayList<FoodCategoryItems>()
        val foodItem1 = FoodCategoryItems(1,"Triple queso","Queso manchego, queso oaxaca, acompañado con papas a la francesa", 100,1,id_seller)
        val foodItem2 = FoodCategoryItems(2,"Hawaina","Queso manchego y piña, acompañado con papas a la francesa", 85,1,id_seller)
        val foodItem3 = FoodCategoryItems(3,"Doble","Queso con jamón acompañado con papas a la francesa", 70,1,id_seller)
        val foodItem4 = FoodCategoryItems(4,"Sencilla","Queso con jamón acompañado con papas a la francesa", 60,1,id_seller)
        val foodItem5 = FoodCategoryItems(4,"Jumbo","La que lleva de todo con papas a la francesa", 150,1,id_seller)
        tempArrayList.add(foodItem1)
        tempArrayList.add(foodItem2)
        tempArrayList.add(foodItem3)
        tempArrayList.add(foodItem4)
        tempArrayList.add(foodItem5)
        return tempArrayList
    }

}
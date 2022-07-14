package com.dev.eraydel.market.view.ui.fragments.food

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.dev.eraydel.market.OnItemClickListener
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.FoodAdapter
import com.dev.eraydel.market.api.APIService
import com.dev.eraydel.market.databinding.FragmentFoodBinding
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.model.ServicesModel
import com.dev.eraydel.market.response.FoodResponse
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FoodFragment : Fragment(), OnItemClickListener {

    private lateinit var adapter: FoodAdapter
    private lateinit var binding: FragmentFoodBinding
    private val foodItems = ArrayList<FoodModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater,container,false)
        initRecyclerView()
        getFood()
        setUpSearchView()
        binding.btnInfoFood.setOnClickListener{
            showInfo()
        }
        return binding.root
    }

    private fun showInfo(){
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.show_animation, null)

        view.findViewById<LottieAnimationView>(R.id.idAnimation).setAnimation(R.raw.foodorder)
        view.findViewById<TextView>(R.id.titleAnimation).text = "¿Antojo de comer algo?"
        view.findViewById<TextView>(R.id.descriptionAnimation).text = "Market Palermo te ayuda a saciar tu hambre"

        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(true)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }

    private fun setUpSearchView() {
        binding.foodSearch.queryHint = "¿Qué te gustaría comer?"
        binding.foodSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })


    }

    //getFood
    private fun getFood()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<FoodResponse> = getRetrofit().create(APIService::class.java).getFoodItems("foodList")
            val fodResponse: FoodResponse? =  call.body()
            requireActivity().runOnUiThread {
                if(call.isSuccessful)
                {
                    var items: ArrayList<FoodModel> = (fodResponse?.food ?: emptyArray<FoodResponse>()) as ArrayList<FoodModel>
                    foodItems.clear()
                    foodItems.addAll(items)
                    binding.progressBar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        }

    }

    //initRecyclerView
    private fun initRecyclerView(){

        adapter = FoodAdapter(foodItems,this)

        binding.rvFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        //binding.rvFood.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        binding.rvFood.adapter = adapter

    }

    //retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo7733613.mockable.io/api/v1/" )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    override fun clickFoodItem(item: FoodModel) {
        Log.i("Food" , item.title)
        val details = FoodFragmentDetails()
        val args = Bundle()
        //sending the arguments...
        var gson = Gson()
        var jsonString = gson.toJson(item)
        args.putString("item" , jsonString)

        details.arguments = args
        binding.rvFood.isVisible = false
        binding.materialToolbar2.isVisible = false
        binding.foodSearch.isVisible = false
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(binding.FragmentContainer.id, details).commit()
    }

    override fun clickProductsItem(products: ProductsModel) {
        TODO("Not yet implemented")
    }

    override fun clickServicesItem(services: ServicesModel) {
        TODO("Not yet implemented")
    }
}




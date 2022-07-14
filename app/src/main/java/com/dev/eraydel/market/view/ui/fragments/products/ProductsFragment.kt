package com.dev.eraydel.market.view.ui.fragments.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.dev.eraydel.market.OnItemClickListener
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.FoodAdapter
import com.dev.eraydel.market.adapter.ProductsAdapter
import com.dev.eraydel.market.api.APIService
import com.dev.eraydel.market.databinding.FragmentFoodBinding
import com.dev.eraydel.market.databinding.FragmentProductsBinding
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.model.ServicesModel
import com.dev.eraydel.market.response.FoodResponse
import com.dev.eraydel.market.response.ProductsResponse
import com.dev.eraydel.market.view.ui.fragments.food.FoodFragmentDetails
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductsFragment : Fragment() , OnItemClickListener {

    private lateinit var adapter: ProductsAdapter
    private lateinit var binding: FragmentProductsBinding
    private val productsItems = ArrayList<ProductsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)
        initRecyclerView()
        getProducts()
        setUpSearchView()
        binding.btnInfo.setOnClickListener{
            showInfo()
        }
        return binding.root
    }

    private fun showInfo(){
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.show_animation, null)

        view.findViewById<LottieAnimationView>(R.id.idAnimation).setAnimation(R.raw.productsorder)
        view.findViewById<TextView>(R.id.titleAnimation).text = "¿Qué producto estás buscando?"
        view.findViewById<TextView>(R.id.descriptionAnimation).text = "Encuéntralo de forma sencilla, fácil y rápida"

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

    //getProducts
    private fun getProducts()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ProductsResponse> = getRetrofit().create(APIService::class.java).getProductsItems("productsList")
            val prodResponse: ProductsResponse? =  call.body()
            requireActivity().runOnUiThread {
                if(call.isSuccessful)
                {
                    var items: ArrayList<ProductsModel> = (prodResponse?.products ?: emptyArray<ProductsResponse>()) as ArrayList<ProductsModel>
                    productsItems.clear()
                    productsItems.addAll(items)
                    binding.progressBar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setUpSearchView() {
        binding.productSearch.queryHint = "¿Qué producto estás buscando?"
        binding.productSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

    //initRecyclerView
    private fun initRecyclerView(){

        adapter = ProductsAdapter(productsItems,this)

        binding.rvProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        //binding.rvFood.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        binding.rvProduct.adapter = adapter

    }

    //retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo7733613.mockable.io/api/v1/" )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    override fun clickProductsItem(item: ProductsModel) {
        Log.i("Product" , item.title)
        val details = ProductsFragmentDetails()
        val args = Bundle()
        //sending the arguments...
        var gson = Gson()
        var jsonString = gson.toJson(item)
        args.putString("item" , jsonString)

        details.arguments = args
        binding.rvProduct.isVisible = false
        binding.materialToolbar2.isVisible = false
        binding.productSearch.isVisible = false
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(binding.FragmentContainerProducts.id, details).commit()
    }

    override fun clickServicesItem(services: ServicesModel) {

    }

    override fun clickFoodItem(food: FoodModel) {

    }


}
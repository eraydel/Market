package com.dev.eraydel.market.view.ui.fragments.food

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.MenuAdapter
import com.dev.eraydel.market.databinding.FragmentFoodDetailsBinding
import com.dev.eraydel.market.model.FoodModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class FoodFragmentDetails : Fragment(){

    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var title: String
    private lateinit var owner: String
    private lateinit var telephone: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailsBinding.inflate(inflater,container,false)

        // reading the arguments...
        val item = arguments?.getString("item")
        item?.let {
            var gson = Gson()
            var element = gson.fromJson(item, FoodModel::class.java)
            binding.title.text = element.title.toString()
            binding.itemTitle.text = element.owner.toString()
            Picasso.get().load(element.image).into(binding.ivItemImage)
            binding.itemDescription.text = element.description
            binding.itemSchedule.text = element.schedule
            val menuAdapter = MenuAdapter(requireContext(),element.menu)
            binding.lvMenu.adapter = menuAdapter
            //detail item
            binding.lvMenu.setOnItemClickListener { adapterView, view, i, l ->

                showDetail(element , i)
            }
            this.title = element.title.toString()
            this.owner = element.owner.toString()
            this.telephone = element.telephone.toString()

            //business card
            binding.btnInfo.setOnClickListener {
                showBusinessCard(element)
            }

        }

        binding.btnBack.setOnClickListener(){
            Log.i("btnBack" , "btn back")
            val food = FoodFragment()
            binding.materialToolbar.isVisible = false
            binding.scroll.isVisible = false
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(binding.DetailsFragment.id, food).commit()
        }

        //share button
        binding.btnShare.setOnClickListener(){
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hola, te recomiendo " + this.title + " " + this.owner + " " + this.telephone)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }



        return binding.root
    }

    //business card
    private fun showBusinessCard(e: FoodModel){
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.business_card, null)

        view.findViewById<TextView>(R.id.tvTitle).text = e.title
        view.findViewById<TextView>(R.id.tvTitleName).text = e.owner
        Picasso.get().load(e.image).into(view.findViewById<ImageView>(R.id.ivFoodImage))

        view.findViewById<Button>(R.id.btnCall).setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + e.telephone)
            startActivity(dialIntent)
        }

        view.findViewById<Button>(R.id.btnMessage).setOnClickListener{
            sendWhatsApp(requireContext(),"+52" + e.telephone , e.owner)
        }

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

    //share option
    private fun showDetail(e: FoodModel, p0: Int){

        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.activity_bottom_sheet, null)

        view.findViewById<TextView>(R.id.itemTitle).text = e.menu[p0].title + " $" + e.menu[p0].price
        view.findViewById<TextView>(R.id.itemDescription).text = e.menu[p0].description
        Picasso.get().load(e.menu[p0].image).into(view.findViewById<ImageView>(R.id.ivItemImage))
        view.findViewById<Button>(R.id.btnContactSeller).setOnClickListener{
            sendWhatsApp(requireContext(),"+52" + e.telephone,e.menu[p0].title + " " + e.menu[p0].description)
        }

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

    private fun sendWhatsApp(context: Context, mobileNumber: String , name: String ) {
        val url = "https://api.whatsapp.com/send?phone=${mobileNumber}&text=Hola estoy interesado en ${name} "

        val intent = Intent(Intent.ACTION_VIEW).apply {
            this.data = Uri.parse(url)
            this.`package` = "com.whatsapp"
        }

        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Debe tener instalado whatsapp", Toast.LENGTH_SHORT).show()
        }
    }
}
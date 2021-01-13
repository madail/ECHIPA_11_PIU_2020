package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.VisualAid.VisualAidActivity
import com.example.ustoyou.babysitting.BabySittingServiceActivity
import com.example.ustoyou.babysitting.BabysittingServiceFormActivity
import com.example.ustoyou.delivery.DeliveryServiceFormActivity
import com.example.ustoyou.delivery.DeliveryServicesActivity
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.teaching.TeachingServiceActivity
import com.google.android.material.navigation.NavigationView

class CategoryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    var selectedPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val adding = intent.getBooleanExtra("adding",false)
        if(adding)
            supportActionBar?.title = "Create a new service"
        else supportActionBar?.title = "Categories"

        val categories = resources.getStringArray(R.array.category_list)

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter =
                ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories)
            spinner.adapter = adapter
        }

        spinner.prompt = "Choose a category"

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        setNavigationViewListener()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun launchNextActivity(view: View) {
        val adding = intent.getBooleanExtra("adding",false)

        if(!adding) {
            if (selectedPosition == 0) {
               Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 1) {
                val intent = Intent(this, BabySittingServiceActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 2) {
                val intent = Intent(this, DeliveryServicesActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 3) {
                val intent = Intent(this, TeachingServiceActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 4) {
                val intent = Intent(this, ITProvidersList::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 5) {
                val intent = Intent(this, HouseholdActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 6) {
                val intent = Intent(this, VisualAidActivity::class.java)
                startActivity(intent)
            }
        }else{
            if (selectedPosition == 0) {
                val intent = Intent(this, TherapyServiceForm1Activity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 1) {
                val intent = Intent(this, BabysittingServiceFormActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 2) {
                val intent = Intent(this, DeliveryServiceFormActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 3) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 4) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 5) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 6) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_services -> {
                val intent = Intent(this, MyServicesActivity::class.java)
                startActivity(intent)
            }
            R.id.my_profile -> {
                val intent = Intent(this, ProfilePageActivity::class.java)
                startActivity(intent)
            }
            R.id.categories -> {
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.payment_details -> {
                val intent = Intent(this, PaymentDetailsActivity::class.java)
                startActivity(intent)
            }
            R.id.my_orders -> {
                val intent = Intent(this, MyOrdersActivity::class.java)
                startActivity(intent)
            }
            R.id.settings -> {
                Toast.makeText(this, "Settings coming soon", Toast.LENGTH_LONG).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
    }
}
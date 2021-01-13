package com.example.ustoyou.delivery

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.*
import com.example.ustoyou.model.DeliveryServices
import com.example.ustoyou.model.GenericService
import com.example.ustoyou.model.Service
import com.example.ustoyou.model.ServicesListSingleton
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.google.android.material.navigation.NavigationView


class DeliveryServiceFormActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_service_form)
        supportActionBar?.title = "New delivery service"

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

    fun validate(name: EditText, city: EditText, price: EditText, description: EditText): Boolean {
        if (name.text.toString() == "") {
            name.error = "Title missing!"
            return false
        }
        if (price.text.toString() == "") {
            price.error = "Price missing!"
            return false
        }
        if (city.text.toString() == "") {
            city.error = "City missing!"
            return false
        }
        if (description.text.toString() == "") {
            description.error = "Description missing!"
            return false
        }
        return true;
    }

    fun continueToConfirmation(view: View) {
        val name = findViewById<EditText>(R.id.deliveryServiceTitleEditText)
        val city = findViewById<EditText>(R.id.deliveryServiceCityEditText)
        val price = findViewById<EditText>(R.id.deliveryServicePriceEditText)
        val description = findViewById<EditText>(R.id.deliveryServiceDescriptionEditText)

        val isValid = validate(name, city, price, description)
        val category = "Delivery"
        if (isValid) {
            val service = Service(name.text.toString(), category, R.drawable.delivery_service_1)
            ServicesListSingleton.services.add(service)

            DeliveryServices.addService(
                GenericService(
                    R.drawable.delivery_service_2,
                    name.text.toString(),
                    description.text.toString(),
                    price.text.toString().toInt()
                )
            )

            val intent = Intent(this, UploadPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radioDrone ->
                    if (checked) {

                    }
                R.id.radioCar ->
                    if (checked) {
                    }
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
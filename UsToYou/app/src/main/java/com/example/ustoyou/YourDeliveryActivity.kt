package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.DeliveryAdapter
import com.example.ustoyou.model.*
import com.google.android.material.navigation.NavigationView

class YourDeliveryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_delivery)
        supportActionBar?.title = "Your Order"

        val type = intent.getIntExtra("typeOfDelivery", -1)

        DeliveryObjects.getObjects(type)

        val recyclerView: RecyclerView = findViewById(R.id.rv_pizzaDetailsDelivery)
        val pizzaDeliveryAdapter = DeliveryAdapter(
            DeliveryObjects.objects,
            this
        )
        recyclerView.adapter = pizzaDeliveryAdapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager

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

    fun payOrder(view: View) {
        val name: EditText = findViewById(R.id.pizzaDeliveryConfirmationNameEditText)
        val phone: EditText = findViewById(R.id.pizzaDeliveryConfirmationPhoneEditText)
        val address: EditText = findViewById(R.id.pizzaDeliveryConfirmationAddressEditText)
        val price: TextView = findViewById(R.id.pizzaDeliveryConfirmationTotal)
        val currentPrice = price.text.toString().split(" ").toTypedArray()[1].dropLast(1)

        val isValid = validateDetails(name, phone, address)

        if (isValid) {

            val intent1 = Intent(this, PaymentMethodActivity::class.java)
            intent1.putExtra("pizza", "pizza")
            intent1.putExtra("image", intent.getIntExtra("image", -1))
            intent1.putExtra("name", intent.getStringExtra("name"))
            intent1.putExtra("deliveryOrderName",name.text.toString())
            intent1.putExtra("deliveryOrderPhone",phone.text.toString())
            intent1.putExtra("deliveryOrderAddress",address.text.toString())
            intent1.putExtra( "typeOfDelivery",intent.getIntExtra("typeOfDelivery", -1))
            intent1.putExtra("total",currentPrice)
            startActivity(intent1)
        }

    }

    private fun validateDetails(
        name: EditText,
        phone: EditText,
        address: EditText
    ): Boolean {
        if (name.text.toString().isEmpty()) {
            name.error = "Name required!"
            return false
        }

        if (phone.text.toString().isEmpty()) {
            phone.error = "Phone required!"
            return false
        } else {
            if (phone.text.toString().length != 10) {
                phone.error = "Phone should contains 10 figure!"
                return false
            }
        }

        if (address.text.toString().isEmpty()) {
            address.error = "Address required!"
            return false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.my_services) {
            val intent = Intent(this, TeachingServiceActivity::class.java)
            startActivity(intent)

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
    }
}
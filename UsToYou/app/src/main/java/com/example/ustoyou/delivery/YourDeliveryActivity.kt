package com.example.ustoyou.delivery

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.*
import com.example.ustoyou.adapters.DeliveryAdapter
import com.example.ustoyou.model.*
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.payment.PaymentMethodActivity
import com.google.android.material.navigation.NavigationView

class YourDeliveryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_delivery)
        supportActionBar?.title = "Your Order"

        val type = intent.getIntExtra("typeOfDelivery", -1)

        val name: EditText = findViewById(R.id.pizzaDeliveryConfirmationNameEditText)
        val phone: EditText = findViewById(R.id.pizzaDeliveryConfirmationPhoneEditText)
        val address: EditText = findViewById(R.id.pizzaDeliveryConfirmationAddressEditText)

        if (intent.getBooleanExtra("payBack", false)) {
            val order = intent.getSerializableExtra("deliveryOrder") as BabysittingOrder

            name.setText(order.name)
            phone.setText(order.phone)
            address.setText(order.address)
            var total = 0
            for(obj in DeliveryObjects.objects){
                if(obj.isChosen){
                    total += obj.price
                }
            }

            val price: TextView = findViewById(R.id.pizzaDeliveryConfirmationTotal)
            price.text = "Total: $total$"
        } else {
            DeliveryObjects.getObjects(type)
        }

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

            val babysittingOrder = BabysittingOrder(
                name.text.toString(),
                phone.text.toString(),
                address.text.toString(), "", ""
            )

            intent1.putExtra("deliveryOrder", babysittingOrder)
            intent1.putExtra("card",intent.getStringExtra("card"))
            intent1.putExtra("pizza", "pizza")
            intent1.putExtra("image", intent.getIntExtra("image", -1))
            intent1.putExtra("name", intent.getStringExtra("name"))
            intent1.putExtra("deliveryOrderName", name.text.toString())
            intent1.putExtra("deliveryOrderPhone", phone.text.toString())
            intent1.putExtra("deliveryOrderAddress", address.text.toString())
            intent1.putExtra("typeOfDelivery", intent.getIntExtra("typeOfDelivery", -1))
            intent1.putExtra("total", currentPrice)
            intent1.putExtra("activity", "delivery")
            startActivity(intent1)
            finish()
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
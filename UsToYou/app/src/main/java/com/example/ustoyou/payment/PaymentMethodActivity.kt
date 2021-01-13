package com.example.ustoyou.payment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.*
import com.example.ustoyou.babysitting.YourOrderBabysittingConfirmation
import com.example.ustoyou.delivery.YourDeliveryConfirmation
import com.example.ustoyou.model.CurrentPrice
import com.example.ustoyou.model.User
import com.example.ustoyou.teaching.YourTeachingServiceConfirmation
import com.google.android.material.navigation.NavigationView

class PaymentMethodActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var activityType: String
    lateinit var spinner: Spinner
    private val currency = arrayOf<String>("RON", "GBP", "EUR", "USD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        spinner = findViewById(R.id.payment_method_spinner)

        activityType = intent.getStringExtra("activity").toString()

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, currency
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = currencyAdapter

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

        val price: EditText = findViewById(R.id.payment_method_textAmount)
        price.setText(CurrentPrice.price.toString())

    }

    fun confirm(view: View) {
        val teachingServiceOrderDetails = intent.getSerializableExtra("teachingOrder")
        val babysittingOrder = intent.getSerializableExtra("babySittingOrder")
        val image: Int = intent.getIntExtra("image", -1)
        val deliveryOrderName = intent.getStringExtra("deliveryOrderName")
        val deliveryOrderPhone = intent.getStringExtra("deliveryOrderPhone")
        val deliveryOrderAddress = intent.getStringExtra("deliveryOrderAddress")

        val radioGroup: RadioGroup = findViewById(R.id.payMethodRadioButtons)
        val radioButtonSelected = resources.getResourceEntryName(radioGroup.checkedRadioButtonId)
        Log.d("PAYMENT", radioButtonSelected)

        var intent1 = Intent()

        when(activityType){
            "teaching" ->{
                intent1 = Intent(this, YourTeachingServiceConfirmation::class.java)
                intent1.putExtra("teachingOrder", teachingServiceOrderDetails)
                intent1.putExtra("image", image)
                intent1.putExtra("card", intent.getStringExtra("card"))
                intent1.putExtra("name", intent.getStringExtra("name"))
                finish()
            }
            "babysitting"->{
                intent1 = Intent(this, YourOrderBabysittingConfirmation::class.java)
                intent1.putExtra("babySittingOrder", babysittingOrder)
                intent1.putExtra("image", image)
                intent1.putExtra("card", intent.getStringExtra("card"))
                intent1.putExtra("name", intent.getStringExtra("name"))
                finish()
            }
            "delivery" ->{
                intent1 = Intent(this, YourDeliveryConfirmation::class.java)
                intent1.putExtra("deliveryOrderName",deliveryOrderName)
                intent1.putExtra("deliveryOrderPhone",deliveryOrderPhone)
                intent1.putExtra("deliveryOrderAddress",deliveryOrderAddress)
                intent1.putExtra("deliveryOrder",intent.getSerializableExtra("deliveryOrder"))
                intent1.putExtra("name",intent.getStringExtra("name"))
                intent1.putExtra( "typeOfDelivery",intent.getIntExtra("typeOfDelivery", -1))
                intent1.putExtra("total",intent.getStringExtra("total"))
                intent1.putExtra("confirmation",true)
                intent1.putExtra("image", image)
                intent1.putExtra("card", intent.getStringExtra("card"))
                finish()
            }
        }

        if (radioButtonSelected == "payment_method_cash") {
            intent1.putExtra("cash", true)
            User.currentUser?.cash = true
        }

        startActivity(intent1)
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
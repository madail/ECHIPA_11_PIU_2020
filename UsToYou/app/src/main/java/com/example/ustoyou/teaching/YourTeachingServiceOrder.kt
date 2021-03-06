package com.example.ustoyou.teaching

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.*
import com.example.ustoyou.model.TeachingServiceOrderDetails
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.payment.PaymentMethodActivity
import com.google.android.material.navigation.NavigationView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class YourTeachingServiceOrder : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    var types = arrayOf<String>("Online", "Onsite")
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_teaching_service_order)
        supportActionBar?.title = "Your Order"

        spinner = findViewById(R.id.yourTeachingOrderTypeSpinner)

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, types
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = currencyAdapter

        val nameEditText: EditText = findViewById(R.id.yourTeachingOrderNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourTeachingOrderPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.yourTeachingOrderAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourTeachingOrderDateEditText)

        if (intent.getBooleanExtra("payBack", false)) {
            val order = intent.getSerializableExtra("teachingOrder") as TeachingServiceOrderDetails

            nameEditText.setText(order.name)
            phoneEditText.setText(order.phone)
            addressEditText.setText(order.address)
            dateEditText.setText(order.date)
            if (order.type == "Online") {
                spinner.setSelection(0)
            } else {
                spinner.setSelection(1)
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

    fun payOrder(view: View) {
        val nameEditText: EditText = findViewById(R.id.yourTeachingOrderNameEditText)
        val phoneEditText: EditText = findViewById(R.id.yourTeachingOrderPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.yourTeachingOrderAddressEditText)
        val dateEditText: EditText = findViewById(R.id.yourTeachingOrderDateEditText)
        val image: Int = intent.getIntExtra("image",-1)

        val isValid = validateDetails(
            nameEditText,
            phoneEditText,
            addressEditText,
            dateEditText
        )

        if (isValid) {

            val intent1 = Intent(this, PaymentMethodActivity::class.java)

            val teachingServiceOrder = TeachingServiceOrderDetails(
                nameEditText.text.toString(),
                phoneEditText.text.toString(),
                spinner.selectedItem.toString(),
                addressEditText.text.toString(),
                dateEditText.text.toString()
            )

            intent1.putExtra("teachingOrder", teachingServiceOrder)
            intent1.putExtra("image",image)
            intent1.putExtra("activity","teaching")
            intent1.putExtra("image", intent.getIntExtra("image", -1))
            intent1.putExtra("name", intent.getStringExtra("name"))
            intent1.putExtra("card", intent.getStringExtra("card"))

            startActivity(intent1)
        }
    }

    private fun validateDetails(
        name: EditText,
        phone: EditText,
        address: EditText,
        date: EditText
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

        if (date.text.toString().isEmpty()) {
            date.error = "Date required!"
            return false
        } else {
            try {
                val formatter = SimpleDateFormat("dd/MM/yyyy HH:MM", Locale.getDefault())
                val myDate = formatter.parse(date.text.toString())
                val current = Date()
                if (!current.before(myDate)) {
                    date.error = "The date should be in the future!"
                    return false
                }
            } catch (ex: ParseException) {
                date.error = "Format should be DD/MM/YYYY HH:MM"
                return false
            }
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
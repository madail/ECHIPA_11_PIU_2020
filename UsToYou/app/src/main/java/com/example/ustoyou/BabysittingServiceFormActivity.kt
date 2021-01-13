package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.model.Service
import com.example.ustoyou.model.ServicesListSingleton
import com.google.android.material.navigation.NavigationView

class BabysittingServiceFormActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_babysitting_service_form)

        supportActionBar?.title = "New babysitting service"

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

    fun validate(name: EditText, city: EditText, age:EditText, price: EditText,) : Boolean {
        if(name.text.toString() == "") {
            name.error = "Title missing!"
            return false
        }
        if(price.text.toString() == "") {
            price.error = "Price missing!"
            return false
        }
        if(age.text.toString() == "") {
            age.error = "Age missing!"
            return false;
        }
        if(city.text.toString() == "") {
            city.error = "City missing!"
            return false
        }
        return true;
    }

    fun continueToNextActivity(view: View) {
        val name = findViewById<EditText>(R.id.babysittingServiceTitleEditText)
        val city = findViewById<EditText>(R.id.babysittingServiceCityEditText)
        val age = findViewById<EditText>(R.id.babysittingServiceAgeEditText)
        val price = findViewById<EditText>(R.id.babysittingServicePriceEditText)
        val category = "Babysitting";
        val isValid = validate(name, city, age, price)
        if(isValid) {
            val service = Service(name.text.toString(), category, R.drawable.babysitting_service_1)
            ServicesListSingleton.services.add(service)
            val intent = Intent(this, UploadFileActivity::class.java)
            startActivity(intent)
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
package com.example.ustoyou.payment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.*
import com.google.android.material.navigation.NavigationView

class PaymentDetailsActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

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

    fun confirm(view: View) {
        val cardNumberEditText: EditText = findViewById(R.id.payment_details_cardDetailsEditText)
        val expDateEditText: EditText = findViewById(R.id.payment_details_expirationEditText)
        val cvcEditText: EditText = findViewById(R.id.payment_details_cvc2EditText)

        val isValid = validate(cardNumberEditText, expDateEditText, cvcEditText)

        if(isValid) {
            val intent = Intent().apply {
                putExtra("cardNumber", cardNumberEditText.text.toString())
                putExtra("expirationDate", expDateEditText.text.toString())
                putExtra("cvc",cvcEditText.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun validate(cardNumber: EditText, expDate: EditText, cvvEditText: EditText): Boolean{
        if(cardNumber.text.toString().isEmpty()){
            cardNumber.error = "Card Number is required!"
            return false
        }

        if(expDate.text.toString().isEmpty()){
            expDate.error = "Card Number is required!"
            return false
        }

        if(cvvEditText.text.toString().isEmpty()){
            cvvEditText.error = "Card Number is required!"
            return false
        }

        return true
    }
}
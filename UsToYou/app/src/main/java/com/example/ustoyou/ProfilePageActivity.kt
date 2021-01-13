package com.example.ustoyou

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
import com.example.ustoyou.model.User
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.google.android.material.navigation.NavigationView

class ProfilePageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var phoneNameEditText: EditText
    private lateinit var emailNameEditText: EditText
    private lateinit var birthdayNameEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        supportActionBar?.title = "My profile"

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

        firstNameEditText = findViewById(R.id.profileFirstName)
        lastNameEditText = findViewById(R.id.profileLastName)
        phoneNameEditText = findViewById(R.id.profilePhoneNumber)
        birthdayNameEditText = findViewById(R.id.profileBirthday)
        emailNameEditText = findViewById(R.id.profileEmail)

        firstNameEditText.setText(User.currentUser?.firstName)
        lastNameEditText.setText(User.currentUser?.lastName)
        phoneNameEditText.setText(User.currentUser?.phoneNumber)
        birthdayNameEditText.setText(User.currentUser?.birthday)
        emailNameEditText.setText(User.currentUser?.email)
    }

    fun save(view: View) {
        User.setUser(
            User(
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                phoneNameEditText.text.toString(),
                emailNameEditText.text.toString(),
                birthdayNameEditText.text.toString()
            )
        )
        Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show()
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
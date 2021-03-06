package com.example.ustoyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.adapters.MyServicesAdapter
import com.example.ustoyou.model.MyServices
import com.example.ustoyou.model.ServicesListSingleton
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.google.android.material.navigation.NavigationView

class MyServicesActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var listReference : ListView
    var servicesAdapter : MyServicesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_services)

        supportActionBar?.title = "My services"

        listReference = findViewById(R.id.servicesListView)

        servicesAdapter = MyServicesAdapter(this, MyServices().getServices())
        listReference.adapter = servicesAdapter

        if(ServicesListSingleton.services.size != 0) {
            val list = findViewById<ListView>(R.id.servicesListView)
            list.visibility = View.VISIBLE
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

    fun addService(view: View){
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("adding",true)
        startActivity(intent)
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
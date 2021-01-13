package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.babysitting.YourOrderBabysittingActivity
import com.example.ustoyou.delivery.YourDeliveryActivity
import com.example.ustoyou.model.GenericService
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.teaching.YourTeachingServiceOrder
import com.google.android.material.navigation.NavigationView

class ServiceDetails : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        val teachingService =
            intent.getSerializableExtra("selectedTeachingService") as GenericService

        supportActionBar?.title = teachingService.titleSubject

        val imageView: ImageView = findViewById(R.id.teachingServiceImage)
        imageView.setImageResource(teachingService.imageRes)

        val descriptionText: TextView = findViewById(R.id.descriptionTeachingService)
        descriptionText.text = teachingService.description

        val priceText: TextView = findViewById(R.id.priceValueTeachingService)

        if (intent.getStringExtra("type") == "delivery") {
            val price: TextView = findViewById(R.id.priceTeachingService)
            priceText.visibility = View.GONE
            price.visibility = View.GONE
        } else {
            priceText.text = "${teachingService.price}$"
        }

        val titleText: TextView = findViewById(R.id.titleTeachingSubject)
        titleText.text = teachingService.titleSubject

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

    fun orderNow(view: View) {
        val type = intent.getStringExtra("type")
        val typeDelivery = intent.getIntExtra("typeOfDelivery", -1)

        val teachingService =
            intent.getSerializableExtra("selectedTeachingService") as GenericService
        if (type == "babysitting") {
            val intent = Intent(this, YourOrderBabysittingActivity::class.java)
            intent.putExtra("image", teachingService.imageRes)
            intent.putExtra("name", teachingService.titleSubject)
            startActivity(intent)
        } else if (type == "delivery") {
            val intent = Intent(this, YourDeliveryActivity::class.java)
            intent.putExtra("image", teachingService.imageRes)
            intent.putExtra("name", teachingService.titleSubject)
            intent.putExtra("typeOfDelivery", typeDelivery)
            startActivity(intent)
        } else {
            val intent = Intent(this, YourTeachingServiceOrder::class.java)
            intent.putExtra("image", teachingService.imageRes)
            intent.putExtra("name", teachingService.titleSubject)
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
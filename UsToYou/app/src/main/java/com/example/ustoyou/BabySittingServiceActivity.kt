package com.example.ustoyou

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ustoyou.adapters.ServiceAdapter
import com.example.ustoyou.model.BabysittingServices
import com.google.android.material.navigation.NavigationView

class BabySittingServiceActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_sitting_service)

        val recyclerView: RecyclerView = findViewById(R.id.rvBabySittingServices)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = ServiceAdapter(
            BabysittingServices().getBabysittingServices(),
            this,
            "babysitting"
        )
        supportActionBar?.title = "Babysitting Services"

        recyclerView.adapter = adapter

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
        if (item.itemId == R.id.my_services) {
            val intent = Intent(this, DeliveryServicesActivity::class.java)
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
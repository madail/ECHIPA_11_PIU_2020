package com.example.ustoyou.ScheduleTherapySession

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ustoyou.*
import com.example.ustoyou.VisualAid.VisualAidActivity
import com.example.ustoyou.babysitting.BabySittingServiceActivity
import com.example.ustoyou.babysitting.BabysittingServiceFormActivity
import com.example.ustoyou.delivery.DeliveryServiceFormActivity
import com.example.ustoyou.delivery.DeliveryServicesActivity
import com.example.ustoyou.model.BabysittingOrder
import com.example.ustoyou.model.TeachingServiceOrderDetails
import com.example.ustoyou.model.TherapyOrder
import com.example.ustoyou.payment.PaymentDetailsActivity
import com.example.ustoyou.payment.PaymentMethodActivity
import com.example.ustoyou.teaching.TeachingServiceActivity
import com.google.android.material.navigation.NavigationView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ScheduleTherapyDetailsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var scheduleTherapyDrawerLayout: DrawerLayout

    private lateinit var scheduleTherapyUserName: EditText
    private lateinit var scheduleTherapyUserPhone: EditText
    private lateinit var scheduleTherapyUserAddress: EditText
    private lateinit var scheduleTherapyTypeDropdown: Spinner
    private lateinit var scheduleTherapyDate: EditText

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    var types = arrayOf<String>("Online", "Onsite")

    var selectedPosition: Int = 0

    var sessionType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_therapy_details)

        supportActionBar?.title = "Your Order"

        bindItems()

        scheduleTherapyTypeDropdown.prompt = "Choose a category"

        scheduleTherapyTypeDropdown.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val currencyAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, types
        )
        currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        scheduleTherapyTypeDropdown.adapter = currencyAdapter

        scheduleTherapyDrawerLayout = findViewById(R.id.scheduleTherapyDrawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            scheduleTherapyDrawerLayout,
            R.string.open,
            R.string.close
        )
        scheduleTherapyDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        setNavigationViewListener()

        if (intent.getBooleanExtra("payBack", false)) {
            val order = intent.getSerializableExtra("teachingOrder") as TeachingServiceOrderDetails

            sessionType = order.type
            scheduleTherapyUserName.setText(order.name)
            scheduleTherapyUserPhone.setText(order.phone)
            scheduleTherapyUserAddress.setText(order.address)
            scheduleTherapyDate.setText(order.date)
            if (order.type == "Online") {
                scheduleTherapyTypeDropdown.setSelection(0)
            } else {
                scheduleTherapyTypeDropdown.setSelection(1)
            }
        }
    }

    private fun bindItems() {
        scheduleTherapyDrawerLayout = findViewById(R.id.scheduleTherapyDrawerLayout)

        scheduleTherapyUserName = findViewById(R.id.scheduleTherapyUserName)
        scheduleTherapyUserPhone = findViewById(R.id.scheduleTherapyUserPhone)
        scheduleTherapyUserAddress = findViewById(R.id.scheduleTherapyUserAddress)
        scheduleTherapyDate = findViewById(R.id.scheduleTherapyDate)

        scheduleTherapyTypeDropdown = findViewById(R.id.scheduleTherapyTypeDropdown)
    }

    fun payOrder(view: View) {
        val isValid = validateDetails(
            scheduleTherapyUserName,
            scheduleTherapyUserPhone,
            scheduleTherapyUserAddress,
            scheduleTherapyDate
        )

        if (isValid) {

            if (scheduleTherapyTypeDropdown.selectedItemPosition == 0 ) {
                sessionType = "Online"
            } else {
                sessionType = "Onsite"
            }
            val intent1 = Intent(this, PaymentMethodActivity::class.java)
            val therapyOrder = TherapyOrder(
                scheduleTherapyUserName.text.toString(),
                scheduleTherapyUserPhone.text.toString(),
                scheduleTherapyUserAddress.text.toString(),
                sessionType,
                scheduleTherapyDate.text.toString(),
                intent.getStringExtra("name").toString()
            )

            intent1.putExtra("therapyOrder", therapyOrder)
            intent1.putExtra("activity","therapy")
            intent1.putExtra("image", intent.getIntExtra("image", -1))
            intent1.putExtra("name", intent.getStringExtra("name"))
            intent1.putExtra("card", intent.getStringExtra("card"))
            startActivity(intent1)
            finish()
        }
    }

    fun launchNextActivity(view: View) {
        val adding = intent.getBooleanExtra("adding",false)

        if(!adding) {
            if (selectedPosition == 0) {
                val intent = Intent(this, ScheduleTherapyDetailsActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 1) {
                val intent = Intent(this, BabySittingServiceActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 2) {
                val intent = Intent(this, DeliveryServicesActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 3) {
                val intent = Intent(this, TeachingServiceActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 4) {
                val intent = Intent(this, ITProvidersList::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 5) {
                val intent = Intent(this, HouseholdActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 6) {
                val intent = Intent(this, VisualAidActivity::class.java)
                startActivity(intent)
            }
        }else{
            if (selectedPosition == 0) {
                val intent = Intent(this, TherapyServiceForm1Activity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 1) {
                val intent = Intent(this, BabysittingServiceFormActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 2) {
                val intent = Intent(this, DeliveryServiceFormActivity::class.java)
                startActivity(intent)
            }
            if (selectedPosition == 3) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 4) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 5) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
            if (selectedPosition == 6) {
                Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show()
            }
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

        scheduleTherapyDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
    }
}
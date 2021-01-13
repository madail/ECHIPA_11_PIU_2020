package com.example.ustoyou.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ustoyou.CategoryActivity
import com.example.ustoyou.R
import com.example.ustoyou.model.User

class LoginActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.usernameEditText)
        password = findViewById(R.id.passwordEditText)

    }

    fun signUpGoogle(view: View) {
        User.setUser(User("Google", "User", "1234567890", "googleUser@gmail.com", "09/10/1999",cash = true))
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun createAccount(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun signIn(view: View) {
        if (validate(username, password)) {
            if (username.text.toString() == "poor_user" && password.text.toString() == "12345") {
                User.setUser(User("Poor", "User", "1234567890", "poor_user", "09/10/1999"))
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
                finish()
            } else if (username.text.toString() == "rich_user" && password.text.toString() == "12345") {
                User.setUser(User("Rich", "User", "1234567890", "rich_user", "09/10/1999",cash = true))
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "User or password is incorrect!", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun validate(username: EditText, password: EditText): Boolean {
        if (username.text.toString().isEmpty()) {
            username.error = "Username required!"
            return false
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Username required!"
            return false
        }

        return true
    }
}
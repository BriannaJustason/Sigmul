package com.example.sigmul3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.delasign.samplestarterproject.utils.data.com.example.sigmul3.DBHelper


class RegisterActivity : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var repassword: EditText? = null
    var signup: Button? = null
    var signin: Button? = null
    var DB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        username = findViewById<View>(R.id.username) as EditText
        password = findViewById<View>(R.id.password) as EditText
        repassword = findViewById<View>(R.id.repassword) as EditText
        signup = findViewById<View>(R.id.btnsignup) as Button
        signin = findViewById<View>(R.id.btnsignin) as Button
        DB = DBHelper(this)
        signup!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            val repass = repassword!!.text.toString()
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@RegisterActivity,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass) {
                    val checkuser = DB!!.checkusername(user)
                    if (checkuser == false) {
                        val insert = DB!!.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registration failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "User already exists! please sign in",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Passwords not matching", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        signin!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.sigmul3


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.delasign.samplestarterproject.utils.data.com.example.sigmul3.DBHelper


class LoginActivity : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var btnlogin: Button? = null
    var DB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        username = findViewById<View>(R.id.username1) as EditText
        password = findViewById<View>(R.id.password1) as EditText
        btnlogin = findViewById<View>(R.id.btnsignin1) as Button
        DB = DBHelper(this)
        btnlogin!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            if (user == "" || pass == "") Toast.makeText(
                this@LoginActivity,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkuserpass = DB!!.checkusernamepassword(user, pass)
                if (checkuserpass == true) {
                    Toast.makeText(this@LoginActivity, "Sign in successfull", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
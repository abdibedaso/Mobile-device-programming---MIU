package com.miu.cvbuilderapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.miu.cvbuilderapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.login.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var spf: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        spf = getSharedPreferences("login", Context.MODE_PRIVATE)
        val name = spf.getString("email","")
        val pwd = spf.getString("pass","")
        etEmail.setText(name)
        etPassword.setText(pwd)
        //
        tvReq.text = ""
        btnSignIn.setOnClickListener{
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            if(email.equals("") || password.equals("")){
                tvReq.text = "*Required field(s)."
            }else{
                //save the credential
                val spf = getSharedPreferences("login", Context.MODE_PRIVATE)
                val spe = spf.edit()
                spe.putString("email", etEmail.text.toString())
                spe.putString("pass", etPassword.text.toString())
                spe.apply()
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,UserActivity::class.java)
                intent.putExtra("username",email)
                startActivity(intent)

            }

        }
    }
    override fun onBackPressed() {
        tvReq.text = ""
    }



}
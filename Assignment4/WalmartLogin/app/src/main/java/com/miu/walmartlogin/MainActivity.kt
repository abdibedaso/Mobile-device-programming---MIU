package com.miu.walmartlogin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var userArr = arrayListOf(
        User("Abdi Tufa","Beadso","abdi@gmail.com","12345")
        ,User("Beka Tufa","Bedaso","segni@gmail.com","12345")
        ,User("Segni Tufa","Bedaso","segni@gmail.com","12345")
        ,User("Hamziya Tufa","Bedaso","hamziya@hotmail.com","12345")
        ,User("Amina","Gamada","amina@yahoo.com","12345"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        btnSignIn.setOnClickListener{
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            for(u in userArr){
                if(u.username == email && u.password == password){
                    //Toast.makeText(this,email.toString(),Toast.LENGTH_LONG).show()
                    //pass the user name to
                    val intent = Intent(this,ShoppingCategory::class.java)
                    intent.putExtra("username",email)
                    startActivity(intent)
                    break
                }else{
                    Toast.makeText(this,"Unable to login. Please try again!",Toast.LENGTH_LONG).show()
                }
            }

        }
        //Task5
        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //callback to retrieve the result
            //result object contains the intent and RESULT_OK or RESULT_CANCEL
                result -> //what will do after getting result back
            if(result.resultCode == Activity.RESULT_OK)
                //userArr.add(result.data?.data as User)
                //tv.text = result.data?.data.toString()//data means getting the intent
                //val hi = result.data?.data.toString()
                //println("========= Reg Act ==========: $result.data?.data.toString()")
                Log.i("Reg Act:",result.data?.data.toString())
            else
                Log.i("Reg Act:","Failed to get Result")
        }

        btnCreate.setOnClickListener{
            val intent = Intent(this,CreateAccount::class.java)
            resultContracts.launch(intent)
        }
    }

}
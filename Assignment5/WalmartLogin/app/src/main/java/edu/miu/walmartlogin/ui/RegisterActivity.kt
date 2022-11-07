package edu.miu.walmartlogin.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.walmartlogin.data.User
import edu.miu.walmartlogin.databinding.ActivityMainBinding
import edu.miu.walmartlogin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onCreateAccount(view: View) {
        val firstName = binding.etRegisterFirstName.text.toString().trim()
        val lastName = binding.etRegisterLastName.text.toString().trim()
        val emailAddress = binding.etRegisterEmailAddress.text.toString().trim()
        val password = binding.etRegisterPassword.text.toString().trim()
        val user = User(firstName, lastName, emailAddress, password)
        if(firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || password.isEmpty()){
            Toast.makeText(applicationContext,"Please fill all required fields", Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(applicationContext,"Account created successfully", Toast.LENGTH_LONG).show()
        clearFields()
        openMainActivity(user)
    }

    private fun clearFields(){
        binding.etRegisterFirstName.text.clear()
        binding.etRegisterLastName.text.clear()
        binding.etRegisterEmailAddress.text.clear()
        binding.etRegisterPassword.text.clear()
    }

    private fun openMainActivity(user: User){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}
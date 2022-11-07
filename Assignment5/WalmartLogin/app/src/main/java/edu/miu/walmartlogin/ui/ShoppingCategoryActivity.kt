package edu.miu.walmartlogin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.walmartlogin.data.User
import edu.miu.walmartlogin.databinding.ActivityShoppingCategoryBinding


class ShoppingCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCategoryBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: User? = intent.getSerializableExtra("user") as User?
        binding.welcomeMessage.text = "Welcome: ${user?.username}"
    }

    fun onElectronicsClick(view: View) {
        startActivity(Intent(this, ElectronicsCategoryActivity::class.java))
    }
    fun onClothesClick(view: View) {
        Toast.makeText(applicationContext,"Clothes", Toast.LENGTH_LONG).show()
    }
    fun onBeautyClick(view: View) {
        Toast.makeText(applicationContext,"Beauty", Toast.LENGTH_LONG).show()
    }
    fun onFoodClick(view: View) {
        Toast.makeText(applicationContext,"Food", Toast.LENGTH_LONG).show()
    }
}
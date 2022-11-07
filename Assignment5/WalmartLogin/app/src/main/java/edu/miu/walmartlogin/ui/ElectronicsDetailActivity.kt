package edu.miu.walmartlogin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.walmartlogin.data.Product
import edu.miu.walmartlogin.data.User
import edu.miu.walmartlogin.databinding.ActivityElectronicsDetailBinding
import edu.miu.walmartlogin.databinding.ActivityShoppingCategoryBinding


class ElectronicsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElectronicsDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElectronicsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val product: Product? = intent.getSerializableExtra("product") as Product?
        product?.image?.let { binding.detailProductImage.setBackgroundResource(it) }
        binding.detailProductTitle.text = product?.title
        binding.detailProductColor.text = "Color: ${product?.color}"
        binding.detailProductNumber.text = "Walmart #: ${product?.itemId.toString()}"
        binding.detailProductDescription.text = "Item Description:\n${product?.desc}"
    }

}
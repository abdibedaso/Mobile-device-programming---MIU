package edu.miu.walmartlogin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import edu.miu.walmartlogin.R
import edu.miu.walmartlogin.data.Product

class CategoryAdapter(val context: Context, val products: MutableList<Product>) :
    RecyclerView.Adapter<BaseViewHolder?>() {

    private var mCallback: Callback? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        val itemEvents: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_category, viewGroup, false)
        return AssignedTasksViewHolder(itemEvents)
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
        baseViewHolder.onBind(i)
    }
    
    fun setCallback(callback: Callback?) {
        mCallback = callback
    }

    override fun getItemCount(): Int = products.size

    interface Callback {
        fun onItemClicked(product: Product?)
    }

    inner class AssignedTasksViewHolder(view: View?) : BaseViewHolder(view) {
        var title: TextView? = view?.findViewById(R.id.rv_title)
        var image: ImageView? = view?.findViewById(R.id.rv_image)
        var price: TextView? = view?.findViewById(R.id.rv_price)
        var color: TextView? = view?.findViewById(R.id.rv_color)
        var itemContainer: LinearLayout? = view?.findViewById(R.id.item_container)
        @SuppressLint("NotifyDataSetChanged")
        override fun onBind(position: Int) {
            super.onBind(position)
            val product = products[position]
            
            image?.setBackgroundResource(product.image)
            title?.text = product.title
            price?.text = "Price : ${product.price}"
            color?.text = "Color : ${product.color}"

            itemContainer?.setOnClickListener {
                mCallback?.onItemClicked(product)
                notifyDataSetChanged()
            }
        }
    }
}
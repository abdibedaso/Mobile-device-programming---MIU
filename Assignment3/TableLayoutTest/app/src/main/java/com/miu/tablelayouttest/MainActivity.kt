package com.miu.tablelayouttest

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.core.view.marginBottom
import androidx.core.view.updateMargins
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import android.widget.TextView as TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener{
            var tvCode = TextView(applicationContext)
            tvCode.setBackgroundResource(R.drawable.border)
            tvCode.setTextColor(R.color.black)
            tvCode.text = etCodeName.text.toString()
            var tvVer = TextView(applicationContext)
            tvVer.setBackgroundResource(R.drawable.border)
            tvVer.setTextColor(R.color.black)
            tvVer.text = etVer.text.toString()

            var tableRow = TableRow(applicationContext)//this

            // Set new table row layout parameters.
            val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0,0,0,10)
            tableRow.setLayoutParams(layoutParams)

            // add values into row by calling addView()
            tableRow.addView(tvVer,0,layoutParams)//***required to pass layoutParams, if not margin not working
            tableRow.addView(tvCode,1,layoutParams)
            // Finally add the created row row into layout
            tbl.addView(tableRow, layoutParams)

        }
    }
}
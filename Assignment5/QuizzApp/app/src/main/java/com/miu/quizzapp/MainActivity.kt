package com.miu.quizzapp

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // when button is clicked, show the alert
        submit.setOnClickListener {

            // Get the checked radio button id from radio group
            var id = rGDrinks.checkedRadioButtonId
            if (id == R.id.rbJuice){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                score += 50
            }

            if ((!kfc.isChecked()) && (raisin.isChecked() && potato.isChecked())) {
                score += 50
            }

            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            val sdf = SimpleDateFormat("M/d/yyyy hh:mm")
            val currentDate = sdf.format(Date())

            // set message of alert dialog
            dialogBuilder.setMessage("Congratulations! You submitted on $currentDate \n You achieved $score%")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Done", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id ->
                    run {
                        dialog.cancel()
                        this.score = 0
                    }
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Quiz Result")
            // show alert dialog
            alert.show()
        }

        reset.setOnClickListener{

            rGDrinks.clearCheck()
            kfc.setChecked(false)
            raisin.setChecked(false)
            potato.setChecked(false)
            score=0
        }
    }
}
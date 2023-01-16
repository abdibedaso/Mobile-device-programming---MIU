package androidquiz.app.util.custom_dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import androidquiz.app.R

@SuppressLint("InflateParams")
fun customAlertDialog(
    context: Context,
    message: String,
    positiveButton: (() -> Unit)?,
    positiveText: String?,
    negativeButton:(()->Unit)?,
    negativeText: String?,
    cancellable: Boolean
): AlertDialog {
    val builder = AlertDialog.Builder(context)
    val view = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null)
    view.tv_dialogMessage.text = message
    builder.setCancelable(cancellable)
    builder.setView(view)
    val dialog = builder.create()
    if (positiveButton != null) {
        view.btn_positive.visibility = View.VISIBLE
        view.btn_positive.text = positiveText!!
        view.btn_positive.setOnClickListener {
            positiveButton()
            dialog.dismiss()
        }
    }
    if (negativeButton!=null) {
        view.btn_negative.visibility = View.VISIBLE
        view.btn_negative.text = negativeText!!
        view.btn_negative.setOnClickListener {
            negativeButton()
            dialog.dismiss()
        }
    }
    return dialog
}
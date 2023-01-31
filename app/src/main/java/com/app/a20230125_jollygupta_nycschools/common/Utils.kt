package com.app.a20230125_jollygupta_nycschools.common

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

object Utils {
    private  var dialog: AlertDialog? = null

    @SuppressLint("SetTextI18n")
    fun showProgressDialog(activity: Context?) {
        if (dialog != null && dialog!!.isShowing) return
        val llPadding = 30
        val ll = LinearLayout(activity)
        ll.setBackgroundColor(Color.parseColor("#FFFFFF"))
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(40, llPadding, llPadding, llPadding)
        //        ll.setGravity(Gravity.CENTER);
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam
        val progressBar = ProgressBar(activity)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam
        llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(activity)
        tvText.text = "Loading"
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 15f
        tvText.layoutParams = llParam
        ll.addView(progressBar)
        ll.addView(tvText)
        val builder = AlertDialog.Builder(activity)
        builder.setView(ll)
        builder.setCancelable(false)
        dialog = builder.create()
        val window = dialog?.window
        Log.e(TAG, "loader ", )
        if (window != null) {
            Log.e(TAG, "loader 2", )
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog?.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog?.window!!.attributes = layoutParams
        }
        dialog?.show()
    }

    fun dismissProgressDialog() {
        if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()
    }


}
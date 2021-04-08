package com.sleeve.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sleeve.util.LDisplay
import com.sleeve.util.LToast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_test0).setOnClickListener(this)
        findViewById<Button>(R.id.btn_test1).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_test0 -> {
            }
            R.id.btn_test1 -> {
            }
        }
    }
}

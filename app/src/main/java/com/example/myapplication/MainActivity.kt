package com.example.myapplication

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R.id.btn_plus

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var tvResult: TextView
    private lateinit var tvCounter: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        edtHeight = findViewById(R.id.edt_height)
        edtWidth = findViewById(R.id.edt_width)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        btnPlus = findViewById(btn_plus)
        btnMinus = findViewById(R.id.btn_minus)
        tvResult = findViewById(R.id.tv_result)
        tvCounter = findViewById(R.id.tv_counter)
        btnCalculate.setOnClickListener(this)

       btnPlus.setOnClickListener {
           val currentResult = tvCounter.text.toString().toInt() ?: 0
           val newResult = currentResult + 1
           tvCounter.text = newResult.toString()
       }

        btnMinus.setOnClickListener {
            val currentResult = tvCounter.text.toString().toInt() ?: 0
            val newResult = currentResult - 1
            tvCounter.text = newResult.toString()
        }

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            var isEmptyfield = false
            if (inputLength.isEmpty()) {
                isEmptyfield = true
                edtLength.error = getString(R.string.error_empty_field)
            }
            if (inputHeight.isEmpty()) {
                isEmptyfield = true
                edtHeight.error = getString(R.string.error_empty_field)
            }
            if (inputWidth.isEmpty()) {
                isEmptyfield = true
                edtWidth.error = getString(R.string.error_empty_field)
            }
            if (!isEmptyfield) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }


    }
}
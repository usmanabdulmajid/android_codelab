package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {
        val stringInTextField: String = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null || cost == 0.0){
            val toast: Toast = Toast.makeText(this, "Enter a valid input", Toast.LENGTH_SHORT)
            toast.show()
            displayTip(0.0)
            return
        }
        val tipPercentage: Double = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 20.0
            R.id.option_eighteen_percent -> 18.0
            else -> 15.0
        }
        var tip = cost * tipPercentage
        if(binding.roundUpSwitch.isChecked){
          tip =  kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }
    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
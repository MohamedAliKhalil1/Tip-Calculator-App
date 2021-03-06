package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipAmount.text = ""
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {

        // get the cost entered by the user
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if (cost == null) {
            binding.tipAmount.text = ""
            return
        }

        // get the tip percentage of the selected radio button
        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // calculate the tip amount
        var tip = percentage * cost

        // check if the tip amount must be rounded up or not
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        // tip formatting
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // update the view with the tip amount
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }

}
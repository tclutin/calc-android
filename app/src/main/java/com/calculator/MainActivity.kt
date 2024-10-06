package com.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var currentExpression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val display: TextView = findViewById(R.id.input_operation)
        setupListenerButtons(display)
    }

    private fun setupListenerButtons(display: TextView) {
        findViewById<Button>(R.id.button_one).setOnClickListener {
            appendToExpressionHandler("1", display)
        }

        findViewById<Button>(R.id.button_two).setOnClickListener {
            appendToExpressionHandler("2", display)
        }


        findViewById<Button>(R.id.button_three).setOnClickListener {
            appendToExpressionHandler("3", display)
        }

        findViewById<Button>(R.id.button_four).setOnClickListener {
            appendToExpressionHandler("4", display)
        }

        findViewById<Button>(R.id.button_five).setOnClickListener {
            appendToExpressionHandler("5", display)
        }

        findViewById<Button>(R.id.button_six).setOnClickListener {
            appendToExpressionHandler("6", display)
        }

        findViewById<Button>(R.id.button_seven).setOnClickListener {
            appendToExpressionHandler("7", display)
        }

        findViewById<Button>(R.id.button_eight).setOnClickListener {
            appendToExpressionHandler("8", display)
        }

        findViewById<Button>(R.id.button_nine).setOnClickListener {
            appendToExpressionHandler("9", display)
        }

        findViewById<Button>(R.id.button_zero).setOnClickListener {
            appendToExpressionHandler("0", display)
        }

        findViewById<Button>(R.id.button_dot).setOnClickListener {
            appendToExpressionHandler(".", display)
        }

        findViewById<Button>(R.id.button_division).setOnClickListener {
            appendToExpressionHandler("/", display)
        }

        findViewById<Button>(R.id.button_multiply).setOnClickListener {
            appendToExpressionHandler("*", display)
        }

        findViewById<Button>(R.id.button_plus).setOnClickListener {
            appendToExpressionHandler("+", display)
        }

        findViewById<Button>(R.id.button_minus).setOnClickListener {
            appendToExpressionHandler("-", display)
        }

        findViewById<Button>(R.id.button_ac).setOnClickListener {
            clearInputHandler(display)
        }

        findViewById<Button>(R.id.button_remove).setOnClickListener {
            removeSymbolHandler(display)
        }

        findViewById<Button>(R.id.button_result).setOnClickListener {
            getResultHandler(display)
        }
    }

    private fun appendToExpressionHandler(value: String, display: TextView) {
        currentExpression += value
        display.text = currentExpression
    }

    private fun clearInputHandler(display: TextView) {
        display.text = ""
        currentExpression = ""
    }

    private fun removeSymbolHandler(display: TextView) {
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.dropLast(1)
            display.text = currentExpression
        }
    }

    private fun getResultHandler(display: TextView) {
        try {
            val result = Calc.calculate(currentExpression)
            display.text = result
            currentExpression = result
        } catch (e: Exception) {
            display.text = "Ошибка"
            currentExpression = ""
        }
    }
}
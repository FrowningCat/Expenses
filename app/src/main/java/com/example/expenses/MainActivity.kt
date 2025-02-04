package com.example.expenses

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textAmount = findViewById(R.id.textAmount)
        textAmount.text = "0"

        // Назначаем обработчики для чисел
        setNumberClickListener(R.id.textNumber0, "0")
        setNumberClickListener(R.id.textNumber1, "1")
        setNumberClickListener(R.id.textNumber2, "2")
        setNumberClickListener(R.id.textNumber3, "3")
        setNumberClickListener(R.id.textNumber4, "4")
        setNumberClickListener(R.id.textNumber5, "5")
        setNumberClickListener(R.id.textNumber6, "6")
        setNumberClickListener(R.id.textNumber7, "7")
        setNumberClickListener(R.id.textNumber8, "8")
        setNumberClickListener(R.id.textNumber9, "9")
        setNumberClickListener(R.id.textNumberPoint, ".") // Точка

        // Назначаем обработчик для кнопки удаления
        findViewById<LinearLayout>(R.id.btnDelete).setOnClickListener {
            deleteLastCharacter()
        }
    }

    private fun setNumberClickListener(viewId: Int, value: String) {
        findViewById<TextView>(viewId).setOnClickListener {
            updateAmount(value)
        }
    }

    private fun updateAmount(value: String) {
        val currentText = textAmount.text.toString()

        if (value == ".") {
            // Запрещаем ввод второй точки, если она уже есть в числе
            if (currentText.contains(".")) return
            if (currentText.isEmpty()) textAmount.text = "0." // Если первое число — ставим "0."
            else textAmount.text = currentText + "."
        } else {
            // Если текущее значение "0", заменяем его на новое число (кроме точки)
            if (currentText == "0") textAmount.text = value
            else textAmount.text = currentText + value
        }
    }

    private fun deleteLastCharacter() {
        val currentText = textAmount.text.toString()
        textAmount.text = if (currentText.length > 1) currentText.dropLast(1) else "0"
    }
}

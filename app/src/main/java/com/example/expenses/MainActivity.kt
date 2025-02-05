package com.example.expenses

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.ImageView
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var textAmount: TextView
    private lateinit var textListCategory: TextView
    private lateinit var textList: TextView
    private lateinit var icList: ImageView
    private var isListVisible = false // Состояние видимости списка

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

        // Инициализация textListCategory, textList и icList
        textListCategory = findViewById(R.id.textListCategory)
        textList = findViewById(R.id.textList)
        icList = findViewById(R.id.icList)

        // Назначаем обработчик для ImageView
        icList.setOnClickListener {
            toggleListVisibility() // Переключаем видимость списка
        }

        // Назначаем обработчик для textListCategory
        textListCategory.setOnClickListener {
            closeListAndUpdateText() // Закрываем список и обновляем текст
        }
    }

    private fun toggleListVisibility() {
        if (isListVisible) {
            // Если список видим, скрываем его
            textListCategory.visibility = View.GONE
            icList.setImageResource(R.drawable.ic_down) // Меняем иконку на "вниз"
        } else {
            // Если список скрыт, показываем его
            textListCategory.visibility = View.VISIBLE
            icList.setImageResource(R.drawable.ic_up) // Меняем иконку на "вверх"
        }
        isListVisible = !isListVisible // Меняем состояние видимости
    }

    private fun closeListAndUpdateText() {
        // Закрываем список
        textListCategory.visibility = View.GONE
        icList.setImageResource(R.drawable.ic_down) // Меняем иконку на "вниз"
        isListVisible = false

        // Обновляем текст в textList
        val selectedText = textListCategory.text.toString()
        textList.text = selectedText
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
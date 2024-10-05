package com.example.filework

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class Task3 : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var buttonGenerateTask3: Button
    private lateinit var buttonReadTask3: Button
    private lateinit var numbersTask3: TextView

    private val fileName = "task3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbarTask3)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonGenerateTask3 = findViewById(R.id.buttonGenerateTask3)
        buttonReadTask3 = findViewById(R.id.buttonReadTask3)
        numbersTask3 = findViewById(R.id.numbersTask3)

        buttonGenerateTask3.setOnClickListener {
            //количество чисел
            val count = Random.nextInt(4, 11)

            //генерируем строку чисел для записи в файл
            var out = ""
            repeat(count) {
                val num = Random.nextInt(-100, 101).toString()
                out = "$out $num"
            }
            out = out.drop(1)

            //Пишем в файл
            writeToFileInDownload(fileName, out)
            //showSnackbar(out)
        }

        buttonReadTask3.setOnClickListener {
            //сформируем list чисел из файла
            val numbers = readFromFile(fileName)
            val numbersList = numbers.split(" ")
            if (numbersList.count() >= 4) {
                //Выведем первые 2 и последние 2 элемента
                val out = "${numbersList.take(2).joinToString(" ")} ${
                    numbersList.takeLast(2).joinToString(" ")
                }"

                //запишем в текстовое поле
                numbersTask3.text = out

                //покажем все элементы
                showSnackbar(numbers)
            }
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Завершаем активность и возвращаемся к предыдущей
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
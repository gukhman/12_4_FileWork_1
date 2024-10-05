package com.example.filework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Task2 : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var fileNameTask2: EditText
    private lateinit var numberTask2: EditText
    private lateinit var buttonSaveTask2: Button
    private lateinit var buttonReadTask2: Button

    private lateinit var fileNameStr: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbarTask2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fileNameTask2 = findViewById(R.id.fileNameTask2)
        numberTask2 = findViewById(R.id.numberTask2)
        buttonSaveTask2 = findViewById(R.id.buttonSaveTask2)
        buttonReadTask2 = findViewById(R.id.buttonReadTask2)

        buttonSaveTask2.setOnClickListener {

            //Получаем число
            val defaultNumber = 10
            var number = numberTask2.text.toString().toIntOrNull() ?: defaultNumber
            if (number < 1) number = defaultNumber

            //Формируем строку вывода в файл
            var out = ""
            for (i in 1..number) {
                if (i % 2 == 0) out = "$out $i"
            }
            out = out.drop(1)

            //Записываем содержимое в файл
            val fileName = fileNameTask2.text.toString()
            writeToFileInDownload(fileName, out)

            //Запомним имя файла, чтобы потом прочитать его
            fileNameStr = fileName
        }

        buttonReadTask2.setOnClickListener {
            //Читаем из файла
            readFromFile(fileNameStr)
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
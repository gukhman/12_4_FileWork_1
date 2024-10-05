package com.example.filework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Task1 : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var buttonSave: Button
    private lateinit var buttonRead: Button
    private lateinit var textToSave: EditText
    private lateinit var textFromFile: EditText

    private val fileName = "task1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbarTask1)
        setSupportActionBar(toolbar)
        // Настройка Action Bar с кнопкой "Назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonSave = findViewById(R.id.buttonSaveTask1)
        buttonRead = findViewById(R.id.buttonRead)
        textToSave = findViewById(R.id.textToSave)
        textFromFile = findViewById(R.id.textFromFile)

        buttonSave.setOnClickListener {
            writeToFileInDownload(fileName, textToSave.text.toString())
        }

        buttonRead.setOnClickListener {
            val text = readFromFile(fileName)
            textFromFile.setText(text)
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
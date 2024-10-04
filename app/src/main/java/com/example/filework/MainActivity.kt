package com.example.filework

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var buttonSave: Button
    private lateinit var buttonRead: Button
    private lateinit var textToSave: EditText
    private lateinit var textFromFile: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonSave = findViewById(R.id.buttonSave)
        buttonRead = findViewById(R.id.buttonRead)
        textToSave = findViewById(R.id.textToSave)
        textFromFile = findViewById(R.id.textFromFile)

        buttonSave.setOnClickListener {
            writeToFileInDownload("example.txt", textToSave.text.toString())
        }

        buttonRead.setOnClickListener {
            val text = readFromFile("example.txt")
            textFromFile.setText(text)
        }
    }

    private fun writeToFileInDownload(fileName: String, content: String) {
        // Определяем каталог Downloads
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        // Создаем файл
        val file = File(downloadsDirectory, fileName)

        try {
            // Записываем текст в файл
            file.writeText(content)

            // Уведомляем пользователя об успешной записи
            showSnackbar("Файл записан: ${file.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
            showSnackbar("Ошибка записи файла: ${e.message}")
        }
    }

    private fun showSnackbar(message: String) {
        val rootView = findViewById<android.view.View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    private fun readFromFile(fileName: String): String {
        // Определяем каталог Downloads
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        // Создаем объект File для указанного файла
        var text = ""
        val file = File(downloadsDirectory, fileName)
        if (file.exists()) {
            try {
                text = file.readText()
                showSnackbar("Файл прочитан")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return text
    }

}
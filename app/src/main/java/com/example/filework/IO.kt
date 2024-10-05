package com.example.filework

import android.app.Activity
import android.os.Environment
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.IOException


fun Activity.writeToFileInDownload(fileName: String, content: String) {
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

fun Activity.showSnackbar(message: String) {
    val rootView = findViewById<android.view.View>(android.R.id.content)
    Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
}

fun Activity.readFromFile(fileName: String): String {
    // Определяем каталог Downloads
    val downloadsDirectory =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    // Создаем объект File для указанного файла
    var text = ""
    val file = File(downloadsDirectory, fileName)
    if (file.exists()) {
        try {
            text = file.readText()
            showSnackbar("Содержимое файла: $text")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } else showSnackbar("Файл не существует")
    return text
}

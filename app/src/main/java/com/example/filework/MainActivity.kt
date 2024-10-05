package com.example.filework

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var task1: Button
    private lateinit var task2: Button
    private lateinit var task3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        task1 = findViewById(R.id.task1)
        task2 = findViewById(R.id.task2)
        task3 = findViewById(R.id.task3)

        task1.setOnClickListener {
            val intent = Intent(this, Task1::class.java)
            startActivity(intent)
        }

        task2.setOnClickListener {
            val intent = Intent(this, Task2::class.java)
            startActivity(intent)
        }

        task3.setOnClickListener {
            val intent = Intent(this, Task3::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_exit) finishAffinity()
        return true
    }
}
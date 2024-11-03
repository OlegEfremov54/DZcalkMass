package com.example.dzcalkmass

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var heightET: EditText
    private lateinit var weightET: EditText
    private lateinit var buttonCalculateBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "ДЗ Индекс массы тела"
        toolbarMain.subtitle = "Версия 1. Главная активити"
        toolbarMain.setLogo(R.drawable.krest)

        heightET = findViewById(R.id.heightET)
        weightET = findViewById(R.id.weightET)
        buttonCalculateBTN = findViewById(R.id.buttonCalculateBTN)

        buttonCalculateBTN.setOnClickListener{view ->
            if (heightET.text.isEmpty() ||
                weightET.text.isEmpty() ||
                heightET.text.toString().toInt() < 20 ||
                heightET.text.toString().toInt() > 350 ||
                weightET.text.toString().toInt() < 1 ||
                weightET.text.toString().toInt() > 500) {
                Snackbar.make(view, "Данные введены неверно!", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("height", heightET.text.toString())
            intent.putExtra("weight", weightET.text.toString())
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Версия 1.1.",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
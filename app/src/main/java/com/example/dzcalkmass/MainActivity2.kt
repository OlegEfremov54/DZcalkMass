package com.example.dzcalkmass

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity() {

    private lateinit var toolbarSek: Toolbar
    private lateinit var indexMassBodyTV: TextView
    private lateinit var imageBodyTypeIV: ImageView
    private lateinit var infoForWeightLossTV: TextView
    private lateinit var buttonBackBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarSek = findViewById(R.id.toolbarSek)
        setSupportActionBar(toolbarSek)
        title = "  ДЗ Индекс массы тела"
        toolbarSek.subtitle = "  Версия 1. Вторая активити"
        toolbarSek.setLogo(R.drawable.krest)
        indexMassBodyTV = findViewById(R.id.indexMassBodyTV)
        imageBodyTypeIV = findViewById(R.id.imageBodyTypeIV)
        infoForWeightLossTV = findViewById(R.id.infoForWeightLossTV)
        buttonBackBTN = findViewById(R.id.buttonBackBTN)

        buttonBackBTN.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            finish()
        }

        val height = intent.getStringExtra("height")!!.toDouble()
        val weight = intent.getStringExtra("weight")!!.toDouble()
        val indexMassBody = (weight / ((height / 100) * (height / 100))).toInt()
        indexMassBodyTV.text = indexMassBody.toString()
        when(indexMassBody) {
            in 1..17 -> {
                imageBodyTypeIV.setImageResource(R.drawable.hudoba)
                infoForWeightLossTV.setText(R.string.easy_mass_body)
            }
            in 18..25 -> {
                imageBodyTypeIV.setImageResource(R.drawable.norma)
                infoForWeightLossTV.setText(R.string.normal_mass_body)
            }
            in 25..30 -> {
                imageBodyTypeIV.setImageResource(R.drawable.tolst)
                infoForWeightLossTV.setText(R.string.medium_mass_body)
            }
            in 30..1000 -> {
                imageBodyTypeIV.setImageResource(R.drawable.kashmar)
                infoForWeightLossTV.setText(R.string.hard_mass_body)
            }
            else -> {
                Snackbar.make(infoForWeightLossTV, "Видимо какая то ошибка", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                finish()
            }
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
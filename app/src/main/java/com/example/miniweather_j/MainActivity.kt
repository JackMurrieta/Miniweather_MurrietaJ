package com.example.miniweather_j

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var tvGreeting: TextView
    private lateinit var tvCity: TextView
    private lateinit var ivWeather: ImageView
    private lateinit var tvTemperature: TextView
    private lateinit var tvWeather: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializar elementos de la UI
        tvGreeting = findViewById(R.id.tvGreeting)
        tvCity = findViewById(R.id.tvCity)
        ivWeather = findViewById(R.id.ivWeather)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvWeather = findViewById(R.id.tvWeather)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val citySelected = intent.getStringExtra("city")
        // agregar ene l archivo correspondiente en grettings

        //edit text greeting se le asigna un valor dependiendo de la hora del dia esta en values string

        val time = LocalTime.now().hour

        when(time){
            // in de 5 am a 11 am = greeting .R.good_morning
            //in 12 pm - 7 pm  = greeting .R.good_afternoon
            // in 8pm - 4 om = greeting R.good_evening
        }
    }

    }
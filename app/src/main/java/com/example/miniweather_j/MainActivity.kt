package com.example.miniweather_j

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miniweather_j.domain.Weather
import com.example.miniweather_j.utilities.WeatherService
import java.util.Calendar

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

        // Configurar barra de estado para fondo oscuro
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        // Aceptar el mensaje del Intent con la ciudad seleccionada
        val citySelected = intent.getStringExtra("CITY_NAME") ?: "Obregon"

        // Asignar saludo según la hora del día
        val time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        when(time){
            // in de 5 am a 11 am = greeting .R.good_morning
            in 5..11 -> tvGreeting.text = getString(R.string.good_morning)
            //in 12 pm - 7 pm  = greeting .R.good_afternoon
            in 12..19 -> tvGreeting.text = getString(R.string.good_afternoon)
            // in 8pm - 4 am = greeting R.good_evening
            else -> tvGreeting.text = getString(R.string.good_evening)
        }

        // Crear instancia de WeatherService y solicitar el clima de la ciudad
        val weatherService = WeatherService(this)
        val weather: Weather = weatherService.getCityWeather(citySelected)

        // Llenar la información del clima
        tvCity.text = citySelected
        tvTemperature.text = "${weather.temperature}°"
        tvWeather.text = weather.weather

        // Cambiar la imagen según el clima
        val weatherIconRes = when(weather.weather) {
            getString(R.string.sunny) -> R.drawable.ic_sunny
            getString(R.string.cloudy) -> R.drawable.ic_cloudy
            getString(R.string.rainy) -> R.drawable.ic_rainy
            getString(R.string.stormy) -> R.drawable.ic_stormy
            getString(R.string.windy) -> R.drawable.ic_windy
            else -> R.drawable.ic_sunny
        }
        ivWeather.setImageResource(weatherIconRes)
    }
}
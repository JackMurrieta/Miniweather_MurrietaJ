package com.example.miniweather_j

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miniweather_j.utilities.WeatherService

class CityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_city)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar barra de estado para fondo oscuro
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        //agregar servicio se pasa el mismo weather context creado como mismo
        val weatherService = WeatherService(this)

        // agregar button  con el id.btn_save_city
        val btnSaveCity: Button = findViewById(R.id.btn_save_city)

        // agregar un val scitySelector : Spinner con id = city_selector
        val citySelector: Spinner = findViewById(R.id.city_selector)

        //agregar en el adapter . .simple_spinner_dropdown_item
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, weatherService.getCities())
        citySelector.adapter = adapter

        //agregar un listener en el spinner selector
        var selectedCity = ""
        citySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCity = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }

        // Listener del botón siguiente que manda llamar a MainActivity con la ciudad seleccionada
        btnSaveCity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("CITY_NAME", selectedCity)
            startActivity(intent)
        }
    }
}

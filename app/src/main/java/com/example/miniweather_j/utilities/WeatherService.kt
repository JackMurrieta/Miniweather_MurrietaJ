package com.example.miniweather_j.utilities

import android.content.Context
import com.example.miniweather_j.R
import com.example.miniweather_j.domain.Weather

class WeatherService( val context: Context) {
 // windy
    //stormy
    //rainy
    //cloudy
    //sunny

    val weatherService = arrayOf(
        context.getString(R.string.windy),
        context.getString(R.string.stormy),
        context.getString(R.string.rainy),
        context.getString(R.string.cloudy),
        context.getString(R.string.sunny)
        )

    fun getCities() : Array<String> {
        //agregar 5 ciudades mas
        return arrayOf("Obregon","Londres","Paris","Tokyo","Nueva York","Berlin","Madrid")
    }

    fun generateWeather() : Weather {
        val temp = (-15..50).random()

        var weatherIndex = -1

        when(temp){
            //realizar un switch y se cambia el weather index la primea
        // case es -15 <= .. <0 weather index = 0
            in -15..-1 -> weatherIndex = 0
            //segundo case es 1 <= .. >= 10 weather index es (1 >== .. <6).random()
            in 1..10 -> weatherIndex = (1..5).random()
            //tercer case es 19<= .. <= 20 - > weather index = (4<= .. <=5).random()
            in 19..20 -> weatherIndex = (4..5).random()
            //else weather index = 4
            else -> weatherIndex = 4
        }

        return Weather(temp, weatherService[weatherIndex])

    }

    fun getCityWeather(city:String) : Weather{
        //devuelva el clima de la ciudad usar funcion generateWheater
        return generateWeather();
    }
}

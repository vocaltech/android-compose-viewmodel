package fr.vocaltech.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TemperatureConverterModel: ViewModel() {
    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    // for testing purpose only
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val model = TemperatureConverterModel()

            model.convertTemp("32")
            println(model.result) // 0

            model.convertTemp("33")
            println(model.result) // 0.5556

            model.convertTemp("34")
            println(model.result) // 1.11111

            model.isFahrenheit = false
            model.convertTemp("0")
            println(model.result) // 32

            model.convertTemp("7")
            println(model.result) // 44.6
        }
    }

    fun switchChange() {
        isFahrenheit = !isFahrenheit
    }

    fun convertTemp(temp: String) {
        result = try {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                ((tempInt - 32) * 0.5556).toFloat().toString()
            } else {
                ((tempInt * 1.8) + 32).toFloat().toString()
            }
        } catch (e: Exception) {
            "Invalid Entry"
        }
    }
}
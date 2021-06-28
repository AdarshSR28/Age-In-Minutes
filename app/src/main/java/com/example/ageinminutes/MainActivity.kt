package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnClick = findViewById<Button>(R.id.buttonPicker)
        btnClick.setOnClickListener{view ->
            clickOnDatePicker(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickOnDatePicker(view: View)
    {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonthOfYear, selectedDayOfMonth ->
               //Toast.makeText(this, "The selected year is $selectedYear",Toast.LENGTH_SHORT).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonthOfYear+1}/$selectedYear"
                val showDate = findViewById<TextView>(R.id.inpDate)
                showDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                // returns date in milliseconds
                val selectedDateInMinutes = theDate!!.time / 60000  // from 1st jan 1970 till selected
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time/60000

                val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
                val toShow = findViewById<TextView>(R.id.showDate)
                toShow.text = diffInMinutes.toString()


        }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }


}
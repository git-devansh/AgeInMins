package com.anything11.ageinmins

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){

        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)

        val dpt = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "Chosen year is $selectedYear, month is $selectedMonth and day is $selectedDay", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMins = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMins = currentDate!!.time / 60000

            val diffInMins = currentDateInMins - selectedDateInMins

            tvSelectedDateInMinutes.text = diffInMins.toString()

        },year, month, day )

        dpt.datePicker.maxDate = Date().time - 86400000
        dpt.show()

    }
}
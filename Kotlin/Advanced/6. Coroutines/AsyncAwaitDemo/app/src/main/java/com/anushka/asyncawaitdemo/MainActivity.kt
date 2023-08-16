package com.anushka.asyncawaitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            Log.i("My Tag", "Calculation started")
            val stock1 = async() { getStock1() }
            val stock2 = async() { getStock2() }
            val stock = stock1.await() + stock2.await()
            Log.i("My Tag", "Total Stock $stock")
//            Toast.makeText(applicationContext, "my tag : total stock is $stock", Toast.LENGTH_SHORT)
//                .show()
        }
    }

    private suspend fun getStock1(): Int {
        delay(10000)
        Log.i("My Tag", "stock 1 returned")
        return 55555
    }

    private suspend fun getStock2(): Int {
        delay(8000)
        Log.i("My Tag", "stock 2 returned")
        return 44444
    }
}
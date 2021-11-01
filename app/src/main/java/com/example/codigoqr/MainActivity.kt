package com.example.codigoqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.codigoqr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ScanCodigo.setOnClickListener {
            scanearqr()
        }
    }
    fun scanearqr(){
       val integretor= IntentIntegrator(this)
        integretor.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integretor.setBeepEnabled(true)
        integretor.setPrompt("Enfoque al codigo qr")
        integretor.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                binding.valorCodigo.setText("${result.contents}")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
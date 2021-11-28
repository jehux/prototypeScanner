package mx.na_at.scannerprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import mx.na_at.scannerprototype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setScanner()
    }

    fun setScanner(){

        var scanner = IntentIntegrator(this)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.EAN_13)
        scanner.setPrompt("Escanea tu código")
        scanner.setBeepEnabled(false)
        scanner.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                //Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
                binding.tvresultCode.text = result.contents
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
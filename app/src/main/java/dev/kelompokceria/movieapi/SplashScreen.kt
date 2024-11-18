package dev.kelompokceria.movieapi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
         Handler().postDelayed({
            // Setelah splash screen selesai, pindah ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Menutup SplashScreenActivity agar tidak bisa kembali ke layar splash
        }, 5000)  // 3000 ms = 3 detik
    }
}
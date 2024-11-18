package dev.kelompokceria.movieapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menyesuaikan tampilan edge-to-edge jika diperlukan
        enableEdgeToEdge()

        // Set layout dan konten tampilan
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Adapter untuk RecyclerView
        val adapter = PokemonAdapter()
        recyclerView.adapter = adapter

        // Ambil data menggunakan Retrofit dan tampilkan di RecyclerView
        lifecycleScope.launch {
            try {
                // Ambil data Pokemon dari API
                val pokemons = RetrofitHelper.PokemonService.getPokemonList() // Pastikan ini mengembalikan data yang sesuai
                withContext(Dispatchers.Main) {
                    adapter.submitList(pokemons) // Kirimkan data ke adapter untuk ditampilkan
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching Pokemon data", e) // Menangani kesalahan
            }
        }
    }

}
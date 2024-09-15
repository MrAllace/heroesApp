package com.example.heroesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroesapp.ui.theme.HeroesAppTheme

data class Publisher(val id: Int, val name: String, val image: String)
        class PublisherActivity : AppCompatActivity() {

            private lateinit var recyclerView: RecyclerView
            private lateinit var logoutButton: Button
            private lateinit var sharedPreferences: SharedPreferences

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.publisher_activity)

                // Inicializar SharedPreferences
                sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

                // Inicializar RecyclerView
                recyclerView = findViewById(R.id.recyclerViewPublishers)
                recyclerView.layoutManager = LinearLayoutManager(this)

                // Cargar lista de publishers
                val publisherList = listOf(
                    Publisher(1, "Marvel", "https://image-url-marvel"),
                    Publisher(2, "DC", "https://image-url-dc")
                )

                // Configurar Adapter
                val adapter = PublisherAdapter(publisherList) { publisher ->
                    // Navegar a HeroesActivity pasando el ID del publisher seleccionado
                    val intent = Intent(this, HeroesActivity::class.java)
                    intent.putExtra("PUBLISHER_ID", publisher.id)
                    startActivity(intent)
                }
                recyclerView.adapter = adapter

                // Configurar botón de cerrar sesión
                logoutButton = findViewById(R.id.logoutButton)
                logoutButton.setOnClickListener {
                    // Limpiar SharedPreferences y volver a MainActivity
                    sharedPreferences.edit().putBoolean("isLogged", false).apply()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }



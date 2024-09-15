package com.example.heroesapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.content.SharedPreferences
import android.content.Context
import android.content.Intent
import android.util.Patterns
import com.google.android.material.snackbar.Snackbar



class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        // Verificar si el usuario ya está logueado
        if (sharedPreferences.getBoolean("isLogged", false)) {
            navigateToPublisherActivity()
        }

        emailEditText = this.findViewById(R.id.emailEditText)
        passwordEditText = this.findViewById(R.id.passwordEditText)
        loginButton = this.findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (!isValidEmail(email)) {
                // Mostrar Snackbar de error
                Snackbar.make(it, "Email no válido", Snackbar.LENGTH_LONG).show()
            } else {
                // Intentar autenticación
                if (authenticate(email, password)) {
                    // Guardar estado de sesión en SharedPreferences
                    sharedPreferences.edit().putBoolean("isLogged", true).apply()

                    // Navegar a PublisherActivity
                    navigateToPublisherActivity()
                } else {
                    Snackbar.make(it, "Credenciales incorrectas", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    // Validación de email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Autenticación
    private fun authenticate(email: String, password: String): Boolean {
        return UserRepository.users.any { user -> user.email == email && user.password == password }
    }

    // Navegación a PublisherActivity
    private fun navigateToPublisherActivity() {
        val intent = Intent(this, PublisherActivity::class.java)
        startActivity(intent)
        finish() // Finalizar la actividad de login
    }
}

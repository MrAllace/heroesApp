package com.example.heroesapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HeroesActivity : AppCompatActivity() {

    data class Hero(
        val id: Int,
        val name: String,
        val image: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heroes_activity)

        // Obtener el ID del publisher
        val publisherId = intent.getIntExtra("PUBLISHER_ID", -1)

        // Dependiendo del ID, mostrar los héroes de Marvel o DC
        if (publisherId == 1) {
            // Mostrar héroes de Marvel
        } else if (publisherId == 2) {
            // Mostrar héroes de DC
        }
        class HeroesAdapter(
            private val heroes: List<Hero>,
            private val onHeroClicked: (Hero) -> Unit
        ) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

            inner class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val heroImage: ImageView = view.findViewById(R.id.heroImage)
                val heroName: TextView = view.findViewById(R.id.heroName)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
                return HeroViewHolder(view)
            }

            override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
                val hero = heroes[position]
                holder.heroName.text = hero.name

                Picasso.get().load(hero.image).into(holder.heroImage)

                holder.itemView.setOnClickListener {
                    onHeroClicked(hero)
                }
            }

            override fun getItemCount(): Int = heroes.size
        }
        class HeroesActivity : AppCompatActivity() {

            private lateinit var recyclerView: RecyclerView
            private lateinit var adapter: HeroesAdapter
            private val marvelHeroes = listOf(
                Hero(1, "Spider-Man", "https://..."),
                Hero(2, "Iron Man", "https://..."),
            )
            private val dcHeroes = listOf(
                Hero(1, "Superman", "https://..."),
                Hero(2, "Batman", "https://..."),
            )

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.heroes_activity)

                recyclerView = findViewById(R.id.recyclerViewHeroes)
                recyclerView.layoutManager = LinearLayoutManager(this)

                val publisherId = intent.getIntExtra("PUBLISHER_ID", -1)

                val heroes = if (publisherId == 1) { // Marvel (ID 1)
                    marvelHeroes
                } else { // DC (ID 2)
                    dcHeroes
                }

                adapter = HeroesAdapter(heroes) { selectedHero ->
                    // Navegar a HeroDetailActivity con el ID del héroe seleccionado
                    val intent = Intent(this, HeroDetailActivity::class.java)
                    intent.putExtra("HERO_ID", selectedHero.id)
                    startActivity(intent)
                }
                recyclerView.adapter = adapter
            }
        }


    }
}

class HeroDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heroes_activity)

        val heroId = intent.getIntExtra("HERO_ID", -1)

    }
}


}

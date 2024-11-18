package dev.kelompokceria.movieapi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kelompokceria.movieapi.Pokemon
import dev.kelompokceria.movieapi.databinding.ItemPokemonLayoutBinding


class PokemonAdapter: ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonDiffCallback()) {

    // ViewHolder untuk setiap item dalam RecyclerView
    class PokemonViewHolder(val binding: ItemPokemonLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // Inflate layout item_pokemon dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding : ItemPokemonLayoutBinding = ItemPokemonLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    // Bind data Pokemon ke ViewHolder
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)

        // Load gambar menggunakan Glide
        Glide.with(holder.binding.pokemonImage.context)
            .load(pokemon.image_url)
            .into(holder.binding.pokemonImage)

        holder.binding.pokemonName.text = pokemon.pokemon
        holder.binding.pokemonType.text = pokemon.type
          // Clear previous abilities (important when reusing ViewHolder)
        holder.binding.linearLayoutAbilities.removeAllViews()

        // Add abilities dynamically
        for (ability in pokemon.abilities) {
            val textView = TextView(holder.binding.pokemonAbility.context)
            textView.text = ability
            textView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            textView.textSize = 12f
            textView.setPadding(12, 12, 12, 12)
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Wrap TextView in a CardView
            val cardView = CardView(holder.binding.cardLayoutAbility.context)
            cardView.radius = 20f
            cardView.setContentPadding(10, 10, 10, 10)
            cardView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = 5
                marginEnd = 5
                bottomMargin = 30
                topMargin = 10
            }
            if ( ability == pokemon.abilities.last()) {
                cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
            } else {
                cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            }
            cardView.addView(textView)

            // Add CardView to LinearLayout
            holder.binding.linearLayoutAbilities.addView(cardView)
        }
    }

    class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                // Cek jika id Pokemon sama
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                // Cek jika konten Pokemon sama (misalnya nama, jenis, dll)
                return oldItem == newItem
            }
    }


}



package  unifedideas.ama.gamekotlin.features.allGames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unifedideas.ama.gamekotlin.databinding.ItemGameBinding
import unifedideas.ama.gamekotlin.features.allGames.adapter.GamesAdapter.ViewHolder

class GamesAdapter() : RecyclerView.Adapter<ViewHolder>() {

    private var images: ArrayList<Game> = ArrayList()

    constructor(images: ArrayList<Game>) : this() {
        this.images = images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemGameBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setData(data: ArrayList<Game>) {
        images.clear()
        images.addAll(data)
        notifyDataSetChanged()
    }

    fun appendData(data: ArrayList<Game>) {
        images.addAll(data)
        //notifyDataSetChanged()
    }

    fun setGames(data: List<Game>) {
        images = data as ArrayList<Game>
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.viewModel = GameItemViewModel(game)
            binding.executePendingBindings()
        }
    }
}
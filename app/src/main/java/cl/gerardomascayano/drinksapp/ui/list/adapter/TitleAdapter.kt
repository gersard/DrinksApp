package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.databinding.TvTitleBinding

class TitleAdapter(val title: String) : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TitleViewHolder(
        TvTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) = holder.bind()


    inner class TitleViewHolder(private val viewBinding: TvTitleBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind() {
            viewBinding.tvTitleFavoriteDrinks.text = title
        }

    }

}
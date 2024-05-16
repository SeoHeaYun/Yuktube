package kr.camp.youtube.view.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.camp.youtube.databinding.ItemCategoryVideoBinding
import kr.camp.youtube.databinding.ItemPopularVideoBinding
import kr.camp.youtube.databinding.ItemSearchBinding
import kr.camp.youtube.extension.toSpanned
import kr.camp.youtube.view.home.state.HomePopularItem
import kr.camp.youtube.view.home.state.HomeVideoItem
import kr.camp.youtube.view.search.state.item.SearchListItem


class HomePopularAdapter(
    private val onItemClick: (HomePopularItem) -> Unit = {}
) : RecyclerView.Adapter<HomePopularAdapter.HomePopularItemViewHolder>() {

    private val homePopularlList = mutableListOf<HomePopularItem>()

    class HomePopularItemViewHolder(
        var binding: ItemPopularVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val glide = Glide.with(binding.root)
        fun bind(homePopularItemPosition: HomePopularItem) = with(binding) {
            glide.load(homePopularItemPosition.thumbnailUrl).into(thumbnailImageView)
            videoTitleTextView.text = homePopularItemPosition.videoTitle
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePopularItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularVideoBinding.inflate(layoutInflater, parent, false)
        return HomePopularItemViewHolder(binding)
    }

    override fun getItemCount(): Int = homePopularlList.size

    override fun onBindViewHolder(holder: HomePopularItemViewHolder, position: Int) {
        val itemPosition = homePopularlList[position]
        holder.apply {
            bind(itemPosition)
            binding.root.setOnClickListener {
                onItemClick(itemPosition)
            }

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun update(newHomePopularList: List<HomePopularItem>) {
        this.homePopularlList.clear()
        this.homePopularlList.addAll(newHomePopularList)
        notifyDataSetChanged()
    }
}

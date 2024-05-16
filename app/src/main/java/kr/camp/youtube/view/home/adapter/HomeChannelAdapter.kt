package kr.camp.youtube.view.home.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.camp.youtube.databinding.ItemCategoryChannelBinding
import kr.camp.youtube.view.home.state.HomeChannelItem

class HomeChannelAdapter(
    private val onItemClick: (HomeChannelItem) -> Unit = {}
) : RecyclerView.Adapter<HomeChannelAdapter.HomeChannelItemViewHolder>() {

    private val homeChannelList = mutableListOf<HomeChannelItem>()

    class HomeChannelItemViewHolder(
        var binding: ItemCategoryChannelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val glide = Glide.with(binding.root)
        fun bind(homeChannelItemPosition: HomeChannelItem) = with(binding) {
            glide.load(homeChannelItemPosition.thumbnailUrl).into(thumbnailImageView)
            channelNameTextView.text = homeChannelItemPosition.channelName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChannelItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryChannelBinding.inflate(layoutInflater, parent, false)
        return HomeChannelItemViewHolder(binding)
    }

    override fun getItemCount(): Int = homeChannelList.size

    override fun onBindViewHolder(holder: HomeChannelItemViewHolder, position: Int) {
        val itemPosition = homeChannelList[position]
        holder.apply {
            bind(itemPosition)
            binding.root.setOnClickListener {
                onItemClick(itemPosition)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(newHomeChannelList: List<HomeChannelItem>) {
        this.homeChannelList.clear()
        this.homeChannelList.addAll(newHomeChannelList)
        notifyDataSetChanged()
    }
}



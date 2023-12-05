package ua.olehkv.pethome.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import ua.olehkv.pethome.R
import ua.olehkv.pethome.databinding.SliderLayoutBinding
import ua.olehkv.pethome.models.SliderData


class SliderAdapter(context: Context, private val sliderDataArrayList: List<SliderData>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.slider_layout, null, false)
        return SliderAdapterViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        viewHolder.bind(sliderDataArrayList[position])
    }

    override fun getCount() = sliderDataArrayList.size

    class SliderAdapterViewHolder(itView: View) : ViewHolder(itView){
        private val binding = SliderLayoutBinding.bind(itView)
        fun bind(sliderItem: SliderData) = with(binding){
            Glide.with(root)
            .load(sliderItem.imgUrl)
            .fitCenter()
            .into(petImage)
        }

    }
}


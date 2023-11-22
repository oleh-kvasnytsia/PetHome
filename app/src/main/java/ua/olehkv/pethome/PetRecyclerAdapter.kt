package ua.olehkv.pethome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.olehkv.pethome.databinding.PetLayoutBinding

class PetRecyclerAdapter(val petList: List<PetModel>) : RecyclerView.Adapter<PetRecyclerAdapter.PetHoder>() {

    inner class PetHoder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = PetLayoutBinding.bind(view)
        fun bind(pet: PetModel) = with(binding){
            tvName.text = pet.name
            tvLocation.text = pet.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHoder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_layout, parent, false)
        return PetHoder(view)
    }

    override fun getItemCount() = petList.size

    override fun onBindViewHolder(holder: PetHoder, position: Int) {
        holder.bind(petList[position])
    }
}
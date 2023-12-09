package ua.olehkv.pethome.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.olehkv.pethome.ChoosePetActivity
import ua.olehkv.pethome.R
import ua.olehkv.pethome.databinding.PetLayoutBinding
import ua.olehkv.pethome.models.PetModel

class PetRecyclerAdapter(private var petList: List<PetModel>, val context: Context) : RecyclerView.Adapter<PetRecyclerAdapter.PetHolder>() {

    inner class PetHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = PetLayoutBinding.bind(view)
        fun bind(pet: PetModel) = with(binding){
            Picasso.Builder(context)
                .build()
                .load(pet.first_pet_photo)
                .into(imageView)
            tvName.text = pet.name

//            tvLocation.text = "id = ${pet.pet_id}"
            tvLocation.text = pet.city
            itemView.setOnClickListener {
                context.startActivity(Intent(context, ChoosePetActivity::class.java).putExtra("ID", pet.pet_id))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_layout, parent, false)
        return PetHolder(view)
    }

    fun updateList(newList: List<PetModel>){
        petList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = petList.size

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        holder.bind(petList[position])
    }
}
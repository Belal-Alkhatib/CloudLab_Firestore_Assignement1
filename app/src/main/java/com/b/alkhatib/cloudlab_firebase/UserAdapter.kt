package com.b.alkhatib.cloudlab_firebase

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b.alkhatib.cloudlab_firebase.databinding.UserInfoItemBinding
import com.google.firebase.firestore.FirebaseFirestore

class UserAdapter(var activity: Activity, var data: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.myViewHolder>() {
    val db = FirebaseFirestore.getInstance()

    class myViewHolder(var binding: UserInfoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = UserInfoItemBinding.inflate(activity.layoutInflater, parent, false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.binding.tvName.text = data[position].name
        holder.binding.tvNumber.text = data[position].number
        holder.binding.tvAddress.text = data[position].address

        holder.binding.icDelete.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun deleteUser(id:String){
        db.collection("users").document(id)
            .delete()
            .addOnSuccessListener {
            }
            .addOnFailureListener { exception ->
            }

    }
}
package com.example.dummy_app.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dummy_app.R
import com.example.dummy_app.model.User
import com.example.dummy_app.model.UserResponse
import kotlinx.android.synthetic.main.card_item.view.*

class UserAdapter(val context: Context, val user: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = user[position]

        if(position%2 == 0){
            holder.itemView.visibleCard.setCardBackgroundColor(context.resources.getColor(R.color.pastel_blue))
            holder.itemView.expandedLayout.setCardBackgroundColor(context.resources.getColor(R.color.pastel_blue))


        } else {
            holder.itemView.visibleCard.setCardBackgroundColor(context.resources.getColor(R.color.pastel_grey))
            holder.itemView.expandedLayout.setCardBackgroundColor(context.resources.getColor(R.color.pastel_grey))

        }
        holder.itemView.apply {
            Glide.with(this).load(user.image).into(iconIV)
            fnametextView.text = user.firstName
            lnametextView.text = user.lastName
            mnametextView.text = user.maidenName
            agetextView.text = user.age.toString()
            birthtextView.text = user.birthDate
            mailtextView.text = user.email
            phonetextView.text = user.phone
            universitytextView.text = user.university
            gendertextView.text = user.gender
            doamintextView.text = user.domain
            addresstextView.text = user.address?.address
            ssntextView.text = user.ssn
            unameView.text = user.username
            passwordtextView.text = user.password
        }
        var isVisible : Boolean = user.visibility
        holder.itemView.expandedLayout.visibility = if(isVisible) View.VISIBLE else View.GONE

        holder.itemView.visibleCard.setOnClickListener{
            user.visibility = !user.visibility
            notifyItemChanged(position)

        }
    }

    override fun getItemCount(): Int {
        return user.size
    }

}

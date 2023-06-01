package com.example.wallet.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wallet.Modal.TransactionModal
import com.example.wallet.R
import com.example.wallet.databinding.ItemViewRcvBinding

class MainTransAdapter : RecyclerView.Adapter<MainTransAdapter.MainTransHolder>() {


    lateinit var transaction: ArrayList<TransactionModal>

    class MainTransHolder(itemView: ItemViewRcvBinding) : ViewHolder(itemView.root){

        var binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTransHolder {

        var binding = ItemViewRcvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainTransHolder(binding)
    }

    override fun getItemCount(): Int {
        return transaction.size


    }

    override fun onBindViewHolder(holder: MainTransHolder, position: Int) {

        holder.binding.apply {

            TxtName.text = transaction[position].name
            TxtAmt.text = transaction[position].amt.toString()
            TxtCart.text = transaction[position].category

            if (transaction.get(position).isExpense == 0){
                TxtAmt.setTextColor(Color.GREEN)
                round.setImageResource(R.drawable.shap_item)
                upAro.setImageResource(R.drawable.baseline_arrow_upward_24)
            }else{
                TxtAmt.setTextColor(Color.RED)
                round.setImageResource(R.drawable.shap_down_item)
                upAro.setImageResource(R.drawable.baseline_arrow_downward_24)
            }

        }
     }


    fun addtruns (transaction : ArrayList<TransactionModal>){

        this.transaction = transaction
    }
}
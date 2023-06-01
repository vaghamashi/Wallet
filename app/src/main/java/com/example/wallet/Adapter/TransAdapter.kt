package com.example.wallet.Adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wallet.Modal.TransactionModal
import com.example.wallet.R
import com.example.wallet.databinding.ItemViewBinding

class TransAdapter(detele: (Int) -> Unit, update: (TransactionModal) -> Unit) :
    RecyclerView.Adapter<TransAdapter.TransHolder>() {

    var deletClick = detele
    var updateClick = update
    lateinit var transaction: ArrayList<TransactionModal>

    class TransHolder(itemView: ItemViewBinding) : ViewHolder(itemView.root) {

        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {

        var binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransHolder(binding)
    }

    override fun getItemCount(): Int {

        return transaction.size

    }

    override fun onBindViewHolder(
        holder: TransHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        holder.binding.apply {


            txtName.text = transaction.get(position).name
            txtAmt.text = transaction.get(position).amt.toString()
            txtCategory.text = transaction.get(position).category
            txtNote.text = transaction.get(position).note
            date.text = transaction.get(position).time

            if (transaction.get(position).isExpense == 0) {
                isincome.setText("Is Icome")
                isincome.setTextColor(Color.GREEN)
                txtAmt.setTextColor(Color.GREEN)
            } else {
                txtAmt.setTextColor(Color.RED)
                isincome.setText("Is expense")
                isincome.setTextColor(Color.RED)
            }
        }
        holder.itemView.setOnLongClickListener(object : OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                var opton = PopupMenu(holder.itemView.context, holder.itemView)
                opton.menuInflater.inflate(R.menu.popupmenu, opton.menu)

                opton.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {


                        when (item?.itemId) {
                            R.id.detel -> {
                                deletClick.invoke(transaction.get(position).id)
                            }

                            R.id.Update -> {
                                updateClick.invoke(transaction.get(position))

                            }
                        }
                        return false
                    }


                })
                opton.show()
                return true
            }

        })

    }

    fun addTrans(transaction: ArrayList<TransactionModal>) {
        this.transaction = transaction
    }

    fun update(transaction: ArrayList<TransactionModal>) {

        this.transaction = transaction

        notifyDataSetChanged()
    }
}
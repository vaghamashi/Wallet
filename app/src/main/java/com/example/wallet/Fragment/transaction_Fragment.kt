package com.example.wallet.Fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallet.Adapter.TransAdapter
import com.example.wallet.DBHelper.DBhelper
import com.example.wallet.Modal.TransactionModal
import com.example.wallet.databinding.FragmentTransactionBinding
import com.example.wallet.databinding.UpdateItemBinding

class transaction_Fragment : Fragment() {

    lateinit var binding: FragmentTransactionBinding
    lateinit var adapter: TransAdapter
    lateinit var dbHelper: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        dbHelper = DBhelper(context)
        adapter = TransAdapter({

            dbHelper.deleteTransaction(it)
            adapter.update(dbHelper.getTransaction())

        }, {
            var dialog = Dialog(requireContext())
            var bind = UpdateItemBinding.inflate(layoutInflater)
            dialog.setContentView(bind.root)


            it.apply {
                bind.apply {
                    editAmt.setText(amt.toString())
                    editCart.setText(category)
                    editNote.setText(note)


                    btnUpdate.setOnClickListener {
                        var name2 = editName.text.toString()
                        var amt2 = editAmt.text.toString().toInt()
                        var category2 = editCart.text.toString()
                        var note2 = editNote.text.toString()

                        var modal = TransactionModal(id, name2, amt2, category2, note2, isExpense,time)

                        dbHelper.updateTransaction(modal)
                        adapter.update(dbHelper.getTransaction())
                        dialog.dismiss()
                    }
                }
            }
            dialog.show()

        })







        adapter.addTrans(dbHelper.getTransaction())

        binding.rcvlist.layoutManager = LinearLayoutManager(context)
        binding.rcvlist.adapter = adapter

        return binding.root
    }

}


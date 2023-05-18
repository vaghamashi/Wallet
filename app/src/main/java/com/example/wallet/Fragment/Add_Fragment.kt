package com.example.wallet.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wallet.DBHelper.DBhelper
import com.example.wallet.Modal.TransactionModal
import com.example.wallet.databinding.FragmentAddBinding
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup


class Add_Fragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var db : DBhelper
    var isexpense =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        db = DBhelper(context)

        binding.btnNext.setOnClickListener {
            var name = binding.editName.text.toString()
            var amt = binding.editAmt.text.toString().toInt()
            var category = binding.editCart.text.toString()
            var note = binding.editNote.text.toString()

            var data = TransactionModal(0, name,amt, category, note, isexpense)

            db.addTrans(data)
        }


        binding.groupChoices.setOnCheckedChangeListener(object :SingleSelectToggleGroup.OnCheckedChangeListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onCheckedChanged(group: SingleSelectToggleGroup?, checkedId: Int) {

                if (binding.choiceA.id == checkedId){
                    isexpense =0
                    binding.btnNext.text = "ADD Income"
                }else if (binding.choiceB.id == checkedId)
                    isexpense = 1
                    binding.btnNext.text = "ADD Expense"
            }


        })
        return  binding.root
    }


}
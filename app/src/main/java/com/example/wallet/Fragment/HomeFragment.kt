package com.example.wallet.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallet.Adapter.MainTransAdapter
import com.example.wallet.DBHelper.DBhelper
import com.example.wallet.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: MainTransAdapter
    lateinit var dbHelper: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        dbHelper = DBhelper(context)
        updateDashboard()

        adapter = MainTransAdapter()


        adapter.addtruns(dbHelper.getTransaction())


        binding.rcvlist.layoutManager = LinearLayoutManager(context)
        binding.rcvlist.adapter = adapter


        return binding.root
    }


    fun updateDashboard() {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        dbHelper = DBhelper(context)
        var transList = dbHelper.getTransaction()
        var totalIncome = 0
        var totalExpense = 0

        for (addTrans in transList) {

            if (addTrans.isExpense == 0) {
                totalIncome += addTrans.amt
            } else if (addTrans.isExpense == 1) {
                totalExpense += addTrans.amt
            }

        }

        var totalbalance = (totalIncome - totalExpense).toString()
        binding.income.text = totalIncome.toString().toInt().toString()
        binding.expense.text = totalExpense.toString().toInt().toString()
        binding.amt.text = totalbalance.toInt().toString()


    }
}






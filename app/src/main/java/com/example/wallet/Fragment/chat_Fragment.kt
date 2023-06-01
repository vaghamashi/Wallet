package com.example.wallet.Fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallet.Adapter.MainTransAdapter
import com.example.wallet.DBHelper.DBhelper
import com.example.wallet.R
import com.example.wallet.databinding.FragmentChatBinding
import com.example.wallet.databinding.FragmentHomeBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import ir.mahozad.android.PieChart
import java.lang.reflect.Modifier


/**
 * A simple [Fragment] subclass.
 * Use the [chat_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class chat_Fragment : Fragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var dbHelper: DBhelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentChatBinding.inflate(layoutInflater)

        dbHelper = DBhelper(context)
        var transList = dbHelper.getTransaction()
        var totalIncome = 0
        var totalExpense = 0

        for (trans in transList) {

            if (trans.isExpense == 0) {
                totalIncome += trans.amt
            } else if (trans.isExpense == 1) {
                totalExpense += trans.amt
            }

        }

        var overall = (totalIncome - totalExpense).toString()

        binding.chart.setUsePercentValues(true);
        binding.chart.getDescription().setEnabled(false);
        binding.chart.setExtraOffsets(5F, 10F, 5F, 5F);

        binding.chart.setDragDecelerationFrictionCoef(0.95f);

        binding.chart.setCenterText(overall);

        binding.chart.setDrawHoleEnabled(true);
        binding.chart.setHoleColor(Color.TRANSPARENT);

        binding.chart.setTransparentCircleColor(Color.BLACK);
        binding.chart.setTransparentCircleAlpha(110);

        // 58 61
        binding.chart.setHoleRadius(58f);
        binding.chart.setTransparentCircleRadius(61f);

        binding.chart.setDrawCenterText(true);

        binding.chart.setRotationAngle(0F);
        binding.chart.setRotationEnabled(true);
        binding.chart.setHighlightPerTapEnabled(true);

        val colors = ArrayList<Int>()

        colors.add(Color.BLUE)
        colors.add(Color.parseColor("#FF94EA98"))

        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(totalExpense.toFloat(), totalExpense.toString()))
        entries.add(PieEntry(totalIncome.toFloat(), totalIncome.toString()))
        val dataSet = PieDataSet(entries, "Income")
        dataSet.setColors(colors)
        var pieData = PieData(dataSet)
        binding.chart.data = pieData

        return binding.root

    }


}



package com.example.wallet.Fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.wallet.DBHelper.DBhelper
import com.example.wallet.Modal.TransactionModal
import com.example.wallet.databinding.FragmentAddBinding
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale


class Add_Fragment : Fragment(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: FragmentAddBinding
    lateinit var db: DBhelper
    var isexpense = 0
    var calendar = Calendar.getInstance()
    var formatter = SimpleDateFormat("MMM. dd, yyyy", Locale.US)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        db = DBhelper(context)

        //  var dialog = Dialog(requireContext())

        binding.setDate.setOnClickListener {

            DatePickerDialog(
                requireContext(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
                .show()

        }


        binding.btnNext.setOnClickListener {
            var name = binding.editName.text.toString()
            var amt = binding.editAmt.text.toString().toInt()
            var category = binding.editCart.text.toString()
            var note = binding.editNote.text.toString()
            var time = binding.editDate.text.toString()

            var data = TransactionModal(0, name, amt, category, note,isexpense,time)

            db.addTrans(data)
        }


        binding.groupChoices.setOnCheckedChangeListener(object :SingleSelectToggleGroup.OnCheckedChangeListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onCheckedChanged(group: SingleSelectToggleGroup?, checkedId: Int) {

                if (binding.choiceA.id == checkedId) {
                    isexpense = 0
                    binding.btnNext.text = "ADD Income"
                } else if (binding.choiceB.id == checkedId)
                    isexpense = 1
                binding.btnNext.text = "ADD Expense"
            }


        })





        return binding.root

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {


        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)


        val slectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(calendar.time)
        val slectedDateBundle = Bundle()
        slectedDateBundle.putString("",slectedDate)

        val mCalendar = Calendar.getInstance()
        mCalendar[Calendar.YEAR] = year
        mCalendar[Calendar.MONTH] = month
        mCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        val selectedDate: String =
            DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.time)
        binding.editDate.setText(selectedDate)
    }




}



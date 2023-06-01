package com.example.wallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wallet.Fragment.Add_Fragment
import com.example.wallet.Fragment.HomeFragment
import com.example.wallet.Fragment.chat_Fragment
import com.example.wallet.Fragment.transaction_Fragment
import com.example.wallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var fragment: Add_Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadFragments(HomeFragment())

        initView()
    }

    fun initView() {
        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item) {

                0 -> {
                    loadFragments(HomeFragment())
                }

                1 -> {
                    loadFragments(Add_Fragment())
                }

                2 -> {
                    loadFragments(chat_Fragment())
                }
                3 -> {
                    loadFragments(transaction_Fragment())
                }


            }
        }

    }

    private fun loadFragments(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.view, fragment)
            .commit()

    }

    }

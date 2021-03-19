package com.example.homework4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.fragments.ColorChoiseFragment
import com.example.homework4.fragments.ColoristFragment
import com.example.homework4.fragments.FirstFragment
import com.example.homework4.fragments.ListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

interface RecyclerViewInterface {
    fun doRecView()
}

class MainActivity : AppCompatActivity(), RecyclerViewInterface {

    private val listFragment = ListFragment()
    private val coloristFragment = ColoristFragment()
    private val firstFragment = FirstFragment()
    private val brushFragment = ColorChoiseFragment()
    private val colorsList = mutableListOf<Int>()
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var rcView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        colorsList.add(Color.RED)
        colorsList.add(Color.GREEN)
        colorsList.add(Color.MAGENTA)
        colorsList.add(Color.BLACK)
        colorsList.add(Color.BLUE)
        colorsList.add(Color.GRAY)
        colorsList.add(Color.YELLOW)
        colorsList.add(Color.CYAN)
        colorsList.add(Color.WHITE)
        colorsList.add(Color.rgb(r,g,b))
        replaceFragment(firstFragment)
    }

    override fun doRecView() {
        rcView = findViewById(R.id.rc_view)
        rcView.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false)
        rcView.adapter = ColorsAdapter(colorsList, this)
    }

    override fun onStart() {
        super.onStart()
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_list_of_colorst -> replaceFragment(listFragment)
                R.id.ic_quiz -> replaceFragment(coloristFragment)
                R.id.ic_brush -> replaceFragment(brushFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
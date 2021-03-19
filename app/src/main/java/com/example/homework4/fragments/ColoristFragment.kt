package com.example.homework4.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.homework4.R
import kotlin.random.Random

private const val POSITION = "position"

class ColoristFragment : Fragment() {

    private lateinit var mainColor: ViewGroup
    private lateinit var textBuffer: TextView
    private lateinit var textCount: TextView
    private lateinit var buttonResultHex: Button
    private lateinit var buttonResultHex2: Button
    private var position = 0
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION)
        }
    }

    override fun onStart() {
        super.onStart()
        mainColor = view!!.findViewById(R.id.background_layout)
        buttonResultHex = view!!.findViewById(R.id.button_result_hex)
        buttonResultHex2 = view!!.findViewById(R.id.button_result_hex_2)
        textCount = view!!.findViewById(R.id.count)
        textBuffer = view!!.findViewById(R.id.text_buffer)
        createBackgroundColor(buttonResultHex, buttonResultHex2)

        buttonResultHex.setOnClickListener {
            countResult(buttonResultHex.text.toString(), textBuffer.text.toString())
            createBackgroundColor(buttonResultHex, buttonResultHex2)
        }
        buttonResultHex2.setOnClickListener {
            countResult(buttonResultHex2.text.toString(), textBuffer.text.toString())
            createBackgroundColor(buttonResultHex, buttonResultHex2)
        }
    }

    private fun getRandomColor():Int {
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        val i = Color.rgb(r,g,b)
        return i
    }

    private fun createBackgroundColor(button1: Button, button2: Button) {
        val rightColor = getRandomColor()
        val noRightColor = getRandomColor()

        fun getHexTextOfBackgroundColor() : String {
            val hex = Integer.toHexString(rightColor)
            val text = correctHexText(hex)
            return text
        }

        fun getHexTextOfBackgroundColor2() : String {
            val hex = Integer.toHexString(noRightColor)
            val text = correctHexText(hex)
            return text
        }

        val arrayButtons : Array<Button> = arrayOf(button1, button2)
        fun randomElement() {
            val randomValue = Random.nextInt(arrayButtons.size)
            val f = arrayButtons.indexOf(arrayButtons[randomValue])
            val remainingValue = arrayButtons[arrayButtons.size - f - 1]
            arrayButtons[randomValue].text = getHexTextOfBackgroundColor()
            remainingValue.text = getHexTextOfBackgroundColor2()
        }
        mainColor.setBackgroundColor(rightColor)
        textBuffer.text = getHexTextOfBackgroundColor()
        randomElement()
    }

    private fun correctHexText(text: String) : String {
        val t = text.substring(1)
        val itogText = "#" + t.substring(1)
        return itogText
    }

    private fun countResult(text1 : String, text2: String) {
        if(text1 == text2) {
            count++
        } else if (text1 != text2)
            count --
        textCount.text = count.toString()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_colorist, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(position: Int) =
            ColoristFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                }
            }
    }
}
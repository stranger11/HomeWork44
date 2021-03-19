package com.example.homework4

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ContentActivity : AppCompatActivity() {

    private lateinit var color: ViewGroup
    private lateinit var textHex: TextView
    private lateinit var buttonBuffer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        color = findViewById(R.id.color)
        textHex = findViewById(R.id.text_hex)
        textHex.text = getHexText()
        buttonBuffer = findViewById(R.id.button_buffer)
    }

    override fun onStart() {
        super.onStart()
        color.setBackgroundColor(intent.getIntExtra("color", R.color.black))
        textHex.text = getHexText()
        buttonBuffer.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", textHex.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this,
                    "Скопировано в буфер обмена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getHexText() : String {
        val hex = Integer.toHexString(
            intent.getIntExtra("color", R.color.black)
        ).toString()
        val text = correctHexText(hex)
        return text.toUpperCase()
    }


    private fun correctHexText(text: String) : String {
        val t = text.substring(1)
        return "#" + t.substring(1)
    }
}

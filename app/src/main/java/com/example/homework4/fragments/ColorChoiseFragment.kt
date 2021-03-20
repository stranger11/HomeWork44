package com.example.homework4.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.homework4.R

private const val POSITION = "position"

class ColorChoiseFragment : Fragment() {

    private lateinit var bitmap: Bitmap
    private lateinit var imageView: ImageView
    private lateinit var colorView: View
    private lateinit var resultTv: TextView
    private lateinit var button: Button
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onStart() {
        super.onStart()
        imageView = view!!.findViewById(R.id.image_view)
        colorView = view!!.findViewById(R.id.color_view)
        resultTv = view!!.findViewById(R.id.result_tv)
        button = view!!.findViewById(R.id.button_share)
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache(true)

        imageView.setOnTouchListener { _, event ->
            button.visibility = View.VISIBLE
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                bitmap = imageView.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)
                val hex = Integer.toHexString(pixel)
                colorView.setBackgroundColor(Color.rgb(r,g,b))
                resultTv.text = correctHexText(hex.toUpperCase())
            }
            true
        }

        button.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, resultTv.text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }
    }

    private fun correctHexText(text: String) : String {
        val t = text.substring(1)
        return "#" + t.substring(1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_color_choise, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(position: Int) =
                ColorChoiseFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, position)
                    }
                }
    }
}
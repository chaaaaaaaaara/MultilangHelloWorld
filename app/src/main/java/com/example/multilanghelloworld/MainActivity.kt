package com.example.multilanghelloworld

import android.app.Activity          // 改这里
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Locale

class MainActivity : Activity() {    // 改这里

    private var index = 0

    private val locales = arrayOf(
        Locale.SIMPLIFIED_CHINESE,
        Locale.ENGLISH,
        Locale.FRENCH
    )

    private val flags = intArrayOf(
        R.drawable.flag_cn,
        R.drawable.flag_en,
        R.drawable.flag_fr
    )

    private lateinit var flagIv: ImageView
    private lateinit var helloTv: TextView
    private lateinit var switchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(48, 48, 48, 48)
            setBackgroundColor(Color.WHITE)
        }

        val titleTv = TextView(this).apply {
            text = "梁珂维2024110520"
            textSize = 24f
            gravity = Gravity.CENTER
            setTextColor(Color.BLACK)
        }

        flagIv = ImageView(this).apply {
            setImageResource(flags[index])
            layoutParams = LinearLayout.LayoutParams(360, 240).apply {
                topMargin = 32
                bottomMargin = 32
            }
            scaleType = ImageView.ScaleType.FIT_CENTER
        }

        helloTv = TextView(this).apply {
            text = localizedString(locales[index], R.string.hello)
            textSize = 32f
            gravity = Gravity.CENTER
            setTextColor(Color.BLACK)
        }

        switchBtn = Button(this).apply {
            text = localizedString(locales[index], R.string.switch_language)
            setOnClickListener {
                index = (index + 1) % locales.size
                flagIv.setImageResource(flags[index])
                helloTv.text = localizedString(locales[index], R.string.hello)
                switchBtn.text = localizedString(locales[index], R.string.switch_language)
            }
        }

        root.addView(titleTv)
        root.addView(flagIv)
        root.addView(helloTv)
        root.addView(switchBtn)

        setContentView(root)
    }

    private fun localizedString(locale: Locale, resId: Int): String {
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        return createConfigurationContext(config).resources.getString(resId)
    }
}
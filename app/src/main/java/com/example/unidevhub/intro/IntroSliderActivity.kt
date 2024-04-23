package com.example.unidevhub.intro

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.unidevhub.MainActivity
import com.example.unidevhub.R
import com.example.unidevhub.auth.LoginActivity
import com.example.unidevhub.databinding.ActivityIntroSliderBinding

class IntroSliderActivity : AppCompatActivity() {


    private lateinit var binding : ActivityIntroSliderBinding
    private lateinit var layouts :IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroSliderBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        layouts = intArrayOf(
            R.layout.intro_layout1,
            R.layout.intro_layout2,
            R.layout.intro_layout3
        )


        binding.viewPager.adapter = IntroAdapter(this,layouts)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            @SuppressLint("MissingSuperCall")
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        binding.nextText.text = getString(R.string.next)
                        binding.nextText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                            AppCompatResources.getDrawable(this@IntroSliderActivity,R.drawable.ic_arrow_right_double), null)
                    }

                    1->{
                        binding.nextText.text = getString(R.string.next)
                        binding.nextText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                            AppCompatResources.getDrawable(this@IntroSliderActivity,R.drawable.ic_arrow_right_double), null)
                    }

                    2->{
                        binding.nextText.text = getString(R.string.start)
                        binding.nextText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                            AppCompatResources.getDrawable(this@IntroSliderActivity,R.drawable.ic_arrow_right_single), null)
                    }

                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


        binding.nextText.setOnClickListener {
            when(binding.viewPager.currentItem){
                0->{
                    binding.viewPager.currentItem = 1
                }
                1->{
                    binding.viewPager.currentItem = 2
                }
                2->{
                    startActivity(Intent(this@IntroSliderActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }



    }

    inner class IntroAdapter(private val context: Context, private val layout:IntArray) : PagerAdapter(){

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view  = layoutInflater.inflate(layout[position], container, false)
            container.addView(view)
            return view
        }


        override fun getCount(): Int {
            return layout.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }

    }

}
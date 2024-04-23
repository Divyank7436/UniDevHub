package com.example.unidevhub.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidevhub.R
import com.example.unidevhub.adapters.GlobalCommunityAdapter
import com.example.unidevhub.adapters.RecyclerviewAdapter
import com.example.unidevhub.databinding.ActivityGlobalCommunityBinding
import com.example.unidevhub.models.GlobalCommunity
import com.example.unidevhub.models.UserData

class GlobalCommunityActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGlobalCommunityBinding
    private var communityRecycler: RecyclerView? = null
    private var recyclerviewAdapter: GlobalCommunityAdapter? = null
    private var searchView: EditText? = null
    private var search: CharSequence = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalCommunityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchView = binding.searchBar

        val userDataList: MutableList<GlobalCommunity> = ArrayList<GlobalCommunity>()
        userDataList.add(
            GlobalCommunity(
                "Java",
                "Followers:213K",
                R.drawable.community6
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Phython",
                "Followers:203K",
                R.drawable.community7
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Javascript",
                "Followers:194K",
                R.drawable.community5
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Flutter",
                "Followers:188K",
                R.drawable.community2
            )
        )
        userDataList.add(
            GlobalCommunity(
                "React",
                "Followers:176K",
                R.drawable.community1
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Kotlin",
                "Followers:172K",
                R.drawable.community3
            )
        )
        userDataList.add(
            GlobalCommunity(
                "C++",
                "Followers:169K",
                R.drawable.community9
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Swift",
                "Followers:157K",
                R.drawable.community4
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Ruby",
                "Followers:143K",
                R.drawable.community10
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Rust",
                "Followers:129K",
                R.drawable.community11
            )
        )
        userDataList.add(
            GlobalCommunity(
                "C",
                "Followers:117K",
                R.drawable.community8
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Typescript",
                "Followers:114K",
                R.drawable.community13
            )
        )
        userDataList.add(
            GlobalCommunity(
                "Go",
                "Followers:107K",
                R.drawable.community12
            )
        )





        setUserRecycler(userDataList)


        searchView!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                recyclerviewAdapter!!.filter.filter(charSequence)
                search = charSequence
            }

            override fun afterTextChanged(editable: Editable) {}
        })



    }


    private fun setUserRecycler(userDataList: List<GlobalCommunity>) {
        communityRecycler = binding.globalCommunityRecycler
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        communityRecycler!!.setLayoutManager(layoutManager)
        recyclerviewAdapter = GlobalCommunityAdapter(this, userDataList)
        communityRecycler!!.setAdapter(recyclerviewAdapter)
    }

}
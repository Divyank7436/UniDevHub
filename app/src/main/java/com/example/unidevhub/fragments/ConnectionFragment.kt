package com.example.unidevhub.fragments



import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidevhub.R
import com.example.unidevhub.adapters.RecyclerviewAdapter
import com.example.unidevhub.databinding.FragmentConnectionBinding
import com.example.unidevhub.models.UserData


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectionFragment : Fragment() {

    private var userRecycler: RecyclerView? = null
    private var recyclerviewAdapter: RecyclerviewAdapter? = null
    private var searchView: EditText? = null
    private var search: CharSequence = ""

    private lateinit var binding : FragmentConnectionBinding


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentConnectionBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConnectionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = binding.searchBar

        val userDataList: MutableList<UserData> = ArrayList<UserData>()
        userDataList.add(
            UserData(
                "Anderson Thomas",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_1
            )
        )
        userDataList.add(
            UserData(
                "Adams Green",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_2
            )
        )
        userDataList.add(
            UserData(
                "Laura Michelle",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_3
            )
        )
        userDataList.add(
            UserData(
                "Betty L",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_4
            )
        )
        userDataList.add(
            UserData(
                "Garcia Lewis",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_1
            )
        )
        userDataList.add(
            UserData(
                "Roberts Turner",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_2
            )
        )
        userDataList.add(
            UserData(
                "Mary Jackson",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_3
            )
        )
        userDataList.add(
            UserData(
                "Sarah Scott",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_4
            )
        )
        userDataList.add(
            UserData(
                "Anderson Thomas",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_1
            )
        )
        userDataList.add(
            UserData(
                "Adams Green",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_2
            )
        )
        userDataList.add(
            UserData(
                "Laura Michelle",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_3
            )
        )
        userDataList.add(
            UserData(
                "Betty L",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_male_4
            )
        )
        userDataList.add(
            UserData(
                "Garcia Lewis",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_1
            )
        )
        userDataList.add(
            UserData(
                "Roberts Turner",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_2
            )
        )
        userDataList.add(
            UserData(
                "Mary Jackson",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_3
            )
        )
        userDataList.add(
            UserData(
                "Sarah Scott",
                "Android is awesome and this is the part 3 of recyclerview.",
                R.drawable.photo_female_4
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
    private fun setUserRecycler(userDataList: List<UserData>) {
        userRecycler = binding.userRecycler
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        userRecycler!!.setLayoutManager(layoutManager)
        recyclerviewAdapter = RecyclerviewAdapter(context, userDataList)
        userRecycler!!.setAdapter(recyclerviewAdapter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConnectionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConnectionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
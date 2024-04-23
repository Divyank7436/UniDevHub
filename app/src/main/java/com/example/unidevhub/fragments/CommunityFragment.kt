package com.example.unidevhub.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unidevhub.activities.GlobalCommunityActivity
import com.example.unidevhub.activities.LocalCommunityActivity
import com.example.unidevhub.adapters.TrendsAdapter
import com.example.unidevhub.databinding.FragmentCommunityBinding
import com.example.unidevhub.models.trends

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommunityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding : FragmentCommunityBinding
    private lateinit var trendsAdapter: TrendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCommunityBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        binding.globalcommunitybtn.setOnClickListener {
            startActivity(Intent( requireContext(), GlobalCommunityActivity::class.java))
        }
        binding.localcommunitybtn.setOnClickListener {
            var i = Intent(requireContext(), LocalCommunityActivity::class.java)
            startActivity(i)
        }
    }

    private fun initRecyclerView() {
        var items : ArrayList<trends> = arrayListOf<trends>()
        items.add(0, trends("Future in AI, What will\n"+"tomorrow be like?", "The National", "trends"))
        items.add(1, trends("Important points in, \n"+"concluding the work contract", "Reuters", "trends2"))



        // Use binding.projects here instead of just projects
        binding.trendsRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        trendsAdapter = TrendsAdapter(items)
        binding.trendsRv.adapter = trendsAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommunityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
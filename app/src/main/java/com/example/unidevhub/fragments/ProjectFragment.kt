package com.example.unidevhub.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unidevhub.adapters.OngoingAdapter
import com.example.unidevhub.databinding.FragmentProjectBinding
import com.example.unidevhub.models.Project

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProjectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentProjectBinding
    private lateinit var adapterOngoing : OngoingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProjectBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun initRecyclerView() {
       var items : ArrayList<Project> = arrayListOf<Project>()
       items.add(0,Project("Food Delivery","April 3 2024", "58", "ongoing1"))
        items.add(1,Project("AI Recording","March 24 2024", "72", "ongoing2"))
        items.add(2,Project("Weather App","March 18 2024", "43", "ongoing3"))
        items.add(3,Project("E-Book App","Feb 24 2024", "96", "ongoing4"))
        binding.ongoingProjects.layoutManager = GridLayoutManager(context, 2)
        adapterOngoing = OngoingAdapter(items)
        binding.ongoingProjects.adapter = adapterOngoing



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_project, container, false)
        binding = FragmentProjectBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProjectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProjectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
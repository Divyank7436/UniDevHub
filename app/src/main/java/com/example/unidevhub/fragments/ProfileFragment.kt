package com.example.unidevhub.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unidevhub.adapters.CommunityAdapter
import com.example.unidevhub.adapters.ProjectAdapter
import com.example.unidevhub.databinding.FragmentProfileBinding
import com.example.unidevhub.models.Community

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentProfileBinding
    private lateinit var projectAdapter : ProjectAdapter
    private lateinit var communityAdapter : CommunityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun initRecyclerView() {
        var items : ArrayList<String> = arrayListOf<String>()
        items.add(0, "UI UX Designs")
        items.add(1, "Kotlin Projects")
        items.add(2, "Backend Projects")
        items.add(3, "Web Development")

        // Use binding.projects here instead of just projects
        binding.projects.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        projectAdapter = ProjectAdapter(items)
        binding.projects.adapter = projectAdapter
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initRecyclerViewCommunity()
    }

    private fun initRecyclerViewCommunity() {
        var items : ArrayList<Community> = arrayListOf<Community>()
        items.add(0, Community("Kotlin Developers" , "Active"))
        items.add(1, Community("React Developers", "Active"))
        items.add(2, Community("Flutter Developers", "Active"))
        items.add(3, Community("Cloud Developers","Active"))

        // Use binding.projects here instead of just projects
        binding.viewTeams.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        communityAdapter = CommunityAdapter(items)
        binding.viewTeams.adapter = communityAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
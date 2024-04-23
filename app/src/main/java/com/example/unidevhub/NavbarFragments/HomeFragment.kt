package com.example.unidevhub.NavbarFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unidevhub.R
import com.example.unidevhub.activities.MessageActivity
import com.example.unidevhub.adapters.ProjectsAdapter
import com.example.unidevhub.adapters.ShuffleAdapter
import com.example.unidevhub.adapters.TrendsAdapter
import com.example.unidevhub.databinding.FragmentCommunityBinding
import com.example.unidevhub.databinding.FragmentHomeBinding
import com.example.unidevhub.models.Post
import com.example.unidevhub.models.Project1
import com.example.unidevhub.models.trends

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var projectsAdapter: ProjectsAdapter
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }




        private fun initRecyclerView() {
            val projects = listOf(
                Project1("Weather App", "A mobile app to check weather forecasts. This app provides hourly and daily weather updates, radar maps, and severe weather alerts.", "Beginner", "John Doe", "photo_male_1"),
                Project1("To-Do List", "An application to manage tasks and to-do lists. This app allows users to create, edit, and delete tasks, set reminders, and organize tasks into categories.", "Intermediate", "Emily Smith", "photo_female_2"),
                Project1("Recipe Finder", "An app to discover and save recipes. Users can search for recipes by ingredients, cuisine, or dietary preferences. They can also save favorite recipes and create shopping lists.", "Intermediate", "Michael Johnson", "photo_male_3"),
                Project1("Fitness Tracker", "A fitness app to track workouts and progress. This app allows users to log exercises, set fitness goals, track calories burned, and monitor progress over time.", "Advanced", "Sophia Williams", "photo_female_4"),
                Project1("Language Learning", "An app to learn new languages with interactive lessons. Users can practice vocabulary, grammar, and pronunciation through quizzes, games, and audio exercises.", "Intermediate", "David Brown", "photo_male_2"),
                Project1("Budget Manager", "An app to manage personal finances and expenses. Users can track income and expenses, set budgets, view spending trends, and receive financial insights.", "Advanced", "Emma Taylor", "photo_female_3"),
                Project1("Social Media Analyzer", "An application to analyze social media trends and engagement. Users can track followers, likes, comments, and shares across various social media platforms.", "Advanced", "Matthew Martinez", "photo_male_4"),
                Project1("Music Player", "A music player app with features like playlists and equalizer. Users can create playlists, shuffle songs, adjust playback settings, and explore new music recommendations.", "Intermediate", "Olivia Anderson", "photo_female_1"),
                Project1("Job Finder", "An app to search and apply for job opportunities. Users can browse job listings, filter by location and industry, save favorite jobs, and submit applications.", "Beginner", "Daniel Clark", "photo_male_3"),
                Project1("Virtual Assistant", "An AI-powered virtual assistant to help users with tasks. This assistant can answer questions, set reminders, provide recommendations, and perform actions based on user commands.", "Advanced", "Sophia Johnson", "photo_female_2"),
                Project1("E-commerce Platform", "An online shopping platform for buying and selling products. Users can browse products, add items to cart, make secure payments, and track order status.", "Intermediate", "James Wilson", "photo_male_1"),
                Project1("Travel Planner", "An app to plan and organize trips and travel itineraries. Users can search for destinations, book flights and accommodations, create trip plans, and share travel experiences.", "Intermediate", "Emma Lee", "photo_female_4"),
                Project1("Health Tracker", "An app to track health metrics like steps, sleep, and heart rate. Users can monitor their daily activity, set fitness goals, and receive insights to improve overall health.", "Advanced", "Ethan Harris", "photo_male_2"),
                Project1("Photo Editor", "A mobile photo editing app with filters and effects. Users can edit photos, apply filters, adjust colors, add text and stickers, and share edited photos on social media.", "Intermediate", "Ava Brown", "photo_female_3"),
                Project1("Note-taking App", "An application to create and organize notes and memos. Users can write, edit, and organize notes by categories, set reminders, and sync notes across devices.", "Beginner", "Noah Miller", "photo_male_4")
            )


            val posts = listOf(
                Post("Emma Taylor", "photo_female_3", "Kotlin Native Android Developers", "The Kotlin community is vibrant and supportive, known for its enthusiasm in adopting modern programming practices and sharing resources for both beginners and experts alike.", "postimage1", "", 10, false),
                Post("James Wilson", "photo_male_4", "React developers", "The React community is vast and diverse, renowned for its innovation, continuous evolution, and extensive ecosystem of libraries and tools, fueled by the dedication of developers worldwide.\n" +
                        "\n", "postimage3", "", 17, false),
                Post("Matthew Martinez", "photo_male_1", "Flutter Hybrid Developers", "The Flutter community is passionate and rapidly growing, characterized by its collaborative spirit, extensive documentation, and active engagement in sharing plugins and widgets." , "postimage5", "", 23, false),
                Post("Thomas Andrew", "photo_male_2", "KMP vs Flutter vs React", "KMP: Streamlines code sharing across platforms, integrating seamlessly with Kotlin projects while supporting platform-specific UI. Flutter: Google-supported, offers customizable widgets and fast development with hot reload. " , "postimage4", "", 23, false),





                )

            val adapter = ShuffleAdapter(requireContext(), projects, posts)
            binding.recyclerProjects.layoutManager = LinearLayoutManager(context)
            binding.recyclerProjects.adapter = adapter

            binding.messengerBtn.setOnClickListener {
                startActivity(Intent(context,MessageActivity::class.java))
            }

        }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.unidevhub.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unidevhub.R
import com.example.unidevhub.models.Project1
import com.example.unidevhub.utils.ItemAnimation

class ProjectsAdapter(private val context: Context, private val projects: List<Project1>) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.titleTextView.text = project.title
        holder.descriptionTextView.text = project.description
        holder.difficultyTextView.text = "Difficulty: ${project.difficulty}"
        holder.username.text = project.username
        val drawableResourceId = holder.itemView.resources.getIdentifier(
            project.userImage,
            "drawable",
            context.packageName
        )

        Glide.with(context).load(drawableResourceId).into(holder.userImage)
        ItemAnimation.animateFadeIn(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView_project_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textView_project_description)
        val difficultyTextView: TextView = itemView.findViewById(R.id.textView_difficulty)
        val userImage : ImageView = itemView.findViewById(R.id.imageView_user)
        val username : TextView = itemView.findViewById(R.id.textView_username)
    }


}

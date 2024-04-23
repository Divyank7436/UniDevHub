package com.example.unidevhub.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unidevhub.R
import com.example.unidevhub.activities.ProjectsActivity
import com.example.unidevhub.models.Post
import com.example.unidevhub.models.Project1
import com.example.unidevhub.utils.ItemAnimation
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ShuffleAdapter(
    private val context: Context,
    private val projects: List<Project1>,
    private val posts: List<Post>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_PROJECT = 0
        const val TYPE_POST = 1
    }

    private val shuffledItems: MutableList<Any> = mutableListOf()

    init {
        val shuffledProjects = projects.shuffled()
        val shuffledPosts = posts.shuffled()

        val minSize = minOf(shuffledProjects.size, shuffledPosts.size)
        for (i in 0 until minSize) {
            shuffledItems.add(shuffledProjects[i])
            shuffledItems.add(shuffledPosts[i])
        }

        if (shuffledProjects.size > minSize) {
            shuffledItems.addAll(shuffledProjects.subList(minSize, shuffledProjects.size))
        } else if (shuffledPosts.size > minSize) {
            shuffledItems.addAll(shuffledPosts.subList(minSize, shuffledPosts.size))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PROJECT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
                ProjectViewHolder(view)
            }
            TYPE_POST -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
                PostViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_PROJECT -> {
                val project = shuffledItems[position] as Project1
                val projectHolder = holder as ProjectViewHolder
                projectHolder.bind(project)
            }
            TYPE_POST -> {
                val post = shuffledItems[position] as Post
                val postHolder = holder as PostViewHolder
                postHolder.bind(post)
            }
        }
        ItemAnimation.animateFadeIn(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return shuffledItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (shuffledItems[position] is Project1) {
            TYPE_PROJECT
        } else {
            TYPE_POST
        }
    }

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView_project_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textView_project_description)
        val difficultyTextView: TextView = itemView.findViewById(R.id.textView_difficulty)
        val userImage: ImageView = itemView.findViewById(R.id.imageView_user)
        val username: TextView = itemView.findViewById(R.id.textView_username)


        fun bind(project: Project1) {
            titleTextView.text = project.title
            descriptionTextView.text = project.description
            difficultyTextView.text = "Difficulty: ${project.difficulty}"
            username.text = project.username
            val drawableResourceId = itemView.resources.getIdentifier(
                project.userImage,
                "drawable",
                context.packageName
            )
            Glide.with(context).load(drawableResourceId).into(userImage)
            itemView.setOnClickListener {
                val intent = Intent(context,ProjectsActivity::class.java)
                intent.putExtra("projectName", project.title)
                context.startActivity(intent)

            }


        }
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.journal_row_username)
        val userProfileImageView: ImageView = itemView.findViewById(R.id.profileDp)
        val postImageView: ImageView = itemView.findViewById(R.id.journal_image_list)
        val titleTextView: TextView = itemView.findViewById(R.id.journal_title_list)
        val descriptionTextView: TextView = itemView.findViewById(R.id.journal_thought_list)
        val likesTextView: TextView = itemView.findViewById(R.id.number_of_likes)
        val likeButton: ImageButton = itemView.findViewById(R.id.like_btn)
        val likeTextStatus: TextView = itemView.findViewById(R.id.textView5)
        val commentButton: ImageButton = itemView.findViewById(R.id.comment_btn)
        val shareButton: ImageButton = itemView.findViewById(R.id.journal_row_share_button)
        val saveButton: ImageButton = itemView.findViewById(R.id.share_btn)
        val exoPlayerView: PlayerView = itemView.findViewById(R.id.journal_video_view)
        var exoPlayer: SimpleExoPlayer? = null



        fun bind(post: Post) {
            val drawableResourceId = itemView.resources.getIdentifier(
                post.userImage,
                "drawable",
                context.packageName
            )
            Glide.with(context).load(drawableResourceId).into(userProfileImageView)
            usernameTextView.text = post.username

            if (post.image.isNotEmpty()) {
                postImageView.visibility = View.VISIBLE
                val drawableResourceId = itemView.resources.getIdentifier(
                    post.image,
                    "drawable",
                    context.packageName
                )
                Glide.with(context).load(drawableResourceId).into(postImageView)
            } else {
                postImageView.visibility = View.GONE
            }

            if (post.video.isNotEmpty()) {
                exoPlayerView.visibility = View.VISIBLE
                postImageView.visibility = View.GONE

                if (exoPlayer == null) {
                    exoPlayer = SimpleExoPlayer.Builder(context).build()
                    exoPlayerView.player = exoPlayer
                }

                val videoUri = Uri.parse(post.video)
                val mediaItem = MediaItem.fromUri(videoUri)
                exoPlayer?.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)

                val dataSourceFactory = DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, "UnidevHub")
                )
                val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaItem)

                exoPlayer?.setMediaSource(mediaSource)
                exoPlayer?.prepare()
                exoPlayer?.play()
            } else {
                exoPlayerView.visibility = View.GONE
            }

            titleTextView.text = post.title
            descriptionTextView.text = post.description
            likesTextView.text = post.likes.toString()

            if (post.likeFlag) {
                likeButton.setImageResource(R.drawable.like_clicked)
                likeTextStatus.text = context.getString(R.string.liked)
            } else {
                likeButton.setImageResource(R.drawable.like)
                likeTextStatus.text = context.getString(R.string.like)
            }
        }
    }
}

package com.example.unidevhub.adapters

import android.content.Context
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
import com.example.unidevhub.models.Post
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PostAdapter(private val context: Context, private var postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    // ViewHolder class for each item in the RecyclerView
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
    }

    override fun onViewDetachedFromWindow(holder: PostViewHolder) {
        // Release ExoPlayer when the item goes out of view
        holder.exoPlayer?.release()
        holder.exoPlayer = null
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]

        // Set user profile photo and username
        Glide.with(context).load(post.userImage).into(holder.userProfileImageView)
        holder.usernameTextView.text = post.username

        // Set post image or video
        if (post.image.isNotEmpty()) {
            holder.postImageView.visibility = View.VISIBLE
            Glide.with(context).load(post.image).into(holder.postImageView)
        } else {
            holder.postImageView.visibility = View.GONE
        }

        if (post.video.isNotEmpty()) {
            holder.exoPlayerView.visibility = View.VISIBLE
            holder.postImageView.visibility = View.GONE

            // Initialize ExoPlayer
            if (holder.exoPlayer == null) {
                val exoPlayer = SimpleExoPlayer.Builder(context).build()
                holder.exoPlayerView.player = exoPlayer
                holder.exoPlayer = exoPlayer
            }

            // Create a MediaItem
            val videoUri = Uri.parse(post.video)
            val mediaItem = MediaItem.fromUri(videoUri)

            // Set the resize mode to "fill"
            holder.exoPlayer?.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)

            // Create a MediaSource
            val dataSourceFactory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, "UnidevHub")
            )
            val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaItem)

            // Prepare the player
            holder.exoPlayer?.setMediaSource(mediaSource)
            holder.exoPlayer?.prepare()
            holder.exoPlayer?.play()
        } else {
            holder.exoPlayerView.visibility = View.GONE
        }

        // Set post title and description
        holder.titleTextView.text = post.title
        holder.descriptionTextView.text = post.description

        // Set likes count
        holder.likesTextView.text = post.likes.toString()

        // Set like button state
        if (post.likeFlag) {
            holder.likeButton.setImageResource(R.drawable.like_clicked)
            holder.likeTextStatus.text = context.getString(R.string.liked)
        } else {
            holder.likeButton.setImageResource(R.drawable.like)
            holder.likeTextStatus.text = context.getString(R.string.like)
        }

    }

}


package com.grdj.devigettest.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.grdj.devigettest.R
import com.grdj.devigettest.domain.Children
import kotlinx.android.synthetic.main.post_item.view.*

class PostItemAdapter (private val listener: PostItemClickListener) : RecyclerView.Adapter<PostItemAdapter.MyViewHolder>() {

    private var items: ArrayList<Children>

    init {
        this.items = ArrayList<Children>()
    }

    fun setItems(items: ArrayList<Children>) {
        this.items = items
    }

    fun getItems(): ArrayList<Children> {
        return this.items
    }

    fun clearItems() {
        this.items = ArrayList<Children>()
    }

    fun getItem(position: Int): Children {
        return this.items.get(position)
    }

    fun removeItem(position: Int) {
        this.items.removeAt(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val viewHolderView = inflater.inflate(R.layout.post_item, parent, false)
        return MyViewHolder(viewHolderView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = items[position]
        holder.fetch(post, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun fetch(post: Children, listener: PostItemClickListener) {
            val res: Resources = itemView.resources
            val options = RequestOptions()
                .placeholder(getProgressDrawable(itemView.context))
                .error(R.drawable.no_image_white)

            Glide.with(itemView.context)
                .setDefaultRequestOptions(options)
                .load(post.data.thumbnail)
                .into(itemView.thumb)

            itemView.title.text = post.data.author
            itemView.time.text = convertToTimeAgo(res, post.data.created.toLong())
            itemView.descrption.text = post.data.title
            itemView.comments.text = "${post.data.num_comments} ${res.getString(R.string.comments_string)}"

            itemView.clickGroup.setAllOnClickListener(View.OnClickListener {
                listener.onPostClicked(adapterPosition)
            })

            itemView.dissmisGroup.setAllOnClickListener(View.OnClickListener {
                listener.onDeleteClicked(adapterPosition)
            })
        }
    }
}
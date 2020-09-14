package com.seiyria.reuuzei.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.seiyria.reuuzei.R
import com.seiyria.reuuzei.data.model.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * Created by Alireza Afkar on 16/9/2018AD.
 */
class AlbumAdapter(
    private var album: String?,
    private val listener: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var items = mutableListOf<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.bind(this, this.id == album, listener)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Album, selected: Boolean, listener: (Album) -> Unit) = with(itemView) {
            name.text = item.title
            tick.isVisible = selected

            val layoutParams = layoutParams as StaggeredGridLayoutManager.LayoutParams

            count.text = if (item.id.isEmpty()) {
                Picasso.get().load(R.drawable.tehran).into(image)
                layoutParams.isFullSpan = true
                this.context.getString(R.string.category_description)
            } else {
                Picasso.get().load(item.coverPhotoUrl).into(image)
                layoutParams.isFullSpan = false
                this.context.getString(R.string.items_count, item.itemsCount)
            }

            setOnClickListener { listener(item) }
        }
    }

    fun addItems(items: List<Album>) {
        val oldSize = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(oldSize, items.size)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setAlbum(album: String?) {
        this.album = album
        notifyDataSetChanged()
    }
}

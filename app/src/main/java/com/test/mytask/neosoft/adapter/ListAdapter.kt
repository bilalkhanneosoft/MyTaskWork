package com.test.mytask.neosoft.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.mytask.R
import com.test.mytask.databinding.ItemImageViewHolderBinding
import com.test.mytask.neosoft.repository.ImageModel
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter : RecyclerView.Adapter<ListAdapter.DeveloperViewHolder>() , Filterable {

    private var mDeveloperModel: ArrayList<ImageModel>? = null
    private lateinit var filteredImages: ArrayList<ImageModel>

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DeveloperViewHolder {
        val mDeveloperListItemBinding = DataBindingUtil.inflate<ItemImageViewHolderBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_image_view_holder, viewGroup, false
        )

        return DeveloperViewHolder(mDeveloperListItemBinding)
    }

    override fun onBindViewHolder(mDeveloperViewHolder: DeveloperViewHolder, i: Int) {
        val imageModel = mDeveloperModel!![i]
        mDeveloperViewHolder.mDeveloperListItemBinding.developerModel = imageModel
        mDeveloperViewHolder.mDeveloperListItemBinding.ivImage.setImageResource(imageModel.imgUrl)
        mDeveloperViewHolder.mDeveloperListItemBinding.tvName.text = imageModel.name
    }

    override fun getItemCount(): Int {
        return if (mDeveloperModel != null) {
            mDeveloperModel!!.size
        } else {
            0
        }
    }

    fun setDeveloperList(mDeveloperModel: ArrayList<ImageModel>) {
        filteredImages = mDeveloperModel!!
        this.mDeveloperModel = mDeveloperModel
        notifyDataSetChanged()
    }

    inner class DeveloperViewHolder(var mDeveloperListItemBinding: ItemImageViewHolderBinding) :
        RecyclerView.ViewHolder(mDeveloperListItemBinding.root)


    override fun getFilter(): Filter = FilterData()

    inner class FilterData : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            filteredImages = if (constraint == null || constraint.isEmpty()) {
                filteredImages
            } else {
                val newList = ArrayList<ImageModel>()
                filteredImages.filter {
                    it.name.lowercase().contains(constraint.toString().lowercase())
                }.forEach {
                    newList.add(it)
                }
                newList
            }

            return FilterResults().apply { values = filteredImages }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredImages =
                if (results?.values == null) arrayListOf() else results.values as ArrayList<ImageModel> /* = java.util.ArrayList<com.test.mytask.neosoft.repository.ImageModel> */
            notifyDataSetChanged()
        }
    }
}

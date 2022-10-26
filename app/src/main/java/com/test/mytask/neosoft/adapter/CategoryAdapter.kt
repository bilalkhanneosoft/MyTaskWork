package com.test.mytask.neosoft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.test.mytask.databinding.ItemViewHolderBinding
import com.test.mytask.neosoft.repository.DataSource
import com.test.mytask.neosoft.repository.ListDataModel

class CategoryAdapter(private val images: List<ListDataModel> = DataSource.getAllImages()) :
    PagerAdapter() {

    private lateinit var context: Context

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        context = container.context
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemViewHolderBinding.inflate(layoutInflater)
        binding.ivImage.setImageDrawable(ContextCompat.getDrawable(context, images[position].imgUrl))

        container.addView(binding.root)
        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view === `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View?)
    }
}
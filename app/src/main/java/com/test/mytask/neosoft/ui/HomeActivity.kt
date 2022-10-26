package com.test.mytask.neosoft.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.test.mytask.databinding.ActivityHomeBinding
import com.test.mytask.neosoft.adapter.ListAdapter
import com.test.mytask.neosoft.repository.DataSource
import com.test.mytask.neosoft.repository.ImageModel
import com.test.mytask.neosoft.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var viewModel: ViewModel
    private var mListAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        // init the Custom adataper
        mListAdapter = ListAdapter()
        // set the CustomAdapter
        mBinding.recyclerView.setAdapter(mListAdapter)

        initView()

        mBinding.etSearch.doOnTextChanged { text, start, before, count ->
            text?.let {
                mListAdapter!!.filter!!.filter(it.toString())
            }
        }

        mBinding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mListAdapter?.setDeveloperList(
                    DataSource.getAllImages().get(position).subList as ArrayList<ImageModel>
                )
            }
        })
    }

    private fun initView() {
        mBinding.recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        mBinding.recyclerView!!.setHasFixedSize(true)

        mBinding.viewPager.adapter = viewModel.categoryAdapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
        mListAdapter?.setDeveloperList(
            DataSource.getAllImages().get(0).subList as ArrayList<ImageModel>
        )
    }

}
package com.example.notbored.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.notbored.R
import com.example.notbored.databinding.ActivityItemBinding

class ActivitiesAdapter(private val mContext: Context, private val activitiesList: List<String>): ArrayAdapter<String>(mContext, R.layout.activity_item, activitiesList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item, parent, false)
        val binding = ActivityItemBinding.bind(layout)
        val activity = activitiesList[position]
        binding.activityName.text = activity

        return layout
    }
}

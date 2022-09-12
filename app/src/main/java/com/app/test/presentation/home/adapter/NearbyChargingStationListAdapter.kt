package com.app.test.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.test.databinding.SingleRowBinding
import com.app.test.domain.home.entity.Results

class NearbyChargingStationListAdapter(private val list:List<Results>):RecyclerView.Adapter<NearbyChargingStationListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SingleRowBinding) : RecyclerView.ViewHolder(binding.root)
    var onTapListener: OnItemTap? = null
    interface OnItemTap{
        fun onTap(position:Int, videoMetaData:Results)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearbyChargingStationListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(SingleRowBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: NearbyChargingStationListAdapter.ViewHolder, position: Int) {
        holder.binding.data = list[position]
        holder.binding.position = position
        holder.binding.clickListener = onTapListener
    }

    override fun getItemCount(): Int {
        return list.count()
    }

}
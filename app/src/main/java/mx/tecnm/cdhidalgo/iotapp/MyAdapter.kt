package mx.tecnm.cdhidalgo.iotapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataset: Array<Array<String?>>?, private val listener: ItemListener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, listener: ItemListener) :
        RecyclerView.ViewHolder(itemView) {
        var tvItemId: TextView
        var tvItemType: TextView
        var tvItemName: TextView
        var tvItemValue: TextView
        var tvItemDate: TextView
        var btnItemEdit: Button
        var btnItemDelete: Button
        init {
            tvItemId = itemView.findViewById(R.id.tvItemId)
            tvItemType = itemView.findViewById(R.id.tvItemType)
            tvItemName = itemView.findViewById(R.id.tvItemName)
            tvItemValue = itemView.findViewById(R.id.tvItemValue)
            tvItemDate = itemView.findViewById(R.id.tvItemDate)
            btnItemEdit = itemView.findViewById(R.id.btnItemEdit)
            btnItemDelete = itemView.findViewById(R.id.btnItemDelete)
            btnItemEdit.setOnClickListener { view -> listener.onEdit(view, adapterPosition) }
            btnItemDelete.setOnClickListener { view -> listener.onDel(view, adapterPosition) }
            itemView.setOnClickListener { view -> listener.onClick(view, adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor, parent, false)
        return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemId.text = dataset!![position][0]
        holder.tvItemName.text = dataset!![position][1]
        holder.tvItemType.text = dataset!![position][2]
        holder.tvItemValue.text = dataset!![position][3]
        holder.tvItemDate.text = dataset!![position][4]
    }

    override fun getItemCount(): Int {
        return dataset!!.size
    }
}
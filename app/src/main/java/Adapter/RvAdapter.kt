package Adapter

import Model.MyModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vohidov.discount.R
import kotlinx.android.synthetic.main.item_rv.view.*

class RvAdapter(var context: Context, var list: ArrayList<MyModel>, var itemClick: ItemClick) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    inner class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: MyModel, position: Int) {
            //Picasso.get().load(list[position]).into(itemView2.item_image)
            itemView.txt_name.text = list[position].name
            itemView.txt_number.text = list[position].number

            itemView.setOnClickListener {
                itemClick.call(list, position)
            }
            itemView.setOnLongClickListener {
                itemClick.addFavourite(list, position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun filterList(filteredList: ArrayList<MyModel>) {
        list = filteredList
        notifyDataSetChanged()
    }
}

interface ItemClick {
    fun call(list: ArrayList<MyModel>, position: Int)
    fun addFavourite(list: ArrayList<MyModel>, position: Int)
}
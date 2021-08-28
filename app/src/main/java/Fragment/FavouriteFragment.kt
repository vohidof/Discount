package Fragment

import Adapter.ItemClick
import Adapter.RvAdapter
import Model.MyModel
import Utils.MySharedPReference
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vohidov.discount.R
import kotlinx.android.synthetic.main.fragment_favourite.view.*


class FavouriteFragment : Fragment() {

    lateinit var root: View
    lateinit var adapter: RvAdapter
    lateinit var list:ArrayList<MyModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_favourite, container, false)

        MySharedPReference.init(root.context)
        list = MySharedPReference.objectString

        adapter = RvAdapter(root.context, list, object : ItemClick {
            override fun call(list: ArrayList<MyModel>, position: Int) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${list[position].number}")
                startActivity(intent)
            }

            override fun addFavourite(list: ArrayList<MyModel>, position: Int) {
                list.remove(list[position])
                MySharedPReference.objectString = list
                onResume()
                Toast.makeText(context, "Delete from favourite", Toast.LENGTH_SHORT).show()
            }
        })

        root.RecyclerView2.adapter = adapter
        onResume()

        return root
    }

    override fun onResume() {
        super.onResume()
        adapter = RvAdapter(root.context, list, object : ItemClick {
            override fun call(list: ArrayList<MyModel>, position: Int) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${list[position].number}")
                startActivity(intent)
            }

            override fun addFavourite(list: ArrayList<MyModel>, position: Int) {
                list.remove(list[position])
                MySharedPReference.objectString = list
                onResume()
                Toast.makeText(context, "Delete from favourite", Toast.LENGTH_SHORT).show()
            }
        })
        root.RecyclerView2.adapter = adapter
    }

}
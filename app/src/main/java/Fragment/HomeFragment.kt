package Fragment

import Adapter.ItemClick
import Adapter.RvAdapter
import Model.MyModel
import Utils.MyDbHelper
import Utils.MySharedPReference
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vohidov.discount.R
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    lateinit var root: View
    lateinit var myDbHelper: MyDbHelper
    lateinit var list: ArrayList<MyModel>
    lateinit var rvAdapter: RvAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false)

        myDbHelper = MyDbHelper(root.context)

        root.btn_add.setOnClickListener {
            val dialog = BottomSheetDialog(root.context)

            dialog.setContentView(
                layoutInflater.inflate(
                    R.layout.dialog_layout,
                    null,
                    false
                )
            )

            dialog.btn_save.setOnClickListener {
                val name = dialog.edt_name.text.toString()
                val number = dialog.edt_number.text.toString()

                val myModel = MyModel(name, number)
                myDbHelper.addNotes(myModel)
                Toast.makeText(context, "Saved note $name", Toast.LENGTH_SHORT).show()
                onResume()
            }

            dialog.show()
        }


        return root
    }

    override fun onResume() {
        super.onResume()
        list = ArrayList()
        list.addAll(myDbHelper.getAllNotes())
        MySharedPReference.init(root.context)


        rvAdapter = RvAdapter(root.context, list, object : ItemClick {
            override fun call(list: ArrayList<MyModel>, position: Int) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${list[position].number}")
                startActivity(intent)
            }

            override fun addFavourite(list: ArrayList<MyModel>, position: Int) {
                val name = list[position].name
                val number = list[position].number

                val listFavourite = MySharedPReference.objectString
                listFavourite.add(MyModel(name, number))
                MySharedPReference.objectString = listFavourite
                Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show()
            }
        })
        rvAdapter.notifyDataSetChanged()
        root.RecyclerView.adapter = rvAdapter
    }
}
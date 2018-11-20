package cat.devsofthecoast.teammanagementdemo.commons.controllers.autoupdatefields

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.Team


class TMDSpinnerAdapter(context: Context, @LayoutRes val resourceId: Int, @IdRes textViewRespurceId: Int, list: ArrayList<Team>) : ArrayAdapter<Team>(context, resourceId, textViewRespurceId, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var retView = convertView
        if (convertView == null) {
            retView = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val vh = ViewHolder()

            vh.tvName = retView.findViewById(R.id.tvName)
            vh.ivPicture = retView.findViewById(R.id.ivPicture)
            retView.tag = vh
        }
        val viewHolder = retView?.tag as ViewHolder

        val selectedItem = getItem(position)
        viewHolder.tvName?.text = selectedItem?.name


        return retView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var retView = convertView
        if (convertView == null) {
            retView = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val vh = ViewHolder()

            vh.tvName = retView.findViewById(R.id.tvName)
            vh.ivPicture = retView.findViewById(R.id.ivPicture)
            retView.tag = vh
        }
        val viewHolder = retView?.tag as ViewHolder

        val selectedItem = getItem(position)
        viewHolder.tvName?.text = selectedItem?.name


        return retView
    }

    class ViewHolder {
        var tvName: TextView? = null
        var ivPicture: ImageView? = null
    }
}
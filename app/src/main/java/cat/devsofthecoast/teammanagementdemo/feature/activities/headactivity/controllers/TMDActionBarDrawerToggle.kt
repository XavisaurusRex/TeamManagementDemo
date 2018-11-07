package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.controllers

import android.app.Activity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout

class TMDActionBarDrawerToggle(activity: Activity?, drawerLayout: DrawerLayout?, openDrawerContentDescRes: Int, closeDrawerContentDescRes: Int) : ActionBarDrawerToggle(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes) {

    var listener: OnDrawerChange? = null
    fun setOnDrawerStateChange(listener: OnDrawerChange){
        this.listener = listener
    }
    override fun onDrawerOpened(drawerView: View) {
        super.onDrawerOpened(drawerView)
        this.listener?.onDrawerOpened()
    }

    override fun onDrawerClosed(drawerView: View) {
        super.onDrawerClosed(drawerView)
        listener?.onDrawerClosed()
    }

    interface OnDrawerChange {
        fun onDrawerOpened()
        fun onDrawerClosed()
    }
}
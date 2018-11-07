package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.controllers

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class TMDDrawerLayout : DrawerLayout, TMDActionBarDrawerToggle.OnDrawerChange {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var opened = false
    private var listener: TMDActionBarDrawerToggle.OnDrawerChange? = null

    fun toggleState() {
        if (opened) {
            closeDrawer(GravityCompat.START)
        } else {
            openDrawer(GravityCompat.START)
        }
    }

    fun setOnChangeDrawerStateListener(listener: TMDActionBarDrawerToggle.OnDrawerChange){
        this.listener = listener
    }

    override fun onDrawerOpened() {
        opened = true
        listener?.onDrawerOpened()
    }

    override fun onDrawerClosed() {
        opened = false
        listener?.onDrawerClosed()
    }
}
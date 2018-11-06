package cat.devsofthecoast.teammanagementdemo.commons.controllers.toolbar

import android.content.Context
import android.util.AttributeSet
import android.widget.Toolbar
import androidx.annotation.AttrRes

class TMDToolbar: Toolbar {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {

    }


}
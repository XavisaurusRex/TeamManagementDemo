package cat.devsofthecoast.teammanagementdemo.commons.utilities

import java.util.*

class DateUtils {
    companion object {
        fun substractDays(calendar: Calendar, days: Int){
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DATE) - days,
                    0, 0, 0
            )
        }

    }
}
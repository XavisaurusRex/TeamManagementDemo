package cat.devsofthecoast.teammanagementdemo.commons.cache

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class TMDCache<K, V> {
    private val cache: HashMap<K, Data<V>> = hashMapOf()

    operator fun get(key: K): V? {
        return cache[key]?.value
    }

    operator fun set(key: K, value: V){
        cache[key] = Data(value)
    }

    operator fun contains(key: K): Boolean{
        return isValid(key)
    }

    private fun isValid(key: K): Boolean {
        return cache[key] != null && cache[key]!!.value != null && cache[key]!!.isValid
    }

    inner class Data<V> {
        private val DEFAULT_VALUE_TIMEOUT = TimeUnit.SECONDS.toMillis(60)
        private var timer = Timer()

        var timeout: Long = DEFAULT_VALUE_TIMEOUT
        var isValid: Boolean = false

        var value: V? = null
            set(value) {
                field = value
                startTimeout()
            }

        private constructor()

        constructor(value: V) : this() {
            this.value = value
        }

        private fun startTimeout() {
            isValid = true
            timer.cancel()
            timer = Timer()
            timer.schedule(timeout) {
                isValid = false
            }
        }

    }
}

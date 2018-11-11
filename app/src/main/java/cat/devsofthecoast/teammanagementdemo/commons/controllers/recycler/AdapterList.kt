package cat.devsofthecoast.teammanagementdemo.commons.controllers.recycler

interface AdapterList<T> {
    fun add(value: T)
    fun add(values: List<T>)

    fun remove(value: T)
    fun removeAt(position: Int)

    fun size(): Int
    fun removeAll()
}
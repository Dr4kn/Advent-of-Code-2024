// from https://www.baeldung.com/kotlin/multimap
class MultiMap<K, V> {
    private val map: MutableMap<K, MutableCollection<V>> = HashMap()

    fun put(key: K, value: V) {
        if (!map.containsKey(key)) {
            map[key] = HashSet()
        }
        map[key]?.add(value)
    }

    operator fun get(key: K): Collection<V> {
        return map[key] ?: hashSetOf()
    }

    fun putAll(key: K, values: Collection<V>){
        if (!map.containsKey(key)) {
            map[key] = HashSet()
        }
        map[key]?.addAll(values)
    }

    fun remove(key: K, value: V): Boolean {
        return map[key]!!.remove(value)
    }

    fun removeAll(key: K) {
        map[key] = HashSet()
    }

    fun replace(key: K, oldValue: V, newValue: V): Boolean {
        return if(remove(key, oldValue)){
            put(key, newValue)
            true
        } else {
            false
        }
    }

    fun replaceAll(key: K, values: Collection<V>){
        map[key] = values.toHashSet()
    }

    fun clear() {
        map.clear()
    }
}


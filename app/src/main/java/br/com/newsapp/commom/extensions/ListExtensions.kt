package br.com.newsapp.commom.extensions


fun <T> Collection<T>?.isNotNullOrEmpty() = !this.isNullOrEmpty()
fun <T, Y> Map<T, Y>?.isNotNullOrEmpty() = !this.isNullOrEmpty()
fun <T> Array<T>?.isNotNullOrEmpty() = !this.isNullOrEmpty()

inline fun <reified T, Y> Map<T, Y>.keysToSequence(): Sequence<T> {
    val enumeration: Set<T> = this.keys
    return enumeration.asSequence()
}

/**
 * Retorna todas as chaves de um Map, MutableMap, Hashtable, e outros derivados
 */
inline fun <reified T, Y> Map<T, Y>.getAllKeys(): List<T> {
    return this.keysToSequence().toList()
}
package hope.musinsa.global.util

interface GenericMapper<D, E> {
    fun toDomain(entity: E?): D?
    fun toEntity(domain: D): E
}
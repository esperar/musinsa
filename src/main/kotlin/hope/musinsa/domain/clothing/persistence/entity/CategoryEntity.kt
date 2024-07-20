package hope.musinsa.domain.clothing.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "tbl_category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val category: ClothingCategory
)

enum class ClothingCategory {
    TOP,
    OUTER,
    PANTS,
    SNEAKERS,
    BAG,
    HAT,
    SOCKS,
    ACCESSORY
}

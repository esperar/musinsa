package hope.musinsa.domain.clothing.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "tbl_clothing")
class ClothingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    val brand: String,

    @Column(nullable = false, columnDefinition = "INT")
    val price: Int,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: CategoryEntity
)


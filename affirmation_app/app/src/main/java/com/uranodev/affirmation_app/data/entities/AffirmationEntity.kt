package com.uranodev.affirmation_app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uranodev.affirmation_app.domain.models.Affirmation

@Entity(tableName = "Affirmations")
data class AffirmationEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val message: String,
    val imageName: String? = null
)

val AffirmationEntity.getMappedAffirmation: Affirmation
    get() = Affirmation(message = this.message, id = this.uid, iconName = this.imageName)

val List<AffirmationEntity>.getAffirmations: List<Affirmation>
    get() = this.map { affirmation -> affirmation.getMappedAffirmation }

val Affirmation.getMappedAffirmationEntity: AffirmationEntity
    get() = AffirmationEntity(message = this.message, imageName = this.iconName)
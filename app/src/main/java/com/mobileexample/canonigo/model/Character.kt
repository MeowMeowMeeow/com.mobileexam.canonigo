    package com.mobileexample.canonigo.model

    import androidx.annotation.DrawableRes
    import androidx.annotation.StringRes
    import androidx.room.ColumnInfo
    import androidx.room.Entity
    import androidx.room.PrimaryKey

    import kotlinx.serialization.SerialName
    import kotlinx.serialization.Serializable


    @Serializable
    data class Character(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val image: String,
        val gender: String,
        val origin: Origin,
        val location: Location,
        val episode: List<String>
    )

    @Serializable
    data class Origin(
        val name: String,
        val url: String,
    )

    @Serializable
    data class Location(
        val name: String,
        val url: String
    )

    @Serializable
    data class CharacterResponse(
        @SerialName("results") val characters: List<Character>
    )


package com.mobileexample.canonigo.utils

import com.mobileexample.canonigo.data.RoomCharacter
import com.mobileexample.canonigo.model.Character

import com.mobileexample.canonigo.model.Location
import com.mobileexample.canonigo.model.Origin



fun Character.toRoomCharacter(): RoomCharacter {
    return RoomCharacter(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        image = image,
        gender = gender,
        originName = origin.name,
        originUrl = origin.url,
        locationName = location.name,
        locationUrl = location.url,
        episodeList = episode.joinToString(",")
    )
}

fun RoomCharacter.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        image = image,
        gender = gender,
        origin = Origin(name = originName, url = originUrl),
        location = Location(name = locationName, url = locationUrl),
        episode = episodeList.split(",")
    )
}

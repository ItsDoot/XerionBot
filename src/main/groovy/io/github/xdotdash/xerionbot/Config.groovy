package io.github.xdotdash.xerionbot

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable

@ConfigSerializable
class Config {

    @Setting
    String token = "my bot token"

    @Setting
    List<String> allowedUserIds = new ArrayList<>()
}
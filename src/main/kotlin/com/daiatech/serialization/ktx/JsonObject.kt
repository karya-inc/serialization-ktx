package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

/**
 * Create an empty [JsonObject]
 */
fun emptyJsonObject(): JsonObject {
    return buildJsonObject { }
}

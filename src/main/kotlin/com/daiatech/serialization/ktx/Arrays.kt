package com.daiatech.serialization.ktx

import kotlinx.serialization.json.*

val JsonElement.asStringArray: List<String>
    get() = try {
        this.jsonArray.map { it.jsonPrimitive.content }
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        listOf()
    }

val JsonElement.asJsonArray
    get() = try {
        if (this is JsonPrimitive || this is JsonObject) {
            JsonArray(listOf(this))
        } else {
            this.jsonArray
        }
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }
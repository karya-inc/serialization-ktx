package com.daiatech.serialization.ktx

import kotlinx.serialization.json.*

/**
 * Extension property to safely convert a [JsonElement] into a [List] of [String].
 *
 * This expects the [JsonElement] to be a JSON array, where each element is a string.
 *
 * Example:
 * ```json
 * ["apple", "banana", "cherry"]
 * ```
 *
 * If the [JsonElement] is not a valid array of strings, or if any element cannot be
 * converted into a string, an empty list is returned. The exception is caught and
 * printed for debugging purposes.
 *
 * @receiver JsonElement to be converted into a list of strings.
 * @return List of strings if the conversion is successful, or an empty list if an error occurs.
 */
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

val JsonElement.asIntArray: List<Int>
    get() = try {
        this.jsonArray.map { it.jsonPrimitive.content.toInt()}
    }catch (e: IllegalArgumentException){
        e.printStackTrace()
        listOf()
    }
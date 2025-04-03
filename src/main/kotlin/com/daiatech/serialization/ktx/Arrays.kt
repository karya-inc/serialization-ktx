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

/**
 * Extension property to safely convert a [JsonElement] into a [JsonArray].
 *
 * If the [JsonElement] is already a [JsonArray], it is returned as is.
 * If the [JsonElement] is a [JsonPrimitive] or a [JsonObject], it is wrapped inside a new [JsonArray].
 *
 * Example:
 * ```json
 * {"key": "value"}
 * ```
 * will be converted to:
 * ```json
 * [{"key": "value"}]
 * ```
 *
 * If an error occurs during conversion, `null` is returned, and the exception is caught and printed for debugging.
 *
 * @receiver JsonElement to be converted into a JsonArray.
 * @return A [JsonArray] containing the element or `null` if an error occurs.
 */
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

/**
 * Extension property to safely convert a [JsonElement] into a [List] of nullable [Int].
 *
 * This expects the [JsonElement] to be a JSON array, where each element is either an integer or null.
 *
 * Example:
 * ```json
 * [1, 2, 3, null, 5]
 * ```
 *
 * If any element cannot be converted to an integer (e.g., a string or boolean), an empty list is returned.
 * If the element is `null`, it is preserved in the resulting list.
 * The exception is caught and printed for debugging purposes.
 *
 * @receiver JsonElement to be converted into a list of integers.
 * @return List of integers if the conversion is successful, or an empty list if an error occurs.
 */
val JsonElement.asIntArray: List<Int?>
    get() = try {
        this.jsonArray.map {
            if (it == JsonNull) {
                null
            } else {
                it.jsonPrimitive.content.toInt()
            }
        }
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        listOf()
    }
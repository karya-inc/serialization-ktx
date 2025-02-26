package com.daiatech.serialization.ktx

import kotlinx.serialization.json.*

val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    coerceInputValues = true
    explicitNulls = false
    classDiscriminator = "__type"
}

val JsonElement.asJsonObject: JsonObject?
    get() = try {
        this.jsonObject
    } catch (e: IllegalArgumentException) {
        null
    }

/**
 * Retrieves a specific JSON element from the "data" object within a given `JsonObject`.
 *
 * This function is an extension for `JsonObject` that attempts to fetch a value
 * associated with the specified `key` from a nested "data" object inside the main JSON structure.
 *
 * @receiver The `JsonObject` instance containing a "data" key.
 * @param key The key whose associated `JsonElement` needs to be retrieved from the "data" object.
 * @return The `JsonElement` corresponding to the specified `key` within "data",
 *         or `null` if "data" is missing, not a `JsonObject`, or does not contain the `key`.
 *
 * @throws IllegalStateException if "data" exists but is not a `JsonObject`,
 *         as `jsonObject` will throw an exception in such cases.
 *
 * Example Usage:
 * ```
 * val json = Json.parseToJsonElement("""
 *     {
 *         "data": {
 *             "user": "JohnDoe",
 *             "age": 30
 *         }
 *     }
 * """).jsonObject
 *
 * val user = json.data("user") // Returns JsonPrimitive("JohnDoe")
 * val age = json.data("age")   // Returns JsonPrimitive(30)
 * val missingKey = json.data("email") // Returns null
 * ```
 */
fun JsonObject.data(key: String): JsonElement? {
    return this["data"]?.asJsonObject?.get(key)
}

/**
 * Retrieves a specific JSON element from the "files" object within a given `JsonObject`.
 *
 * This function is an extension for `JsonObject` that attempts to fetch a value
 * associated with the specified `key` from a nested "files" object inside the main JSON structure.
 *
 * @receiver The `JsonObject` instance containing a "files" key.
 * @param key The key whose associated `JsonElement` needs to be retrieved from the "files" object.
 * @return The `JsonElement` corresponding to the specified `key` within "files",
 *         or `null` if "files" is missing, not a `JsonObject`, or does not contain the `key`.
 *
 * @throws IllegalStateException if "files" exists but is not a `JsonObject`,
 *         as `jsonObject` will throw an exception in such cases.
 *
 * Example Usage:
 * ```
 * val json = Json.parseToJsonElement("""
 *     {
 *         "files": {
 *             "image1": "image_data_1",
 *             "image2": "image_data_2"
 *         }
 *     }
 * """).jsonObject
 *
 * val fileElement = json.file("image1") // Returns JsonPrimitive("image_data_1")
 * val missingFile = json.file("image3") // Returns null
 * ```
 */
fun JsonObject.file(key: String): JsonElement? {
    return this["files"]?.asJsonObject?.get(key)
}

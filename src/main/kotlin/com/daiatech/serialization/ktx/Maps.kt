package com.daiatech.serialization.ktx

import kotlinx.serialization.json.*


val JsonElement.asStringRes: Map<String, String>
    get() = try {
        when (this) {
            is JsonArray -> mapOf("DEFAULT" to Json.encodeToString(this))

            is JsonObject -> this.jsonObject.mapValues { it.value.jsonPrimitive.content }

            //  can be stringified localized string or just a string
            is JsonPrimitive -> {
                try {
                    // Try to parse as JSON encoded as a string
                    val jsonObject = Json.parseToJsonElement(content).jsonObject
                    jsonObject.mapValues { it.value.jsonPrimitive.content }
                } catch (e: IllegalArgumentException) {
                    // Treat as a normal string
                    mapOf("DEFAULT" to content)
                }
            }

            JsonNull -> mapOf("DEFAULT" to "null")
        }
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        mapOf("DEFAULT" to Json.encodeToString(this))
    }

/**
 * Extension property to convert a [JsonElement] into a nested map of strings.
 *
 * This function expects the JSON structure to be of the form:
 * ```json
 * {
 *   "key1": {"lang1": "value1", "lang2": "value2"},
 *   "key2": {"lang1": "value3", "lang2": "value4"}
 * }
 * ```
 *
 * @receiver JsonElement The JSON element to be converted.
 * @return A [Map] where each key maps to another [Map] of language codes and their corresponding string values.
 *         Returns an empty map if the JSON structure is invalid or doesn't match the expected format.
 */
val JsonElement.asStringResMap: Map<String, Map<String, String>>
    get() = try {
        this.jsonObject.mapValues { option ->
            option.value.jsonObject.mapValues {
                it.value.jsonPrimitive.content
            }
        }
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        e.printStackTrace()
        mapOf()
    }

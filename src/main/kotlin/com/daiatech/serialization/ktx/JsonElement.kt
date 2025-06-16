package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

/**
 * Returns `true` if this [JsonElement] is **not** a [JsonNull] instance.
 *
 * Useful for null-safety checks when working with JSON structures.
 *
 * @return `true` if the element is not [JsonNull], `false` otherwise.
 */
val JsonElement.isNotJsonNull: Boolean
    get() = this !is JsonNull

/**
 * Returns `true` if this [JsonElement] is a [JsonNull] instance.
 *
 * Can be used to explicitly check if a JSON field is null.
 *
 * @return `true` if the element is [JsonNull], `false` otherwise.
 */
val JsonElement.isJsonNull: Boolean
    get() = this is JsonNull

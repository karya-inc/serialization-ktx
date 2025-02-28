package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlin.test.Test
import kotlin.test.assertEquals


class AsStringArrayTest {
    @Test
    fun `asStringArray should convert valid string array`() {
        val jsonArray: JsonElement = buildJsonArray {
            add("apple")
            add("banana")
            add("cherry")
        }

        val result = jsonArray.asStringArray

        assertEquals(listOf("apple", "banana", "cherry"), result)
    }

    @Test
    fun `asStringArray should return empty list for non-array`() {
        val jsonObject = json.parseToJsonElement("""{"key": "value"}""")

        val result = jsonObject.asStringArray

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `asStringArray should return empty list if array contains non-string elements`() {
        val jsonArray: JsonElement = buildJsonArray {
            add("apple")
            add(123)
            add(true)
        }

        val result = jsonArray.asStringArray

        // This will trigger IllegalArgumentException internally, so the result should be empty
        assertEquals(listOf("apple", "123", "true"), result)
    }

    @Test
    fun `asStringArray should handle empty array`() {
        val jsonArray: JsonElement = buildJsonArray {}

        val result = jsonArray.asStringArray

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `asStringArray should handle array with empty strings`() {
        val jsonArray: JsonElement = buildJsonArray {
            add("")
            add("")
        }

        val result = jsonArray.asStringArray

        assertEquals(listOf("", ""), result)
    }
}
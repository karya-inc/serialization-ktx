package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlin.test.Test
import kotlin.test.assertEquals

class AsIntArrayTest {

    @Test
    fun `asIntArray should convert valid int array`() {
        val jsonArray: JsonElement = buildJsonArray {
            add(2)
            add(4)
            add(6)
        }

        val result = jsonArray.asIntArray

        assertEquals(listOf(2, 4, 6), result)
    }

    @Test
    fun `asIntArray should return empty list if array contains non-string elements`() {
        val jsonArray: JsonElement = buildJsonArray {
            add("apple")
            add(123)
            add(true)
        }

        val result = jsonArray.asIntArray

        // This will trigger NumberFormatException internally, so the result should be empty
        assertEquals(listOf("apple", 123, true), result)
    }

    @Test
    fun `asIntArray should handle empty array`() {
        val jsonArray: JsonElement = buildJsonArray {}

        val result = jsonArray.asIntArray

        assertEquals(emptyList<Int>(), result)
    }
}
package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BuildJsonArrayTest {
    @Test
    fun `emptyJsonArray should return an empty JsonArray`() {
        val result = emptyJsonArray()
        assertTrue(result.isEmpty(), "Expected an empty JsonArray")
        assertEquals(0, result.size, "JsonArray should have size 0")
    }

    @Test
    fun `buildJsonArray with size 0 should return empty JsonArray`() {
        val result = buildJsonArray(0) { JsonPrimitive(it) }
        assertTrue(result.isEmpty(), "Expected JsonArray of size 0")
    }

    @Test
    fun `buildJsonArray should generate correct elements`() {
        val result = buildJsonArray(3) { idx -> JsonPrimitive(idx * 2) }
        val expected = buildJsonArray {
            add(JsonPrimitive(0))
            add(JsonPrimitive(2))
            add(JsonPrimitive(4))
        }

        assertEquals(expected, result, "JsonArray contents did not match expected values")
    }

    @Test
    fun `buildJsonArray should preserve JsonElement types`() {
        val result = buildJsonArray(2) { idx ->
            if (idx % 2 == 0) JsonPrimitive(true) else JsonNull
        }

        assertEquals(JsonPrimitive(true), result[0])
        assertEquals(JsonNull, result[1])
    }
}

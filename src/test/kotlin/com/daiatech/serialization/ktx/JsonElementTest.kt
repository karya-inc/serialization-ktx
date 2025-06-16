package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JsonElementTest {

    @Test
    fun `isJsonNull should return true for JsonNull`() {
        val element: JsonElement = JsonNull
        assertTrue(element.isJsonNull)
        assertFalse(element.isNotJsonNull)
    }

    @Test
    fun `isNotJsonNull should return true for JsonPrimitive`() {
        val element: JsonElement = JsonPrimitive("hello")
        assertTrue(element.isNotJsonNull)
        assertFalse(element.isJsonNull)
    }

    @Test
    fun `isNotJsonNull should return true for JsonObject`() {
        val element: JsonElement = buildJsonObject { put("key", JsonPrimitive(123)) }
        assertTrue(element.isNotJsonNull)
    }

    @Test
    fun `isNotJsonNull should return true for JsonArray`() {
        val element: JsonElement = buildJsonArray { add(JsonPrimitive(true)) }
        assertTrue(element.isNotJsonNull)
    }
}

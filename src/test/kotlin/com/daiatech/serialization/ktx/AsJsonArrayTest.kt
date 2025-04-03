package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AsJsonArrayTest {

    @Test
    fun `asJsonArray should return same array for valid JSON array`() {
        val json = json.parseToJsonElement("[\"apple\", \"banana\"]")
        val result = json.asJsonArray
        assertEquals(JsonArray(listOf(JsonPrimitive("apple"), JsonPrimitive("banana"))), result)
    }

    @Test
    fun `asJsonArray should wrap JsonPrimitive in array`() {
        val json = json.parseToJsonElement("\"apple\"")
        val result = json.asJsonArray
        assertNotNull(result)
        assertEquals(JsonArray(listOf(JsonPrimitive("apple"))), result)
    }

    @Test
    fun `asJsonArray should wrap JsonObject in array`() {
        val json = json.parseToJsonElement("{\"key\": \"value\"}")
        val result = json.asJsonArray
        assertNotNull(result)
        assertEquals(JsonArray(listOf(json)), result)
    }

    @Test
    fun `asJsonArray should return null for invalid JSON`() {
        val json = json.parseToJsonElement("null")
        val result = json.asJsonArray
        assertEquals(JsonArray(listOf(JsonNull)), result)
    }
}
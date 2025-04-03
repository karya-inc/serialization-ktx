package com.daiatech.serialization.ktx

import kotlin.test.Test
import kotlin.test.assertEquals

class AsStingArrayTest {
    @Test
    fun `asStringArray should return list of strings for valid JSON array`() {
        val json = json.parseToJsonElement("[\"apple\", \"banana\", \"cherry\"]")
        val result = json.asStringArray
        assertEquals(listOf("apple", "banana", "cherry"), result)
    }

    @Test
    fun `asStringArray should return empty list for non-array JSON`() {
        val json = json.parseToJsonElement("{\"key\": \"value\"}")
        val result = json.asStringArray
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `asStringArray should return empty list for empty JSON array`() {
        val json = json.parseToJsonElement("[]")
        val result = json.asStringArray
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `asStringArray should handle mixed-type JSON arrays gracefully`() {
        val json = json.parseToJsonElement("[\"apple\", 42, true, null]")
        val result = json.asStringArray
        assertEquals(listOf("apple", "42", "true", "null"), result) // Only valid strings should be included
    }

    @Test
    fun `asStringArray should return empty list for invalid JSON`() {
        val json = json.parseToJsonElement("null")
        val result = json.asStringArray
        assertEquals(emptyList(), result)
    }
}
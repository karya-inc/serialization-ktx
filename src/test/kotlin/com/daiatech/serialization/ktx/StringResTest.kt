package com.daiatech.serialization.ktx

import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals

class StringResTest {
    @Test
    fun `test with normal string`() {
        val jsonElement: JsonElement = JsonPrimitive("Normal string")
        val expected = mapOf("DEFAULT" to "Normal string")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with JSON encoded as string`() {
        val jsonElement: JsonElement = JsonPrimitive(
            "{\"EN\": \"What is your age?\", \"HI\": \"आपकी उम्र क्या है?\", \"ML\": \"നിങ്ങളുടെ പ്രായം എത്ര?\"}"
        )
        val expected = mapOf(
            "EN" to "What is your age?",
            "HI" to "आपकी उम्र क्या है?",
            "ML" to "നിങ്ങളുടെ പ്രായം എത്ര?"
        )
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with normal JSON object`() {
        val jsonElement: JsonElement = buildJsonObject {
            put("EN", "What is your age?")
            put("HI", "आपकी उम्र क्या है?")
            put("ML", "നിങ്ങളുടെ പ്രായം എത്ര?")
        }
        val expected = mapOf(
            "EN" to "What is your age?",
            "HI" to "आपकी उम्र क्या है?",
            "ML" to "നിങ്ങളുടെ പ്രായം എത്ര?"
        )
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with invalid JSON element`() {
        val jsonElement: JsonElement = JsonPrimitive(12345)
        val expected = mapOf("DEFAULT" to "12345")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with malformed JSON string`() {
        val jsonElement: JsonElement = JsonPrimitive("{EN: What is your age?}")
        val expected = mapOf("DEFAULT" to "{EN: What is your age?}")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with empty string`() {
        val jsonElement: JsonElement = JsonPrimitive("")
        val expected = mapOf("DEFAULT" to "")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with empty JSON object`() {
        val jsonElement: JsonElement = buildJsonObject {}
        val expected = emptyMap<String, String>()
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with empty JSON array`() {
        val jsonElement: JsonElement = buildJsonArray {}
        val expected = mapOf("DEFAULT" to "[]")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with nested JSON object`() {
        val jsonElement: JsonElement = buildJsonObject {
            put("EN", buildJsonObject { put("text", "What is your age?") })
        }
        val expected = mapOf("DEFAULT" to "{\"EN\":{\"text\":\"What is your age?\"}}")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with JsonNull`() {
        val jsonElement: JsonElement = JsonNull
        val expected = mapOf("DEFAULT" to "null")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with boolean primitive`() {
        val jsonElement: JsonElement = JsonPrimitive(true)
        val expected = mapOf("DEFAULT" to "true")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with simple JSON array`() {
        val jsonElement: JsonElement = buildJsonArray {
            add("One")
            add("Two")
            add("Three")
        }
        val expected = mapOf("DEFAULT" to "[\"One\",\"Two\",\"Three\"]")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with mixed-type JSON array`() {
        val jsonElement: JsonElement = buildJsonArray {
            add("String")
            add(123)
            add(true)
            add(JsonNull)
        }
        val expected = mapOf("DEFAULT" to "[\"String\",123,true,null]")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with array of JSON objects`() {
        val jsonElement: JsonElement = buildJsonArray {
            add(buildJsonObject { put("EN", "Hello") })
            add(buildJsonObject { put("HI", "नमस्ते") })
        }
        val expected = mapOf("DEFAULT" to """[{"EN":"Hello"},{"HI":"नमस्ते"}]""")
        assertEquals(expected, jsonElement.asStringRes)
    }

    @Test
    fun `test with nested JSON arrays`() {
        val jsonElement: JsonElement = buildJsonArray {
            add(buildJsonArray { add(1); add(2) })
            add(buildJsonArray { add(3); add(4) })
        }
        val expected = mapOf("DEFAULT" to "[[1,2],[3,4]]")
        assertEquals(expected, jsonElement.asStringRes)
    }
}
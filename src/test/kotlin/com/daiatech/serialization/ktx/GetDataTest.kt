package com.daiatech.serialization.ktx

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class GetDataTest {
    @Test
    fun `data should return the correct JsonElement when key exists`() {
        val json = Json.parseToJsonElement(
            """
            {
                "data": {
                    "user": "JohnDoe",
                    "age": 30
                }
            }
        """
        ).jsonObject

        assertEquals(JsonPrimitive("JohnDoe"), json.data("user"))
        assertEquals(JsonPrimitive(30), json.data("age"))
    }

    @Test
    fun `data should return null when key does not exist in data`() {
        val json = Json.parseToJsonElement(
            """
            {
                "data": {
                    "user": "JohnDoe"
                }
            }
        """
        ).jsonObject

        assertNull(json.data("email"))
    }

    @Test
    fun `data should return null when data key is missing`() {
        val json = Json.parseToJsonElement(
            """
            {
                "otherKey": "value"
            }
        """
        ).jsonObject

        assertNull(json.data("user"))
    }

    @Test
    fun `data should return null when data is explicitly null`() {
        val json = Json.parseToJsonElement(
            """
            {
                "data": null
            }
        """
        ).jsonObject

        assertNull(json.data("user"))
    }

    @Test
    fun `data should retrieve nested JSON objects correctly`() {
        val json = Json.parseToJsonElement(
            """
            {
                "data": {
                    "settings": {
                        "theme": "light",
                        "notifications": true
                    }
                }
            }
        """
        ).jsonObject

        val expectedJsonObject = JsonObject(
            mapOf(
                "theme" to JsonPrimitive("light"),
                "notifications" to JsonPrimitive(true)
            )
        )

        assertEquals(expectedJsonObject, json.data("settings"))
    }
}

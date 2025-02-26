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
        val jsonData = json.parseToJsonElement(
            """
            {
                "data": {
                    "user": "JohnDoe",
                    "age": 30
                }
            }
        """
        ).jsonObject

        assertEquals(JsonPrimitive("JohnDoe"), jsonData.data("user"))
        assertEquals(JsonPrimitive(30), jsonData.data("age"))
    }

    @Test
    fun `data should return null when key does not exist in data`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "data": {
                    "user": "JohnDoe"
                }
            }
        """
        ).jsonObject

        assertNull(jsonData.data("email"))
    }

    @Test
    fun `data should return null when data key is missing`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "otherKey": "value"
            }
        """
        ).jsonObject

        assertNull(jsonData.data("user"))
    }

    @Test
    fun `data should return null when data is explicitly null`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "data": null
            }
        """
        ).jsonObject

        assertNull(jsonData.data("user"))
    }

    @Test
    fun `data should retrieve nested JSON objects correctly`() {
        val jsonData = json.parseToJsonElement(
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

        assertEquals(expectedJsonObject, jsonData.data("settings"))
    }
}

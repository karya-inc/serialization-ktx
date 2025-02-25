package com.daiatech.serialization.ktx

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetFilesTest {
    @Test
    fun `file should return the correct JsonElement when key exists`() {
        val json = Json.parseToJsonElement(
            """
            {
                "files": {
                    "image1": "image_data_1",
                    "image2": 12345
                }
            }
        """
        ).jsonObject

        assertEquals(JsonPrimitive("image_data_1"), json.file("image1"))
        assertEquals(JsonPrimitive(12345), json.file("image2"))
    }

    @Test
    fun `file should return null when key does not exist in files`() {
        val json = Json.parseToJsonElement(
            """
            {
                "files": {
                    "image1": "image_data_1"
                }
            }
        """
        ).jsonObject

        assertNull(json.file("nonexistent"))
    }

    @Test
    fun `file should return null when files key is missing`() {
        val json = Json.parseToJsonElement(
            """
            {
                "otherKey": "some_value"
            }
        """
        ).jsonObject

        assertNull(json.file("image1"))
    }

    @Test
    fun `file should return null when files is null`() {
        val json = Json.parseToJsonElement(
            """
            {
                "files": null
            }
        """
        ).jsonObject

        assertNull(json.file("image1"))
    }

    @Test
    fun `file should work with nested JSON elements`() {
        val json = Json.parseToJsonElement(
            """
            {
                "files": {
                    "config": {
                        "theme": "dark",
                        "version": 2
                    }
                }
            }
        """
        ).jsonObject

        val expectedJsonObject = JsonObject(
            mapOf(
                "theme" to JsonPrimitive("dark"),
                "version" to JsonPrimitive(2)
            )
        )

        assertEquals(expectedJsonObject, json.file("config"))
    }
}

package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetFilesTest {
    @Test
    fun `file should return the correct JsonElement when key exists`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "files": {
                    "image1": "image_data_1",
                    "image2": 12345
                }
            }
        """
        ).jsonObject

        assertEquals(JsonPrimitive("image_data_1"), jsonData.file("image1"))
        assertEquals(JsonPrimitive(12345), jsonData.file("image2"))
    }

    @Test
    fun `file should return null when key does not exist in files`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "files": {
                    "image1": "image_data_1"
                }
            }
        """
        ).jsonObject

        assertNull(jsonData.file("nonexistent"))
    }

    @Test
    fun `file should return null when files key is missing`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "otherKey": "some_value"
            }
        """
        ).jsonObject

        assertNull(jsonData.file("image1"))
    }

    @Test
    fun `file should return null when files is null`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "files": null
            }
        """
        ).jsonObject

        assertNull(jsonData.file("image1"))
    }

    @Test
    fun `file should work with nested JSON elements`() {
        val jsonData = json.parseToJsonElement(
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

        assertEquals(expectedJsonObject, jsonData.file("config"))
    }
}

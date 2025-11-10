package com.daiatech.serialization.ktx

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetScratchTest {
    @Test
    fun `scratch should return the correct JsonElement when key exists`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "scratch": {
                    "tempValue": "temporary_data",
                    "counter": 42
                }
            }
        """
        ).jsonObject

        assertEquals(JsonPrimitive("temporary_data"), jsonData.scratch("tempValue"))
        assertEquals(JsonPrimitive(42), jsonData.scratch("counter"))
    }

    @Test
    fun `scratch should return null when key does not exist in scratch`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "scratch": {
                    "tempValue": "temporary_data"
                }
            }
        """
        ).jsonObject

        assertNull(jsonData.scratch("missing"))
    }

    @Test
    fun `scratch should return null when scratch key is missing`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "otherKey": "some_value"
            }
        """
        ).jsonObject

        assertNull(jsonData.scratch("tempValue"))
    }

    @Test
    fun `scratch should return null when scratch is null`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "scratch": null
            }
        """
        ).jsonObject

        assertNull(jsonData.scratch("tempValue"))
    }

    @Test
    fun `scratch should work with nested JSON elements`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "scratch": {
                    "metadata": {
                        "status": "processing",
                        "retries": 3
                    }
                }
            }
        """
        ).jsonObject

        val expectedJsonObject = JsonObject(
            mapOf(
                "status" to JsonPrimitive("processing"),
                "retries" to JsonPrimitive(3)
            )
        )

        assertEquals(expectedJsonObject, jsonData.scratch("metadata"))
    }

    @Test
    fun `scratch should handle boolean values correctly`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "scratch": {
                    "isTemporary": true,
                    "shouldCache": false
                }
            }
        """
        ).jsonObject

        assertEquals(JsonPrimitive(true), jsonData.scratch("isTemporary"))
        assertEquals(JsonPrimitive(false), jsonData.scratch("shouldCache"))
    }

    @Test
    fun `scratch should handle empty scratch object`() {
        val jsonData = json.parseToJsonElement(
            """
            {
                "scratch": {}
            }
        """
        ).jsonObject

        assertNull(jsonData.scratch("anyKey"))
    }
}

package com.daiatech.serialization.ktx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JsonObjectTest {
    @Test
    fun `emptyJsonObject should return an empty JsonArray`() {
        val result = emptyJsonObject()
        assertTrue(result.isEmpty(), "Expected an empty JsonArray")
        assertEquals(0, result.size, "JsonArray should have size 0")
    }
}

package com.daiatech.serialization.ktx

import kotlinx.serialization.json.*


val JsonElement.asString
    get() = try {
        if (this is JsonArray && this.jsonArray.size == 1) {
            this.jsonArray[0].jsonPrimitive.content
        } else {
            this.jsonPrimitive.content
        }
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        this.toString()
    }

val JsonElement.asInt
    get() = try {
        this.jsonPrimitive.intOrNull
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }

val JsonElement.asLong
    get() = try {
        this.jsonPrimitive.longOrNull
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }

val JsonElement.asFloat
    get() = try {
        this.jsonPrimitive.floatOrNull
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }

val JsonElement.asBoolean
    get() = try {
        this.jsonPrimitive.booleanOrNull
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }

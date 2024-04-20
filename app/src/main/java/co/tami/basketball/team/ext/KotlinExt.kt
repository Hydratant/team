package co.tami.basketball.team.ext

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.toMap(): Map<String, Int> {
    val test = Json.encodeToString(this)
    return Json.decodeFromString<Map<String, Int>>(test)
}
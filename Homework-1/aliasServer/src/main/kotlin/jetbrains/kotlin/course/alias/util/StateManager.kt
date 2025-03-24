package jetbrains.kotlin.course.alias.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object StateManager {
    private const val STATE_FILE = "gamestate.json"

    fun saveState(gameState: GameState) {
        val json = Json { prettyPrint = true }
        File(STATE_FILE).writeText(json.encodeToString(gameState))
    }

    fun loadState(): GameState? {
        val file = File(STATE_FILE)
        return if (file.exists()) {
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<GameState>(file.readText())
        } else null
    }
}

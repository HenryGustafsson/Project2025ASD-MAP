package jetbrains.kotlin.course.alias.util

import kotlinx.serialization.Serializable
import jetbrains.kotlin.course.alias.card.Card
import jetbrains.kotlin.course.alias.team.Team


@Serializable
data class GameState(
    val gameHistory: List<List<Team>>,
    val teamsStorage: Map<Int, Team>,
    val cardIdentifierCounter: Int,
    val teamIdentifierCounter: Int,
    val usedCards: List<Card>
)

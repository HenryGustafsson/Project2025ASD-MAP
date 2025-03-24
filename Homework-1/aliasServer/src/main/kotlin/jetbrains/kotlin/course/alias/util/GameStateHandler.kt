package jetbrains.kotlin.course.alias.util

import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class GameStateHandler(
    private val cardService: CardService,
    private val teamService: TeamService,
    private val gameResultsService: GameResultsService
) {
    @PostConstruct
    fun loadState() {
        val state = StateManager.loadState()
        state?.let {

            GameResultsService.gameHistory.clear()
            GameResultsService.gameHistory.addAll(it.gameHistory)

            TeamService.teamsStorage.clear()
            TeamService.teamsStorage.putAll(it.teamsStorage)

            cardService.setIdentifierCounter(it.cardIdentifierCounter)
            teamService.setIdentifierCounter(it.teamIdentifierCounter)

            cardService.setCards(it.usedCards)
        }
    }

    @PreDestroy
    fun saveState() {
        val state = GameState(
            gameHistory = GameResultsService.gameHistory.toList(),
            teamsStorage = TeamService.teamsStorage.toMap(),
            cardIdentifierCounter = cardService.getIdentifierCounter(),
            teamIdentifierCounter = teamService.getIdentifierCounter(),
            usedCards = cardService.getCards()
        )
        StateManager.saveState(state)
    }
}

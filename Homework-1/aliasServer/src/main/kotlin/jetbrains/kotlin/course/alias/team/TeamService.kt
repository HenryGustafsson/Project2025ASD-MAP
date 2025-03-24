package jetbrains.kotlin.course.alias.team

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import kotlinx.serialization.Serializable


@Serializable
data class Team(
    val id: Identifier,
    var points: Int = 0
) {
    @Transient
    val name: String = "Team#${id + 1}"
}

@Service
class TeamService {
    private val identifierFactory = IdentifierFactory()

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = List(teamsNumber) {
            val id = identifierFactory.uniqueIdentifier()
            Team(id).also {teamsStorage[id] = it}
        }
        return teams
    }

    fun getIdentifierCounter(): Int = identifierFactory.getCounter()

    fun setIdentifierCounter(value: Int) {
        identifierFactory.setCounter(value)
    }
}
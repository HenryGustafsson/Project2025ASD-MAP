package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service


data class Word(val word: String)
data class Card(val id: Identifier, val words: List<Word>)

@Service
class CardService {
    private val identifierFactory = IdentifierFactory()
    companion object {
        const val WORDS_IN_CARD = 4
        val cardsAmount = words.size / WORDS_IN_CARD
    }
    private val cards: List<Card> = generateCards()

    private fun List<String>.toWords(): List<Word> = this.map {Word(it)}

    private fun generateCards(): List<Card> {
        return words.shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk -> Card(identifierFactory.uniqueIdentifier(), chunk.toWords()) }
    }

    fun getCardByIndex(index: Int): Card {
        return cards.getOrNull(index) ?: throw IllegalArgumentException("Card doesn't exist.")
    }
}

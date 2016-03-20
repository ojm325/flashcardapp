package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardMultipleChoice;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;

import java.util.*;

/**
 * Created by Omar on 1/9/2016.
 */
public interface DataStorage {
    void addDeck(Deck deck);
    void addCard(FlashCard card, int deckId);
    void modifyDeck(Deck deck);
    void modifyCard(FlashCard card, int deckId);
    ArrayList<Deck> getAllDecks();
    ArrayList<FlashCard> getAllCardsForDeck(int deckId);
    Deck getDeck(int deckId);
    FlashCard getCard(int deckId);
    void deleteDeck(int deckId);
    void deleteCard(int deckId, int cardId);
    void setAnswerChoiceForCard(int deckId, int cardId, String answerChoice, boolean isAnswer);
    void modifyAnswerChoiceForCard(int cardChoiceId, int deckId, int cardId);
    LinkedHashMap getAnswerChoicesForCard(int deckId, int cardId);

}

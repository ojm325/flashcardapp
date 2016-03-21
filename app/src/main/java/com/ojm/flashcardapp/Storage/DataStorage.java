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
    /*
        Adding to Storage
     */
    void addDeck(Deck deck);
    void addCard(FlashCard card, int deckId);
    void setAnswerChoiceForCard(int deckId, int cardId, String answerChoice, boolean isAnswer);

    /*
        Modifying Existing Values in Storage
     */
    void modifyDeck(Deck deck);
    void modifyCard(FlashCard card, int deckId);
    void modifyAnswerChoiceForCard(int cardChoiceId, int deckId, int cardId);

    /*
        Retrieving From Storage
     */
    ArrayList<Deck> getAllDecks();
    ArrayList<FlashCard> getAllCardsForDeck(int deckId);
    Deck getDeck(int deckId);
    FlashCard getCard(int deckId);
    int getLastInsertedCardId(int deckId); // Used to get the cardId of what card the answers will go to.
    TreeMap getAnswerChoicesForCard(int deckId, int cardId);

    /*
        Removing from Storage
     */
    void deleteDeck(int deckId);
    void deleteCard(int deckId, int cardId);
    void deleteAllAnswerChoicesForCard(int deckId, int cardId);
    void deleteAnswerChoice(int deckId, int cardId, String choice);


}

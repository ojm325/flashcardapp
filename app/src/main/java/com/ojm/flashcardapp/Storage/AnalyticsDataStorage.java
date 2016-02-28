package com.ojm.flashcardapp.Storage;

/**
 * Created by Omar on 2/28/2016.
 */
public interface AnalyticsDataStorage {
    /* Put into storage the user's response to a card */
    void setCardResponse();

    /* Get all the responses the user has put in for a single card */
    void getAllCardResponsesForSingleCard();

    /* Get all the responses a user has made from when they started using a deck to when they finished.
       This can be used for getting the amount of responses correct, incorrect, and partially correct.
     */
    void getAllCardResponsesForSession();

    /* Get all the responses a user has made ever for a deck of cards.
       This can be useful for getting overall session trends, average session score, etc.
     */
    void getAllCardResponsesForDeck();

    /* Gets all of the responses a user has made for a single card between certain dates.
        Can be useful for tracking correct/ incorrect streak.
     */
    void getResponsesForSingleCardByDates();

    /* Sets the timestamp for when the user started a deck session*/
    void setDeckStartTime();

    /* Sets the timestamp for when the user ended a deck session. A user can end a deck session without
       actually completing all the questions in the deck.
    */
    void setDeckEndTime();
}

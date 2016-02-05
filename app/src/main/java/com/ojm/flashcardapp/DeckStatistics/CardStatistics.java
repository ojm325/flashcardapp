package com.ojm.flashcardapp.DeckStatistics;

/**
 * Created by Omar on 2/4/2016.
 */
public class CardStatistics {
    protected int deckId;
    protected int cardId;
    protected String timestampCorrect;
    protected String timeStampWrong;
    protected String providedAnswer;

    public CardStatistics(int deckId, int cardId){
        this.deckId = deckId;
        this.cardId = cardId;
    }

    public int getDeckId() { return deckId; }

    public int getCardId() { return cardId; }

    public String getTimestampCorrect() { return timestampCorrect; }
    public void setTimestampCorrect(String timestampCorrect) {
        this.timestampCorrect = timestampCorrect;
    }

    public String getTimeStampWrong() { return timeStampWrong; }
    public void setTimeStampWrong(String timeStampWrong) {
        this.timeStampWrong = timeStampWrong;
    }

    public String getProvidedAnswer() { return providedAnswer; }
    public void setProvidedAnswer(String providedAnswer) {
        this.providedAnswer = providedAnswer;
    }
}

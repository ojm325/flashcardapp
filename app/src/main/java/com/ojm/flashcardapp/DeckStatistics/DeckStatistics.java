package com.ojm.flashcardapp.DeckStatistics;

/**
 * Created by Omar on 2/4/2016.
 */
public class DeckStatistics {
    protected int deckId;
    protected int timesAttempted;
    protected int timesCompleted;
    protected double avgScore;
    protected double highestScore;
    protected String cardMostCorrect;
    protected String cardLeastCorrect;

    public DeckStatistics(int deckId){
        this.deckId = deckId;
    }

    public int getDeckId(){ return deckId; }

    public int getTimesAttempted(){ return timesAttempted; }
    public void setTimesAttempted(int timesAttempted) {
        this.timesAttempted = timesAttempted;
    }

    public int getTimesCompleted(){ return timesCompleted; }
    public void setTimesCompleted(int timesCompleted) {
        this.timesCompleted = timesCompleted;
    }

    public double getAvgScore() { return avgScore; }
    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public double getHighestScore() { return highestScore; }
    public void setHighestScore(double highestScore) {
        this.highestScore = highestScore;
    }

    public String getCardLeastCorrect() { return cardLeastCorrect; }
    public void setCardLeastCorrect(String cardLeastCorrect) {
        this.cardLeastCorrect = cardLeastCorrect;
    }

    public String getCardMostCorrect() { return cardMostCorrect; }
    public void setCardMostCorrect(String cardMostCorrect) {
        this.cardMostCorrect = cardMostCorrect;
    }
}

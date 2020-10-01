package OOD.Poker;

import java.util.*;

public final class Player {
    private int id;
    private String username;
    private Date registeredAt;
    private int score;
    private List<Card> handCards;

    public Player(int id, String username) {
        this.id = id;
        this.username = username;
        this.registeredAt = new Date();
        this.score = 0;
        this.handCards = new ArrayList<>();
    }

    public void addHandCard(Deck deck, int num) {
        if (deck == null) {
            throw new IllegalArgumentException("Invalid deck.");
        }
        if (num < 1 || num > deck.getSize()) {
            throw new IllegalArgumentException("Invalid card number.");
        }

        for (int i = 0; i < num; i++) {
            this.handCards.add(deck.deliverCard());
        }
    }

    public void addHandCard(Deck deck) {
        addHandCard(deck, 1);
    }

    public int addScore(int addScore) {
        this.score += addScore;
        return this.score;
    }

    public int getScore() {
        return this.score;
    }
}

package OOD.Poker;

import java.util.*;

public final class Deck {
    private final int id;
    private List<Card> cards;
    private static final Random rand = new Random();

    public Deck(int id) {
        this.id = id;
        this.cards = generateCards();
    }

    public void shuffle() {
        for (int i = cards.size(); i > 0; i--) {
            int randIdx = rand.nextInt(i);
            swap(i - 1, randIdx);
        }
    }

    public Card deliverCard() {
        if (this.cards.isEmpty()) {
            throw new IllegalArgumentException("The deck is empty.");
        }
        return this.cards.remove(this.cards.size() - 1);
    }

    public void restore() {
        this.cards = generateCards();
    }

    public int getSize() {
        return this.cards.size();
    }


    private List<Card> generateCards() {
        List<Card> ret = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                ret.add(new Card(suit, i));
            }
        }
        return ret;
    }

    private void swap(int i, int j) {
        Card temp = this.cards.get(i);
        this.cards.set(i, this.cards.get(j));
        this.cards.set(j, temp);
    }
}

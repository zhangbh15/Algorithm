package OOD.Poker;

public final class Card {
    private final Suit suit;
    private final int val;

    public Card(Suit suit, int val) {
        this.suit = suit;
        this.val = val;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getVal() {
        return this.val;
    }
}

enum Suit {
    SPADE(4),
    HEART(3),
    DIAMOND(2),
    CLUB(1);

    private int val;

    Suit(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }
}

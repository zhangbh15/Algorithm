package OOD.Poker;

import java.util.*;

public abstract class Game {
    private int id;
    private String name;
    private List<Player> players;
    private List<Deck> decks;

    public Game(int id, String name) {
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
        this.decks = new ArrayList<>();
    }

    public boolean removePlayer(Player player) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i) == player) {
                this.players.remove(i);
                return true;
            }
        }

        return false;
    }


    /**
     * Implement with different limits on player number.
     */
    public abstract boolean addPlayer();

    public abstract void startGame();

    public abstract void endGame();

    public abstract Player checkWinner();
}

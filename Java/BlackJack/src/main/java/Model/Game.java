package Model;

/**
 * The game class, regroups all that composes the game. Has an array of (two)
 * players, a deck that gets drawn from until empty, a round counter and a bet
 * that changes with each round, as per the player's choice.
 *
 * @author Ihab Tazi 55130.
 */
public class Game implements Model {

    private Player[] players;
    private Deck currentDeck;
    private int round = 0;
    private int bet;

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Pseudo-constructor for the Game class. Shuffles the current deck.
     *
     * @param players Players playing the game.
     * @param deck Deck for each round.
     * @param bet Bet for this round.
     */
    @Override
    public void initGame(Player[] players, Deck deck, int bet) {
        if (players == null || deck == null || bet <= 0) {
            throw new IllegalArgumentException("Invalid arguments players, deck,bet:"
                    + players + ", " + deck + ", " + bet);
        }
        this.players = players;
        this.currentDeck = deck;
        this.bet = bet;
        deck.shuffle();
    }

    @Override
    public Deck getCurrentDeck() {
        return this.currentDeck;
    }

    /**
     * Gets the index in the players array of the winner of the round.
     *
     * @param cpuTotal the tally of the bank.
     * @param humanTotal the tally of the human player.
     * @return the index of the player who won the round.
     */
    @Override
    public int getRoundWinnerIndex(int cpuTotal, int humanTotal) {
        if (cpuTotal == humanTotal) {
            throw new IllegalArgumentException("the two totals should be "
                    + "different for there to be winner ! ");
        } else if (cpuTotal < 0 || humanTotal < 0) {
            throw new IllegalArgumentException("The totals cannot be negative !");
        }
        if (players[1].isOver21()) {
            return 0;
        } else if (players[0].isOver21()) {
            return 1;
        }
        if (cpuTotal < humanTotal) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Checks if the human player is blocked.
     *
     * @return true if the human is blocked, false otherwise.
     */
    @Override
    public boolean playerIsBlocked() {
        return getHumanPlayer().isBlocked();
    }

    /**
     * Gets the losing player of the round.
     *
     * @param cpuTotal the tally of the bank.
     * @param humanTotal the tally of the human player.
     * @return the Player who lost the round.
     */
    @Override
    public Player getRoundLoser(int cpuTotal, int humanTotal) {
        int loserIndex = (getRoundWinnerIndex(cpuTotal, humanTotal) + 1) % 2;
        return players[loserIndex];
    }

    /**
     * Gets the winning player of the round.
     *
     * @param cpuTotal the tally of the bank.
     * @param humanTotal the tally of the human player.
     * @return the Player who won the round.
     */
    @Override
    public Player getRoundWinner(int cpuTotal, int humanTotal) {
        int winnerIndex = getRoundWinnerIndex(cpuTotal, humanTotal);
        return players[winnerIndex];
    }

    /**
     * Gets the bank player.
     *
     * @return the bank Player.
     */
    @Override
    public Cpu getCpu() {
        return (Cpu) players[0];
    }

    /**
     * Gets the human player.
     *
     * @return the human Player.
     */
    @Override
    public HumanPlayer getHumanPlayer() {
        return (HumanPlayer) players[1];
    }

    /**
     * Checks if the deck still has cards. Calls Deck.isEmpty()
     *
     * @return true if there are still cards int he deck, false otherwise.
     */
    @Override
    public boolean deckStillHasCards() {
        return !this.currentDeck.isEmpty();
    }

    /**
     * Checks if a player is over 21
     *
     * @return true if a player is over 21, false otherwise.
     */
    @Override
    public boolean isAPlayerOver21() {
        return getCpu().isOver21() || getHumanPlayer().isOver21();
    }

    /**
     * Starts new round, by incrementing the round by 1.
     */
    @Override
    public void nextRound() {
        this.round++;
    }

    @Override
    public int getRound() {
        return round;
    }

    /**
     * Adds the money parameter to the winner's Score
     *
     * @param money amount of money to add.
     */
    @Override
    public void addMoneyToWinner(int money) {
        getRoundWinner(getCpuCurrentTotal(), getHumanCurrentTotal())
                .winsMoney(money);
    }

    /**
     * Removes the money parameter from the loser's Score
     *
     * @param money amount of money to remove.
     */
    @Override
    public void removeMoneyFromLoser(int money) {
        getRoundLoser(getCpuCurrentTotal(), getHumanCurrentTotal())
                .losesMoney(money);
    }

    /**
     * Checks if the player is broke.
     *
     * @param player player to check.
     * @return true if the player has no money left, false otherwise.
     */
    @Override
    public boolean playerIsBroke(Player player) {
        return player.isBroke();
    }

    /**
     * Gets the current money of the bank.
     *
     * @return the amount of money in the bank.
     */
    @Override
    public int getCpuScore() {
        return players[0].getScore();
    }

    /**
     * Gets the current money of the player.
     *
     * @return the amount of money of the player.
     */
    @Override
    public int getHumanScore() {
        return players[1].getScore();
    }

    /**
     * Gets the current card tally of the human player.
     *
     * @return the current tally of the cards for the human.
     */
    @Override
    public int getHumanCurrentTotal() {
        return players[1].getCurrentTotal();
    }

    /**
     * Gets the current card tally of the ban.
     *
     * @return the current tally of the cards for the bank.
     */
    @Override
    public int getCpuCurrentTotal() {
        return players[0].getCurrentTotal();
    }

    /**
     * Checks if both the bank and the player are on the same tally.
     *
     * @return true if both have the same card tally.
     */
    @Override
    public boolean bothHaveSameTotal() {
        return getCpuCurrentTotal() == getHumanCurrentTotal();
    }

    /**
     * Calls the bank to draw a Card.
     *
     * @return the drawn card.
     */
    @Override
    public Card cpuDrawsCard() {
        return players[0].drawCard(currentDeck);
    }

    /**
     * Calls the player to draw a Card.
     *
     * @return the drawn card.
     */
    @Override
    public Card playerDrawsCard() {
        return players[1].drawCard(currentDeck);
    }

    /**
     * Checks if the bank's tally is still under the limit to draw (17 in our
     * game)
     *
     * @return true if the current tally if under 17, false otherwise.
     */
    @Override
    public boolean isCpuUnderLimit() {
        return getCpu().isUnderLimit();
    }

    /**
     * Blocks the human player from playing this game.
     */
    @Override
    public void blockHuman() {
        getHumanPlayer().block();
    }

}

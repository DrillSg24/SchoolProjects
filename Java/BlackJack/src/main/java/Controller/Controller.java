package Controller;

import Model.Cpu;
import Model.Deck;
import Model.HumanPlayer;
import Model.Model;
import Model.Player;
import View.InterfaceView;

/**
 *
 * @author Ihab Tazi 55130.
 */
public class Controller {

    private final InterfaceView view;
    private final Model game;

    public Controller(InterfaceView view, Model game) {
        this.view = view;
        this.game = game;
    }

    /**
     * Main game loop, asks for the name for the entire game, and then asks for
     * the bet for each round, and initializes a game state with the same money
     * counts as last round, with the new bet.
     *
     * @param cpuScore Current money of the bank.
     * @param humanScore Current money of the human player.
     * @param deck Current deck, is NOT re-initialized each round.
     */
    public void startGame(int cpuScore, int humanScore, Deck deck) {
        view.displayMessage("Welcome to Blackjack !");
        String humanName = view.askHumanName();
        boolean gameIsActive = true;
        view.displayMessage("You and the bank both have 5000€ to begin with");
        while (gameIsActive) {
            view.displayMessage("New Round");
            game.nextRound();
            game.initGame(new Player[]{new Cpu(cpuScore),
                new HumanPlayer(humanName, humanScore)}, deck, view.askBet());
            while (game.getBet() > game.getHumanScore()) {
                view.displayMessage("You must have enough money !");
                game.setBet(view.askBet());
            }
            view.displayMessage("You bet " + game.getBet() + "€ !");
            updateGameState();
            playAutoHumanTurn();
            while (!game.playerIsBlocked() && !checkPlayerHit21OrMore()) {
                askAndPlayTurn();
            }
            showResults();
            //Setting new scores for the eventual next Round.
            cpuScore = game.getCpuScore();
            humanScore = game.getHumanScore();
            if (!game.deckStillHasCards()) {
                view.displayMessage("No more cards in the deck !");
            } else {
                gameIsActive = !checkPlayerIsBroke()
                        && view.askYesOrNo("Do you want to start a new round ?");
            }
        }
        showFinalState();
    }

    /**
     * Cpu draws a card. Calls on cpuDrawsCard.
     *
     */
    private void playCpuTurn() {
        if (game.deckStillHasCards()) {
            view.displayMessage("BANK'S TURN");
            cpuDrawsCard();
        } else {
            view.displayMessage("No more cards !");
        }
    }

    /**
     * Player's initial turn, automatically gets two cards, unless there's no
     * more cards in the deck.
     *
     */
    private void playAutoHumanTurn() {
        if (game.deckStillHasCards()) {
            view.displayMessage(game.getHumanPlayer().toString().toUpperCase()
                    + "'S TURN");
            playerDrawsCard();
            playerDrawsCard();
            updateGameState();
        } else {
            view.displayMessage("No more cards !");
        }
    }

    /**
     * Asks the player if he wants another card, if so, draws the card, and
     * checks if the game is still possible. Otherwise, blocks the player from
     * playing again, and calls on the CPU turn.
     *
     * @return true if the game is not possible anymore.
     */
    private boolean askAndPlayTurn() {
        if (game.deckStillHasCards() && !checkPlayerHit21OrMore()) {
            if (view.askYesOrNo("Do you want to draw another card ?")) {
                playerDrawsCard();
                return !game.isAPlayerOver21();
            } else {
                view.displayMessage(game.getHumanPlayer().toString()
                        + " has decided not to draw a card");
                game.blockHuman();
                playCpuTurn();
                return !game.isAPlayerOver21();
            }
        } else if (!game.deckStillHasCards()) {
            view.displayMessage("No more cards !");
        }
        return false;
    }

    /**
     * Reward the winning side with the money parameter, and removes money from
     * the losing side.
     *
     * @param money amount of money to add/remove.
     */
    private void rewardWinner(int money) {
        game.addMoneyToWinner(money);
        game.removeMoneyFromLoser(money);
        updateGameState();
    }

    /**
     * Updates the user on the current state of the game, by showing the current
     * round, the current money and the current tally of both players.
     *
     */
    private void updateGameState() {
        view.displayCurrentGame(game.getRound(), game.getCpu(),
                game.getHumanPlayer(),
                game.getCurrentDeck());
    }

    /**
     * Shows the final state of the game, by showing the final money count for
     * both players, as well as the total number of rounds played.
     *
     */
    private void showFinalState() {
        view.displayFinalScores(game.getRound(), game.getCpu(),
                game.getHumanPlayer());
        view.displayMessage(game.getHumanPlayer()
                + ", Thank you for playing Blackjack");
    }

    /**
     * Shows the results after each round is played. A result can be either a
     * draw, a bank win or a player win. The winner and loser are then
     * rewarded/punished.
     *
     */
    private void showResults() {
        if (game.bothHaveSameTotal()) {
            view.showDraw();
        } else {
            showWinner();
            rewardWinner(game.getBet());
        }
    }

    /**
     * Gets the winner as well as the loser of the game, and shows how much they
     * won/lost.
     *
     */
    private void showWinner() {
        Player winner = game.getRoundWinner(game.getCpuCurrentTotal(),
                game.getHumanCurrentTotal());
        Player loser = game.getRoundLoser(game.getCpuCurrentTotal(),
                game.getHumanCurrentTotal());
        view.displayMessage(winner
                + " has won this round.");
        view.displayMessage(winner + " gets " + game.getBet() + "€ !");
        view.displayMessage(loser + " loses " + game.getBet() + "€ !");
    }

    /**
     * The human draws a card which is then displayed, and the updated player
     * tally is shown.
     *
     */
    private void playerDrawsCard() {
        view.displayCard(game.getHumanPlayer(), game.playerDrawsCard());
        view.displayMessage("The current total for the player is : "
                + game.getHumanCurrentTotal());
    }

    /**
     * The CPU keeps drawing cards until the tally is over 17. The cards are
     * displayed and the updated and shown each draw.
     *
     */
    private void cpuDrawsCard() {
        while (game.isCpuUnderLimit()) {
            view.displayCard(game.getCpu(), game.cpuDrawsCard());
            view.displayMessage("The current total for the bank is : "
                    + game.getCpuCurrentTotal());
            if (!game.getCpu().isOver21() && !game.isCpuUnderLimit()) {
                view.displayMessage(game.getCpu()
                        + " has decided not to draw another card");
            }
        }
    }

    /**
     * Checks if the round is still possible, i.e if a player is over 21, or if
     * a player has hit exactly 21.
     *
     * @return true if a player has hit 21 or more.
     */
    private boolean checkPlayerHit21OrMore() {
        if (game.isAPlayerOver21()) {
            view.displayMessage("Over 21 ! Round Over !");
            return true;
        } else {
            return game.getCpuCurrentTotal() == 21
                    || game.getHumanCurrentTotal() == 21;
        }
    }

    /**
     * Check if either player has no money left.
     *
     * @return true if the bank or the player is bankrupt, false otherwise.
     */
    private boolean checkPlayerIsBroke() {
        if (game.getCpu().isBroke()) {
            view.displayMessage(game.getCpu() + " has no more money !");
            return true;
        } else if (game.getHumanPlayer().isBroke()) {
            view.displayMessage(game.getHumanPlayer() + " has no more money !");
            return true;
        }
        return false;
    }

}

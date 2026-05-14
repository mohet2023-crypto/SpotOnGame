package com.example.spotongame;

/**
 * Manages the game state: score, lives, level, and progression rules.
 */
public class GameState {

    private static final int MAX_LIVES = 7;
    private static final int SPOTS_PER_LEVEL = 10;

    private int score = 0;
    private int lives = 3;
    private int level = 1;
    private int spotsClickedThisLevel = 0;
    private boolean gameOver = false;

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
    public boolean isGameOver() { return gameOver; }

    /** Called when a spot is successfully clicked. Returns true if leveled up. */
    public boolean registerHit() {
        score += 10 * level;
        spotsClickedThisLevel++;
        if (spotsClickedThisLevel >= SPOTS_PER_LEVEL) {
            level++;
            spotsClickedThisLevel = 0;
            if (lives < MAX_LIVES) lives++;
            return true;
        }
        return false;
    }

    /** Called when the player clicks empty space. */
    public void registerMiss() {
        score -= 15 * level;
    }

    /** Called when a spot escapes uncaught. Returns true if game is over. */
    public boolean registerSpotEscaped() {
        lives--;
        if (lives <= 0) {
            gameOver = true;
            return true;
        }
        return false;
    }
}
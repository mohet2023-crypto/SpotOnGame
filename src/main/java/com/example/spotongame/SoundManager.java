package com.example.spotongame;

import javafx.scene.media.AudioClip;
import java.util.Objects;

/**
 * Loads and plays all game audio.
 */
public class SoundManager {

    private final AudioClip hitSound;
    private final AudioClip missSound;
    private final AudioClip disappearSound;

    public SoundManager() {
        hitSound = load("/sounds/hit.mp3", 0.4);
        missSound = load("/sounds/miss.mp3", 0.3);
        disappearSound = load("/sounds/disappear.mp3", 0.3);
    }

    private AudioClip load(String path, double volume) {
        AudioClip clip = new AudioClip(Objects.requireNonNull(
                getClass().getResource(path)).toString());
        clip.setVolume(volume);
        return clip;
    }

    public void playHit()       { if (!hitSound.isPlaying()) hitSound.play(); }
    public void playMiss()      { if (!missSound.isPlaying()) missSound.play(); }
    public void playDisappear() { if (!disappearSound.isPlaying()) disappearSound.play(); }

    public void stopAll() {
        hitSound.stop();
        missSound.stop();
        disappearSound.stop();
    }
}
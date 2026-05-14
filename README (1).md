# 🎯 SpotOn Game

An interactive reflex-based game built with **Java** and **JavaFX**. Spots appear on screen, move across the pane, and shrink as they travel. Click them before they disappear — but don't miss!

---

## 🎮 Gameplay

- Spots spawn every 0.5 seconds and travel across the screen while shrinking
- **Click a spot** → earn points
- **Miss a click** → lose points
- **Spot escapes** → lose a life
- Clear **10 spots** to level up — spots get faster each level
- Gain **+1 life** on every level up (max 7 lives)
- Game ends when all lives are lost

### Scoring
| Action | Effect |
|---|---|
| Successful click | +10 × current level |
| Missed click | −15 × current level |
| Spot escapes | −1 life |
| Level up | +1 life (max 7) |

---

## ✨ Features

- 🔴 Animated spots with red/green image fills
- 🎵 Sound effects for hits, misses, and disappearing spots
- ❤️ Lives displayed as icons
- 📈 Progressive difficulty — spots speed up each level
- 🎯 Accurate miss-click detection
- 🏁 Game Over dialog with final score and level reached

---

## 🛠️ Technologies Used

- **Java 21+**
- **JavaFX 21** — GUI, animations, event handling
- **OOP Design** — inheritance, encapsulation, composition
- **Maven** — dependency management

---

## 🗂️ Project Structure

```
src/main/java/com/example/spotongame/
├── Launcher.java        # Entry point — launches the JavaFX app
├── SpotOnApp.java       # Sets up the Stage and Scene (window)
├── GamePane.java        # Main UI container — coordinates everything
├── Spot.java            # Extends Circle — the clickable spot
├── GameState.java       # Manages score, lives, level logic
├── SoundManager.java    # Loads and plays all audio clips
└── SpotAnimator.java    # Builds PathTransition + ScaleTransition animations

src/main/resources/
├── images/
│   ├── red_spot.png
│   ├── green_spot.png
│   └── life.png
└── sounds/
    ├── hit.mp3
    ├── miss.mp3
    └── disappear.mp3
```

---

## 🚀 How to Run

### Prerequisites
- Java JDK 17 or higher
- IntelliJ IDEA (recommended)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/SpotOnGame.git
   ```

2. **Open in IntelliJ IDEA**
   - File → Open → select the `SpotOnGame` folder
   - Wait for Maven to download dependencies automatically

3. **Run the game**
   - Right-click `Launcher.java` → Run 'Launcher.main()'
   - Or use the Run button after selecting the Launcher configuration

---

## 🏗️ OOP Design Highlights

### Inheritance
- `Spot extends Circle` — the spot is a JavaFX Circle with an image fill
- `GamePane extends Pane` — the game board is a JavaFX layout container
- `SpotOnApp extends Application` — required JavaFX application entry point

### Encapsulation
- `GameState` keeps `score`, `lives`, and `level` private, exposing only clean methods like `registerHit()`, `registerMiss()`, and `registerSpotEscaped()`

### Composition
- `GamePane` is composed of `GameState`, `SoundManager`, and `SpotAnimator` — each handles one responsibility

### Single Responsibility Principle
Each class has exactly one job:

| Class | Responsibility |
|---|---|
| `GameState` | Score, lives, level logic |
| `SoundManager` | Audio loading and playback |
| `SpotAnimator` | Animation construction |
| `Spot` | Visual spot appearance |
| `GamePane` | UI coordination |

---

## 📊 UML Class Diagram

```
          Launcher
              │ uses
         SpotOnApp (extends Application)
              │ creates
          GamePane (extends Pane)
         ╱    │    ╲
GameState  SoundManager  SpotAnimator
                              │ creates
                            Spot (extends Circle)
```

---

## 📚 Key JavaFX Concepts Used

- **`ParallelTransition`** — runs `PathTransition` (movement) and `ScaleTransition` (shrinking) simultaneously
- **`Timeline`** — spawns a new spot every 0.5 seconds using `KeyFrame`
- **`setOnMouseClicked`** — handles both spot clicks and pane-level miss-click detection
- **`ImagePattern`** — fills Circle shapes with PNG images
- **`AudioClip`** — plays short sound effects with low latency
- **`HBox`** — displays life icons in a horizontal row

---

## 📄 License

This project was built as part of an Object-Oriented Programming course assignment.

---

> Built with ❤️ using Java & JavaFX

# Minesweeper Game

## Overview

This project is a Java-based implementation of the classic Minesweeper game. The game features a graphical user interface (GUI) built using JavaFX and requires the implementation of fundamental data structures, including a dynamic array and a 2D grid, to support the gameplay. A fun twist that was added is that the first click is **not** guaranteed to be safe, meaning it could reveal a mine.

## Features

- **Dynamic Array Implementation:** The internal game board storage is managed using a custom dynamic array.
- **Minesweeper Gameplay:** Supports multiple difficulty levels, with predefined grid sizes and mine counts.
- **JavaFX GUI:** A fully functional graphical user interface that allows users to play the game with ease.
- **Game States:** Tracks game status, including `INIT`, `IN_GAME`, `EXPLODED`, and `SOLVED`.

## Game Levels

| Level  | Rows | Columns | Mines |
|--------|------|---------|-------|
| Tiny   | 5    | 5       | 3     |
| Easy   | 9    | 9       | 10    |
| Medium | 16   | 16      | 40    |
| Hard   | 16   | 30      | 99    |
| Custom | ?    | ?       | ?     |

## Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Ensure you have JDK 11 or higher installed.
- **JavaFX SDK:** Download the JavaFX SDK from the official [JavaFX website](https://gluonhq.com/products/javafx/) and set it up as described below.

### Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/husseinAl2/Minesweeper.git
   cd Minesweeper/src

2. **Set the JavaFX path:**

   Set up the path to your JavaFX SDK. Replace the path below with the path to your JavaFX SDK on your machine.
   ```bash
   export PATH_TO_FX=/path/to/your/javafx-sdk/lib
3. **Compile the project:**

   Compile all .java files using the JavaFX modules.
   ```bash
   javac --module-path $PATH_TO_FX --add-modules javafx.controls *.java
4. **Run the game:**

   Run the Minesweeper game using the following command:
   ```bash
   java --module-path $PATH_TO_FX --add-modules javafx.controls MineGUI

## Usage
- **Left-click:** Opens a cell to reveal whether it is a mine or how many adjacent mines are present.
- **Right-click:** Flags or unflags a cell as a potential mine.
- **Game Status:** The game tracks whether it's in INIT, IN_GAME, EXPLODED, or SOLVED state.

## Project Structure
- **DynArr310.java:** Implements a dynamic array class used for internal storage.
- **DynGrid310.java:** Implements a 2D grid using dynamic arrays.
- **MineSweeper.java:** The main game logic, including mine placement, cell state management, and gameplay rules.
- **MineGUI.java:** The graphical user interface for interacting with the game.
- **Cell.java:** Represents individual cells on the Minesweeper board, including their state (hidden, exposed, flagged, or exploded) and whether they contain a mine.

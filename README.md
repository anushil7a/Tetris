# Dynamic Arrays Project
<img width="607" alt="tetris" src="https://github.com/user-attachments/assets/6710f5dd-ee80-4ec8-a315-167988e91000">

## Overview
This project implements a custom version of the classic **Tetris** game with several unique twists. The game is built to explore the concept of dynamic arrays in a practical context.

## Features
- **Randomized Shapes**: Unlike the traditional Tetris, the pieces in this game have random shapes and sizes.
- **Advanced Piece Manipulation**: Pieces can be flipped horizontally and vertically, rotated, and scaled up or down.
- **Dynamic Board**: The game board changes based on the player's actions, providing a more complex gameplay experience.

## Classes
### DynamicArray
A generic class implementing a dynamic array. The array can contain null values, representing empty spots on the Tetris board.

### Tile
Represents a single tile of a Tetris piece. Each piece can occupy up to a 3x3 square and have up to 9 tiles.

### Block
Represents a two-dimensional Tetris piece. Blocks are dynamic and can transform by moving, flipping, rotating, or scaling.

### Board
Represents the game board where the pieces drop. The board can dynamically change its content based on player actions.

### Tetris
Implements the rules of the Tetris game, validating player actions and checking for game-over conditions.

### Game
Handles the initialization, GUI, and event management of the game.

## Installation
1. Clone the repository to your local machine.
2. Ensure you have Java installed on your system.
3. Compile the project files.

## Running the Game
To run the game, use the following command:
This will create a game board with 20 width and 15 height.
```bash
java Game 20 15

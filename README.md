# Connections - A Java Implementation

## Overview

Connections is a Java-based version of the popular New York Times game *Connections*. The game challenges players to group words into sets of four based on a common theme, this version contains three levels of difficulty. This project is designed with Java and JavaFX for the user interface.

## Features

- Interactive graphical user interface (GUI) built with JavaFX
- Randomized word groups for a unique challenge every round
- Feedback for correct and incorrect groupings
- Allows users to add their own categories to the game

## Installation

### Steps

1. Clone the repository:
   ```sh
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```sh
   cd connections
   ```
3. Compile and run the application:
   ```sh
   ```

## Usage

- Start the game and choose between three levels (2-4 words)
- Observe the words displayed on the screen.
- Select words that you believe share a common theme.
- Click the "Submit" button to check your selection.
- If correct, the words will be grouped; if incorrect, you'll receive feedback.
- Continue until all groups are correctly identified.

## Project Structure

```
connections-game/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── connections/
│   │   │   │   ├── ConnectionsApp.java   # Main UI application file
│   │   │   │   ├── AppController.java   # Connects game logic to UI
│   │   │   │   ├── Delegator.java        # Manages logic
│   │   │   │   ├── FourWordWorker.java   # Manages logic
│   │   │   │   ├── ThreeWordWorker.java   # Manages logic
│   │   │   │   ├── TwoWordWorker.java     # Manages logic
│   │   │   │   ├── WorkerInterface.java       
│   │   │   │   ├── Save.java            # Saves user information
│   │   ├── resources/
│   │   │   ├── connections/
│   │   │   │   ├── App.fxml            
│── README.md
│── pom.xml
```

## Acknowledgments

- Inspired by the *Connections* game by The New York Times.
- Built using Java and JavaFX.

Enjoy the game!


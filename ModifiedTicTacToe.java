import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ModifiedTicTacToe extends Application {

    private char currentPlayer = 'X';  // 'X' starts the game
    private Button[][] buttons = new Button[5][5];  // 5x5 grid of buttons

    public static void main(String[] args) { // main function
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        // Create buttons and add them to the grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button button = createButton();
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        // Set up the scene and display the stage
        Scene scene = new Scene(gridPane, 300, 300); //wxh
        primaryStage.setTitle("TicTacToe 5x5"); //title
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Create a button with a specific size and action
    private Button createButton() {
        Button button = new Button();
        button.setMinSize(60, 60);
        button.setOnAction(event -> onButtonClick(button));
        return button;
    }

    // Handle button click events
    private void onButtonClick(Button button) {
        if (button.getText().equals("")) {
            button.setText(String.valueOf(currentPlayer));

            // Check for a win or draw
            if (checkForWin()) {
                showAlert("Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetGame();
            } else {
                // Switch to the other player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Check if the current player has won
    private boolean checkForWin() {
        for (int i = 0; i < 5; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        return checkDiagonals();
    }

    // Check if a specific row is filled with the current player's marks
    private boolean checkRow(int row) {
        for (int i = 0; i < 5; i++) {
            if (buttons[row][i].getText().charAt(0) != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    // Check if a specific column is filled with the current player's marks
    private boolean checkColumn(int col) {
        for (int i = 0; i < 5; i++) {
            if (buttons[i][col].getText().charAt(0) != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    // Check if either diagonal is filled with the current player's marks
    private boolean checkDiagonals() {
        // Check main diagonal
        for (int i = 0; i < 5; i++) {
            if (buttons[i][i].getText().charAt(0) != currentPlayer) {
                break;
            }
            if (i == 4) {
                return true;
            }
        }

        // Check anti-diagonal
        for (int i = 0; i < 5; i++) {
            if (buttons[i][4 - i].getText().charAt(0) != currentPlayer) {
                break;
            }
            if (i == 4) {
                return true;
            }
        }

        return false;
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Reset the game by clearing all buttons and resetting the current player
    private void resetGame() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    // Display an alert with the specified message
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

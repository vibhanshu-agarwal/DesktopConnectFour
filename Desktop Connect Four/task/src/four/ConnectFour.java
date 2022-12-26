package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectFour extends JFrame {


    AtomicInteger nrTurns = new AtomicInteger(0);
    AtomicBoolean isTerminate = new AtomicBoolean(false);
    int rows = 6;
    int cols = 7;

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 1000);
        setVisible(true);
        setLayout(null);
        setTitle("Connect Four");

        build();
    }

    public void build() {
        //Build the GameBoard for Connect4
        buildGameBoard();


        //make a move on the game board
//        makeMove();

        //check if the game is over, or we have a winner.
//        checkGameOver();

    }

    private void buildGameBoard() {
        //Create a 6X7 grid of buttons using GridLayout
        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(rows, cols));


        //Create a 2D array of buttons
        JButton[][] buttons = new JButton[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //For every cell on game board
                //1. Create button
                buttons[i][j] = new JButton();
                //2. Set its properties
                setButtonProperties(buttons[i][j], colToLetter(j) + (cols - (i + 1)));
                //3. Add it to game board
                gameBoard.add(buttons[i][j]);
                //4. Attach an action listener to the button
                buttons[i][j].addActionListener(e -> performActionOnBtnClick(buttons, e));
            }
        }

        gameBoard.setBounds(0, 0, 600, 800);
        gameBoard.setVisible(true);
        add(gameBoard);

        //Add reset button to game board
        JPanel gameInfo = new JPanel();
        gameInfo.setLayout(new BorderLayout());

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.setPreferredSize(new Dimension(80, 60));
        resetButton.setVisible(true);

        resetButton.addActionListener(e -> {
            nrTurns.set(0);
            isTerminate.set(false);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    setButtonProperties(buttons[i][j], colToLetter(j) + (cols - (i + 1)));
                }
            }
        });

        gameInfo.add(resetButton, BorderLayout.EAST);

        gameInfo.setBounds(480, 810, 120, 80);
        gameInfo.setVisible(true);
        add(gameInfo);

        if (isTerminate.get()) {
            JOptionPane.showMessageDialog(null, "Game Over");
        }
    }

    private void performActionOnBtnClick(JButton[][] buttons, ActionEvent e) {
        if (isTerminate.get()) {
            return;
        }
        JButton button = (JButton) e.getSource();
        button.setFocusPainted(false); // to remove highlighting from the cell.
        //Get the column letter of the button
        String colLetter = button.getName().substring(6, 7);
        //Find the first empty row in column
        int row = findFirstEmptyRow(buttons, colLetter);
        int col = colToNumber(colLetter);
        //If there is an empty row, then update the text on that button to X or O
        if (row != -1) {
            makeMove(col, buttons[row]);
        }

        //Check if there is a winner
        if (checkWinner(buttons, rows, cols) || nrTurns.get() == 42) {
            isTerminate.set(true);
        }
    }

    private boolean checkWinner(JButton[][] buttons, int rows, int cols) {
        //Loop through all the buttons and check if there is a winner
        boolean isWinner;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //Check if there is a winner in the row/column/up diagonal/down diagonals
                isWinner = checkWinnerInRow(buttons, cols, i, j)
                        || checkWinnerInColumn(buttons, rows, i, j)
                        || checkWinnerInUpDiagonal(buttons, rows, cols, i, j)
                        || checkWinnerInDownDiagonal(buttons, rows, i, j);
                if (isWinner) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkWinnerInDownDiagonal(JButton[][] button, int rows, int i, int j) {
        boolean isWinner = false;
        if (i + 3 < rows && j - 3 >= 0
                && button[i][j].getText().equals(button[i + 1][j - 1].getText())
                && button[i][j].getText().equals(button[i + 2][j - 2].getText())
                && button[i][j].getText().equals(button[i + 3][j - 3].getText())
                && !button[i][j].getText().equals(" ")) {
            button[i][j].setBackground(Color.GREEN);
            button[i + 1][j - 1].setBackground(Color.GREEN);
            button[i + 2][j - 2].setBackground(Color.GREEN);
            button[i + 3][j - 3].setBackground(Color.GREEN);
            isWinner = true;
        }
        return isWinner;
    }

    private static boolean checkWinnerInUpDiagonal(JButton[][] button, int rows, int cols, int i, int j) {
        boolean isWinner = false;
        if (i + 3 < rows && j + 3 < cols
                && button[i][j].getText().equals(button[i + 1][j + 1].getText())
                && button[i][j].getText().equals(button[i + 2][j + 2].getText())
                && button[i][j].getText().equals(button[i + 3][j + 3].getText())
                && !button[i][j].getText().equals(" ")) {
            button[i][j].setBackground(Color.GREEN);
            button[i + 1][j + 1].setBackground(Color.GREEN);
            button[i + 2][j + 2].setBackground(Color.GREEN);
            button[i + 3][j + 3].setBackground(Color.GREEN);
            isWinner = true;
        }
        return isWinner;
    }

    private static boolean checkWinnerInColumn(JButton[][] button, int rows, int i, int j) {
        boolean isWinner = false;
        if (i + 3 < rows
                && button[i][j].getText().equals(button[i + 1][j].getText())
                && button[i][j].getText().equals(button[i + 2][j].getText())
                && button[i][j].getText().equals(button[i + 3][j].getText())
                && !button[i][j].getText().equals(" ")) {
            button[i][j].setBackground(Color.GREEN);
            button[i + 1][j].setBackground(Color.GREEN);
            button[i + 2][j].setBackground(Color.GREEN);
            button[i + 3][j].setBackground(Color.GREEN);
            isWinner = true;
        }
        return isWinner;
    }

    private static boolean checkWinnerInRow(JButton[][] button, int cols, int i, int j) {
        boolean isWinner = false;
        if (j + 3 < cols
                && button[i][j].getText().equals(button[i][j + 1].getText())
                && button[i][j].getText().equals(button[i][j + 2].getText())
                && button[i][j].getText().equals(button[i][j + 3].getText())
                && !button[i][j].getText().equals(" ")) {
            button[i][j].setBackground(Color.GREEN);
            button[i][j + 1].setBackground(Color.GREEN);
            button[i][j + 2].setBackground(Color.GREEN);
            button[i][j + 3].setBackground(Color.GREEN);
            isWinner = true;
        }
        return isWinner;
    }

    private int colToNumber(String letter) {
        return (letter.charAt(0) - 65);
    }

    private int findFirstEmptyRow(JButton[][] buttons, String colLetter) {
        int row = -1;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getName().substring(6, 7).equals(colLetter)
                        && buttons[i][j].getText().equals(" ")) {
                    row = i;
                    break;
                }
            }
        }
        return row;
    }

    private void makeMove(int col, JButton[] buttons) {
        buttons[col].setText(nrTurns.get() % 2 == 0 ? "X" : "O");
        nrTurns.getAndIncrement();
    }

    private void setButtonProperties(JButton button, String btnLabel) {
        button.setText(" ");
        button.setName("Button" + btnLabel);
        button.setPreferredSize(new Dimension(30, 30));
        //Set the color of the button
        button.setBackground(Color.WHITE);
        button.setVisible(true);
    }

    private String colToLetter(int j) {
        return String.valueOf((char) (j + 65));
    }


}
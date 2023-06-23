package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[] buttons;
    private boolean isPlayerX;
    private JLabel statusLabel;

    public TicTacToe() {
        super("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            buttons[i].addActionListener(new ButtonClickListener());
            add(buttons[i]);
        }

        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel);

        isPlayerX = true;
    }

    private boolean checkWin(String player) {
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                {0, 4, 8}, {2, 4, 6} // diagonals
        };

        for (int[] combination : winCombinations) {
            int a = combination[0];
            int b = combination[1];
            int c = combination[2];
            if (buttons[a].getText().equals(player) && buttons[b].getText().equals(player) &&
                    buttons[c].getText().equals(player))
                return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().isEmpty())
                return false;
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        isPlayerX = true;
        statusLabel.setText("Player X's Turn");
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();

            if (button.getText().isEmpty()) {
                if (isPlayerX) {
                    button.setText("X");
                    if (checkWin("X")) {
                        JOptionPane.showMessageDialog(null, "Player X wins!");
                        resetGame();
                    } else if (checkDraw()) {
                        JOptionPane.showMessageDialog(null, "It's a draw!");
                        resetGame();
                    } else {
                        isPlayerX = false;
                        statusLabel.setText("Player O's Turn");
                    }
                } else {
                    button.setText("O");
                    if (checkWin("O")) {
                        JOptionPane.showMessageDialog(null, "Player O wins!");
                        resetGame();
                    } else if (checkDraw()) {
                        JOptionPane.showMessageDialog(null, "It's a draw!");
                        resetGame();
                    } else {
                        isPlayerX = true;
                        statusLabel.setText("Player X's Turn");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe ticTacToe = new TicTacToe();
            ticTacToe.setVisible(true);
        });
    }
}

package JDBC;

import java.awt.*;

import javax.swing.*;

class HomePage extends JPanel {
    private JButton[] buttons;
    private char currentPlayer;

    public HomePage(RegistrationApplication mainApp) {
        setLayout(new BorderLayout());
        
        JPanel ticTacToePanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];
        currentPlayer = 'X';
        
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            int index = i;
            buttons[i].addActionListener(e -> handleMove(index));
            ticTacToePanel.add(buttons[i]);
        }
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> mainApp.showCard("LoginPage"));
        
        add(new JLabel("Tic Tac Toe"), BorderLayout.NORTH);
        add(ticTacToePanel, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);
    }
    
    private void handleMove(int index) {
        if (buttons[index].getText().equals("")) {
            buttons[index].setText(String.valueOf(currentPlayer));
            if (checkWinner()) {
                JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                resetBoard();
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }
    
    private boolean checkWinner() {
        String line = "" + currentPlayer + currentPlayer + currentPlayer;
        return (buttons[0].getText() + buttons[1].getText() + buttons[2].getText()).equals(line) ||
               (buttons[3].getText() + buttons[4].getText() + buttons[5].getText()).equals(line) ||
               (buttons[6].getText() + buttons[7].getText() + buttons[8].getText()).equals(line) ||
               (buttons[0].getText() + buttons[3].getText() + buttons[6].getText()).equals(line) ||
               (buttons[1].getText() + buttons[4].getText() + buttons[7].getText()).equals(line) ||
               (buttons[2].getText() + buttons[5].getText() + buttons[8].getText()).equals(line) ||
               (buttons[0].getText() + buttons[4].getText() + buttons[8].getText()).equals(line) ||
               (buttons[2].getText() + buttons[4].getText() + buttons[6].getText()).equals(line);
    }
    
    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    
    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        currentPlayer = 'X';
    }
}


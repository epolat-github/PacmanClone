/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanClone;

import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        gameScreen game = new gameScreen();
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(672, 864);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);

        frame.setVisible(true);
    }

}

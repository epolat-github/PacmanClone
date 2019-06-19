/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class gameScreen extends JPanel implements KeyListener {
    
    private createMaze maze;
    private Tiles[][] tiles;
    
    private String testDirection = "left";
    private String direction = "left";

    //public static int dotCount = 350;
    private boolean gameRunning = false;
    
    private pacmanSprite pacman = null;
    private blinkySprite blinky = null;
    
    private BufferedImage pacmanImage = null;
    private BufferedImage blinkyImage = null;
    
    private boolean collision = true;
    
    Timer movePacmanTimer = new Timer();
    Timer collisionTimer = new Timer();
    Timer checkGameTimer = new Timer();
    Timer moveBlinkyTimer = new Timer();
    
    TimerTask checkGameTask = new TimerTask() {
        @Override
        public void run() {
//            if (ghostCollision()) {
//                //System.exit(0);
//                endGame();
//            }
        }
    };
    
    TimerTask movePacmanTask = new TimerTask() {
        @Override
        public void run() {
            
            if (pacman != null) {
                pacman.movePacman(direction);
                //blinky.moveBlinky(pacman.getCoordinate());
            }
            repaint();
        }
    };
    
    TimerTask moveBlinkyTask = new TimerTask() {
        @Override
        public void run() {
            if (blinky != null) {
                blinky.moveBlinky(pacman.getCoordinate());
            }
        }
    };
    
    TimerTask collisionTask = new TimerTask() {
        @Override
        public void run() {
            if (pacman != null) {
                if (pacman.checkCollision(testDirection)) {
                    direction = testDirection;
                }
            }
        }
    };
    
    public gameScreen() throws HeadlessException {
        
        try {
            maze = new createMaze();
            tiles = maze.getTiles();
            initComponents();
            repaint();
            moveBlinkyTimer.schedule(moveBlinkyTask, 100, 180);
            movePacmanTimer.schedule(movePacmanTask, 0, 90);
            collisionTimer.schedule(collisionTask, 100, 80);
            checkGameTimer.schedule(checkGameTask, 500, 10);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "IOException");
        }
        
    }
    
    private void initComponents() {
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        setBackground(Color.BLACK);
        
    }
    
    private boolean ghostCollision() {
        if (pacman.getCoordinate().x == blinky.getCoordinate().x
                && blinky.getCoordinate().y == blinky.getCoordinate().y) {
            return true;
        }
        return false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xTile, yTile;
        Tiles tile;
        
        for (yTile = 0; yTile < tiles.length; yTile++) {
            for (xTile = 0; xTile < tiles[yTile].length; xTile++) {
                tile = tiles[yTile][xTile];
                
                switch (tile.getType()) {
                    case ("wall"):
                        g.setColor(Color.BLUE);
                        g.fillRect(xTile * 24, yTile * 24, 24, 24);
                        break;
                    case ("dotted"):
                        g.drawImage(tile.getdottedTile(), xTile * 24, yTile * 24,
                                24, 24, this);
                        break;
                    case ("pellet"):
                        g.drawImage(tile.getpelletTile(), xTile * 24, yTile * 24,
                                24, 24, this);
                        break;
                    case ("pacman"):
                        pacman = (pacmanSprite) tile;
                        pacmanImage = pacman.returnImage();
                        g.drawImage(pacmanImage, pacman.getCoordinate().x,
                                pacman.getCoordinate().y,
                                24, 24, this);
                        break;
                    case ("blinky"):
                        blinky = (blinkySprite) tile;
                        blinkyImage = blinky.returnImage();
                        g.drawImage(blinkyImage, blinky.getCoordinate().x,
                                blinky.getCoordinate().y,
                                24, 24, this);
                        break;
                    case ("blank"):
                        break;
                }
            }
        }
        gameRunning = true;
        
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                testDirection = "up";
                break;
            case KeyEvent.VK_DOWN:
                testDirection = "down";
                break;
            case KeyEvent.VK_RIGHT:
                testDirection = "right";
                break;
            case KeyEvent.VK_LEFT:
                testDirection = "left";
                break;
            case KeyEvent.VK_S:
                movePacmanTimer.cancel();
                break;
            case KeyEvent.VK_R:
                
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    private void endGame() {
        movePacmanTask.cancel();
        JOptionPane.showMessageDialog(this, "Game Over!");
        
    }
    
}

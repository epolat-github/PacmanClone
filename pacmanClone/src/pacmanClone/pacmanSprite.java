/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanClone;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static pacmanClone.createMaze.tiles;
//import static pacmandeneme5.gameScreen.dotCount;

/**
 *
 * @author User
 */
public class pacmanSprite extends Tiles {

    //pacman path
    private String pacmanPath = getClass().getResource("resources/pacman").getPath();
    private BufferedImage rightSide, leftSide, downSide, upSide;

    private final int moveSpeed = 24;
    private String way = "left";

    private int xTile;
    private int yTile;

    private int xCoor;
    private int yCoor;

    public pacmanSprite() {
    }

    public pacmanSprite(int xTile, int yTile) {
        //this.setType("pacman");
        this.xTile = xTile;
        this.yTile = yTile;
        tileToPixel();
        initializeImages();
    }

    private void initializeImages() {
        try {
            rightSide = ImageIO.read(new File(pacmanPath + "/pacman_right.png"));
            leftSide = ImageIO.read(new File(pacmanPath + "/pacman_left.png"));
            downSide = ImageIO.read(new File(pacmanPath + "/pacman_down.png"));
            upSide = ImageIO.read(new File(pacmanPath + "/pacman_up.png"));
        } catch (IOException ex) {
            System.out.println("io exception");
        }
    }

    public BufferedImage returnImage() {
        switch (way) {
            case ("up"):
                return upSide;
            case ("left"):
                return leftSide;
            case ("right"):
                return rightSide;
            case ("down"):
                return downSide;
            default:
                return null;
        }
    }

    private void tileToPixel() {
        xCoor = xTile * 24;
        yCoor = yTile * 24;
    }

    public Point getCoordinate() {
        Point point = new Point(xCoor, yCoor);
        return point;
    }

    public void movePacman(String direction) {
        if (checkCollision(direction)) {
            way = direction;
            switch (direction) {
                case ("up"):
                    yCoor -= moveSpeed;
                    break;
                case ("down"):
                    yCoor += moveSpeed;
                    break;
                case ("right"):
                    xCoor += moveSpeed;
                    break;
                case ("left"):
                    xCoor -= moveSpeed;
                    break;
            }
        }
    }

    public boolean checkCollision(String direction) {
        switch (direction) {
            case ("up"): {
                int upperTileX = (xCoor + 12) / 24;
                int upperTileY = (yCoor - 24) / 24;
                Tiles tile = tiles[upperTileY][upperTileX];
                if ((tile.getType().equals("wall"))) {
                    return false;
                } else if (tile.getType().equals("dotted")
                        || tile.getType().equals("pellet")) {
                    tiles[upperTileY][upperTileX].setType("blank");
                    //if (dotCount != 0) dotCount--;

                }
                break;
            }
            case ("down"): {
                int downTileX = (xCoor + 12) / 24;
                int downTileY = (yCoor + 24) / 24;
                Tiles tile = tiles[downTileY][downTileX];
                if ((tile.getType().equals("wall"))) {
                    return false;
                } else if (tile.getType().equals("dotted")) {
                    tiles[downTileY][downTileX].setType("blank");
                    //if (dotCount != 0) dotCount--;

                }
                break;
            }
            case ("right"): {
                int rightTileX = (xCoor + 24) / 24;
                int rightTileY = (yCoor) / 24;
                Tiles tile = tiles[rightTileY][rightTileX];
                if ((tile.getType().equals("wall"))) {
                    return false;
                } else if (tile.getType().equals("dotted")) {
                    tiles[rightTileY][rightTileX].setType("blank");
                    //if (dotCount != 0) dotCount--;

                }
                break;
            }
            case ("left"): {
                int leftTileX = (xCoor - 12) / 24;
                int leftTileY = (yCoor + 12) / 24;
                Tiles tile = tiles[leftTileY][leftTileX];
                if ((tile.getType().equals("wall"))) {
                    return false;
                } else if (tile.getType().equals("dotted")) {
                    tiles[leftTileY][leftTileX].setType("blank");
                    //if (dotCount != 0) dotCount--;                    
                }
                break;
            }
        }
        return true;
    }

    public BufferedImage getRightSide() {
        return rightSide;
    }

    public BufferedImage getLeftSide() {
        return leftSide;
    }

    public BufferedImage getDownSide() {
        return downSide;
    }

    public BufferedImage getUpSide() {
        return upSide;
    }

}

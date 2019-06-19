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

/**
 *
 * @author User
 */
public class ghostSprites extends Tiles {

    //private String path = getClass().getResource("resources/ghost").getPath();
    private BufferedImage ghostImage;

    protected int moveSpeed = 24;
    protected String way = "left";

    protected int xTile;
    protected int yTile;

    protected int xCoor;
    protected int yCoor;

    public ghostSprites() {
    }

    public ghostSprites(int xTile, int yTile, String name) {
        this.xTile = xTile;
        this.yTile = yTile;
        tileToPixel();
        initializeImages(name);
    }

    private void initializeImages(String name) {
        try {
            String ghostPath = getClass().getResource("resources/ghost/" + name + ".png").getPath();
            ghostImage = ImageIO.read(new File(ghostPath));
        } catch (IOException ex) {
            System.out.println("io exception");
        }
    }

    public BufferedImage returnImage() {
        return ghostImage;
    }

    private void tileToPixel() {
        xCoor = xTile * 24;
        yCoor = yTile * 24;
    }

    public Point getCoordinate() {
        Point point = new Point(xCoor, yCoor);
        return point;
    }

}

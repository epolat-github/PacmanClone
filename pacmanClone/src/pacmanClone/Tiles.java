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
public class Tiles {

    private BufferedImage wallTile;
    private BufferedImage dottedTile;
    private BufferedImage pelletTile;
    private String type;

    private Point point;

    public BufferedImage getdottedTile() {
        try {
            //dot.png path
            String dottedPath = getClass().getResource("resources/maze/dot.png").getPath();
            dottedTile = ImageIO.read(new File(dottedPath));
        } catch (IOException ex) {
            System.out.println("dotted tile image not found!s");
        }
        return dottedTile;
    }

    public BufferedImage getpelletTile() {
        try {
            //pellet.png path
            String pelletPath = getClass().getResource("resources/maze/pellet.png").getPath();
            pelletTile = ImageIO.read(new File(pelletPath));
        } catch (IOException ex) {
            System.out.println("pellet tile image not found!s");
        }
        return pelletTile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(int x, int y) {
        this.point = new Point(x * 24, y * 24);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanClone;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import static pacmanClone.createMaze.tiles;

/**
 *
 * @author User
 */
public class blinkySprite extends ghostSprites {

    private Point pacmanPoint;
    private Point blinkyPoint;
    private ArrayList<Double> tilesSurround;

    public blinkySprite(int xTile, int yTile, String name) {
        super(xTile, yTile, name);
    }

    public void moveBlinky(Point pacmanPoint) {
        tilesSurround = new ArrayList();
        this.pacmanPoint = pacmanPoint;
        this.blinkyPoint = this.getCoordinate();
        checkMoveableBlinky();

        switch (this.way) {
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

    private void checkMoveableBlinky() {

        Tiles leftTile = tiles[blinkyPoint.y / 24][(blinkyPoint.x - 24) / 24];
        Tiles upperTile = tiles[(blinkyPoint.y - 24) / 24][blinkyPoint.x / 24];
        Tiles rightTile = tiles[blinkyPoint.y / 24][(blinkyPoint.x + 24) / 24];
        Tiles lowerTile = tiles[(blinkyPoint.y + 24) / 24][blinkyPoint.x / 24];

        double leftDistance = 0;
        double upperDistance = 0;
        double rightDistance = 0;
        double lowerDistance = 0;

        if (!(leftTile.getType().equals("wall") || leftTile.getType().equals("blinky"))) {
            leftDistance = pacmanPoint.distance(leftTile.getPoint());
            tilesSurround.add(leftDistance);
        }
        if (!(upperTile.getType().equals("wall"))) {
            upperDistance = pacmanPoint.distance(upperTile.getPoint());
            tilesSurround.add(upperDistance);
        }
        if (!(rightTile.getType().equals("wall"))) {
            rightDistance = pacmanPoint.distance(rightTile.getPoint());
            tilesSurround.add(rightDistance);
        }
        if (!(lowerTile.getType().equals("wall"))) {
            lowerDistance = pacmanPoint.distance(lowerTile.getPoint());
            tilesSurround.add(lowerDistance);
        }

        Collections.sort(tilesSurround);

        double desiredDistance = tilesSurround.get(0);

        if (desiredDistance == leftDistance) {
            this.way = "left";
        }
        if (desiredDistance == upperDistance) {
            this.way = "up";
        }
        if (desiredDistance == rightDistance) {
            this.way = "right";
        }
        if (desiredDistance == lowerDistance) {
            this.way = "down";
        }
    }

}

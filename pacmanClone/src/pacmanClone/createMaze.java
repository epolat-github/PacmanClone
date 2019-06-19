/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanClone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author User
 */
public class createMaze {

    private BufferedReader br;
    //maze.txt path
    private final String mazePath = getClass().getResource("resources/maze/mazeModel.txt").getPath();
    public static Tiles[][] tiles;
    private String line;

    Tiles tile;

    public createMaze() throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(mazePath));
        tiles = new Tiles[31][28];
        readMaze();
    }

    private void readMaze() throws IOException {
        int lineCount = 0;
        while ((line = br.readLine()) != null) {
            for (int charCount = 0; charCount < line.length(); charCount++) {
                tile = new Tiles();
                switch (line.charAt(charCount)) {
                    case '#':

                        tile.setType("wall");
                        tiles[lineCount][charCount] = tile;
                        break;
                    case '*':
                        tile.setPoint(charCount, lineCount);
                        tile.setType("pellet");
                        tiles[lineCount][charCount] = tile;
                        break;
                    case '.':
                        tile.setPoint(charCount, lineCount);
                        tile.setType("dotted");
                        tiles[lineCount][charCount] = tile;
                        break;
                    case 'M':
                        Tiles pacman = new pacmanSprite(charCount, lineCount);
                        pacman.setType("pacman");
                        tiles[lineCount][charCount] = pacman;
                        break;
                    case 'B':
                        Tiles blinky = new blinkySprite(charCount, lineCount, "blinky");
                        blinky.setType("blinky");
                        tiles[lineCount][charCount] = blinky;
                        break;
                    default:
                        tile.setPoint(charCount, lineCount);
                        tile.setType("blank");
                        tiles[lineCount][charCount] = tile;
                        break;
                }

            }
            lineCount++;

        }
    }

    public Tiles[][] getTiles() {
        return tiles;
    }

}

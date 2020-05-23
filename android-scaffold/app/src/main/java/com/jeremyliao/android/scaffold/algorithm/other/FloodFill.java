package com.jeremyliao.android.scaffold.algorithm.other;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class FloodFill {

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int origColor = image[sr][sc];
        fill(image, sr, sc, origColor, newColor);
        return image;
    }

    private static void fill(int[][] image, int x, int y, int origColor, int newColor) {
        int xBounds = image.length;
        int yBounds = image[0].length;
        if (x < 0 || x >= xBounds || y < 0 || y >= yBounds) {
            return;
        }
        if (image[x][y] != origColor) {
            return;
        }
        if (image[x][y] == -1) {
            return;
        }
        image[x][y] = -1;
        fill(image, x + 1, y, origColor, newColor);
        fill(image, x - 1, y, origColor, newColor);
        fill(image, x, y + 1, origColor, newColor);
        fill(image, x, y - 1, origColor, newColor);
        image[x][y] = newColor;
    }
}

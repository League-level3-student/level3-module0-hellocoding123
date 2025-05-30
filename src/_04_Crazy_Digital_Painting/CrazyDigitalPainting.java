package _04_Crazy_Digital_Painting;

import java.awt.Color;

public class CrazyDigitalPainting {
    // 1. Create two final static integers for the width and height of the display.
	final static int width = 400;
	final static int height = 400;

    // 2. Create a 2D array of Color objects. You will need to import
    //    java.awt.Color. Initialize the size of the array using the 
    //    integers created in step 1.
	Color [][]colors = new Color[width][height];


    public CrazyDigitalPainting() {
        // 3. Open the crazy_digital_painting.png file and look at the image.

        // 4. Iterate through the 2D array and initialize each Color object
        //    to a new color. The sample image was created using the following 
        //    pattern:
    	for(int r = 0; r < colors.length-1; r++) {
    		for(int c = 0; c < colors[r].length-1; c++) {
    			colors[r][c] = new Color(r % 256, (r * c) % 256, r % 256);
    		}
    	}
        // 5. Come up with your own pattern to make a cool crazy image.

        // 6. Use the ColorArrayDisplayer class to call the displayColorsAsImage method 
        //    to show off your picture.
    }

    public static void main(String[] args) {
        new CrazyDigitalPainting();
    }
}

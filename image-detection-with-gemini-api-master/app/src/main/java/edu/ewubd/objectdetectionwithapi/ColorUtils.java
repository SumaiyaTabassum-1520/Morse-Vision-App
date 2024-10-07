package edu.ewubd.objectdetectionwithapi;

import java.util.HashMap;
import java.util.Map;

public class ColorUtils {
    // Predefined list of colors and their RGB values
    private static final Map<String, int[]> COLORS = new HashMap<String, int[]>() {{
        put("Black", new int[]{0, 0, 0});
        put("White", new int[]{255, 255, 255});
        put("Red", new int[]{255, 0, 0});
        put("Green", new int[]{0, 255, 0});
        put("Blue", new int[]{0, 0, 255});
        put("Yellow", new int[]{255, 255, 0});
        put("Cyan", new int[]{0, 255, 255});
        put("Magenta", new int[]{255, 0, 255});
        put("Gray", new int[]{128, 128, 128});
        put("Orange", new int[]{255, 165, 0});
        put("Pink", new int[]{255, 192, 203});
        put("Purple", new int[]{128, 0, 128});
        put("Brown", new int[]{165, 42, 42});
    }};

    // Method to find the closest color name
    public static String getColorNameFromRgb(int red, int green, int blue) {
        double minDiff = Double.MAX_VALUE;
        String closestColorName = "Unknown";

        for (Map.Entry<String, int[]> entry : COLORS.entrySet()) {
            int[] colorRgb = entry.getValue();
            double diff = colorDifference(red, green, blue, colorRgb[0], colorRgb[1], colorRgb[2]);

            if (diff < minDiff) {
                minDiff = diff;
                closestColorName = entry.getKey();
            }
        }

        return closestColorName;
    }

    // Helper method to calculate the difference between two colors
    private static double colorDifference(int r1, int g1, int b1, int r2, int g2, int b2) {
        return Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2, 2));
    }
}

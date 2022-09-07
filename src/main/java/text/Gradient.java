package text;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class Gradient {

    private String msg;
    private Color start;
    private Color end;

    public Gradient(String msg, Color start, Color end) {

        this.msg = msg;
        this.start = start;
        this.end = end;
    }

    float degrees = 0;
    float saturation = 0.5f;
    float brightness = 1;


    public String getHSBColor(float degrees, float saturation, float brightness) {
        Color c = Color.getHSBColor(degrees, saturation, brightness);
        return ChatColor.of(c) + "";
    }

    public String setGradient() {
        Color newcolor = new Color(1, 1, 1);

        int red = start.getRed() - end.getRed();
        int blue = start.getBlue() - end.getBlue();
        int green = start.getGreen() - end.getGreen();

        return ChatColor.of(new Color(red,blue,green)) + msg;
    }
}

package bildbearbeitermain;

import java.awt.*;
import java.awt.image.*;

public class Farbbild extends BufferedImage {

    /* Erzeuge ein Farbbild als Kopie von einem BufferedImage.*/
    public Farbbild(BufferedImage image) {
        super(image.getColorModel(), image.copyData(null),
                image.isAlphaPremultiplied(), null);
    }

    /*Erzeuge ein Farbbild mit der angegebenen Größe mit undefiniertem Inhalt.*/
    public Farbbild(int breite, int hoehe) {
        super(breite, hoehe, TYPE_INT_RGB);
    }

    /* Setze den angegebenen Bildpunkt dieses Bildes auf die angegebene Farbe. */
    public void setzePunktfarbe(int x, int y, Color col) {
        int punktfarbe = col.getRGB();
        setRGB(x, y, punktfarbe);
    }

    /* Liefere die Farbe des angegebenen Bildpunktes. */
    public Color gibPunktfarbe(int x, int y) {
        int punktfarbe = getRGB(x, y);
        return new Color(punktfarbe);
    }

    public void abdunkeln() {
        int hoehe = getHeight();
        int breite = getWidth();

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                setzePunktfarbe(x, y, farbe.darker());
            }
        }
    }

    public void aufhellen() {
        int hoehe = getHeight();
        int breite = getWidth();

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                setzePunktfarbe(x, y, farbe.brighter());
            }
        }
    }

    public void greyscale() {
        int hoehe = getHeight();
        int breite = getWidth();

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                int rot = farbe.getRed();
                int green = farbe.getGreen();
                int blue = farbe.getBlue();
                int brightness = (rot + green + blue) / 3;
                Color grayscale = new Color(brightness, brightness, brightness);
                setzePunktfarbe(x, y, grayscale);
            }
        }
    }

    public void weichzeichner() {
        int hoehe = getHeight();
        int breite = getWidth();
        Color[][] neuerGerner = new Color[hoehe - 1][breite - 1];
        Color[] farbe = new Color[9];

        for (int y = 1; y < hoehe - 1; y++) {
            for (int x = 1; x < breite - 1; x++) {
                farbe[0] = gibPunktfarbe(x + 1, y + 1);
                farbe[1] = gibPunktfarbe(x + 1, y);
                farbe[2] = gibPunktfarbe(x + 1, y - 1);
                farbe[3] = gibPunktfarbe(x - 1, y + 1);
                farbe[4] = gibPunktfarbe(x - 1, y);
                farbe[5] = gibPunktfarbe(x - 1, y - 1);
                farbe[6] = gibPunktfarbe(x, y + 1);
                farbe[7] = gibPunktfarbe(x, y - 1);
                farbe[8] = gibPunktfarbe(x, y);

                int r = 0;
                int g = 0;
                int b = 0;

                for (int i = 0; i < 9; i++) {
                    r = r + farbe[i].getRed();
                    g = g + farbe[i].getGreen();
                    b = b + farbe[i].getBlue();
                }
                r = r / 9;
                g = g / 9;
                b = b / 9;

                Color G = new Color(r, g, b);

                neuerGerner[y][x] = G;

            }
        }

        for (int y = 1; y < hoehe - 1; y++) {
            for (int x = 1; x < breite - 1; x++) {
                setzePunktfarbe(x, y, neuerGerner[y][x]);
            }
        }
    }

    public void Kantenerkennung() {
        int hoehe = getHeight();
        int breite = getWidth();
        Color[][] neuesBild = new Color[hoehe-1][breite-1];
        Color[] farbe = new Color[9];
        int rhigh = 0;
        int rlow = 0;
        int ghigh = 0;
        int glow = 0;
        int bhigh = 0;
        int blow = 0;
        
        for(int y = 1; y < hoehe-1; y++){
            for(int x = 1; x < breite-1; x++){
                farbe[0] = gibPunktfarbe(x+1, y);
                farbe[1] = gibPunktfarbe(x+1, y+1);
                farbe[2] = gibPunktfarbe(x, y+1);
                farbe[3] = gibPunktfarbe(x-1, y+1);
                farbe[4] = gibPunktfarbe(x-1, y);
                farbe[5] = gibPunktfarbe(x-1, y-1);
                farbe[6] = gibPunktfarbe(x, y-1);
                farbe[7] = gibPunktfarbe(x+1, y-1);
                farbe[8] = gibPunktfarbe(x,y);
                
                rhigh = farbe[0].getRed();
                rlow = farbe[0].getRed();
                ghigh = farbe[0].getGreen();
                glow = farbe[0].getGreen();
                bhigh = farbe[0].getBlue();
                blow = farbe[0].getBlue();
                
                for(int i = 1; i < 9; i++){
                    if(rhigh < farbe[i].getRed()){
                        rhigh = farbe[i].getRed();
                    }
                    
                    if(ghigh < farbe[i].getGreen()){
                        ghigh = farbe[i].getGreen();
                    }
                    
                    if(bhigh < farbe[i].getBlue()){
                        bhigh = farbe[i].getBlue();
                    }
                    
                    if(rlow > farbe[i].getRed()){
                        rlow = farbe[i].getRed();
                    }
                    
                    if(glow > farbe[i].getGreen()){
                        glow = farbe[i].getGreen();
                    }
                    
                    if(blow > farbe[i].getBlue()){
                        blow = farbe[i].getBlue();
                    }
                }
                
                Color c = new Color(rhigh-rlow,ghigh-glow,bhigh-blow);
                neuesBild[y][x] = c;
                
            }
        }
        
        for(int y = 1; y < hoehe-1; y++){
            for(int x = 1; x < breite-1; x++){
                setzePunktfarbe(x,y,neuesBild[y][x]);                
            }
            
        }
        
        negativ();
    }

    public void spiegelnn() {
        int hoehe = getHeight();
        int breite = getWidth();
        int p1 = hoehe - 1;
        int p2 = breite - 1;

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite / 2; x++, p2--) {

                Color punkt1 = gibPunktfarbe(x, y);
                Color punkt2 = gibPunktfarbe(p2, y);

                setzePunktfarbe(x, y, punkt2);
                setzePunktfarbe(p2, y, punkt1);
            }
            p2 = breite - 1;
        }
    }

    public void sw() {
        int hoehe = getHeight();
        int breite = getWidth();

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                int r = farbe.getRed();
                int g = farbe.getGreen();
                int b = farbe.getBlue();
                int gerner = r + g + b;
                if (gerner <= 255) {
                    Color megagerner = new Color(0, 0, 0);
                    setzePunktfarbe(x, y, megagerner);
                } else if (gerner <= 510) {
                    Color megagagerner = new Color(128, 128, 128);
                    setzePunktfarbe(x, y, megagagerner);
                } else {
                    Color mcgerner = new Color(255, 255, 255);
                    setzePunktfarbe(x, y, mcgerner);
                }
            }
        }
    }

    public void negativ() {
        int hoehe = getHeight();
        int breite = getWidth();

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                int r = farbe.getRed();
                int g = farbe.getGreen();
                int b = farbe.getBlue();

                int rn = 255 - r;
                int gn = 255 - g;
                int bn = 255 - b;

                Color mcgerner = new Color(rn, gn, bn);
                setzePunktfarbe(x, y, mcgerner);

            }
        }
    }

    public void negativ2() {
        int hoehe = getHeight();
        int breite = getWidth();

        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                int r = farbe.getRed();
                int g = farbe.getGreen();
                int b = farbe.getBlue();

                int rn;
                int gn;
                int bn;

                if (r < 128) {
                    rn = 255 - r;
                } else {
                    rn = r;
                }
                if (g < 128) {
                    gn = 255 - g;
                } else {
                    gn = g;
                }
                if (b < 128) {
                    bn = 255 - b;
                } else {
                    bn = b;
                }

                Color mcgerner = new Color(rn, gn, bn);
                setzePunktfarbe(x, y, mcgerner);

            }
        }
    }
}

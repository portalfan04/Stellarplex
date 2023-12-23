package helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureHandler {
    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return resizedImage;
    }

    public static BufferedImage blur(BufferedImage img) {
        BufferedImage blurImg = new BufferedImage(
                img.getWidth()-2, img.getHeight()-2, BufferedImage.TYPE_BYTE_GRAY
        );

        int pix = 0;
        for (int y=0; y<blurImg.getHeight(); y++)
        {
            for (int x=0; x<blurImg.getWidth(); x++)
            {
                pix = (int)(4*(img.getRGB(x+1, y+1)& 0xFF)
                        + 2*(img.getRGB(x+1, y)& 0xFF)
                        + 2*(img.getRGB(x+1, y+2)& 0xFF)
                        + 2*(img.getRGB(x, y+1)& 0xFF)
                        + 2*(img.getRGB(x+2, y+1)& 0xFF)
                        + (img.getRGB(x, y)& 0xFF)
                        + (img.getRGB(x, y+2)& 0xFF)
                        + (img.getRGB(x+2, y)& 0xFF)
                        + (img.getRGB(x+2, y+2)& 0xFF))/16;
                int p = (255<<24) | (pix<<16) | (pix<<8) | pix;
                blurImg.setRGB(x,y,p);
            }
        }

        return blurImg;
    }
    public static void TexBrownDwarf(String name, float seed) {
        float[] basic_array = new float[128];

        for (int i = 0; i < 128; i++) {
            float value = OpenSimplex2S.noise2_ImproveX((long) seed, i/4, 0);
            value++;
            basic_array[i] = value;
        }

        // Create a BufferedImage with a type that supports grayscale values
        BufferedImage image = new BufferedImage(1, 128, BufferedImage.TYPE_BYTE_GRAY);

        // Set pixel values based on the array
        for (int y = 0; y < 128; y++) {
            int pixelValue = (int) (basic_array[y] * 128); // Scale the value to 0-255
            image.setRGB(0, y, (pixelValue << 16) | (pixelValue << 8) | pixelValue);
        }

        image = resizeImage(image, 256, 128);
        image = blur(blur(image));
        image = resizeImage(image, 4096, 2048);

        // Save the image to a file
        try {
            ImageIO.write(image, "PNG", new File("Stellarplex\\PluginData\\Stars\\BrownDwarfMaps\\" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

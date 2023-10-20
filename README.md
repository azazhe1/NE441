# NE441
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HideMessage {

    public static void main(String[] args) {
        String originalText = "esisar";
        String secretText = "P2025";
        String imagePath = "image.png";

        hideMessage(originalText, secretText, imagePath);
        System.out.println("Message caché avec succès dans l'image.");
    }

    public static void hideMessage(String originalText, String secretText, String imagePath) {
        try {
            // Créer une image en mémoire
            BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

            // Convertir le message original en une séquence de bits
            StringBuilder binaryOriginal = new StringBuilder();
            for (char c : originalText.toCharArray()) {
                binaryOriginal.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
            }

            // Convertir le message secret en une séquence de bits
            StringBuilder binarySecret = new StringBuilder();
            for (char c : secretText.toCharArray()) {
                binarySecret.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
            }

            int binaryIndex = 0;

            // Parcourir les pixels de l'image
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);

                    // Masquer les bits de poids faible avec les bits du message secret
                    int newPixel = (pixel & 0xFFFFFFFE) | Integer.parseInt(String.valueOf(binarySecret.charAt(binaryIndex)), 2);

                    image.setRGB(x, y, newPixel);

                    binaryIndex++;
                    if (binaryIndex == binarySecret.length()) {
                        break; // On a caché tout le message
                    }
                }
                if (binaryIndex == binarySecret.length()) {
                    break; // On a caché tout le message
                }
            }

            // Sauvegarder l'image modifiée
            ImageIO.write(image, "png", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package mail.mensaje.vista;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 * @author Félix Pedrozo.
 */
public class UtilImg {
    //Extensiones de las imagenes.
    protected final static String JPG = ".jpg",
                                  JPEG = ".jpeg",
                                  GIF = ".gif",
                                  PNG = ".png";
    
    protected static ImageIcon createImageIcon (String path) {
        URL imgURL = UtilImg.class.getResource(path);
        if (imgURL != null)
            return new ImageIcon(imgURL);
        else
            return null;
    }
    
}

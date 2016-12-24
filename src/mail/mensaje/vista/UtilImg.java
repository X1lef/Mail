package mail.mensaje.vista;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Clase que sirve para realizar operaciones con las imagenes.
 * 
 * @author Félix Pedrozo.
 */
public class UtilImg {
    //Extensiones de las imagenes.
    final static String JPG = ".jpg",
                        JPEG = ".jpeg",
                        GIF = ".gif",
                        PNG = ".png";
    
    /**
     * @param path Ruta en la que se encuentra la imagen.
     * @return Devuelve un objeto <code>ImageIcon</code>
     */
    static ImageIcon createImageIcon (String path) {
        URL imgURL = UtilImg.class.getResource(path);
        if (imgURL != null) 
            return new ImageIcon(imgURL);
        else 
            throw new NullPointerException();
    }
}

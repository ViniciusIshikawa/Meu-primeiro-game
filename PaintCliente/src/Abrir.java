

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Abrir 
{
    public void paintImage(Graphics g)
    {
    	try 
    	{
    		File img = new File("imagem.jpg");
    		BufferedImage imagem = ImageIO.read(img);
    		g.drawImage(imagem, 50, 50, null);
    	} 
    	catch (Exception e) 
    	{
			
		}
    }
}

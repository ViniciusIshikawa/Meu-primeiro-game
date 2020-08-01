import java.awt.*;
import java.util.StringTokenizer;
public class Circulo extends Figura
{
   protected int x,y;
   
   public Circulo (int x, int y)
   {
       this (x, y, Color.BLACK);
   }
   
   public Circulo (int x , int y , Color cor)
   {
	   super (cor);
	   this.x = x;
	   this.y = y;
   }
   
   public Circulo (String s)
   {
       StringTokenizer quebrador = new StringTokenizer(s,":");

       quebrador.nextToken();

       this.x = Integer.parseInt(quebrador.nextToken());
       this.y = Integer.parseInt(quebrador.nextToken());

       this.cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                             Integer.parseInt(quebrador.nextToken()),  // G
                             Integer.parseInt(quebrador.nextToken())); // B
   }
   
   public void setX(int x)
   {
	   this.x = x;
   }
   
   public void setY(int y)
   {
	   this.y = y;
   }
   
   public int getX()
   {
	   return this.x;
   }

   public int getY()
   {
	   return this.y;
   }

public void torneSeVisivel (Graphics g)
   {
   	g.setColor (this.cor);
   	g.drawOval (this.x,this.y,30,30 ); 
   }

public void CirculoPintado(Graphics g)
{
	g.setColor(this.cor);
	g.fillOval(this.x, this.y, 30, 30);
}


   
   public String toString()
   {
       return "c:" +
              this.x +
              ":" +
              this.y +
              ":" +
              this.getCor().getRed() +
              ":" +
              this.getCor().getGreen() +
              ":" +
              this.getCor().getBlue();
   }
	
    
}

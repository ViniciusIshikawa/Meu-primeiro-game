import java.awt.*;
import java.util.StringTokenizer;
public class Quadrado extends Figura
{
	 protected int x,y;
	   
	   public Quadrado (int x, int y)
	   {
	       this (x, y, Color.BLACK);
	   }
	   
	   public Quadrado (int x , int y , Color cor)
	   {
		   super (cor);
		   this.x = x;
		   this.y = y;
	   }
	   
	   public Quadrado (String s)
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
	   	g.drawRect (this.x,this.y,30,30 ); 
	   }

	public void quadradoPintado(Graphics g)
	{
		g.setColor(this.cor);
		g.fillRect(this.x, this.y, 30, 30);
	}


	   
	   public String toString()
	   {
	       return "q:" +
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

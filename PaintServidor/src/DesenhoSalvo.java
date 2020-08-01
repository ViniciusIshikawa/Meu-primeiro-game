import java.util.Vector;

public class DesenhoSalvo extends Comunicado
{
    private Vector<String> desenhoSalvo;


    public Vector getDesenhoSalvo ()
    {
        return this.desenhoSalvo;
    }
    
    public String toString ()
    {
		return (""+this.desenhoSalvo);
	}
    
    public void setDesenhoSalvo()
    {
    	
    }


}

import java.awt.*;
import java.util.Vector;

public class PedidoDeSalvamento extends Comunicado
{
    private String eMailDono;
    private String nome;
    private Vector<String> stringsDesenho;
    	
    
    public PedidoDeSalvamento (String eMailDono, String nome)
    {
        this.eMailDono = eMailDono;
        this.nome      = nome;
    }
    
    public String geteMailDono ()
    {
        return this.eMailDono;
    }
    
    public String getNome ()
    {
        return this.nome;
    }
    
    public String toString ()
    {
        return (""+this.stringsDesenho);
    }
    
    
}

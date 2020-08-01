package bd.dbos;

public class FiguraDoDesenho implements Cloneable {
	private int    idDoDesenho;
    private int id;
    private String  figura;
 
    public void setId (int id) throws Exception
    {
        if (id <= 0)
            throw new Exception ("Codigo invalido");

        this.id = id;
    }   

    public void setIdDoDesenho (int idDoDesenho) throws Exception
    {
        if (idDoDesenho <= 0)
            throw new Exception ("Id do desenho nao fornecido");

        this.idDoDesenho = idDoDesenho;
    }

    public void setFigura (String figura) throws Exception
    {
        if (figura == null)
            throw new Exception ("Figura invalido");

        this.figura = figura;
    }

    public int getId ()
    {
        return this.id;
    }

    public int getIdDoDesenho ()
    {
        return this.idDoDesenho;
    }

    public String getFigura ()
    {
        return this.figura;
    }

    public FiguraDoDesenho(int idDoDesenho, int id, String figura) throws Exception
    {
        this.setIdDoDesenho(idDoDesenho);
        this.setId   (id);
        this.setFigura  (figura);
    }

    public String toString ()
    {
        String ret="";

        ret+="Id do desenho: "+this.idDoDesenho+"\n";
        ret+="Id..: "+this.id  +"\n";
        ret+="Figura.: "+this.figura;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof FiguraDoDesenho))
            return false;

        FiguraDoDesenho fig = (FiguraDoDesenho)obj;

        if (this.idDoDesenho!=fig.idDoDesenho)
            return false;

        if (this.id != fig.id)
            return false;

        if (this.figura!=fig.figura)
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.idDoDesenho).hashCode();
        ret = 7*ret + new Integer(this.id).hashCode();
        ret = 7*ret + this.figura.hashCode();

        return ret;
    }


    public FiguraDoDesenho (FiguraDoDesenho modelo) throws Exception
    {
        this.idDoDesenho = modelo.idDoDesenho; // nao clono, pq nao eh objeto
        this.id   = modelo.id;   // nao clono, pq nao eh clonavel
        this.figura  = modelo.figura;  // nao clono, pq nao eh objeto
    }

    public Object clone ()
    {
        FiguraDoDesenho ret=null;

        try
        {
            ret = new FiguraDoDesenho (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca é null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}

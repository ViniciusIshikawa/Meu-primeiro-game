package bd.dbos;

public class Desenho implements Cloneable
{
	private int    id;
    private String emailDoDono;
    private String  nome;
 
    public void setId (int id) throws Exception
    {
        if (id <= 0)
            throw new Exception ("Codigo invalido");

        this.id = id;
    }   

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    public void setEmailDoDono (String email) throws Exception
    {
        if (email == null)
            throw new Exception ("Email invalido");

        this.emailDoDono = email;
    }

    public int getId ()
    {
        return this.id;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getemailDoDono ()
    {
        return this.emailDoDono;
    }

    public Desenho (int id, String emailDoDono, String nome) throws Exception
    {
        this.setId (id);
        this.setEmailDoDono   (nome);
        this.setNome  (nome);
    }

    public String toString ()
    {
        String ret="";

        ret+="Id: "+this.id+"\n";
        ret+="Email..: "+this.emailDoDono  +"\n";
        ret+="Nome.: "+this.nome;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Desenho))
            return false;

        Desenho des = (Desenho)obj;

        if (this.id!=des.id)
            return false;

        if (this.nome.equals(des.nome))
            return false;

        if (this.emailDoDono!=des.emailDoDono)
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.id).hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.emailDoDono.hashCode();

        return ret;
    }


    public Desenho (Desenho modelo) throws Exception
    {
        this.id = modelo.id; // nao clono, pq nao eh objeto
        this.emailDoDono   = modelo.emailDoDono;   // nao clono, pq nao eh clonavel
        this.nome  = modelo.nome;  // nao clono, pq nao eh objeto
    }

    public Object clone ()
    {
        Desenho ret=null;

        try
        {
            ret = new Desenho (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca é null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}

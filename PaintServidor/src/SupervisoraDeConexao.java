import java.io.*;
import java.net.*;
import java.util.*;
import bd.daos.Desenhos;
import bd.daos.FigurasDosDesenhos;
import bd.dbos.Desenho;
import bd.dbos.FiguraDoDesenho;

public class SupervisoraDeConexao extends Thread
{
    private double              valor=0;
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {

        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }
        
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }


            for(;;)
            {
                Comunicado comunicado = this.usuario.envie ();

                if (comunicado==null)
                    return;
                else if (comunicado instanceof PedidoDeSalvamento)
                {
                	int qtd = 1;
                	int qtd2 = 1;
                	PedidoDeSalvamento pedidoDeSalvamento = (PedidoDeSalvamento)comunicado;
					Desenho desenho = new Desenho(qtd, pedidoDeSalvamento.geteMailDono(), pedidoDeSalvamento.getNome());
					FiguraDoDesenho figura = new FiguraDoDesenho(qtd, qtd2, pedidoDeSalvamento.toString());
					Desenhos.incluir(desenho);
					FigurasDosDesenhos.incluir(figura);
					
					qtd++;
					qtd2++;
					
					comunicado.adeus();
                }
                else if (comunicado instanceof PedidoDeDesenhoSalvo)
                {
                    this.usuario.receba (new Resultado (this.valor));
                }
                
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor   .close ();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
}

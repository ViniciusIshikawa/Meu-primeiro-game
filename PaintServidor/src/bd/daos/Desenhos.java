package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Desenhos {
	public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Desenhos " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

            /* // ou, se preferirmos,

            String sql;

            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            resultado.first();

            retorno = resultado.getInt("QUANTOS") != 0;

            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar o desenho");
        }

        return retorno;
    }

    public static void incluir (Desenho desenho) throws Exception
    {
        if (desenho==null)
            throw new Exception ("Desenho nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO Desenhos " +
                  "(ID,Email,NOME) " +
                  "VALUES " +
                  "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, desenho.getId ());
            BDSQLServer.COMANDO.setString (2, desenho.getemailDoDono ());
            BDSQLServer.COMANDO.setString  (3, desenho.getNome ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao inserir o desenho");
        }
    }

    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM Desenhos " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao excluir o desenho");
        }
    }

    public static void alterar (Desenho desenho) throws Exception
    {
        if (desenho==null)
            throw new Exception ("Desenho nao fornecido");

        if (!cadastrado (desenho.getId()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE Desenho " +
                  "SET EMAIL=? " +
                  "SET NOME=? " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, desenho.getemailDoDono ());
            BDSQLServer.COMANDO.setString  (2, desenho.getNome ());
            BDSQLServer.COMANDO.setInt    (3, desenho.getId ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados do desenho");
        }
    }

    public static Desenho getDesenho (int id) throws Exception
    {
        Desenho desenho = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Desenho " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

         desenho = new Desenho (resultado.getInt   ("ID"),
                               resultado.getString("Email"),
                               resultado.getString ("Nome"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar o desenho");
        }

        return desenho;
    }

    public static MeuResultSet getDesenhos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Desenhos";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar desenhos");
        }

        return resultado;
    }
}

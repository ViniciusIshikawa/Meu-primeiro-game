package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.dbos.Livro;

public class FigurasDosDesenhos {
	public static boolean cadastrado (int idDoDesenho) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM FigurasDosDesenhos " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idDoDesenho);

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
            throw new Exception ("Erro ao procurar a figura do desenho");
        }

        return retorno;
    }

    public static void incluir (FiguraDoDesenho figura) throws Exception
    {
        if (figura==null)
            throw new Exception ("Figura nao fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO FigurasDosDesenhos " +
                  "(IdDoDesenho,id,figura) " +
                  "VALUES " +
                  "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt     (1, figura.getIdDoDesenho());
            BDSQLServer.COMANDO.setInt     (2, figura.getId ());
            BDSQLServer.COMANDO.setString  (3, figura.getFigura ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao inserir a figura");
        }
    }

    public static void excluir (int idDoDesenho) throws Exception
    {
        if (!cadastrado (idDoDesenho))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM FigurasDosDesenhos " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idDoDesenho);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao excluir a figura");
        }
    }

    public static void alterar (FiguraDoDesenho figura) throws Exception
    {
        if (figura==null)
            throw new Exception ("Figura nao fornecida");

        if (!cadastrado (figura.getIdDoDesenho()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE FigurasDosDesenhos " +
                  "SET id=? " +
                  "SET figura=? " +
                  "WHERE idDoDesenho = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt       (1, figura.getIdDoDesenho());
            BDSQLServer.COMANDO.setInt       (2, figura.getId ());
            BDSQLServer.COMANDO.setString    (3, figura.getFigura());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados da figura");
        }
    }

    public static FiguraDoDesenho getFigura (int idDoDesenho) throws Exception
    {
        FiguraDoDesenho figura = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM FigurasDosDesenhos " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idDoDesenho);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            figura = new FiguraDoDesenho(resultado.getInt   ("idDoDesenho"),
                                         resultado.getInt("id"),
                                         resultado.getString ("figura"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar a figura");
        }

        return figura;
    }

    public static MeuResultSet getLivros () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM FigurasDosDesenhos";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar figuras");
        }

        return resultado;
    }
}

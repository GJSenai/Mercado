package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Venda;

public class VendaDAO {

    public void create(Venda venda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Venda (idCliente, idFuncionario, dataVenda, precoTotal, formaPag, quantTotal) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, venda.getIdCliente());
            stmt.setString(2, venda.getIdFuncionario());
            stmt.setString(3, venda.getDataVenda());
            stmt.setString(4, venda.getPrecoTotal());
            stmt.setString(5, venda.getFormaPag());
            stmt.setString(6, venda.getQuantTotal());
            stmt.execute();
            System.out.println("Venda cadastrada!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar venda", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Venda> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Venda");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rs.getString("idVenda"));
                venda.setIdCliente(rs.getString("idCliente"));
                venda.setIdFuncionario(rs.getString("idFuncionario"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setPrecoTotal(rs.getString("precoTotal"));
                venda.setFormaPag(rs.getString("formaPag"));
                venda.setQuantTotal(rs.getString("quantTotal"));

                vendas.add(venda);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler vendas!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return vendas;
    }

    public void update(Venda venda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Venda SET idCliente = ?, idFuncionario = ?, dataVenda = ?, precoTotal = ?, formaPag = ?, quantTotal = ? WHERE idVenda = ?");
            stmt.setString(1, venda.getIdCliente());
            stmt.setString(2, venda.getIdFuncionario());
            stmt.setString(3, venda.getDataVenda());
            stmt.setString(4, venda.getPrecoTotal());
            stmt.setString(5, venda.getFormaPag());
            stmt.setString(6, venda.getQuantTotal());
            stmt.setString(7, venda.getIdVenda());

            stmt.execute();
            System.out.println("Venda atualizada!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idVenda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Venda WHERE idVenda = ?");
            stmt.setString(1, idVenda);

            stmt.execute();
            System.out.println("Venda apagada!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar venda", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Venda> search(String pesquisar) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        pesquisar = "%" + pesquisar + "%";
        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            // Busca pelo nome do cliente ou forma de pagamento, exemplo
            stmt = con.prepareStatement(
                "SELECT * FROM Venda WHERE formaPag LIKE ?"
            );
            stmt.setString(1, pesquisar);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rs.getString("idVenda"));
                venda.setIdCliente(rs.getString("idCliente"));
                venda.setIdFuncionario(rs.getString("idFuncionario"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setPrecoTotal(rs.getString("precoTotal"));
                venda.setFormaPag(rs.getString("formaPag"));
                venda.setQuantTotal(rs.getString("quantTotal"));

                vendas.add(venda);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar vendas!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return vendas;
    }
}

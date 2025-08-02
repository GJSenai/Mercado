package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.VendaProduto;

public class VendaProdutoDAO {

    public void create(VendaProduto vendaProduto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "INSERT INTO VendaProduto (idVenda, idProduto, quantidade, valorTotal) VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, vendaProduto.getIdVenda());
            stmt.setString(2, vendaProduto.getIdProduto());
            stmt.setString(3, vendaProduto.getQuantidade());
            stmt.setString(4, vendaProduto.getValorTotal());
            stmt.execute();
            System.out.println("VendaProduto cadastrado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar VendaProduto", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<VendaProduto> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<VendaProduto> vendaProdutos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM VendaProduto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setIdVendaProduto(rs.getString("idVendaProduto"));
                vendaProduto.setIdVenda(rs.getString("idVenda"));
                vendaProduto.setIdProduto(rs.getString("idProduto"));
                vendaProduto.setQuantidade(rs.getString("quantidade"));
                vendaProduto.setValorTotal(rs.getString("valorTotal"));

                vendaProdutos.add(vendaProduto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler VendaProduto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return vendaProdutos;
    }

    public void update(VendaProduto vendaProduto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE VendaProduto SET idVenda = ?, idProduto = ?, quantidade = ?, valorTotal = ? WHERE idVendaProduto = ?"
            );
            stmt.setString(1, vendaProduto.getIdVenda());
            stmt.setString(2, vendaProduto.getIdProduto());
            stmt.setString(3, vendaProduto.getQuantidade());
            stmt.setString(4, vendaProduto.getValorTotal());
            stmt.setString(5, vendaProduto.getIdVendaProduto());

            stmt.execute();
            System.out.println("VendaProduto atualizado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar VendaProduto", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idVendaProduto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "DELETE FROM VendaProduto WHERE idVendaProduto = ?"
            );
            stmt.setString(1, idVendaProduto);

            stmt.execute();
            System.out.println("VendaProduto apagado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar VendaProduto", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<VendaProduto> search(String pesquisar) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<VendaProduto> vendaProdutos = new ArrayList<>();
        pesquisar = "%" + pesquisar + "%";

        try {
            // Busca exemplo por idVenda (adaptar conforme sua necessidade)
            stmt = con.prepareStatement(
                "SELECT * FROM VendaProduto WHERE CAST(idVenda AS VARCHAR) LIKE ?"
            );
            stmt.setString(1, pesquisar);

            rs = stmt.executeQuery();
            while (rs.next()) {
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setIdVendaProduto(rs.getString("idVendaProduto"));
                vendaProduto.setIdVenda(rs.getString("idVenda"));
                vendaProduto.setIdProduto(rs.getString("idProduto"));
                vendaProduto.setQuantidade(rs.getString("quantidade"));
                vendaProduto.setValorTotal(rs.getString("valorTotal"));

                vendaProdutos.add(vendaProduto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar VendaProduto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return vendaProdutos;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Produto;

public class ProdutoDAO {

    public void create(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Produto (nomeProd, codBarra, tipoUn, dataFab, dataVal, precoUn, estoque) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, produto.getNomeProd());
            stmt.setString(2, produto.getCodBarra());
            stmt.setString(3, produto.getTipoUn());
            stmt.setString(4, produto.getDataFab());
            stmt.setString(5, produto.getDataVal());
            stmt.setString(6, produto.getPrecoUn());
            stmt.setString(7, produto.getEstoque());
            stmt.execute();
            System.out.println("Produto cadastrado");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Produto> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getString("idProduto"));
                produto.setNomeProd(rs.getString("nomeProd"));
                produto.setCodBarra(rs.getString("codBarra"));
                produto.setTipoUn(rs.getString("tipoUn"));
                produto.setDataFab(rs.getString("dataFab"));
                produto.setDataVal(rs.getString("dataVal"));
                produto.setPrecoUn(rs.getString("precoUn"));
                produto.setEstoque(rs.getString("estoque"));

                produtos.add(produto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return produtos;
    }

    public void update(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE Produto SET nomeProd = ?, codBarra = ?, tipoUn = ?, dataFab = ?, dataVal = ?, precoUn = ?, estoque = ? WHERE idProduto = ?"
            );
            stmt.setString(1, produto.getNomeProd());
            stmt.setString(2, produto.getCodBarra());
            stmt.setString(3, produto.getTipoUn());
            stmt.setString(4, produto.getDataFab());
            stmt.setString(5, produto.getDataVal());
            stmt.setString(6, produto.getPrecoUn());
            stmt.setString(7, produto.getEstoque());
            stmt.setString(8, produto.getIdProduto());

            stmt.execute();
            System.out.println("Produto atualizado");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(String idProduto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Produto WHERE idProduto = ?");
            stmt.setString(1, idProduto);

            stmt.execute();
            System.out.println("Produto apagado!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar produto", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Produto> search(String pesquisar) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        pesquisar = "%" + pesquisar + "%";
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produto WHERE nomeProd LIKE ? OR codBarra LIKE ?");
            stmt.setString(1, pesquisar);
            stmt.setString(2, pesquisar);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getString("idProduto"));
                produto.setNomeProd(rs.getString("nomeProd"));
                produto.setCodBarra(rs.getString("codBarra"));
                produto.setTipoUn(rs.getString("tipoUn"));
                produto.setDataFab(rs.getString("dataFab"));
                produto.setDataVal(rs.getString("dataVal"));
                produto.setPrecoUn(rs.getString("precoUn"));
                produto.setEstoque(rs.getString("estoque"));

                produtos.add(produto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return produtos;
    }
    
    public ArrayList<Produto> readProdutoEB() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM VW_ProdutoEstoqueBaixo ORDER BY estoque");
            rs = stmt.executeQuery();
            int i = 1;
            while (rs.	next()) {
                Produto produto = new Produto();
                
                produto.setIdProduto(""+i++);
                produto.setNomeProd(rs.getString("nomeProd"));
                produto.setCodBarra(rs.getString("codBarra"));
                produto.setTipoUn(rs.getString("tipoUn"));
                produto.setDataFab(rs.getString("dataFab"));
                produto.setDataVal(rs.getString("dataVal"));
                produto.setPrecoUn(rs.getString("precoUn"));
                produto.setEstoque(rs.getString("estoque"));            
                produtos.add(produto);
                
                
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return produtos;
    }
    
    public ArrayList<Produto> readProdutoAV() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM VW_ProdutoProximoAVencer ORDER BY dataVal");
            rs = stmt.executeQuery();
            int i = 1;
            while (rs.	next()) {
                Produto produto = new Produto();
                
                produto.setIdProduto(""+i++);
                produto.setNomeProd(rs.getString("nomeProd"));
                produto.setCodBarra(rs.getString("codBarra"));
                produto.setTipoUn(rs.getString("tipoUn"));
                produto.setDataFab(rs.getString("dataFab"));
                produto.setDataVal(rs.getString("dataVal"));
                produto.setPrecoUn(rs.getString("precoUn"));
                produto.setEstoque(rs.getString("estoque"));            
                produtos.add(produto);
                
                
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return produtos;
    }
    
}

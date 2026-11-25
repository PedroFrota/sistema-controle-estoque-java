package dao;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void cadastrar(Produto produto) {
        String sql = "INSERT INTO produtos (codigo_barras, nome, categoria, preco_unitario, quantidade_estoque, estoque_minimo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getCodigoBarras());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCategoria());
            stmt.setDouble(4, produto.getPrecoUnitario());
            stmt.setInt(5, produto.getQuantidadeEstoque());
            stmt.setInt(6, produto.getEstoqueMinimo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto: " + e.getMessage(), e);
        }
    }

    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produtos ORDER BY nome";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(mapearProduto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }
        return produtos;
    }

    public List<Produto> listarEstoqueCritico() {
        String sql = "SELECT * FROM produtos WHERE quantidade_estoque <= estoque_minimo ORDER BY quantidade_estoque ASC";
        List<Produto> criticos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                criticos.add(mapearProduto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar estoque crítico: " + e.getMessage(), e);
        }
        return criticos;
    }

    public void atualizarEstoque(String codigoBarras, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade_estoque = ? WHERE codigo_barras = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setString(2, codigoBarras);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar estoque: " + e.getMessage(), e);
        }
    }

    private Produto mapearProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setCodigoBarras(rs.getString("codigo_barras"));
        p.setNome(rs.getString("nome"));
        p.setCategoria(rs.getString("categoria"));
        p.setPrecoUnitario(rs.getDouble("preco_unitario"));
        p.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
        p.setEstoqueMinimo(rs.getInt("estoque_minimo"));
        return p;
    }
}

package repository;

import database.DatabaseConnection;
import model.ItemVenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaRepository {

    public void salvarItem(ItemVenda item) {

        String sql = "INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco ) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getVendaId());
            stmt.setInt(2, item.getProdutoId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPrecoUnitario());


            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar item da venda: " + e.getMessage());
        }
    }

    public List<ItemVenda> listarItensPorVenda(int vendaId) {

        List<ItemVenda> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_venda WHERE venda_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vendaId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ItemVenda item = new ItemVenda();

                item.setId(rs.getInt("id"));
                item.setVendaId(rs.getInt("venda_id"));
                item.setProdutoId(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco"));


                itens.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens da venda: " + e.getMessage());
        }

        return itens;
    }
}
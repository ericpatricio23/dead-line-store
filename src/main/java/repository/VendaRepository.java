package repository;

import database.DatabaseConnection;
import model.Venda;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class VendaRepository {

    public int criarVenda(Venda venda) {

        String sql = "INSERT INTO vendas (data, total) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, venda.getData());
            stmt.setDouble(2, venda.getValorTotal());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar venda: " + e.getMessage());
        }

        return 0;
    }

    public Venda buscarVendaPorId(int id) {

        String sql = "SELECT * FROM vendas WHERE id = ?";
        Venda venda = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                venda = new Venda();

                venda.setId(rs.getInt("id"));
                venda.setData(rs.getString("data"));
                venda.setValorTotal(rs.getDouble("total"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar venda: " + e.getMessage());
        }

        return venda;
    }

    public List<Venda> listarVendas() {

        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setId(rs.getInt("id"));
                venda.setData(rs.getString("data"));
                venda.setValorTotal(rs.getDouble("total"));

                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }

        return vendas;
    }
    public void atualizarTotal(int vendaId, double total) {

        String sql = "UPDATE vendas SET total = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, total);
            stmt.setInt(2, vendaId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar total da venda: " + e.getMessage());
        }
    }


}
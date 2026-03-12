package database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {

        String produtosTable = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                preco REAL,
                estoque INTEGER
            );
        """;

        String vendasTable = """
            CREATE TABLE IF NOT EXISTS vendas (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            total REAL,
            data TEXT
          );
          """;

        String itensVendaTable = """
            CREATE TABLE IF NOT EXISTS itens_venda (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                venda_id INTEGER,
                produto_id INTEGER,
                quantidade INTEGER,
                preco REAL
            );
        """;



        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(produtosTable);
            stmt.execute(vendasTable);
            stmt.execute(itensVendaTable);

            System.out.println("Banco inicializado.");

        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
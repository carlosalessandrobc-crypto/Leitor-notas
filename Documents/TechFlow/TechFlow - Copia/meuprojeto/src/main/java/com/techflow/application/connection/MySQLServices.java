package com.techflow.application.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techflow.application.model.Parceiro;

public class MySQLServices {

	public static int insertParceiro(String nomeCliente, String endereco, String email, String numeroTelefone) {
		String sql = "INSERT INTO Parceiros (Nome_Cliente, Endereco, Email, Numero_Telefone) VALUES (?, ?, ?, ?)";

		try (Connection conn = MySQLConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, nomeCliente);
			pstmt.setString(2, endereco);
			pstmt.setString(3, email);
			pstmt.setString(4, numeroTelefone);

			int rowsInserted = pstmt.executeUpdate();
			if (rowsInserted > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int idParceiro = rs.getInt(1);
					return idParceiro;
				}
			}
			return -1; // Retorna -1 em caso de falha na inserção
		} catch (SQLException e) {
			System.err.println("Erro ao inserir parceiro: " + e.getMessage());
			return -1; // Retorna -1 em caso de falha na inserção
		}
	}

	public static Parceiro selectParceiro(int idParceiro) {
		String sql = "SELECT * FROM Parceiros WHERE ID_Parceiro = ?";
		Parceiro parceiro = null;

		try (Connection conn = MySQLConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idParceiro);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				parceiro = new Parceiro();
				parceiro.setId(rs.getLong("ID_Parceiro"));
				parceiro.setNome(rs.getString("Nome_Cliente"));
				parceiro.setEndereco(rs.getString("Endereco"));
				parceiro.setEmail(rs.getString("Email"));
				parceiro.setTelefone(rs.getString("Numero_Telefone"));
				// Adicione mais campos conforme necessário
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar parceiro: " + e.getMessage());
		}

		return parceiro;
	}

	public static boolean deleteParceiro(int idParceiro) {
		String sql = "DELETE FROM Parceiros WHERE ID_Parceiro = ?";

		try (Connection conn = MySQLConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idParceiro);
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Parceiro excluído com sucesso.");
				return true;
			} else {
				System.out.println("Nenhum parceiro encontrado com o ID fornecido.");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Erro ao excluir parceiro: " + e.getMessage());
			return false;
		}
	}

	public static List<Parceiro> getAllParceiros() {
		List<Parceiro> parceiros = new ArrayList<>();
		String sql = "SELECT * FROM Parceiros";

		try (Connection conn = MySQLConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Parceiro parceiro = new Parceiro();
				parceiro.setId(rs.getLong("ID_Parceiro"));
				parceiro.setNome(rs.getString("Nome_Cliente"));
				parceiro.setEndereco(rs.getString("Endereco"));
				parceiro.setEmail(rs.getString("Email"));
				parceiro.setTelefone(rs.getString("Numero_Telefone"));
				// Adicione mais atributos conforme necessário

				parceiros.add(parceiro);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar todos os parceiros: " + e.getMessage());
		}

		return parceiros;
	}

}

package com.techflow.application;

import com.techflow.application.connection.MySQLConnection;
import com.techflow.application.connection.MySQLServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Application3 {
    public static void main(String[] args) {
        try {
            // Obtendo uma conexão com o banco de dados
            Connection connection = MySQLConnection.getConnection();
            System.out.println("Conexão bem-sucedida!");

            // Solicitando os dados do parceiro ao usuário
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do parceiro:");
            String nome = scanner.nextLine();
            System.out.println("Digite o endereço do parceiro:");
            String endereco = scanner.nextLine();
            System.out.println("Digite o email do parceiro:");
            String email = scanner.nextLine();
            System.out.println("Digite o número de telefone do parceiro:");
            String telefone = scanner.nextLine();

            // Inserindo o parceiro com os dados fornecidos pelo usuário
            int idParceiro = MySQLServices.insertParceiro(nome, endereco, email, telefone);

            // Verificando se a inserção foi bem-sucedida
            if (idParceiro > 0) {
                System.out.println("Parceiro inserido com sucesso! ID: " + idParceiro);
            } else {
                System.out.println("Erro ao inserir o parceiro.");
            }

            // Solicitando ao usuário que digite o ID do parceiro para exibir suas informações
            System.out.println("Digite o ID do parceiro para exibir suas informações:");
            int idParceiroBusca = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Exibindo informações do parceiro inserido
            MySQLServices.selectParceiro(idParceiroBusca);

            // Fechando a conexão com o banco de dados
            connection.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
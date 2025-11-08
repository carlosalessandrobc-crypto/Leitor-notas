package com.techflow.application.views;

// ARQUIVO DESABILITADO - MIGRAÇÃO PARA ANGULAR
// Este arquivo foi desabilitado durante a migração para Angular
// As funcionalidades de parceiros agora estão no ParceiroController REST API

/*
import com.techflow.application.connection.MySQLServices;
import com.techflow.application.model.Parceiro;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Key;

@Route("parceiro")
public class ParceiroView extends VerticalLayout {

    private TextField idField;
    private TextField nomeField;
    private TextField enderecoField;
    private TextField emailField;
    private TextField telefoneField;
    private Button salvarButton;

    public ParceiroView() {
        idField = new TextField("Id:");
        idField.setSuffixComponent(createLupaIcon());
        idField.addKeyPressListener(Key.ENTER, event -> selectParceiro());
        nomeField = new TextField("Nome:");
        enderecoField = new TextField("Endereço:");
        emailField = new TextField("Email:");
        telefoneField = new TextField("Telefone:");
        salvarButton = new Button("Salvar");
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickListener(event -> salvarParceiro());
       
        add(idField, nomeField, enderecoField, emailField, telefoneField, salvarButton);
    }

    private Icon createLupaIcon() {
        Icon lupaIcon = new Icon(VaadinIcon.SEARCH);
        lupaIcon.addClickListener(event -> selectParceiro());
        return lupaIcon;
    }

    private void selectParceiro() {
        String id = idField.getValue();
        if (id.isEmpty()) {
            Notification.show("Campo ID está vazio", 3000, Notification.Position.MIDDLE);
        } else {
            int idParceiro = Integer.parseInt(id);
            Parceiro parceiro = MySQLServices.selectParceiro(idParceiro);
            if (parceiro != null) {
                // Preencher os campos com as informações do parceiro
                nomeField.setValue(parceiro.getNome());
                enderecoField.setValue(parceiro.getEndereco());
                emailField.setValue(parceiro.getEmail());
                telefoneField.setValue(parceiro.getTelefone());
            } else {
                Notification.show("Parceiro não encontrado", 3000, Notification.Position.MIDDLE);
            }
        }
    }

    private void salvarParceiro() {
        String nome = nomeField.getValue();
        String endereco = enderecoField.getValue();
        String email = emailField.getValue();
        String telefone = telefoneField.getValue();

        if (nome.isEmpty() || endereco.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            Notification.show("Por favor, preencha todos os campos", 3000, Notification.Position.TOP_CENTER);
            return;
        }

        int idParceiro = MySQLServices.insertParceiro(nome, endereco, email, telefone);

        if (idParceiro > 0) {
            Notification.show("Parceiro inserido com sucesso! ID: " + idParceiro, 3000, Notification.Position.TOP_CENTER);
            idField.setValue(String.valueOf(idParceiro));
            //nomeField.setEnabled(false);
            //enderecoField.setEnabled(false);
            //emailField.setEnabled(false);
            //telefoneField.setEnabled(false);
        } else {
            Notification.show("Erro ao inserir o parceiro", 3000, Notification.Position.TOP_CENTER);
        }
    }
}
*/

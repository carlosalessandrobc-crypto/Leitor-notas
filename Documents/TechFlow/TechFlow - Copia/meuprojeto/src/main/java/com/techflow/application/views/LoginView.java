package com.techflow.application.views;

// ARQUIVO DESABILITADO - MIGRAÇÃO PARA ANGULAR
// Este arquivo foi desabilitado durante a migração para Angular
// As funcionalidades de login agora estão no AuthController REST API

/*
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        // Configuração do layout
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setWidthFull();
        setHeightFull();

        // Campos de entrada
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");

        // Botão de login
        Button loginButton = new Button("Login", event -> {
            // Lógica de autenticação (não implementada neste exemplo)
            String username = usernameField.getValue();
            String password = passwordField.getValue();

            // Verifica se o username e a senha estão corretos (fictício neste exemplo)
            if ("admin".equals(username) && "admin".equals(password)) {
                Notification.show("Login successful", 3000, Notification.Position.TOP_CENTER);
                // Redireciona para a página principal (fictício neste exemplo)
                getUI().ifPresent(ui -> ui.navigate("main"));
            } else {
                Notification.show("Invalid username or password", 3000, Notification.Position.TOP_CENTER);
            }
        });

        // Adiciona os componentes ao layout
        add(usernameField, passwordField, loginButton);
    }
}
*/

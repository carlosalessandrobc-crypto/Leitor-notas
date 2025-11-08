package com.techflow.application.views;

// ARQUIVO DESABILITADO - MIGRAÇÃO PARA ANGULAR
/*

import com.techflow.application.security.TokenManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("token-manager")
public class TokenManagerView extends VerticalLayout {

	public TokenManagerView() {
		TokenManager tokenManager = new TokenManager();

		Div respostaDiv = new Div();
		Button obterTokenButton = new Button("Obter Token");
		obterTokenButton.addClickListener(event -> {
			String clientId = "ejPHzQhAm69JmitR1okL"; // Substitua pelo seu ID de cliente
			String clientSecret = "G9bL0vdOHVeqoNfEA1xXTctYOk7KR7wPuiS6JRW1"; // Substitua pelo seu
																				// segredo de
																				// cliente

			String token = tokenManager.obterToken(clientId, clientSecret);
			System.out.println(token);
			respostaDiv.setText("Token de acesso: " + token);
		});

		add(obterTokenButton, respostaDiv);
	}
}
*/

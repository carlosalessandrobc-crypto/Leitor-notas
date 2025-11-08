package com.techflow.application.views;

// ARQUIVO DESABILITADO - MIGRAÇÃO PARA ANGULAR
/*

import java.util.List;

import com.techflow.application.connection.MySQLServices;
import com.techflow.application.model.Parceiro;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("listar-parceiros")
public class ListaParceirosView extends VerticalLayout {

	private final Grid<Parceiro> grid;

	public ListaParceirosView() {
		// Define a altura total do layout como 100%
		setHeight("100%");

		grid = new Grid<>(Parceiro.class);
		grid.setColumns("id", "nome", "endereco", "email", "telefone");

		// Define a altura do Grid como 100% do layout pai (VerticalLayout)
		grid.setHeight("100%");

		// Adiciona a coluna com o botão de exclusão
		grid.addComponentColumn(parceiro -> criarBotaoExclusao(parceiro.getId())).setHeader("Ações");

		atualizarListaParceiros();
		add(grid);
	}

	private Button criarBotaoExclusao(int idParceiro) {
		Icon icon = new Icon(VaadinIcon.TRASH);
		Button button = new Button(icon);
		button.addClickListener(event -> {
			MySQLServices.deleteParceiro(idParceiro);
			atualizarListaParceiros();
		});
		return button;
	}

	private void atualizarListaParceiros() {
		List<Parceiro> parceiros = MySQLServices.getAllParceiros();
		grid.setItems(parceiros);
	}
}
*/

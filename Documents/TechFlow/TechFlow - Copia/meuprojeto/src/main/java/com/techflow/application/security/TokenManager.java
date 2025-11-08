package com.techflow.application.security;

import okhttp3.FormBody;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TokenManager {
	private static final String TOKEN_URL = "https://auth.nuvemfiscal.com.br/oauth/token";
	private final OkHttpClient client;

	public TokenManager() {
		this.client = new OkHttpClient();
	}

	public String obterToken(String clientId, String clientSecret) {
		// Construa o corpo da solicitação com clientId, clientSecret, grant_type e
		// scope
		RequestBody body = new FormBody.Builder().add("grant_type", "client_credentials").add("client_id", clientId)
				.add("client_secret", clientSecret).add("scope", "cnpj").build();

		// Construa a solicitação POST para a URL do token
		Request request = new Request.Builder().url(TOKEN_URL).post(body).build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new RuntimeException("Falha ao obter token: " + response);
			}
			// Extrai e retorna o token de acesso do corpo da resposta
			String responseBody = response.body().string();
			// Aqui você precisa analisar o JSON da resposta para obter o token de acesso
			return extractAccessTokenFromJson(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String extractAccessTokenFromJson(String responseBody) {
		// Analisar o JSON da resposta para obter o token de acesso
		JSONObject jsonResponse = new JSONObject(responseBody);
		String accessToken = jsonResponse.getString("access_token");
		return accessToken;
	}
}

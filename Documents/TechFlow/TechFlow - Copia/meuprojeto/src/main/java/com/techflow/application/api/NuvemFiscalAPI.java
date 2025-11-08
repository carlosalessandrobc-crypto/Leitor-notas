package com.techflow.application.api;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NuvemFiscalAPI {
	private static final String API_URL = "https://api.nuvemfiscal.com.br";

	private final OkHttpClient client;

	public NuvemFiscalAPI() {
		this.client = new OkHttpClient();
	}

	public String emitirCTe(String payload, String token) {
		RequestBody body = RequestBody.create(payload, MediaType.get("application/json"));
		Request request = new Request.Builder().url(API_URL + "/emitir-cte")
				.addHeader("Authorization", "Bearer " + token).post(body).build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful())
				throw new RuntimeException("Failed to emit CT-e: " + response);
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String consultarCNPJ(String cnpj, String token) {
		String url = API_URL + "/cnpj/" + cnpj;
		Request request = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + token).build();

		System.out.println("requisição URL: " + request); // Imprimir a URL do request no console

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful())
				throw new RuntimeException("Failed to consult CNPJ: " + response);
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public String consultarCNPJ(String cnpj, String token) { Request request =
	 * new Request.Builder().url(API_URL + "/cnpj/" + cnpj)
	 * .addHeader("Authorization", "Bearer " + token).build();
	 * System.out.println("request:" + request); try (Response response =
	 * client.newCall(request).execute()) { if (!response.isSuccessful()) throw new
	 * RuntimeException("Failed to consult CNPJ: " + response); return
	 * response.body().string(); } catch (Exception e) { e.printStackTrace(); return
	 * null; } }
	 */
}

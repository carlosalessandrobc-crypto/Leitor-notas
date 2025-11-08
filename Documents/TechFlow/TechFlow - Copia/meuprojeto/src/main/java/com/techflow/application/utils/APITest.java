package com.techflow.application.utils;

import com.techflow.application.api.NuvemFiscalAPI;

public class APITest {

	public static String consumirAPICTE() {
		NuvemFiscalAPI nuvemFiscalAPI = new NuvemFiscalAPI();
		// Substitua o payload e o token pelos valores corretos
		String payload = "{ \"chave\": \"valor\" }"; // Exemplo de payload
		String token = "f50HDGuNQDD2GTwqcTpvO9Ks3jpcZVYrsqtDxxn3"; // Substitua pelo seu token real
		return nuvemFiscalAPI.emitirCTe(payload, token);
	}

	public static String consumirAPICNPJ(String cnpj) {
		NuvemFiscalAPI nuvemFiscalAPI = new NuvemFiscalAPI();
		// Substitua o token pelo valor correto
		String token = "f50HDGuNQDD2GTwqcTpvO9Ks3jpcZVYrsqtDxxn3"; // Substitua pelo seu token real
		return nuvemFiscalAPI.consultarCNPJ(cnpj, token);
	}
}

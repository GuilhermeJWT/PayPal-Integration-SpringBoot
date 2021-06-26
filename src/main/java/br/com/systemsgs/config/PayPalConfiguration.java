package br.com.systemsgs.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PayPalConfiguration {
	
	@Value("${paypal.mode}")
	private String mode;
	
	@Value("${paypal.client.id}")
	private String clienteId;
	
	@Value("${paypal.client.secret}")
	private String clientSecret;
	
	@Bean
	public Map<String, String> configureSdkPayPal(){
		Map<String, String> map = new HashMap<>();
		map.put("mode", mode);
		
		return map;
	}
	
	@Bean
	public APIContext context() throws PayPalRESTException {
		APIContext api = new APIContext(oAuthTokenCredentials().getAccessToken());
		api.setConfigurationMap(configureSdkPayPal());
		
		return context();
	}
	
	@Bean
	public OAuthTokenCredential oAuthTokenCredentials() {
		return new OAuthTokenCredential(clienteId, clientSecret, configureSdkPayPal());
	}
	
}

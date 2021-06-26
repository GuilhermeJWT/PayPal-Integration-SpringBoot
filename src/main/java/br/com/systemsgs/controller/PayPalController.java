package br.com.systemsgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import br.com.systemsgs.Model.ModelDescription;
import br.com.systemsgs.service.PayPalService;

@RestController
public class PayPalController {

	@Autowired
	private PayPalService payPalService;

	/* Caso der Sucesso na Transação vai Retornar para a Página de Sucesso! */
	public static final String SUCESSO_URL = "pay/sucess";

	/* Caso der Errado na Transação vai Retornar para a Página de Erro! */
	public static final String CANCEL_URL = "pay/sucess";

	@GetMapping(value = "/request")
	public String init() {
		return "paypal";
	}

	@GetMapping(value = "/pay")
	public String payment(@ModelAttribute("modelDescription") ModelDescription modelDescription) {
		try {
			Payment payment = payPalService.createPayment(modelDescription.getPrice(), modelDescription.getCurrency(),
					modelDescription.getIntent(), modelDescription.getDescription(), modelDescription.getMethod(),
					"http://localhost:8080/" + CANCEL_URL, "http://localhost:8080/" + SUCESSO_URL);

			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					return "redirect:" + link.getHref();
				}
			}

		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(value = SUCESSO_URL)
	public String paymentSucess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerID) {
		try {

			Payment payment = payPalService.executePayment(paymentId, payerID);

			if (payment.getState().equals("approved")) {
				return "sucess";
			}

		} catch (PayPalRESTException exception) {
			exception.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(value = CANCEL_URL)
	public String paymentCancel() {
		return "cancel";
	}

}

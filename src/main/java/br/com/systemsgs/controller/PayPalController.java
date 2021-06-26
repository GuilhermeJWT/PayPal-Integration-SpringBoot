package br.com.systemsgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.service.PayPalService;

@RestController
public class PayPalController {
	
	@Autowired
	private PayPalService payPalService;

}

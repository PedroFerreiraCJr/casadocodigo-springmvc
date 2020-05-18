package br.com.dotofcodex.casadocodigo.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import br.com.dotofcodex.casadocodigo.model.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart cart;

	@Autowired
	private RestTemplate template;

	public PaymentController() {
		super();
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			String url = "http://book-payment.herokuapp.com/payment";

			try {
				String response = template.postForObject(url, new Object() {
					BigDecimal value = new BigDecimal(100);

					public BigDecimal getValue() {
						return this.value;
					}
				}, String.class);

				return "redirect:/payment/success";
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				return "redirect:/payment/error";
			}

		};
	}

	@RequestMapping(value = "/checkout2", method = RequestMethod.POST)
	public DeferredResult<String> checkout2() {
		DeferredResult<String> result = new DeferredResult<String>();

		IntegrandoPagamento integrandoComPagamento = new IntegrandoPagamento(result, new Object() {
			BigDecimal value = new BigDecimal(100);

			public BigDecimal getValue() {
				return this.value;
			}
		}, template);

		Thread thread = new Thread(integrandoComPagamento);
		thread.start();

		return result;
	}

	private static class IntegrandoPagamento implements Runnable {

		private DeferredResult<String> result;
		private Object total;
		private RestTemplate template;

		public IntegrandoPagamento(DeferredResult<String> result, Object total, RestTemplate template) {
			super();
			this.result = result;
			this.total = total;
			this.template = template;
		}

		@Override
		public void run() {
			String url = "http://book-payment.herokuapp.com/payment";

			try {
				String response = template.postForObject(url, new Object() {
					BigDecimal value = new BigDecimal(100);

					public BigDecimal getValue() {
						return this.value;
					}
				}, String.class);

				result.setResult("redirect:/payment/success");
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				result.setResult("redirect:/payment/error");
			}
		}

	}
}
package br.com.dotofcodex.casadocodigo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dotofcodex.casadocodigo.dao.ProductDAO;
import br.com.dotofcodex.casadocodigo.model.Product;
import br.com.dotofcodex.casadocodigo.type.BookType;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private ProductDAO productDAO;

	public ProductsController() {
		super();
	}

	// first method
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView model = new ModelAndView("products/form");
		model.addObject("types", BookType.values());
		return model;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", productDAO.list());
		return "products/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Product produto, RedirectAttributes attributes) {
		logger.info("saving information...");
		ModelAndView model = new ModelAndView("products/form");
		boolean hasErrors = false;
		List<String> errors = new ArrayList<>(5);

		if (StringUtils.isEmpty(produto.getTitle())) {
			errors.add("O campo título não pode ser vazio");
			hasErrors = true;
		}

		if (StringUtils.isEmpty(produto.getDescription())) {
			errors.add("O campo descrição não pode ser vazio");
			hasErrors = true;
		}

		if (produto.getPages() == null || produto.getPages() == 0) {
			errors.add("O campo pages não pode ser igual a zero");
			hasErrors = true;
		}

		if (hasErrors) {
			logger.info("Há erros na requisição, retornando para a página de cadastro...");
			model.addObject("errors", errors);
			model.addObject("types", BookType.values());
			model.addObject("product", produto);
			return model;
		}

		productDAO.save(produto);
		model = new ModelAndView("redirect:produtos");
		logger.info(produto.toString());
		attributes.addFlashAttribute("message", "Produto cadastrado com sucesso");
		return model;
	}

}

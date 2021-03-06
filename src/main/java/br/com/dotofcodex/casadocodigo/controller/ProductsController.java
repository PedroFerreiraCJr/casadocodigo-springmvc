package br.com.dotofcodex.casadocodigo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dotofcodex.casadocodigo.dao.ProductDAO;
import br.com.dotofcodex.casadocodigo.model.Product;
import br.com.dotofcodex.casadocodigo.type.BookType;
import br.com.dotofcodex.casadocodigo.util.FileSaver;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private FileSaver fileSaver;

	public ProductsController() {
		super();
	}

	/*
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * binder.setValidator(new ProductValidator()); }
	 */

	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("product") Product produto) {
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
	public ModelAndView save(MultipartFile summary, @Valid @ModelAttribute("product") Product produto,
			BindingResult result, RedirectAttributes attributes) {
		logger.info("saving information...");

		ModelAndView model = new ModelAndView("products/form");

		if (result.hasErrors()) {
			logger.info("errors were found...");
			return form(produto);
		}

		logger.info(summary.getName());
		logger.info(summary.getOriginalFilename());

		try {
			produto.setSummaryPath(fileSaver.save("", summary));
		} catch (Exception e) {
			ModelAndView error = new ModelAndView("products/form");
			error.addObject("message_error", "Falha no salvamento do arquivo");
			return error;
		}

		productDAO.save(produto);
		model = new ModelAndView("redirect:produtos");
		logger.info(produto.toString());
		attributes.addFlashAttribute("message", "Produto cadastrado com sucesso");
		return model;
	}

}

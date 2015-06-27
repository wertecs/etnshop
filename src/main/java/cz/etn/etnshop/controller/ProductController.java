package cz.etn.etnshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	private static Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("product/list");
		System.out.println("Count:" + productService.getProducts().size());
		modelAndView.addObject("test", "mytest");
		modelAndView.addObject("count", productService.getProducts().size());
		modelAndView.addObject("products", productService.getProducts());
		return modelAndView;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("product/add");
		return modelAndView;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable String id) {

		int productId = Integer.parseInt(id);
		Product existing = productService.getProductById(productId);

		ModelAndView modelAndView = new ModelAndView("product/edit");
		modelAndView.addObject("product", existing);
		return modelAndView;

	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam String productName,
			@RequestParam String serialNo) {
		logger.debug("Received a product to save. Name:{}, SerialNo:{}",
				productName, serialNo);
		Product p = new Product();
		p.setName(productName);
		p.setSerialNumber(serialNo);
		
		productService.saveProduct(p);
		String message = "Product was saved successfuly";

		ModelAndView modelAndView = new ModelAndView("redirect:list");
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	public ModelAndView post(@PathVariable String id,
			@RequestParam String productName, @RequestParam String serialNo) {
		logger.debug("Received a product to save. Name:{}, SerialNo:{}",
				productName, serialNo);
		Product p = new Product();
		p.setName(productName);
		p.setSerialNumber(serialNo);
		String message = "";
		p.setId(Integer.parseInt(id));
		productService.updateProduct(p);
		message = "Product was updated successfuly";

		ModelAndView modelAndView = new ModelAndView("redirect:../list");
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(required = false) String query) {
		ModelAndView modelAndView = new ModelAndView("product/search");
		if (query != null)
			modelAndView.addObject("products", productService.search(query));
		return modelAndView;
	}

	@RequestMapping(value = "/searchajax/{query}", method = RequestMethod.GET)
	public @ResponseBody String searchAjax(@PathVariable String query) {
		String result = "";
		if (query != null) {
			List<Product> search = productService.search(query);
			if (search.isEmpty())
				result = "No item matches the give query";
			result = createProductsHtml(productService.search(query));
		}

		return result;
	}

	private String createProductsHtml(List<Product> search) {
		String res = "";
		for (Product p : search) {
			res += "<tr><td>" + p.getId() + "</td><td>" + p.getName()
					+ "</td><td>" + p.getSerialNumber() + "</td></tr>";
		}
		logger.debug(res);
		return res;
	}
}

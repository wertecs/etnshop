package cz.etn.etnshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
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
	public ModelAndView add()
	{
		ModelAndView modelAndView = new ModelAndView("product/add");
		return modelAndView;
	}
	
	@ResponseBody
    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public String post(@RequestParam String productName)  {
		logger.debug("Received a product to save. Name:{}",productName);
		Product p = new Product();
		p.setName(productName);
		
		productService.saveProduct(p);
		
		
        return "OK"; 
    }
}

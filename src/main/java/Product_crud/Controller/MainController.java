package Product_crud.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import Product_crud.Dao.ProductDao;
import Product_crud.Model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao productdao;

	@RequestMapping("/")
	public String home(Model model) {

		List<Product> products = productdao.getProducts();
		model.addAttribute("products", products);

		return "index";

	}

	@RequestMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("title", "ADD Product");
		return "add_product_form";
	}

//	handle addproduct form
	@RequestMapping(path = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request) {

		RedirectView redirectview = new RedirectView();
		redirectview.setUrl(request.getContextPath() + "/");

		this.productdao.createProduct(product);
		return redirectview;
	}

//	delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		this.productdao.deleteproduct(productId);
		RedirectView redirectview = new RedirectView();
		redirectview.setUrl(request.getContextPath() + "/");
		return redirectview;
	}

//	modify handler
	@RequestMapping("/update/{ProductId}")
	public String Modify(@PathVariable("ProductId") int productId,Model model)
	{
		Product product = this.productdao.getproduct(productId); 
		model.addAttribute("product", product);
		return "Update_form";
	}

}

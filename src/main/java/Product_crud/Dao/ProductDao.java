package Product_crud.Dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import Product_crud.Model.Product;

@Component
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernatetemplate;

//create	
	@Transactional
	public void createProduct(Product product) {
		this.hibernatetemplate.saveOrUpdate(product);
	}

//	get all the products
	public List<Product> getProducts() {
		List<Product> products = this.hibernatetemplate.loadAll(Product.class);
		return products;
	}

//  delete the single products
	@Transactional
	public void deleteproduct(int pid) {
		Product p = this.hibernatetemplate.load(Product.class, pid);
		this.hibernatetemplate.delete(p);
	}

//	get the single products
	public Product getproduct(int pid) {
		return this.hibernatetemplate.get(Product.class, pid);
	}

}

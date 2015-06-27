package cz.etn.etnshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		persist(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

	@Override
	public void deleteProduct(int productId) {
		Query query = getSession().createSQLQuery(
				"delete from Product where id = :id");
		query.setInteger("id", productId);
		query.executeUpdate();
	}

	@Override
	public void updateProduct(Product product) {
		getSession().update(product);

	}

	@Override
	public Product getProductById(Integer id) {

		Criteria criteria = getSession().createCriteria(Product.class).add(
				Restrictions.eq("id", id));

		return (Product) criteria.uniqueResult();
	}

	@Override
	public List<Product> search(String query) {

		
		Query queryObject = getSession()
				.createQuery(
						"from Product where name like :query OR serialNumber like :query order by id");
		queryObject.setParameter("query","%"+query+"%");
		List<Product> resultList = queryObject.list();
		

		return resultList;
	}

}

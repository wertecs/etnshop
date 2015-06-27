package cz.etn.etnshop.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
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

		// FullTextSession fullTextSession = Search
		// .getFullTextSession(getSession());
		// //Transaction tx = fullTextSession.beginTransaction();
		//
		// QueryBuilder qb = fullTextSession.getSearchFactory()
		// .buildQueryBuilder().forEntity(Product.class).get();
		// org.apache.lucene.search.Query q = qb.keyword()
		// .onFields("name", "serial_no").matching(query).createQuery();
		//
		// // wrap Lucene query in a org.hibernate.Query
		// org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(q,
		// Product.class);
		//
		// // execute search
		//
		// List result = hibQuery.list();
		// Iterator iterator = result.iterator();
		// while (iterator.hasNext()) {
		// System.out.print(iterator.next() + " ");
		// }
		// System.out.println();
		// // Check list empty or not
		// if (result.isEmpty()) {
		// System.out.println("Linked list is empty");
		// }

		Criteria result = getSession().createCriteria(Product.class).add(Restrictions.or(Restrictions.like("name", query),Restrictions.like("serialNumber", query))
				);

		List<Product> res = (List<Product>) result.list();
		for (Product p : res) {
			System.out.println(p.getName());
		}

		return res;
	}

}

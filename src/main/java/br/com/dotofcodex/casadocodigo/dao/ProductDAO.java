package br.com.dotofcodex.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dotofcodex.casadocodigo.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager em;
	
	public ProductDAO() {
		super();
	}

	public void save(Product produto) {
		em.persist(produto);
	}
	
	public List<Product> list() {
		return em.createQuery("from Product", Product.class).getResultList();
	}
	
}

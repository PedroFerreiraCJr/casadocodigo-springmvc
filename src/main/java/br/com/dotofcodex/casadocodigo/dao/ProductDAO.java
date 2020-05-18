package br.com.dotofcodex.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.dotofcodex.casadocodigo.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager em;

	public ProductDAO() {
		super();
	}

	@CacheEvict(value = "books", allEntries = true)
	public void save(Product produto) {
		em.persist(produto);
	}

	@Cacheable("books")
	public List<Product> list() {
		return em.createQuery("from Product", Product.class).getResultList();
	}

}

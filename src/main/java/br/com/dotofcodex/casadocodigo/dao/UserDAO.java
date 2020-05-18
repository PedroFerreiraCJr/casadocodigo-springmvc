package br.com.dotofcodex.casadocodigo.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.dotofcodex.casadocodigo.model.User;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String jpql = "from User u where u.login = :login";

		User user = null;
		try {
			user = em.createQuery(jpql, User.class).setParameter("login", username).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			e.printStackTrace();

			throw new UsernameNotFoundException("Usuário não existe");
		}

		return user;
	}

}

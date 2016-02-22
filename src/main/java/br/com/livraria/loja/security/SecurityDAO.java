package br.com.livraria.loja.security;

import br.com.livraria.loja.models.SystemUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SecurityDAO {
    @PersistenceContext
    private EntityManager em;

    public SystemUser loadUserByUsername(String username) {
        String jpql = "select u from SystemUser u where u.email = :login";
        SystemUser user = em.createQuery(jpql, SystemUser.class)
                .setParameter("login", username)
                .getSingleResult();
        return user;
    }
}

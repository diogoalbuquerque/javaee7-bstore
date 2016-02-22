package br.com.livraria.loja.daos;

import br.com.livraria.loja.models.SystemUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SystemUserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(SystemUser systemUser) {
        entityManager.persist(systemUser);
    }
}
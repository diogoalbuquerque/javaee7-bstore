package br.com.livraria.loja.daos;

import br.com.livraria.loja.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AuthorDAO {
    @PersistenceContext
    private EntityManager manager;

    public void save(Author author) {
        manager.persist(author);
    }

    public List<Author> list() {
        return manager.createQuery(
                "select a from Author a order by a.name asc", Author.class)
                .getResultList();
    }
}

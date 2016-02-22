package br.com.livraria.loja.daos;

import br.com.livraria.loja.models.Book;
import org.hibernate.jpa.QueryHints;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateful
public class BookDAO {
    @PersistenceContext
    private EntityManager manager;

    public BookDAO() {
    }

    public BookDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Book product) {
        manager.persist(product);
    }

    public List<Book> list() {
        return manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class)
                .getResultList();
    }

    public List<Book> lastReleases() {
        TypedQuery<Book> query = manager
                .createQuery(
                        "select b from Book b where b.releaseDate <= now() order by b.id desc",
                        Book.class).setMaxResults(3);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
    }

    public List<Book> last(int number) {
        TypedQuery<Book> query = manager
                .createQuery("select b from Book b join fetch b.authors",
                        Book.class).setMaxResults(number);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
    }

    public List<Book> olderBooks() {
        return manager.createQuery("select b from Book b", Book.class)
                .setMaxResults(20).getResultList();
    }

    public Book findById(Integer id) {
        return manager.find(Book.class, id);
    }
}
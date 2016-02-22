package br.com.livraria.loja.resources;

import br.com.livraria.loja.daos.BookDAO;
import br.com.livraria.loja.models.Book;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("books")
@Stateful
@Produces({MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML})
public class BooksResource {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    private BookDAO bookDAO;

    @PostConstruct
    private void loadDAO() {
        this.bookDAO = new BookDAO(entityManager);
    }

    @GET
    public List<Book> lastBooksJson() {
        return bookDAO.lastReleases();
    }

}
package br.com.livraria.loja.managedbeans.site;

import br.com.livraria.loja.daos.BookDAO;
import br.com.livraria.loja.models.Book;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class HomeBean {
    @Inject
    private BookDAO bookDao;

    public List<Book> lastReleases() {
        return bookDao.lastReleases();
    }

    public List<Book> olderBooks() {
        return bookDao.olderBooks();
    }
}
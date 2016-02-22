package br.com.livraria.loja.managedbeans.admin;


import br.com.livraria.loja.daos.AuthorDAO;
import br.com.livraria.loja.daos.BookDAO;
import br.com.livraria.loja.infra.FileSaver;
import br.com.livraria.loja.infra.MessagesHelper;
import br.com.livraria.loja.models.Author;
import br.com.livraria.loja.models.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class AdminBooksBean {

    @Inject
    private AuthorDAO authorDAO;
    private List<Author> authors = new ArrayList<Author>();
    private Book product = new Book();
    private List<Integer> selectedAuthorsIds =
            new ArrayList<>();
    private Part summary;
    private Part cover;

    @Inject
    private FileSaver fileSaver;
    @Inject
    private BookDAO bookDAO;

    @Inject
    private FacesContext facesContext;

    @Inject
    private MessagesHelper messagesHelper;

    @Inject
    public AdminBooksBean(AuthorDAO authorDAO, BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.authors = authorDAO.list();
    }

    private void clearObjects() {
        this.product = new Book();
        this.selectedAuthorsIds.clear();
    }

    @Transactional
    public String save() {
        String summaryPath = fileSaver.write("summaries", summary);

        product.setSummaryPath(summaryPath);
        bookDAO.save(product);
        messagesHelper.addFlash(
                new FacesMessage("Livro gravado com sucesso"));

        return "/admin/livros/list?faces-redirect=true";
    }


    public Book getProduct() {
        return product;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Part getSummary() {
        return summary;
    }

    public void setSummary(Part cover) {
        this.summary = cover;
    }

    public Part getCover() {
        return cover;
    }

    public void setCover(Part cover) {
        this.cover = cover;
    }

    @PostConstruct
    public void loadObjects() {
        this.authors = authorDAO.list();
    }

}
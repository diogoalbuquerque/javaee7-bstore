package br.com.livraria.loja.managedbeans.admin;

import br.com.livraria.loja.models.Book;
import br.com.livraria.loja.models.Sale;
import br.com.livraria.loja.websockets.ConnectedUsers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class AdminSalesBean {
    private Sale sale = new Sale();
    @Inject
    private ConnectedUsers connectedUsers;

    @PostConstruct
    private void configure() {
        this.sale.setBook(new Book());
    }

    public String save() {
        connectedUsers.send(sale.toJson());
        return "/admin/promocoes/form.xhtml?faces-redirect=true";
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
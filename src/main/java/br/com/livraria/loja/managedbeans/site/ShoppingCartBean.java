package br.com.livraria.loja.managedbeans.site;


import br.com.livraria.loja.daos.BookDAO;
import br.com.livraria.loja.models.Book;
import br.com.livraria.loja.models.ShoppingCart;
import br.com.livraria.loja.models.ShoppingItem;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ShoppingCartBean {
    @Inject
    private ShoppingCart shoppingCart;
    @Inject
    private BookDAO bookDAO;
    public String add(Integer id){
        Book book = bookDAO.findById(id);
        ShoppingItem item = new ShoppingItem(book);
        shoppingCart.add(item);
        return "/site/carrinho?faces-redirect=true";
    }
    public String remove(Integer id){
        Book book = bookDAO.findById(id);
        ShoppingItem item = new ShoppingItem(book);
        shoppingCart.remove(item);
        return "/site/carrinho?faces-redirect=true";
    }
}
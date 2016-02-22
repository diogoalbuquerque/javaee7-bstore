package br.com.livraria.loja.listeners.checkout;

import br.com.livraria.loja.daos.CheckoutDAO;
import br.com.livraria.loja.models.Checkout;
import br.com.livraria.loja.services.InvoiceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//Necessario Criar o topico no arquvio standalone-full ou pelo console, nome do topico = destinationLookup, caminho = java:/jms/topics/checkoutsTopic
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/checkoutsTopic")})
public class GenerateInvoiceListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(GenerateInvoiceListener.class);
    @Inject
    private InvoiceGenerator invoiceGenerator;
    @Inject
    private CheckoutDAO checkoutDao;

    @Override
    public void onMessage(Message message) {
        TextMessage text = (TextMessage) message;
        try {
            Checkout checkout = checkoutDao.findByUuid(text.getText());
            invoiceGenerator.invoiceFor(checkout);
        } catch (JMSException e) {
            logger.error("Problema na geracao da nota fiscal {}", e);
        }
    }

}
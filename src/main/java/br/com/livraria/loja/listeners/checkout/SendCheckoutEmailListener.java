package br.com.livraria.loja.listeners.checkout;

import br.com.livraria.loja.daos.CheckoutDAO;
import br.com.livraria.loja.infra.MailSender;
import br.com.livraria.loja.models.Checkout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//Necessario Criar o topico no arquvio standalone-full ou pelo console, nome do topico = destinationLookup, caminho = java:/jms/topics/checkoutsTopic
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/checkoutsTopic")})
public class SendCheckoutEmailListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(SendCheckoutEmailListener.class);
    @Inject
    private MailSender mailSender;
    @Inject
    private CheckoutDAO checkoutDao;

    @Override
    public void onMessage(Message message) {
        TextMessage text = (TextMessage) message;
        try {
            Checkout checkout = checkoutDao.findByUuid(text.getText());
            String emailBody = "<html><body>Compra realizada com sucesso. O código de acompanhamento é "
                    + checkout.getUuid() + "</body></html>";

            mailSender.send("compras@livraria.com", checkout.getBuyer()
                            .getEmail(), "Sua compra foi registrada com sucesso",
                    emailBody);

        } catch (Exception e) {
            logger.error("Problema no envio do email", e);
        }
    }

}
package br.com.livraria.loja.infra;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Named
@ApplicationScoped
public class Formatter {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public String dateFormat(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return dateFormatter.format(calendar.getTime());
    }
}

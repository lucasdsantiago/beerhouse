package beerhouse.lucas.mobile.pucminas.com.beerhouse.dominio;

/**
 * Created by Lucas on 26/07/2015.
 */
public class ValidacaoReceita {

    private boolean valido;

    private String mensagem;

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

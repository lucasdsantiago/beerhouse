package beerhouse.lucas.mobile.pucminas.com.beerhouse.dto;

import java.io.Serializable;

/**
 * Created by Lucas on 26/07/2015.
 */
public class ReceitaDTO implements Serializable {

    private Integer id;
    private String nome;
    private char tipoFamilia;
    private String valorABV;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(char tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

    public String getValorABV() {
        return valorABV;
    }

    public void setValorABV(String valorABV) {
        this.valorABV = valorABV;
    }
}

package beerhouse.lucas.mobile.pucminas.com.beerhouse.bo;

import android.content.Context;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.dominio.ValidacaoReceita;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dto.ReceitaDTO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.sqlite.ReceitaOpenHelper;

/**
 * Created by Lucas on 26/07/2015.
 */
public class ReceitaBO {

    private ReceitaOpenHelper receitaOpenHelper;

    public ReceitaBO(Context context){
        this.receitaOpenHelper = new ReceitaOpenHelper(context);
    }

    public ValidacaoReceita castrarReceita(ReceitaDTO receitaDTO) {
        ValidacaoReceita retorno = new ValidacaoReceita();
        receitaOpenHelper.cadastrar(receitaDTO);
        retorno.setValido(true);
        retorno.setMensagem("Cadastro Receita efetuado com sucesso!");

        return retorno;
    }


}

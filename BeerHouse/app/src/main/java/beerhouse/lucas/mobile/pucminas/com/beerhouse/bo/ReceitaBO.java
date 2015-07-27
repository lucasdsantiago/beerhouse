package beerhouse.lucas.mobile.pucminas.com.beerhouse.bo;

import android.content.Context;

import java.util.List;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.R;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dominio.ValidacaoReceita;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dto.ReceitaDTO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.sqlite.ReceitaOpenHelper;

/**
 * Created by Lucas on 26/07/2015.
 */
public class ReceitaBO {

    private Context context;
    private ReceitaOpenHelper receitaOpenHelper;

    public ReceitaBO(Context context){
        this.context = context;
        this.receitaOpenHelper = new ReceitaOpenHelper(context);
    }

    public ValidacaoReceita castrarReceita(ReceitaDTO receitaDTO) {
        ValidacaoReceita retorno = new ValidacaoReceita();
        receitaOpenHelper.cadastrar(receitaDTO);
        retorno.setValido(true);
        retorno.setMensagem(this.context.getString(R.string.msg_cadastrar_receita_sucesso));

        return retorno;
    }

    public List<ReceitaDTO> listarPessoas() {
        return receitaOpenHelper.listar();
    }

    public void deletarTodos(){
        receitaOpenHelper.deletarTodos();
    }


}

package beerhouse.lucas.mobile.pucminas.com.beerhouse.bo;

import android.content.Context;

import java.math.BigDecimal;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.R;

/**
 * Created by Lucas on 25/07/2015.
 */
public class ABVBO {

    private Context context;

    public ABVBO(Context context){
        this.context = context;
    }

    public String validarObrigatoriedadeCampos(String diStr, String doStr){
        if(diStr.isEmpty())
            return context.getString(R.string.msg_abv_obrigatorio_di);
        else if(doStr.isEmpty())
            return context.getString(R.string.msg_abv_obrigatorio_df);
        return null;
    }

    public String validarCampos(String diStr, String doStr){
        BigDecimal valorDI = new BigDecimal(diStr);
        BigDecimal valorDO = new BigDecimal(doStr);

        if(valorDI.compareTo(valorDO) >= 0)
            return context.getString(R.string.msg_abv_calculo_erro_di_maior_df);
        else if(valorDI.compareTo(new BigDecimal(1.000)) < 0)
            return context.getString(R.string.msg_abv_calculo_erro_di_menor);
        else if(valorDO.compareTo(new BigDecimal(1.100)) >= 0)
            return context.getString(R.string.msg_abv_calculo_erro_df_maior);
        return null;
    }
}

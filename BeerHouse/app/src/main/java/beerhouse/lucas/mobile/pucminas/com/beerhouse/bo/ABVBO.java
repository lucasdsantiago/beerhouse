package beerhouse.lucas.mobile.pucminas.com.beerhouse.bo;

import android.content.Context;

import java.math.BigDecimal;
import java.math.RoundingMode;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.R;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.util.MensagemUtil;

/**
 * Created by Lucas on 25/07/2015.
 */
public class ABVBO {

    private Context context;

    public ABVBO(Context context){
        this.context = context;
    }

    public String validarObrigatoriedadeCampos(String diStr, String doStr){
        if(diStr.isEmpty()) {
           return context.getString(R.string.msg_abv_obrigatorio_di);
        }
        else if(doStr.isEmpty()) {
            return context.getString(R.string.msg_abv_obrigatorio_df);
        }

        return null;
    }

    public String validarCampos(BigDecimal di, BigDecimal df){

        if (df.compareTo(di) >= 0) {
            return context.getString(R.string.msg_abv_calculo_erro_di_maior_df);
        }
        else if (di.compareTo(new BigDecimal(1.100)) >= 0 || df.compareTo(new BigDecimal(1.000)) <=  0) {
            return context.getString(R.string.msg_abv_calculo_erro_di_df_faixa);
        }

        return null;
    }

    public BigDecimal calculaValorABV(BigDecimal di, BigDecimal df){
        return (di.subtract(df).multiply(new BigDecimal(131))).setScale(3, RoundingMode.UP);
    }
}

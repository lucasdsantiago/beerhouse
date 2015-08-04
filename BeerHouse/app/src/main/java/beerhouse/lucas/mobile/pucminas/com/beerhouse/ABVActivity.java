package beerhouse.lucas.mobile.pucminas.com.beerhouse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.bo.ABVBO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.util.MensagemUtil;


public class ABVActivity extends Activity {

    private EditText edtDensidadeInicial;
    private EditText edtDensidadeFinal;
    private EditText edtValorABV;
    private Button btnCadastrar;
    private ABVBO abvBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abv);

        edtDensidadeInicial = (EditText) findViewById(R.id.edt_abv_di);
        edtDensidadeFinal = (EditText) findViewById(R.id.edt_abv_df);
        edtValorABV = (EditText) findViewById(R.id.edt_abv_valor);
        //edtValorABV.setKeyListener(null);
        edtValorABV.setEnabled(false);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_receita);
        btnCadastrar.setEnabled(false);

        abvBO = new ABVBO(ABVActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que calcula o teor alcoolico da cerveja atraves da
     * Densidade Incial e Final informada.
     * @param view
     */
    public void calcular(View view){

        String diStr = edtDensidadeInicial.getText().toString();
        String dfStr = edtDensidadeFinal.getText().toString();

        String msgObrigatorio = abvBO.validarObrigatoriedadeCampos(diStr, dfStr);

        if(msgObrigatorio == null){
            BigDecimal valorDI = new BigDecimal(diStr).setScale(3, RoundingMode.UP);
            BigDecimal valorDF = new BigDecimal(dfStr).setScale(3, RoundingMode.UP);
            String msgValidacao = abvBO.validarCampos(valorDI, valorDF);
            if(msgValidacao == null){
                BigDecimal valorABVCalculado = abvBO.calculaValorABV(valorDI, valorDF);
                edtValorABV.setText(valorABVCalculado.toString());
                btnCadastrar.setEnabled(true);
                MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_calculo_sucesso));
            }
            else{
                MensagemUtil.addMsg(ABVActivity.this, msgValidacao);
                edtValorABV.setText("");
            }
        }
        else{
            MensagemUtil.addMsg(ABVActivity.this, msgObrigatorio);
            edtValorABV.setText("");
        }
    }

    /**
     * Método que exibe a mensagem de confirmacao se o usuario deseja cadastrar
     * a receita com o teor alcoolico calculado. Caso afirmativo, o valor e passado
     * como parametro para a Tela de Cadastro.
     * @param view
     */
    public void cadastrar(View view){
        MensagemUtil.addMsgConfirm(ABVActivity.this, getString(R.string.lbl_abv_cadastrar_receita),
                getString(R.string.msg_abv_cadastrar_receita_confirmacao), R.drawable.beer32, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle params = new Bundle();
                        params.putString("abv", edtValorABV.getText().toString());
                        Intent itCadastrarReceita = new Intent(ABVActivity.this, CadastrarReceitaActivity.class);
                        itCadastrarReceita.putExtras(params);
                        startActivity(itCadastrarReceita);
                        finish();
                    }
                });
    }

    /**
     * Metodo acionado atraves do botao Limpar na qual limpa todos os campos
     * da Tela e desativa o botao Cadastrar.
     * @param view
     */
    public void limpar(View view){
        edtDensidadeInicial.setText("");
        edtDensidadeFinal.setText("");
        edtValorABV.setText("");
        btnCadastrar.setEnabled(false);
    }
}

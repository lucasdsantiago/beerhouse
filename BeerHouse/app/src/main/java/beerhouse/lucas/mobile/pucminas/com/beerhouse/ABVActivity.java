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

import beerhouse.lucas.mobile.pucminas.com.beerhouse.bo.ABVBO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.util.MensagemUtil;


public class ABVActivity extends Activity {

    private EditText edtDensidadeInicial;
    private EditText edtDensidadeFinal;
    private EditText edtValorABV;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abv);

        edtDensidadeInicial = (EditText) findViewById(R.id.edt_abv_di);
        edtDensidadeFinal = (EditText) findViewById(R.id.edt_abv_df);
        edtValorABV = (EditText) findViewById(R.id.edt_abv_valor);
        edtValorABV.setKeyListener(null);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_receita);
        btnCadastrar.setEnabled(false);
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

    public void calcular(View view){

        //new CalcularABVAsync().execute();

        String diStr = edtDensidadeInicial.getText().toString();
        String doStr = edtDensidadeFinal.getText().toString();

        boolean isObrigatorioFault = false;

        if(diStr.isEmpty()) {
            isObrigatorioFault = true;
            MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_obrigatorio_di));
        }
        else if(doStr.isEmpty()) {
            isObrigatorioFault = true;
            MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_obrigatorio_df));
        }

        if(!isObrigatorioFault) {

            boolean isFault = false;
            BigDecimal valorDI = new BigDecimal(diStr).setScale(3);
            BigDecimal valorDO = new BigDecimal(doStr).setScale(3);

            if (valorDO.compareTo(valorDI) >= 0) {
                isFault = true;
                MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_calculo_erro_di_maior_df));
            }
            else if (valorDI.compareTo(new BigDecimal(1.100)) >= 0 || valorDO.compareTo(new BigDecimal(1.000)) <=  0) {
                isFault = true;
                MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_calculo_erro_di_df_faixa));
            }

            if(!isFault){
                BigDecimal valorABV = valorDI.subtract(valorDO).multiply(new BigDecimal(131));
                edtValorABV.setText(valorABV.toString());
                btnCadastrar.setEnabled(true);
                MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_calculo_sucesso));
            }
            else edtValorABV.setText("");
        }
        else edtValorABV.setText("");


    }

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

    private class CalcularABVAsync extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog = new ProgressDialog(ABVActivity.this);

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Carregando...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            String diStr = edtDensidadeInicial.getText().toString();
            String doStr = edtDensidadeFinal.getText().toString();

            ABVBO abvBO = new ABVBO(ABVActivity.this);
            String retornoObrigatorio = abvBO.validarObrigatoriedadeCampos(diStr, doStr);
            if(retornoObrigatorio.isEmpty())
                return abvBO.validarCampos(diStr, doStr);
            return retornoObrigatorio;
        }

        @Override
        protected void onPostExecute(String msg) {
            progressDialog.dismiss();

            if (msg == null) {
                MensagemUtil.addMsg(ABVActivity.this, getString(R.string.msg_abv_calculo_sucesso));
            } else {
                MensagemUtil.addMsg(ABVActivity.this, msg);
            }
        }
    }
}

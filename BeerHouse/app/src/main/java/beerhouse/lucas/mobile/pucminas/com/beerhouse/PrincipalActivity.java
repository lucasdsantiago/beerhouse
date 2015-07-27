package beerhouse.lucas.mobile.pucminas.com.beerhouse;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.util.MensagemUtil;


public class PrincipalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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


    public void logout(View view){
        MensagemUtil.addMsgConfirm(PrincipalActivity.this, getString(R.string.lbl_principal_sair),
                getString(R.string.msg_logout), R.drawable.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
    }

    public void exibirTabela(View view){
        Uri uri = Uri.parse("http://www.tabeladacerveja.com.br/");
        Intent itNavegar = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(itNavegar);
    }

    public void sobre(View view){
        Intent itSobre = new Intent(PrincipalActivity.this, SobreActivity.class);
        startActivity(itSobre);
    }

    public void calcularABV(View view){
        Intent itABV = new Intent(PrincipalActivity.this, ABVActivity.class);
        startActivity(itABV);
    }

    public void listarReceitas(View view){
        Intent itListarReceitas = new Intent(PrincipalActivity.this, ListarReceitaActivity.class);
        startActivity(itListarReceitas);
    }
}

package beerhouse.lucas.mobile.pucminas.com.beerhouse.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class MensagemUtil {
	
	/**
	 * Metodo de criacao de mensagens rapidas.
	 * 
	 * @param activity
	 * @param msg
	 */
	public static void addMsg(Activity activity, String msg) {
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Metodo de criacao de mensagens com botao "Ok"
	 * 
	 * @param activity
	 * @param titulo
	 * @param msg
	 * @param icone
	 */
	public static void addMsgOk(Activity activity, String titulo, String msg, int icone) {
		AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
		builderDialog.setTitle(titulo);
		builderDialog.setMessage(msg);
		builderDialog.setNeutralButton("Ok", null);
		builderDialog.setIcon(icone);
		builderDialog.show();
	}
	
	/**
	 * Metodo para criacao de uma mensagem de dialogo com opcoes de sim ou nao
	 * 
	 * @param activity
	 * @param titulo
	 * @param msg
	 * @param icone
	 * @param listener
	 */
	public static void addMsgConfirm(Activity activity, String titulo, 
			String msg, int icone, OnClickListener listener) {
		AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
		builderDialog.setTitle(titulo);
		builderDialog.setMessage(msg);
		builderDialog.setPositiveButton("Sim", listener);
		builderDialog.setNegativeButton("Não", null);
		builderDialog.setIcon(icone);
		builderDialog.show();
	}
	
}

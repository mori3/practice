package jp.deci.renshu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Broadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ


		//インテントの生成
		Intent newIntent = new Intent(context, AlarmStop.class);

		newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		//アクティビティ開始
		context.startActivity(newIntent);
	}



}

package jp.deci.renshu;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class AlarmStop extends Activity{


	 // メディアプレイヤーオブジェクト
    private MediaPlayer mp;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_stop);


		// アラーム音の開始
        mp = MediaPlayer.create(this, R.raw.aaa);
        mp.setLooping(true);
        mp.start();

	}

	//ストップボタンが押された時に呼び出されるメソッド
	public void stopButtonOnClick(View v) {
		mp.stop();
	}




}

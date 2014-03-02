package jp.deci.renshu.flash;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	//SettingActivityを起動時のリクエストコード
	private static final int SETTING_CODE = 1;


	//radioボタンで設定された値
	private int[] radio = {0,0,0};
	//表示回数
	private int count = 3;
	//表示される数字
	private int[] number;
	//表示される数字の合計
	private int sum;
	//選択した設定を表示するテキスト
	private String text;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


	}

	//スタートボタンをクリック
	public void onClickStart(View v) {
		sum = 0;
		number = null;


		//表示する数字とその合計を変数にいれる
		numberSet();



		//DisplayActivityに表示する数字と数字の合計と設定を送る
		Intent i = new Intent(this, DisplayActivity.class);
		i.putExtra("KEY_SUM", sum);
		i.putExtra("KEY_NUMBER_ARRAY", number);
		i.putExtra("KEY_SETTING", radio);


		startActivity(i);


	}



	//設定ボタンが押されたらSettingActivityに移動
	public void onClickSetting(View v) {

		Intent i = new Intent(this, SettingActivity.class);
		startActivityForResult(i, SETTING_CODE);


	}




	//SettingActivityからもどってきた値を取得
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SETTING_CODE && resultCode == RESULT_OK ) {
			 radio = data.getIntArrayExtra("KEY_RADIO");



			//個数のセット フィールド変数の配列の要素数きめる
			 countSet();



			 //選択した設定をテキストに表示
			 textSet(0);
			 TextView setText1 = (TextView) findViewById(R.id.textView4);
			 setText1.setText(text);

			 textSet(1);
			 TextView setText2 = (TextView) findViewById(R.id.textView5);
			 setText2.setText(text);

			 textSet(2);
			 TextView setText3 = (TextView) findViewById(R.id.textView6);
			 setText3.setText(text);

		}

	}

	public void textSet(int i) {
		// TODO 自動生成されたメソッド・スタブ
		switch (radio[i]) {
		case 0 :
			text = "easy";
			break;
		case 1:
			text = "normal";
			break;
		case 2 :
			text = "hard";
		}
	}

	public void countSet() {
		// TODO 自動生成されたメソッド・スタブ
		switch (radio[2]) {
		case 0 :
			this.count = 3;
			break;
		case 1:
			this.count = 5;
			break;
		case 2:
			this.count = 10;
			break;
		}
	}

	public void numberSet() {
		// TODO 自動生成されたメソッド・スタブ
		number = new int[count];
		Random r = new Random();

		//数字をいれる
		switch (radio[0]) {

			//1桁
			case 0 :

			for (int i = 0; i < number.length; i++ ) {
				number[i] = r.nextInt(9) + 1;
			}

			for (int value: number) {
				sum = sum + value;
			}
			break;

			//2桁
			case 1 :
			for (int i = 0; i < number.length; i++ ) {
				number[i] = r.nextInt(10);
			}

			for (int i = 0; i < number.length; i++ ) {
				number[i] = number[i] + (r.nextInt(9) + 1) * 10;
			}

			for (int value: number) {
				sum = sum + value;

			}
			break;
			//3桁
			case 2 :
				for (int i = 0; i < number.length; i++) {
					number[i] = r.nextInt(10);

				}
				for (int i = 0; i < number.length; i++ ) {
					number[i] = number[i] + (r.nextInt(9) + 1) * 10;
				}
				for (int i = 0; i < number.length; i++) {
					number[i] = number[i] + (r.nextInt(9) + 1) * 100;
				}

				for (int value: number) {
					sum = sum + value;
				}

			break;
		}

	}

}

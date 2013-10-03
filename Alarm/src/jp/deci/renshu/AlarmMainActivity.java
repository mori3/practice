package jp.deci.renshu;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmMainActivity extends Activity {

	//設定時間の表示
	private TextView textTime;
	//時間設定ボタン
	private Button btnTime;
	//アラームオンオフボタン
	private Button btnAlarm;
	// 選択時間(時)用
	private int selectHour = 0;
	// 選択時間(分)用
	private int selectMin = 0;
	// 設定日時用
		private Calendar selectCal = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_main);


		//設定時間のテキストオブジェクト取得
		textTime = (TextView)findViewById(R.id.text_time);

		//時間設定ボタンのオブジェクト取得
		btnTime = (Button)findViewById(R.id.btn_time_set);

		//アラームオンオフボタンのオブジェクト取得
		btnAlarm = (Button)findViewById(R.id.btn_alarm);



	}
	//アラームオンオフボタンのクリック時に呼び出されるメソッド
	public void alarmButtonOnClick(View v) {



		// AlarmManagerのインスタンス取得
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

		// インテント生成
		Intent intent = new Intent(this,
				Broadcast.class);


		// ペンディングインテント取得
		PendingIntent sender = PendingIntent.getBroadcast(
				this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);

		//アラームオンオフボタンがOFFの場合
		if (btnAlarm.getText().toString().equals("OFF")) {
			btnAlarm.setText("ON");

			long timeInMillis = createTimeInMillis();
		// アラームの設定
		am.setRepeating(AlarmManager.RTC, timeInMillis,
				AlarmManager.INTERVAL_DAY, sender);

		//アラームオンオフボタンがONの場合
		} else {
			btnAlarm.setText("OFF");
		}




	}

	//時間設定ボタンのクリック時に呼び出されるメソッド
	public void timeSetButtonOnClick(View v){
		showTimePickerDialog();
	}

	public void showTimePickerDialog() {

		int defaultHour = 0;
		int defaultMin = 0;
		//時間が設定されている場合



		TimePickerDialog dialog  = new TimePickerDialog(
			this,
			new TimePickerDialog.OnTimeSetListener(){
				@Override
				public void onTimeSet(TimePicker picker, int hour, int min) {
					//時間のフォーマット
					DecimalFormat deci = new DecimalFormat("00");
					//設定時間表示
					textTime.setText(deci.format(hour) + ":" + deci.format(min));
					selectHour = hour;
					selectMin = min;
				}
		}, defaultHour, defaultMin, true);


		dialog.show();
	}

	// createTimeInMillisメソッド(起動時間のUTCミリ秒値を算出処理)
		private long createTimeInMillis() {

			// 日本時間に設定
			TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
			Calendar nowCal = Calendar.getInstance(tz);

			// 設定時間を取得
			selectCal = Calendar.getInstance(tz);
			selectCal.set(Calendar.HOUR_OF_DAY, selectHour);
			selectCal.set(Calendar.MINUTE, selectMin);

			// 現在時間より前の時間を選択した場合は翌日に設定
			if (selectCal.before(nowCal)) {
				selectCal.add(Calendar.DATE, 1);
			}

			// 起動時間のUTCミリ秒値
			return selectCal.getTimeInMillis();

		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_main, menu);
		return true;
	}

}

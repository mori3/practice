package jp.deci.renshu.flash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingActivity extends Activity {

	private int[] radio = new int[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);


		Check check = new Check();

		RadioGroup rgroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		RadioGroup rgroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
		RadioGroup rgroup3 = (RadioGroup) findViewById(R.id.radioGroup3);

		rgroup1.setOnCheckedChangeListener(check);
		rgroup2.setOnCheckedChangeListener(check);
		rgroup3.setOnCheckedChangeListener(check);


	}

	class Check implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO 自動生成されたメソッド・スタブ

			if (group.getId() == R.id.radioGroup1) {
				radioSet(group, checkedId, 0);
			}

			else if (group.getId() == R.id.radioGroup2) {
				radioSet(group, checkedId, 1);
			}

			else if (group.getId() == R.id.radioGroup3) {
				radioSet(group, checkedId, 2);
			}



		}

		//radioボタンから取得した情報を変数にいれる
		public void radioSet(RadioGroup group, int checkedId, int i) {
			// TODO 自動生成されたメソッド・スタブ

			switch (checkedId) {

			case R.id.radio0 :
				radio[i] = 0;
				break;
			case R.id.radio1 :
				radio[i] = 1;
				break;
			case R.id.radio2 :
				radio[i] = 2;
				break;

			}

		}

	}

	public void onClick(View v) {


		Intent i = getIntent();
		i.putExtra("KEY_RADIO", radio);
		setResult(RESULT_OK, i);
		finish();
	}

}

package jp.deci.renshu.flash;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayActivity extends Activity {
	private TextView text1;    //表示するテキスト
	private int sum;
	private int[] number;
	private Handler handler;
	private boolean set;       //
	private int[] radio;
	private int speed;
	private int count;
	private SoundPool soundPool;
	private int soundId1;
	private int soundId2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flash);

		//インテント取得
		Intent i = this.getIntent();
		sum = i.getIntExtra("KEY_SUM", 0);
		number = i.getIntArrayExtra("KEY_NUMBER_ARRAY");
		radio = i.getIntArrayExtra("KEY_SETTING");


		//数字を表示する設定をセット
		radioSet();




	    text1 = (TextView)findViewById(R.id.textView1);
	    text1.setText("start");






	    handler =  new Handler();


	    MyThread thread = new MyThread();
	    thread.start();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundId1 = soundPool.load(this, R.raw.maru,1);
		soundId2 = soundPool.load(this, R.raw.batsu2,1);
	}

	public void radioSet() {
		// TODO 自動生成されたメソッド・スタブ
		switch (radio[1]) {
		case 0 :
			this.speed = 1000;
			break;
		case 1:
			this.speed = 500;
			break;
		case 2:
			this.speed = 200;
			break;
		}

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

	class MyThread extends Thread {
		int i = 0;
		public void run() {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//countの回数分数字を表示
			while(i < count){

				String b = String.valueOf(i);
				Log.d("2", b);

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}


				handler.post(new Runnable() {

					public void run() {


						String t = String.valueOf(i);
						Log.d("3", t);

						//数字をとりだしてテキストにセット
						int b = number[i];
						String s = String.valueOf(b);
						text1.setText(s);
						text1.setVisibility(View.VISIBLE);

					}
				});




				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				i++;
				handler.post(new Runnable() {

					public void run() {

						//テキストを非表示に
						text1.setVisibility(View.INVISIBLE);





					}
				});

			}

			//数字を全て表示したら答えを入力するレイアウトに変える
			handler.post(new Runnable() {

				public void run() {

					setContentView(R.layout.answer);


				}
			});


		}

	}

	//入力された数値の判定
	public void onClickCheck(View v) {

		// エディットテキストのテキストを取得
		EditText editText = (EditText) findViewById(R.id.editText1);
        String text = editText.getText().toString();

        //エディットテキストになにも入力されていない場合
        if(text.equals("")) {

        	Toast.makeText(this, "数字を入力してください", Toast.LENGTH_SHORT).show();

        //入力されている場合
        } else {

        	int t = Integer.parseInt(text);

        	set = true;

        	//正解
	        if (sum == t) {
	        	setContentView(R.layout.result);
	        	soundPool.play(soundId1, 1, 1, 0, 0, 1);
	        //不正解
	        } else {
	        	setContentView(R.layout.result);
	        	ImageView imageview = (ImageView) findViewById(R.id.imageView1);
	        	imageview.setImageResource(R.drawable.batsu);
	        	soundPool.play(soundId2, 1, 1, 0, 0, 1);


	        	Toast.makeText(this, "正解は" + String.valueOf(sum) + "です", Toast.LENGTH_LONG).show();
	        }
        }
	}

	public void onClickRestart(View v) {

		DisplayActivity.this.onCreate(null);

	}

	//正解不正解の判定後画面を押すとアクティビティ終了し元の画面へ
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (set == true) {

				finish();
			}
		}
		return super.onTouchEvent(event);
	}




	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
		soundPool.release();
	}





}








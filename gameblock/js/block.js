enchant();


window.onload = function() {




	core  = new Core(320, 320);

	core.fps = 16;


	core.onload = function() {



	var bg = new Sprite(320, 320);
	bg.backgroundColor = "#707070";
	core.rootScene.addChild(bg);

	//バー作成
	var bar = new Bar(50, 250);

	//ボールの作成
	var ball = new Ball(100, 100);

	//ブロックの作成
	blocks = []; //ブロックを格納する配列
	var key; //ブロックを格納する配列のインデックスキー
	key = 0;

	for (var i = 0; i < 5; i++){
		for (var j = 0; j < 3; j++) {
			var block = new Block(i * 65, j * 15 + 10);
			block.key = key;
			blocks[key] = block;
			key++;

			//blocks.push(block);
		}
	}
	var blockCount = 0;


	//rootSceneのenterframeのイベントリスナ
	core.rootScene.addEventListener('enterframe', function(e) {






	//ボールとブロックの当たり判定
	for (var i in blocks) {
		if(blocks[i].intersect(ball)){
			blocks[i].remove();
			ball.vy *= -1;

			blockCount++;


		}
	}

	//if (blockCount == 2){
		//core.end();
	//}
	/*for (var i = 0; i < blocks.length; i++) {
		if (blocks[i].intersect(ball)) {

			blocks[i].remove();
			ball.vy *= -1;
		}

	}*/


	//ボールとバーの当たり判定
	if (ball.intersect(bar)) {
		//ボールがバーの左側へ当たれば左へ右へ当たれば右へ
		if (ball.x < bar.x) {
			ball.vx = -6;
		} else if (ball.x + 4 < bar.x + 10) {
			ball.vx = -5;
		} else if (ball.x + 4 < bar.x + 20) {
			ball.vx = -4;
		} else if (ball.x + 4 < bar.x + 30) {
			ball.vx = -3;
		} else if (ball.x + 4 < bar.x + 40) {
			ball.vx = 3;
		} else if (ball.x + 4 < bar.x + 50) {
			ball.vx = 4;
		} else if (ball.x + 4 < bar.x + 60) {
			ball.vx = 5;
		} else {
			ball.vx = 6;
		}
		//ボールのスピードアップ
		ball.vy += 0.5;

		ball.vy *= -1;
	}



//ゲームオーバー
	if(ball.y > 280) {
		core.end();
	}

	});

	}

	core.start();

}




//バーのスプライトの作成
var Bar = enchant.Class.create(enchant.Sprite, {
	//initializeメソッド(コンストラクタ)
	initialize: function(x, y){
		//継承元をコール
		enchant.Sprite.call(this, 60, 8);
		this.backgroundColor = "black";
		this.x = x;
		this.y = y;
		this.speed = 8;

		//enterframeイベントリスナ
		this.addEventListener('enterframe', function(e) {
			//左ボタンで左に移動
			if (core.input.left && this.x > 0) {
				this.x -= 8;
			}
			if (core.input.right && this.x < 270) {
				this.x += 8;
			}

		});
		core.rootScene.addChild(this);
	}
});

// ボールのスプライトの作成
var Ball = enchant.Class.create(enchant.Sprite, {
	//initiializeメソッド(コンストラクタ)
	initialize: function(x, y) {
		//継承元をコール
		enchant.Sprite.call(this, 8, 8);
		this.backgroundColor = "white";
		//ボールの座標
		this.x = x;
		this.y = y;
		//ボールの移動量
		this.vx = 4;
		this.vy = 8;
		//enterframeイベント
		this.addEventListener('enterframe', function(e) {
			//ボールの移動
			this.x += this.vx;
			this.y += this.vy;
			//下以外の壁にあたったら反転
			if(this.x < 0 || this.x > 310) {
				this.vx = this.vx * -1;
			}
			if(this.y < 0 || this.y >320) {
				this.vy = this.vy * -1;
			}
		});
		core.rootScene.addChild(this);

	},
	//removeメソッド
	remove: function(){

		delete this;

	}
});

//ブロックのスプライト
var Block = enchant.Class.create(enchant.Sprite, {
	//initializeメソット(コンストラクタ)
	initialize: function(x, y) {
		//継承元をコール
		enchant.Sprite.call(this, 60, 10);
		this.backgroundColor = "yellow";
		//座標
		this.x = x;
		this.y = y;
		core.rootScene.addChild(this);
	},
	//removeメソッド
	remove: function() {
		core.rootScene.removeChild(this);
		delete blocks[this.key];
		delete this;






	}

})











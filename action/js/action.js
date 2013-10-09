enchant();

window.onload = function() {
	core = new Core(480, 320);
	core.fps = 16;
	core.time = 100;

	//画像読み込み
	core.preload('images/map2.png', 'images/chara5.png', 'images/chara6.png', 'images/chara7.png','images/effect0.png','images/monster/bigmonster1.gif');

	//画像の読み込み終了
	core.onload = function() {


		bg = new Sprite(960, 320);
		bg.backgroundColor = "#4abafa";


		var image = new Surface(320, 320);

		for ( var i = 0; i < 20; i++) {
			image.draw(core.assets['images/map2.png'],0, 0, 16, 16, i * 16, 16 * 16 , 64, 64);
		}
		bg.image = image;

		bg.addEventListener('enterframe', function() {
			this.x -= 8;
			if (this.x < -320) {
				this.x = 0;
			}
		});

		core.rootScene.addChild(bg);

		//プレイヤーのスプライト作成
		player = new Sprite(32, 32);

		player.image = core.assets['images/chara5.png'];
		player.x = 60;
		player.y = 256 - 28;
		player.frame = 18;
		player.tick = 0;
		player.jump = false;



		player.addEventListener('enterframe', function(e) {

			this.frame = this.tick % 6 + 18;
			this.tick++;

			if(player.jump == true) {

				this.y -= 16;
				if(player.y < 120) {

					this.jump = false;

				}
			}


			if ( this.jump == false && player.y <= 226) {


					this.y += 8;

			}


			if (core.input.up) {

				if(this.jump == false && player.y >= 226) {
					this.jump = true;

				}

			}



		});

		core.rootScene.addChild(player);

		core.rootScene.addEventListener('enterframe', function(e) {
			if (core.time == 0) {
				core.end();
			}

			if (core.frame % 16 == 0) {
				core.time --;
				timeLabel.text = 'TIME:' + core.time;
			}

			if (core.frame % 32 == 0){
				if(Math.floor(Math.random() * 100) < 65 ){
					var enemy1 = new Enemy1(480,256 - 24);
				}
			}
			if (core.frame % 64 == 0) {
				if(Math.floor(Math.random() * 100) < 50 ){

					var enemy2 = new Enemy2(360,256 - 28);
				}

			}

			if (core.frame % 128 == 0) {

				if(Math.floor(Math.random() * 100) < 25 ){
					var enemy3 = new Enemy3(540,256 -72);
				}
			}
		})




		var timeLabel = new MutableText(10, 0);
		timeLabel.text = 'TIME:' +  100;

		core.rootScene.addChild(timeLabel);

	};



	core.start();

};


var Enemy1 = enchant.Class.create(enchant.Sprite, {
	initialize: function(x, y) {
		enchant.Sprite.call(this,32,32);//1616

		this.image = core.assets['images/chara6.png'];
		this.x = x;
		this.y = y;
		this.frame = 0;
		this.tick = 0;

		this.addEventListener('enterframe', function(e) {
			this.x -= 4;


			this.frame = this.tick % 3 ;
			this.tick ++;

			if (this.x < -32) {

				core.rootScene.removeChild(this);
				delete this;

			}
			if (this.within(player, 16)) {

				player.image = core.assets['images/effect0.png'];
				player.frame = 3;

				core.end();

			}



        });

		core.rootScene.addChild(this);
	}

});

var Enemy2 = enchant.Class.create(enchant.Sprite, {
	initialize: function(x, y) {
		enchant.Sprite.call(this,32,32);//1616

		this.image = core.assets['images/chara7.png'];
		this.x = x;
		this.y = y;
		this.frame = 9;
		this.tick = 0;

		this.addEventListener('enterframe', function(e) {
			this.x -= 8;

			this.frame = this.tick % 9 + 9;
			this.tick ++;

			if (this.x < -32) {

				core.rootScene.removeChild(this);
				delete this;

			}
			if (this.within(player, 16)) {
				player.image = core.assets['images/effect0.png'];
				player.frame = 3;
				core.end();
			}
		});

		core.rootScene.addChild(this);
	}

});

var Enemy3 = enchant.Class.create(enchant.Sprite, {
	initialize: function(x, y) {
		enchant.Sprite.call(this,80,80);//1616

		this.image = core.assets['images/monster/bigmonster1.gif'];
		this.x = x;
		this.y = y;
		this.frame = 0;
		this.tick = 0;

		this.addEventListener('enterframe', function(e) {
			this.x -= 24;

			this.frame = this.tick % 3 + 2;
			this.tick ++;

			if (this.x < -80) {

				core.rootScene.removeChild(this);
				delete this;

			}
			if (this.within(player, 40)) {
				player.image = core.assets['images/effect0.png'];
				player.frame = 3;
				core.end();
			}

		});

		core.rootScene.addChild(this);
	}

});
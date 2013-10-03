using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Timer
{
    public partial class Form1 : Form
    {
        int settingTime; //設定時間
        int settingTime2;
        int passedTime; //経過時間

        int second = 0;
        int minute = 0;
        int hour = 0;

        public Form1()
        {
            InitializeComponent();
        }
        //ストップウォッチ
        private void button4_Click(object sender, EventArgs e)
        {
            if (button4.Text == "スタート")
            {
                button4.Text = "ストップ";


                timer2.Start();
            }
            else
            {
                timer2.Stop();
                button4.Text = "スタート";
            }
        }

        private void timer2_Tick(object sender, EventArgs e)
        {
            second++;
            if (second == 60)
            {
                second = 0;
                label1.Text = second.ToString();
                minute++;
                label2.Text = minute.ToString();

                if (minute == 60)
                {
                    minute = 0;
                    label2.Text = minute.ToString();
                    hour++;
                    label3.Text = hour.ToString();
                }


            }
            else
            {
                label1.Text = second.ToString();
            }

        }

        private void button5_Click(object sender, EventArgs e)
        {
            timer2.Stop();
            button4.Text = "スタート";
            second = 0;
            minute = 0;
            hour = 0;
            label1.Text = second.ToString();
            label2.Text = minute.ToString();
            label3.Text = hour.ToString();

        }

        //タイマー

        private void textBox1_Click(object sender, EventArgs e)
        {
            textBox1.Text = "";
        }
        private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar < '0' || '9' < e.KeyChar)
            {
                textBox1.Text = "";
                MessageBox.Show("数字を入力してください");
            }
        }
        private void textBox2_Click(object sender, EventArgs e)
        {
            textBox2.Text = "";
        }

        private void textBox2_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar < '0' || '9' < e.KeyChar)
            {
                textBox2.Text = "";
                MessageBox.Show("数字を入力してください");
            }
        }


        private void button1_Click(object sender, EventArgs e)
        {

            if (!int.TryParse(textBox1.Text, out settingTime) || !int.TryParse(textBox2.Text, out settingTime2))
            {
                MessageBox.Show("数字を入力してください");
            }
            else
            {
                passedTime = 0;

                //スタート
                timer1.Start();
            }

        }
        //1秒たつごとに呼び出されるメソッド
        private void timer1_Tick(object sender, EventArgs e)
        {
            int restTime; //残り時間
            
           

           //残り時間が0
            if (settingTime == passedTime)
            {
                if (settingTime2 == 0)
                {
                    timer1.Stop();
                    MessageBox.Show("時間終了");
                    restTime = 0;
                    textBox1.Text = restTime.ToString();
                    textBox2.Text = restTime.ToString();
                }
                else
                {
                    settingTime2--;
                    textBox2.Text = settingTime2.ToString();
                    
                    settingTime = 59;
                    textBox1.Text = settingTime.ToString();
                    passedTime = 0;
                    
                }
            }
            else
            {


                passedTime++;
                restTime = settingTime - passedTime;
                textBox1.Text = restTime.ToString();
            }
                


        }

        private void button2_Click(object sender, EventArgs e)
        {
            timer1.Stop();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            settingTime = 0;
            textBox1.Text = settingTime.ToString();
            textBox2.Text = settingTime.ToString();
            timer1.Stop();
        }

        

        

       
        
    }
}

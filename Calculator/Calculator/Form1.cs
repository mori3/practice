using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Calculator
{
    public partial class Form1 : Form
    {
        string hyouji = "0";
        string ope = "0";
        double keisan = 0;



        public Form1()
        {
            InitializeComponent();
        }

        //入力した値を画面に表示
        public void Show(String s)
        {


            if (hyouji == "0")
            {
                hyouji = s;
            }
            else if (hyouji.Length >= 13)
            {
                label1.Text = hyouji;
            }
            else
            {
                hyouji += s;
            }
            label1.Text = hyouji;

        }

        //計算して画面に表示
        public void Calc(string addOpe)
        {
            if (hyouji == "0") //何も入力されてない状態で演算子を押す
            {

                label1.Text = hyouji;


            }
            else//数字が入力されてる
            {
                if (ope == "0")//初めて演算子が押される
                {
                    keisan = double.Parse(hyouji);

                    ope = addOpe;
                    hyouji = "0";
                }
                else //演算子がおされるのが２回目以降
                {
                    if (ope == "+")
                    {
                        keisan += double.Parse(hyouji);

                        
                    }
                    else if (ope == "-")
                    {
                        keisan -= double.Parse(hyouji);
                    }
                    else if (ope == "*")
                    {
                        keisan *= double.Parse(hyouji);
                    }
                    else if (ope == "/")
                    {
                        keisan /= double.Parse(hyouji);
                    }

                    hyouji = keisan.ToString();
                    label1.Text = hyouji;

                    ope = addOpe;
                    hyouji = "0";
                }
            }
        }


        //数字をクリックしたときの処理
        private void button1_Click(object sender, EventArgs e)
        {
            Show("1");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Show("2");
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Show("3");

        }
        private void button4_Click(object sender, EventArgs e)
        {
            Show("4");
        }

        private void button5_Click(object sender, EventArgs e)
        {
            Show("5");
        }

        private void button6_Click(object sender, EventArgs e)
        {
            Show("6");
        }

        private void button7_Click(object sender, EventArgs e)
        {
            Show("7");
        }

        private void button8_Click(object sender, EventArgs e)
        {
            Show("8");
        }

        private void button9_Click(object sender, EventArgs e)
        {
            Show("9");
        }

        private void button10_Click(object sender, EventArgs e)
        {
            Show("0");
        }
        private void buttonComma_Click(object sender, EventArgs e)
        {
            /*if (hyouji == "0")
            {
                
            }
            else if (hyouji.Contains("."))
            {

            }

            else
            {
                Show(".");
            }*/
            /*if (!(hyouji == "0") && !(hyouji.Contains(".")))
            {
                Show(".");
            }*/
            if (!((hyouji == "0") || (hyouji.Contains("."))))
            {
                Show(".");
            }
        }

        //演算子をクリックしたときの処理
        private void buttonTasu_Click(object sender, EventArgs e)
        {
            
            Calc("+");
            //ope = "+";
            //hyouji = "0";

        }
        private void buttonHiku_Click(object sender, EventArgs e)
        {
            Calc("-");
            
            
        }
        private void buttonKake_Click(object sender, EventArgs e)
        {
            Calc("*");
            
        }

        private void buttonWaru_Click(object sender, EventArgs e)
        {
            Calc("/");

            
            
        }
        //イコール
        private void buttonEqual_Click(object sender, EventArgs e)
        {
            Calc("0");

            hyouji = "0";
            keisan = 0;
            ope = "0";
        }
        //クリアー
        private void buttonClear_Click(object sender, EventArgs e)
        {
            hyouji = "0";
            keisan = 0;
            ope = "0";
            label1.Text = hyouji;
        }

        

       



    }
}


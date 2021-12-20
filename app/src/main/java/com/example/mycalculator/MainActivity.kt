package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tvInput:TextView;
    var lastNumeric=false;
    var lastDot=false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvinput)
    }

    fun Ondigit(view: View) {
       tvInput.append((view as Button).text)
        lastNumeric=true

    }
    fun Onclear(view: View) {
        tvInput.text=""
        lastNumeric=false
        lastDot=false
    }
    fun Ondecimalpoint(view: View) {
        if(lastNumeric&&!lastDot)
        {
            tvInput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun OnEqual(view: View)
    {
        if(lastNumeric)
        {
            var tvValue=tvInput.text.toString()
            var prefix=""

            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-"))
                {
                    val splitvalue=tvValue.split("-")
                    var one=splitvalue[0];
                    var two=splitvalue[1];
                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=removezeros((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("*"))
                {
                    val splitvalue=tvValue.split("*")
                    var one=splitvalue[0];
                    var two=splitvalue[1];
                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=removezeros((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("+"))
                {
                    val splitvalue=tvValue.split("+")
                    var one=splitvalue[0];
                    var two=splitvalue[1];
                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=removezeros((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("/"))
                {
                    val splitvalue=tvValue.split("/")
                    var one=splitvalue[0];
                    var two=splitvalue[1];
                    if(!prefix.isEmpty())
                    {
                        one=prefix+one
                    }
                    tvInput.text=removezeros((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun removezeros(result:String):String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)
        return value
    }

    fun Onoperator(view: View) {
        if(lastNumeric&&!OnOperatorAdded(tvInput.text.toString()))
        {
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    private fun OnOperatorAdded(view:String):Boolean
    {
        return if(view.startsWith("-"))
        {
            false
        }
        else
        {
            view.contains("+")||view.contains("/")||view.contains("*")||
                    view.contains("-")
        }
    }

}

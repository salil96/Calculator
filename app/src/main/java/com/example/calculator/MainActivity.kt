package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import org.mozilla.javascript.Context

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editTextview  : TextView
    private lateinit var resultTextView : TextView
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editTextview = findViewById(R.id.editTextView)
        resultTextView = findViewById(R.id.result_TextView)
        binding.apply {
            button0.setOnClickListener(this@MainActivity)
            button1.setOnClickListener(this@MainActivity)
            button2.setOnClickListener(this@MainActivity)
            button3.setOnClickListener(this@MainActivity)
            button4.setOnClickListener(this@MainActivity)
            button5.setOnClickListener(this@MainActivity)
            button6.setOnClickListener(this@MainActivity)
            button7.setOnClickListener(this@MainActivity)
            button8.setOnClickListener(this@MainActivity)
            button9.setOnClickListener(this@MainActivity)

            buttonPlus.setOnClickListener(this@MainActivity)
            buttonMinus.setOnClickListener(this@MainActivity)
            buttonMultiply.setOnClickListener(this@MainActivity)
            buttonDivid.setOnClickListener(this@MainActivity)

            buttonC.setOnClickListener(this@MainActivity)
            buttonOpenBracket.setOnClickListener(this@MainActivity)
            buttonCloseBracket.setOnClickListener(this@MainActivity)
            buttonAc.setOnClickListener(this@MainActivity)
            buttonDot.setOnClickListener(this@MainActivity)
            buttonEqal.setOnClickListener(this@MainActivity)

        }

    }

    override fun onClick(v: View?) {

        val materialButton: MaterialButton = v as MaterialButton
        val buttonText = materialButton.text.toString()
        var dataToCalculate = editTextview.text.toString()
       // editTextview.setText(dataToCalculate)


        when (buttonText) {
            "AC" -> {
                editTextview.text = ""
                resultTextView.text = "0"
                return
            }
            "=" -> {
                editTextview.text = resultTextView.text
                return
            }
            "C" -> {
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length-1)
            }
            else -> {
                dataToCalculate += buttonText
            }
        }
        editTextview.text =dataToCalculate
        val finalResultText = getResult(dataToCalculate)
        if(finalResultText!="Error"){
            resultTextView.text = finalResultText
        }

    }
    private fun getResult(data : String ): String {
        return try{
            val context = Context.enter()
            context.optimizationLevel = -1
            val scriptable = context.initStandardObjects()
            var finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString()
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","")
            }
            finalResult
        }catch(e:Exception){
            "Error"
        }
    }
}



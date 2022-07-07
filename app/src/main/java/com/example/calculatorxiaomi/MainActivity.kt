package com.example.calculatorxiaomi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculatorxiaomi.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

const val KEY_INTENT_NAME = "data"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var check: Boolean = true//for check dot(.)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()


    }

    private fun onOperatorClicked() {
        binding.btnJam.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val newChar = binding.txtExpression.text.last()
                if (newChar != '+' && newChar != '-' && newChar != '/' && newChar != '*' && newChar != '.') {
                    appendText("+")
                    check = true
                }
            }

        }
        binding.btnMenha.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val newChar = binding.txtExpression.text.last()
                if (newChar != '+' && newChar != '-' && newChar != '/' && newChar != '*' && newChar != '.') {
                    appendText("-")
                    check = true
                }
            }

        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val newChar = binding.txtExpression.text.last()
                if (newChar != '+' && newChar != '-' && newChar != '/' && newChar != '*' && newChar != '.') {
                    appendText("/")
                    check = true
                }
            }

        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val newChar = binding.txtExpression.text.last()
                if (newChar != '+' && newChar != '-' && newChar != '/' && newChar != '*' && newChar != '.') {
                    appendText("*")
                    check = true
                }
            }

        }
        binding.btnParntezbaz.setOnClickListener {
            val newChar = binding.txtExpression.text.last()
            if (newChar!='.'){
                appendText("(")
                check = true
            }


        }
        binding.btnParntezbaste.setOnClickListener {
            val newChar = binding.txtExpression.text.last()
            if (newChar!='.'){
                appendText(")")
                check = true
            }
        }
        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtAnswer.text = ""
        }
        binding.btnPakon.setOnClickListener {
            val oldText = binding.txtExpression.text.toString()
            if (oldText.isNotEmpty()) {
                binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
            }

        }
        binding.btnMosavi.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.txtAnswer.text = "= $longResult"
                } else {
                    binding.txtAnswer.text = "= $result"//ashari
                }
            } catch (ex: Exception) {
                binding.txtAnswer.text = ""
                binding.txtExpression.text = ""
                Toast.makeText(this, "دستور نامعتبر است", Toast.LENGTH_LONG).show()

            }


        }
    }

    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }

        }

        binding.btn1.setOnClickListener {
            appendText("1")

        }
        binding.btn2.setOnClickListener {
            appendText("2")

        }
        binding.btn3.setOnClickListener {
            appendText("3")

        }
        binding.btn4.setOnClickListener {
            appendText("4")

        }
        binding.btn5.setOnClickListener {
            appendText("5")

        }
        binding.btn6.setOnClickListener {
            appendText("6")

        }
        binding.btn7.setOnClickListener {
            appendText("7")

        }
        binding.btn8.setOnClickListener {
            appendText("8")

        }
        binding.btn9.setOnClickListener {
            appendText("9")

        }
        binding.btnDot.setOnClickListener {
            val newChar=binding.txtExpression.text.last()
            if (check) {
                if (binding.txtExpression.text.isEmpty() || binding.txtAnswer.text.isNotEmpty()
                    || newChar == '+'||newChar=='-'||newChar=='*'||newChar=='/') {
                    appendText("0.")
                    check=false
                } else {

                    appendText(".")
                    check=false
                }
            }

        }

    }

    private fun appendText(newText: String) {

        binding.txtExpression.append(newText)
        if (binding.txtAnswer.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtAnswer.text = ""

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver

        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }


        })


    }

}


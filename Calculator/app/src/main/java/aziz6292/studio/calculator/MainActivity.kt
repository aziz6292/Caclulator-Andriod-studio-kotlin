package aziz6292.studio.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var etA: EditText
    lateinit var resultTv: TextView
    lateinit var currentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etA = findViewById(R.id.et_a)
        resultTv = findViewById(R.id.result_tv)

        // Initialize the currentEditText to etA
        currentEditText = etA

        // Set click listeners for numeric buttons
        findViewById<Button>(R.id.one).setOnClickListener(this)
        findViewById<Button>(R.id.two).setOnClickListener(this)
        findViewById<Button>(R.id.three).setOnClickListener(this)
        findViewById<Button>(R.id.four).setOnClickListener(this)
        findViewById<Button>(R.id.five).setOnClickListener(this)
        findViewById<Button>(R.id.six).setOnClickListener(this)
        findViewById<Button>(R.id.seven).setOnClickListener(this)
        findViewById<Button>(R.id.eight).setOnClickListener(this)
        findViewById<Button>(R.id.nine).setOnClickListener(this)
        findViewById<Button>(R.id.zero).setOnClickListener(this)

        // Set click listeners for operator buttons
        findViewById<Button>(R.id.btn_add).setOnClickListener(this)
        findViewById<Button>(R.id.btn_subtraction).setOnClickListener(this)
        findViewById<Button>(R.id.btn_multiplication).setOnClickListener(this)
        findViewById<Button>(R.id.btn_division).setOnClickListener(this)

        // Set click listeners for other buttons
        findViewById<Button>(R.id.ce).setOnClickListener(this)
        findViewById<Button>(R.id.equal).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            // Numeric buttons
            R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero -> {
                val button = v as Button
                val number = button.text.toString()

                // Append the clicked number to the text of the currentEditText
                currentEditText.append(number)
            }

            // Operator buttons
            R.id.btn_add, R.id.btn_subtraction, R.id.btn_multiplication, R.id.btn_division -> {
                val button = v as Button
                val operator = button.text.toString()

                // Append the operator to the text of the currentEditText
                currentEditText.append(operator)
            }

            // Clear button
            R.id.ce -> {
                // Clear the text of both EditText views
                etA.text.clear()
                resultTv.text = "Result"
            }

            // Equal button
            R.id.equal -> {
                // Get the expressions from etA and etB
                val expressionA = etA.text.toString()

                try {
                    // Evaluate the expressions and calculate the result
                    val result = evaluateExpression(expressionA)

                    // Update the result TextView with the calculated result
                    resultTv.text = "$result"
                } catch (e: Exception) {
                    // Handle any exceptions that may occur during evaluation
                    resultTv.text = "Error: ${e.message}"
                }
            }

        }
    }


    private fun evaluateExpression(expression: String): Double {
        val exp = ExpressionBuilder(expression).build()
        return exp.evaluate()
    }

}

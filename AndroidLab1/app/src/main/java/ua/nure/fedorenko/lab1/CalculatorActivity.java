package ua.nure.fedorenko.lab1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import butterknife.BindView;
import butterknife.OnClick;


public class CalculatorActivity extends BaseActivity {

    private static final String DOT = ".";
    // TextView used to display the output
    @BindView(R.id.txtScreen)
    private TextView calculationResultTextView;
    // Represent whether the lastly pressed key is numeric or not
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    /**
     * Find and set OnClickListener to numeric buttons.
     */
    @OnClick({R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine})
    private void onNumericButtonClick(View v) {
        Button button = (Button) v;
        if (stateError) {
            // If current state is Error, replace the error message
            calculationResultTextView.setText(button.getText());
            stateError = false;
        } else {
            // If not, already there is a valid expression so append to it
            calculationResultTextView.append(button.getText());
        }
        // Set the flag
        lastNumeric = true;
    }

    /**
     * Find and set OnClickListener to operator buttons, equal button and decimal point button.
     */
    @OnClick({R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide})
    private void onOperatorButtonClick(View v) {
        // If the current state is Error do not append the operator
        // If the last input is number only, append the operator
        if (lastNumeric && !stateError) {
            Button button = (Button) v;
            calculationResultTextView.append(button.getTag().toString());
            lastNumeric = false;
            lastDot = false;
        }
    }

    @OnClick(R.id.btnDot)
    public void onDotButtonClick(View v) {
        if (lastNumeric && !stateError && !lastDot) {
            calculationResultTextView.append(DOT);
            lastNumeric = false;
            lastDot = true;
        }
    }

    @OnClick(R.id.btnClear)
    public void onClearButtonClick(View v) {
        calculationResultTextView.setText("");
        // Reset all the states and flags
        lastNumeric = false;
        stateError = false;
        lastDot = false;
    }

    @OnClick(R.id.btnEqual)
    public void onEqualButtonClick(View v) {
        if (lastNumeric && !stateError) {
            // Read the expression
            String txt = calculationResultTextView.getText().toString();
            // Create an Expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                // Calculate the result and display
                double result = expression.evaluate();
                calculationResultTextView.setText(Double.toString(result));
                lastDot = true;
            } catch (ArithmeticException ex) {
                Log.e("CalculatorActivity", "ArithmeticException occurred!", ex);
                calculationResultTextView.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}

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
    TextView calculationResultTextView;
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

    @OnClick({R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine})
    void onNumericButtonClick(View v) {
        Button button = (Button) v;
        if (stateError) {
            calculationResultTextView.setText(button.getTag().toString());
            stateError = false;
        } else {
            calculationResultTextView.append(button.getTag().toString());
        }
        lastNumeric = true;
    }

    @OnClick({R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide})
    void onOperatorButtonClick(View v) {
        if (lastNumeric && !stateError) {
            Button button = (Button) v;
            calculationResultTextView.append(button.getTag().toString());
            lastNumeric = false;
            lastDot = false;
        }
    }

    @OnClick(R.id.btnDot)
    void onDotButtonClick(View v) {
        if (lastNumeric && !stateError && !lastDot) {
            calculationResultTextView.append(DOT);
            lastNumeric = false;
            lastDot = true;
        }
    }

    @OnClick(R.id.btnClear)
    void onClearButtonClick(View v) {
        calculationResultTextView.setText("");
        lastNumeric = false;
        stateError = false;
        lastDot = false;
    }

    @OnClick(R.id.btnEqual)
    void onEqualButtonClick(View v) {
        if (lastNumeric && !stateError) {
            String txt = calculationResultTextView.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                calculationResultTextView.setText(Double.toString(result));
                lastDot = true;
            } catch (ArithmeticException ex) {
                Log.e("CalculatorActivity", "ArithmeticException occurred!", ex);
                calculationResultTextView.setText(R.string.error);
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}

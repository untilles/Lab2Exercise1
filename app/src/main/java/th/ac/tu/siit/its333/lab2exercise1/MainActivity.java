package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    int memory = 0;
    int ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    public void recalculate() {
        //Calculate the expression and display the output

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");

        int i;
        ans = Integer.parseInt(tokens[0]);
        if (tokens.length >= 3) {
            for (i = 0; i < tokens.length - 2; i++) {

                if (i % 2 == 0) {
                    char op = tokens[i + 1].charAt(0);
                    switch (op) {
                        case '+':
                            ans = ans + Integer.parseInt(tokens[i + 2]);
                            break;
                        case '-':
                            ans = ans - Integer.parseInt(tokens[i + 2]);
                            break;
                        case '*':
                            ans = ans * Integer.parseInt(tokens[i + 2]);
                            break;
                        case '/':
                            ans = ans / Integer.parseInt(tokens[i + 2]);
                            break;
                    }
                } else {

                }
            }
        }



        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Integer.toString(ans));

    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        //IF the last character in expr is not an operator and expr is not "",
        //THEN append the clicked operator and updateExprDisplay,
        if (!expr.toString().isEmpty() && !isOperand(expr.charAt(expr.length()-1))) {
            String o = ((TextView)v).getText().toString();
            expr.append(o);
            updateExprDisplay();
        } else if (!expr.toString().isEmpty() && isOperand(expr.charAt(expr.length()-1))) {
            String o = ((TextView)v).getText().toString();
            expr.deleteCharAt(expr.length()-1);
            expr.append(o);
            updateExprDisplay();
        } else {

        }
        //ELSE do nothing
    }

    public boolean isOperand(char c){
        return c == '/' || c == '*' || c == '-' || c == '+';
    }

    public void equalClicked(View v) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(tvAns.getText().toString());
        tvAns.setText(" ");
    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        updateExprDisplay();
        tvAns.setText(" ");
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 1) {
            expr.deleteCharAt(expr.length() - 1);
            recalculate();
            updateExprDisplay();

        }
    }

    public void MemPlus(View v){
        TextView TvAns = (TextView)findViewById(R.id.tvAns);
        memory += Integer.parseInt(TvAns.getText().toString());
        //updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory) , Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }
    public void MemMinus(View v){
        TextView TvAns = (TextView)findViewById(R.id.tvAns);
        memory -= Integer.parseInt(TvAns.getText().toString());
        //updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory) , Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }
    public void MemR(View v){
        expr = new StringBuffer(Integer.toString(memory));
        updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory), Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }
    public void MemC(View v){
        memory = 0;
        //updateExprDisplay();
        Toast t = Toast.makeText(this.getApplicationContext(), Integer.toString(memory), Toast.LENGTH_SHORT);
        t.show();
        recalculate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

/**
 * {This app is a high low guessing game. The player can guess a number between 1-1000 and has only 10 tries to do so.}
 *
 * @author {chri0195@algonquinlive.com}
 */

package com.chri0195algonquincollege.guessanumber;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import static com.chri0195algonquincollege.guessanumber.R.id.addNumber;
import static com.chri0195algonquincollege.guessanumber.R.id.Reset;


public class MainActivity extends AppCompatActivity {

    private static final String ABOUT_DIALOG_TAG;
    private static final String LOG_TAG;
    protected int theNumber;
    public int count;

    static {
        ABOUT_DIALOG_TAG = "About Dialog";
        LOG_TAG = "GUESSING GAME";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ThreadLocalRandom.current().nextInt(1, 1001);

        count = 0;
        final Random rdn = new Random();
        theNumber = rdn.nextInt(1000) + 1;

        Button Submit = (Button) findViewById(R.id.Submit);
        Button Reset = (Button) findViewById(R.id.Reset);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i(LOG_TAG, String.valueOf(theNumber));

                EditText addNumberText = (EditText) findViewById(R.id.addNumber);

                String addNumber = addNumberText.getText().toString();


                if (addNumber.isEmpty()) {
                    addNumberText.setError("Enter A Number From 1-1000");
                    addNumberText.requestFocus();
                    return;
                }

                int addNumberInput;
                try {
                    addNumberInput = Integer.parseInt(addNumber);
                } catch (NumberFormatException e) {
                    addNumberText.setError("You Must Enter A Number From 1-1000");
                    addNumberText.requestFocus();
                    return;
                }
                if (addNumberInput > 1000) {
                    addNumberText.setError("Enter A Number From 1-1000");
                    addNumberText.requestFocus();
                    return;
                }


                if (addNumberInput > theNumber) {
                    Toast.makeText(getApplicationContext(), "Your answer is a bit too high!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (addNumberInput < theNumber) {
                    Toast.makeText(getApplicationContext(), "Your answer is a bit too low!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (count > 9) {
                    Toast.makeText(getApplicationContext(), "Reset the Game!", Toast.LENGTH_SHORT).show();
                    return;

                }

                count++;


                if (addNumberInput == theNumber) {

                    if (count < 5) {
                        Toast.makeText(getApplicationContext(), "Excellent Win!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (count >= 6 && count <= 9) {
                        Toast.makeText(getApplicationContext(), "Superior Win!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                }

            }

        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = 0;

                theNumber = rdn.nextInt(1000) + 1;

                EditText addNumberText = (EditText) findViewById(R.id.addNumber);
                addNumberText.setText("");
                Toast.makeText(getApplicationContext(), "Lets go again!", Toast.LENGTH_LONG).show();

                return;
            }

        });


        Reset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(getApplicationContext(), " The Number Was " + theNumber, Toast.LENGTH_LONG).show();

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: add this method to handle when the user selects a menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}



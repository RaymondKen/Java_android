package com.todolist.chronos.activity_example;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Chronos on 22/01/2017.
 */

public class App2Activity extends Activity {
    Button button;
    private CheckBox chkIos, chkAndroid, chkWindows;
    private Button btnDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.urlbutton);
        onListenerButton();
        addListenerOnChkIos();
        addListenerButtonCheckbox();
    }

    public void onListenerButton()
    {
        button = (Button)findViewById(R.id.urlButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.fr"));
                startActivity(browserIntent);
            }
        });
    }

    public void addListenerOnChkIos()
    {
        chkIos = (CheckBox) findViewById(R.id.chkIos);
        chkIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked())
                {
                    Toast.makeText(App2Activity.this,
                            "Bro, try Android :)", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void addListenerButtonCheckbox()
    {
        chkIos = (CheckBox) findViewById(R.id.chkIos);
        chkAndroid = (CheckBox) findViewById(R.id.chkAndroid);
        chkWindows = (CheckBox) findViewById(R.id.chkWindows);
        button = (Button)findViewById(R.id.Display);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();
                result.append("IPhone check : ").append(chkIos.isChecked());
                result.append("\nAndroid check : ").append(chkAndroid.isChecked());
                result.append("\nWindows Mobile check :").append(chkWindows.isChecked());

                Toast.makeText(App2Activity.this, result.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}

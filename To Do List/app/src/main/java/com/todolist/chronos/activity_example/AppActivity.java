package com.todolist.chronos.activity_example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chronos on 22/01/2017.
 */

public class AppActivity extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addListenerOnButton();
    }

    public void addListenerOnButton()
    {
        final Context context = this;

        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, linear_tab.class);
                startActivity(intent);
            }
        });
    }
}
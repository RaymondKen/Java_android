package com.todolist.chronos.activity_example;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.todolist.chronos.activity_example.db.TaskDbHelper;

import java.util.ArrayList;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;
    private static final String TAG = "Main activity";
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private int amount = 0;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new TaskDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.todo);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(taskContract.TaskEntry.TABLE,
                new String[]{taskContract.TaskEntry._ID, taskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while(cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(taskContract.TaskEntry.COL_TASK_TITLE);
            Log.d(TAG, "Task: " + cursor.getString(idx));
        }
        cursor.close();
        db.close();
        updateUI();
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);



        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Log.d(TAG, "Premier");
                createNewTask();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Log.d(TAG, "Deuxieme");

            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Log.d(TAG, "Troisieme");
                Intent intent = new Intent(context, information_activity.class);
                startActivity(intent);
            }
        });

        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Log.d(TAG, "Quatriememe");
                Intent intent = new Intent(context, linear_tab.class);
                startActivity(intent);

            }
        });
    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(taskContract.TaskEntry.TABLE,
                new String[]{taskContract.TaskEntry._ID, taskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(taskContract.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_block,
                    R.id.task_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
        System.out.println(amount);
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(taskContract.TaskEntry.TABLE,
                taskContract.TaskEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        updateUI();
        amount -= 1;
    }

    public void validTask(View view)
    {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(taskContract.TaskEntry.TABLE,
                taskContract.TaskEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        updateUI();
        amount += 1;
    }

    public boolean createNewTask() {
        final EditText boxEdit = new EditText(this);
        final DatePicker dtp =  new DatePicker(this);
        mHelper = new TaskDbHelper(this);
                Log.d(TAG, "Add a new task");
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(boxEdit)
                        //.setView(R.layout.task_order)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(boxEdit.getText());

                                Log.d(TAG, "Task to add: " + task);
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(taskContract.TaskEntry.COL_TASK_TITLE, task);
                                db.insertWithOnConflict(taskContract.TaskEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;
    }

    public void openNewActivity() {
        final Context context = this;
        TextView editText;

        editText= (TextView)findViewById(R.id.task_title);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, linear_tab.class);
                startActivity(intent);
            }
        });
    }

}


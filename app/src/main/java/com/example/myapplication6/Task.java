package com.example.myapplication6;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class Task extends AppCompatActivity {
    ListView listView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    private TaskDatabaseHelper todoListSQLHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }
        setContentView(R.layout.activity_task);
        listView = findViewById(R.id.list);
        updateTodoList();
        //showTimePickerDialog(new View(getApplicationContext()));


        // Restore the saved time (if available)


        // Restore the saved time (if available)


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_task) {
            AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
            todoTaskBuilder.setTitle(R.string.add_item);
            todoTaskBuilder.setMessage(R.string.describe_the_todo_task);
            final EditText todoET = new EditText(this);
            todoTaskBuilder.setView(todoET);
            todoTaskBuilder.setPositiveButton(R.string.add_taskD, (dialogInterface, i) -> {
                String todoTaskInput = todoET.getText().toString();
                //showTimePickerDialog(this.getCurrentFocus());
                //todoTaskInput=todoTaskInput+showTimePickerDialog(this.getCurrentFocus()).getText().toString();
                //showTimePickerDialog(this.getCurrentFocus());
                todoListSQLHelper = new TaskDatabaseHelper(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.clear();

                //write the Todo task input into database table
                values.put(TaskDatabaseHelper.COL1_TASK, todoTaskInput);
                sqLiteDatabase.insertWithOnConflict(TaskDatabaseHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                //update the Todo task list UI
                updateTodoList();
                //showTimePickerDialog(this.getCurrentFocus());
            });

            todoTaskBuilder.setNegativeButton(R.string.cancelneg, null);

            todoTaskBuilder.create().show();
            return true;

        }
        return false;

    }

    //update the todo task list UI
    private void updateTodoList() {
        todoListSQLHelper = new TaskDatabaseHelper(Task.this);
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TaskDatabaseHelper.TABLE_NAME,
                new String[]{TaskDatabaseHelper._ID, TaskDatabaseHelper.COL1_TASK},
                null, null, null, null, null);

        //binds the todo task list with the UI
        ListAdapter todoListAdapter = new SimpleCursorAdapter(
                this,
                R.layout.todo_task,
                cursor,
                new String[]{TaskDatabaseHelper.COL1_TASK},
                new int[]{R.id.todoTaskTV},

                0
        );
        //showTimePickerDialog(this.getCurrentFocus()).getText();


        listView.setAdapter(todoListAdapter);
    }

    //closing the todo task item
    public void showDialogOnClk(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(R.string.Confirm);
        builder.setMessage(R.string.sure);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.Yes, (dialogInterface, i) -> onDoneButtonClick(view));
        builder.setNegativeButton(R.string.no, (dialogInterface, i) -> {


        });
        builder.show();
    }
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = v.findViewById(R.id.todoTaskTV);
        String todoTaskItem = todoTV.getText().toString();

        String deleteTodoItemSql = "DELETE FROM " + TaskDatabaseHelper.TABLE_NAME +
                " WHERE " + TaskDatabaseHelper.COL1_TASK + " = '" + todoTaskItem + "'";

        todoListSQLHelper = new TaskDatabaseHelper(Task.this);
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateTodoList();

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(getApplicationContext(), NavigationDrawer.class);
        startActivity(i);
        finish();
    }

    private boolean loadTheme() {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }

    public TextView showTimePickerDialog(View view) {
        View v = (View) view.getParent();
        TextView tv = v.findViewById(R.id.txtViewTime);
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Handle the selected time (e.g., update the EditText)
                        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        tv.setText(formattedTime);


                    }
                    },
                hour,
                minute,
                true // Set to true if you want 24-hour format
        );

        timePickerDialog.show();
        //tv.setVisibility(View.INVISIBLE);

        return  tv;
    }
}
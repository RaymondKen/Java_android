package com.todolist.chronos.activity_example;

import android.provider.BaseColumns;

/**
 * Created by Chronos on 24/01/2017.
 */

public class taskContract {
    public static final String DB_NAME = "Todolist New";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {

        public static final String TABLE = "tasks";
        public static final String OPTION ="options";
        public static final String COL_TASK_TITLE = "title";
    }
}

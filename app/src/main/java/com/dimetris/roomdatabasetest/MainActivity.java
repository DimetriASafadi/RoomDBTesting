package com.dimetris.roomdatabasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dimetris.roomdatabasetest.Models.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button PressToAdd;
    TextView ListCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PressToAdd = findViewById(R.id.PressToAdd);
        ListCount = findViewById(R.id.ListCount);


        PressToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        ListCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTasks();
            }
        });

    }
    private void saveTask() {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTask("تم");
                task.setDesc("تم جداا");
                task.setFinishBy("تمام");
                task.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getTasks();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                ListCount.setText(tasks.size()+"");
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
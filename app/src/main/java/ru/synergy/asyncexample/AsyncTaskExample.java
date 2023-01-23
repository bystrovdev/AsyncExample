package ru.synergy.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class AsyncTaskExample extends AppCompatActivity {


    private TextView mInfoTextView;  //  Создаем переменные
    private ProgressBar mProgressBar;
    private Button mStartButton;
    private ProgressBar mHorizontalProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        mInfoTextView = (TextView) findViewById(R.id.text_info);  // Находим компоненты UI
        mStartButton = (Button) findViewById(R.id.button_start);
        mHorizontalProgressBar = findViewById(R.id.progressBar);
    }

    public void onClick(View v) {  // Кнопка
        CurierTask curierTask = new CurierTask();
        curierTask.execute();
    }

    class CurierTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            mInfoTextView.setText("Доставщик зашел в ваш дом"); // Выводит сообщение на экран
            mStartButton.setVisibility(View.INVISIBLE);  // Убирает кнопку после нажатия
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mInfoTextView.setText("Этаж " + values[0]); // Печатает на экран этаж
            mHorizontalProgressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                int counter = 0;
                for(int i = 0; i < 14; i++) {
                    getFloor(counter);
                   publishProgress(++counter);  // Опубликовать прогресс(этажи)
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mInfoTextView.setText("Звонок в дверь, заберите вашу пиццу)");
            mStartButton.setVisibility(View.VISIBLE);
            mHorizontalProgressBar.setProgress(0);
        }

        private void getFloor(int counter) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }


}


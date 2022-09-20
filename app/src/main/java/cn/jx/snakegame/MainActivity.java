package cn.jx.snakegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TcsScoreListener {
    private TextView tv_score;
    private TextView tv_time;
    private TcsView tcs_22;
    private MyDatabase database;
    private int score;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        database = new MyDatabase(this, "Ranking.db", null, 1);//创建数据库数据表
        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tcs_22 = (TcsView) findViewById(R.id.tcs_22);

        tcs_22.setTcsScoreListener(this);
        tcs_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcs_22.invalidate();
            }
        });
    }

    @Override
    public void onTCSScore(int score) {
        tv_score.setText(score + "分");
        this.score = score;
    }

    @Override
    public void onTime(int time) {
        tv_time.setText((time / 4) + "秒");
        this.time = time;
    }

    @Override
    public void onGameOver() {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();//获取写入对象
        ContentValues values = new ContentValues();
        values.put("score",score);
        values.put("duration",time);
        writableDatabase.insert("Rank",null,values);
        overDialog();
    }

    private void overDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Game Over!")
                .setCancelable(false)
                .setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tcs_22.restart();
                    }
                })
                .setNegativeButton("Back to home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    public void back(View view) {
        finish();
    }
}
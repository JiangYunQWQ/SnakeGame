package cn.jx.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排行榜
 */
public class Ranking extends AppCompatActivity {
    private MyDatabase database;
    private List<DataModel> list = new ArrayList<>();
    private TextView rank,score,duration;
    private int top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        database = new MyDatabase(this, "Ranking.db", null, 1);
        getData();//获取数据
        Collections.sort(list);//排序
        rank=findViewById(R.id.rank);
        score=findViewById(R.id.score);
        duration=findViewById(R.id.duration);
        top=1;
        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        StringBuilder sb3=new StringBuilder();
        for(DataModel model:list){
            sb1.append(top++);
            sb2.append(model.getScore()+"分");
            sb3.append(model.getTime()+"秒");
            sb1.append("\n");
            sb2.append("\n");
            sb3.append("\n");
        }
        rank.setText(sb1.toString());
        score.setText(sb2.toString());
        duration.setText(sb3.toString());
    }

    private void getData() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query("Rank", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //遍历Cursor对象，取出数据并打印
                int score = cursor.getInt(cursor.getColumnIndex("score"));
                int time = cursor.getInt(cursor.getColumnIndex("duration"));
                DataModel model = new DataModel(score, time);
                list.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void back(View view) {
        finish();
    }
}
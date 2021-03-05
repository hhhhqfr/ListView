package com.example.listviewtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import com.example.talk.TalkActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {
    @BindView(R.id.list_view)
    ListView listView;

    private ArrayList<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFruit();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        listView.setAdapter(adapter);

    }

    private void initFruit() {
        for (int i = 0; i < 10; i++) {


        Fruit apple = new Fruit("apple", R.drawable.ic_launcher_background);
        fruitList.add(apple);
        Fruit banana = new Fruit("banana", R.drawable.ic_launcher_foreground);
        fruitList.add(banana);
        }
    }

    @OnItemClick( R.id.list_view)
    public void onItemClick(int position) {
        Fruit fruit = fruitList.get(position);
        String a="banana";
        String fn=fruit.getName();
        if (fn==a){
            Intent intent=new Intent(MainActivity.this, TalkActivity.class);
            startActivity(intent);


        }else {
            Toast.makeText(MainActivity.this, fn, Toast.LENGTH_SHORT).show();
        }
    }



}

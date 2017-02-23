package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private final static List<MenuItemBean> list1 = new ArrayList<MenuItemBean>(){
        {
            add(new MenuItemBean("Delete" , Color.parseColor("#FF0000")));
            add(new MenuItemBean("Order" , Color.parseColor("#0000ff")));
            add(new MenuItemBean("Test" , Color.parseColor("#0000ff")));
            add(new MenuItemBean("test2" , Color.parseColor("#0000ff")));
        }
    };


    private final static List<MenuItemBean> list2 = new ArrayList<MenuItemBean>(){
        {
            add(new MenuItemBean("Delete" , Color.parseColor("#0000ff")));
            add(new MenuItemBean("Order" , Color.parseColor("#0000ff")));
            add(new MenuItemBean("Log" , Color.parseColor("#0000ff")));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView test1 = (TextView)findViewById(R.id.test1);
        TextView test2 = (TextView)findViewById(R.id.test2);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopHelper.showPopulWindow(MainActivity.this, list1, new ChooicePopDismissListener() {
                    @Override
                    public void chooseItem(int position) {
                        Toast.makeText(MainActivity.this , "you click -- "+list1.get(position).getMenuString() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void doAfterDismiss() {
                        Toast.makeText(MainActivity.this , "Pop 消失了 " , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopHelper.showPopulWindow(MainActivity.this, list2, new ChooicePopDismissListener() {
                    @Override
                    public void chooseItem(int position) {
                        Toast.makeText(MainActivity.this , "you click -- "+list2.get(position).getMenuString() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void doAfterDismiss() {
                        Toast.makeText(MainActivity.this , "Pop 消失了 " , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

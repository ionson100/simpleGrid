package com.example.user.simplegrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import grid.DataGridView;
import grid.IDataGridEvents;

public class MainActivity extends AppCompatActivity implements IDataGridEvents {
    DataGridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (DataGridView) this.findViewById(R.id.grid1);
        List<Test1> test1s=new ArrayList<>();
        {
            Test1 t=new Test1();
            t.id=1;
            t.isHiden=true;
            t.name = "ssssss";
            test1s.add(t);
        }
        {
            Test1 t=new Test1();
            t.id=2;
            t.isHiden=false;
            t.name = "ddd";
            test1s.add(t);
        }
        for (int i=0;i<100;i++){
            {
                Test1 t=new Test1();
                t.id=3+i;
                t.isHiden=true;
                t.name = "ggg" + i;
                test1s.add(t);
            }
        }
        grid.setDataSource(test1s);
    }

    @Override
    public void onRowClick(TableRow v) {

        List<Object> list = grid.getSelectObjectsList();
        if (list.size() <= 0) return;
        Test1 tt = (Test1) list.get(0);

        Toast.makeText(this, tt.name, 2).show();
    }

    @Override
    public void onCheckBoxClick(CheckBox checkBox, Object o) {
    }

    @Override
    public void onCellClick(TextView view) {
    }

    @Override
    public void onSortableClick(TextView view) {
    }
}



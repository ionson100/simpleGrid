package com.example.user.simplegrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grid.ColumnTable;
import grid.DataGridView;
import grid.IDataGridEvents;

public class MainActivity extends AppCompatActivity implements IDataGridEvents {

    DataGridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (DataGridView) this.findViewById(R.id.grid1);
        grid.setIDataGridEvents(this);
        List<Test1> test1s=new ArrayList<>();
        {
            Test1 t=new Test1();
            t.id=1;
            t.isHiden=true;
            t.name="tsaewewes";
            test1s.add(t);
        }

        {
            Test1 t=new Test1();
            t.id=2;
            t.isHiden=false;
            t.name="rrfsas";
            test1s.add(t);
        }
        for (int i=0;i<100;i++){
            {
                Test1 t=new Test1();
                t.id=3+i;
                t.isHiden=true;
                t.name="aaaaaaaaa"+i;
                test1s.add(t);
            }
        }
        grid.setDataSource(test1s);
    }

    @Override
    public void onRowClick(TableRow v) {
        int dd=4;
    }

    @Override
    public void onCheckBoxClick(CheckBox checkBox, Object o) {

        boolean ddd=checkBox.isChecked();
        int dd=4;
    }

    @Override
    public void onCellClick(TextView view) {
        int dd=4;
    }

    @Override
    public void onSortableClick(TextView view) {
        int dd=4;
    }
}



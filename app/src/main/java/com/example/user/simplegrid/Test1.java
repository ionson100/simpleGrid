package com.example.user.simplegrid;

import android.graphics.Color;
import android.view.Gravity;

import grid.ColumnTable;
import grid.SelectMode;
import grid.SettingsTable;

@SettingsTable(
        backgroundColorRowHeader = Color.BLACK,
        textColorRowHeader = Color.WHITE,
        SELECT_MODE = SelectMode.SingleRow,
        isSortable = true,
        colorTextSelectRows = Color.WHITE,
        colorRowNegative = Color.WHITE,
        colorRowPozitive = Color.CYAN,
        sizeGrid = 1,
        isShowGrid = true)
public class Test1 {
    @ColumnTable(name = R.string.name1, index = 0, width =20,gravityHeader = Gravity.CENTER,leftPaddingItems = 100)
    public String name;
    @ColumnTable(
            name = R.string.name3,
            index = 2,
            width = 22,
            gravityHeader = Gravity.CENTER,
            stringFormat = "%.2f",
            styleData = R.style.boldText,
            styleHeader = R.style.boldText
    )
    public int id;
    @ColumnTable(name = R.string.name4, index = 3, width = 10, gravityHeader = Gravity.CENTER, gravityItems = Gravity.CENTER)
    public boolean isHiden;

    @ColumnTable(
            name = R.string.zero,
            isImage = true,
            index = -2,
            width =20,
            gravityHeader = Gravity.CENTER,
            gravityItems = Gravity.CENTER
    )
    public int asaaImage(){
        return R.drawable.abc_spinner_mtrl_am_alpha;
    }

    @ColumnTable(name = R.string.name1_, index = -1, width =20,gravityHeader = Gravity.CENTER,gravityItems = Gravity.RIGHT,rightsPaddingItems = 20)
    public int asaa(){
        return 2343333;
    }

    @ColumnTable(name = R.string.name2, index = 1, width =10,gravityHeader = Gravity.CENTER )
    public String getName(){
        return "name";
    }
}

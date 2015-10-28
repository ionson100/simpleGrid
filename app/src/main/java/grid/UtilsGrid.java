package grid;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by USER on 21.10.2015.
 */
public class UtilsGrid {
    static String fchar = String.valueOf(((char) 160));

    /**
     * Получение заголовка таблици
     * @param aClass тип данных для таблици
     * @param table таблица данных
     * @param context сотнекст выполенния кода
     * @param isVisible указывает видимая или не видимая строка заголовка
     * @param width ширина таблици
     * @param backgroundGrid  подложка для строки заголовка, ее цвет определяет цвет сетки
     * @param settingsTable настройки таблици, общие
     * @param iDataGridEvents объект кторый реализует интерфейс IDataGridView ( события манипуляций с таблицей)
     * @param selectMode тип выбора строки
     * @param selectObjects лист объектов полученых в результате выбора из таблици
     * @return Строка заголовка таблици
     */

    public static TableRow getHeaderRow(Class aClass,
                                        final TableLayout table,
                                        Context context,
                                        boolean isVisible,
                                        int width,
                                        View backgroundGrid,
                                        final SettingsTable settingsTable,
                                        final IDataGridEvents iDataGridEvents,
                                        final SelectMode selectMode,
                                        final List<Object> selectObjects){

        final List<ColumnData> columnDatas=CacheData.getColumnData(aClass);

        Collections.sort(columnDatas, new Comparator<ColumnData>() {

            @Override
            public int compare(ColumnData o1, ColumnData o2) {
                if(o1==null||o2==null) return 0;
                if(o1.columnTable.index() <  o2.columnTable.index()) return -1;
                if(o1.columnTable.index() == o2.columnTable.index()) return 0;
                return 1;
            }
        });

        int backgroundGr=Color.GREEN;
        int foreColor=Color.WHITE;
        int backColor=Color.BLACK;
        boolean isSortable=false;
        boolean isShowGrid=true;
        int hrow=50;
        int sizeGrid=2;

        if(settingsTable !=null){
            isSortable= settingsTable.isSortable();
            foreColor = settingsTable.textColorRowHeader();
            backColor = settingsTable.backgroundColorRowHeader();
            hrow= settingsTable.rowHeightHeader();
            backgroundGr= settingsTable.colorGridHeader();
            isShowGrid= settingsTable.isShowGrid();
            sizeGrid=settingsTable.sizeGrid();
        }
        backgroundGrid.setBackgroundColor(backgroundGr);

        TableRow row=new TableRow(context);
        int i=-1;
        for (final ColumnData columnData : columnDatas) {
            if(columnData.getType()==null) continue;
            i++;

            TextView tv=new TextView(context);
            tv.setTag(i);
            tv.setText(columnData.columnTable.name());

            int dd=  width/100*columnData.columnTable.width();
           // tv.setWidth(dd);
            tv.setGravity(columnData.columnTable.gravityHeader());
            tv.setPadding(columnData.columnTable.leftPaddingHeader(), 0, columnData.columnTable.rightsPaddingHeader(), 0);
            tv.setTextColor(foreColor);
            tv.setBackgroundColor(backColor);
            TableRow.LayoutParams params=null;

            if(isVisible==false){
                params = new TableRow.LayoutParams(dd,0);
            }else{
                 params = new TableRow.LayoutParams(dd,hrow);
            }
            if(isShowGrid){
                params.setMargins(0, 0,sizeGrid, 0);
            }

            tv.setLayoutParams(params);

            final boolean finalIsSortable = isSortable;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(finalIsSortable ==true && (table != null)){

                        for (ColumnData data : columnDatas) {
                            if(!data.equals(columnData)){
                                data.sortablePosition=0;
                            }
                        }

                        Sortable.SortableCore(table,columnData,(int)v.getTag());
                        UtilsGrid.PaintGrid(settingsTable, selectMode,table,selectObjects,null );
                        if(iDataGridEvents!=null)
                        iDataGridEvents.onSortableClick((TextView) v);

                    }

                }
            });

            if(columnData.columnTable.styleHeader()!=0){
                tv.setTextAppearance(context, columnData.columnTable.styleHeader());
            }

            row.addView(tv);
        }
        return row;
    }

    /**
     * Получение строки данных на основе оъекта, сам объект лежит в getTag строки.
     * @param o Объект, на его основе получаем строку данных
     * @param table таблица для строк данных
     * @param context контекст выполнения
     * @param width ширина таблици
     * @param backgroundGrid подлжка, определяет цвет сетки таблици
     * @param settingsTable настройки таблици
     * @param iDataGridEvents  объект кторый реализует интерфейс IDataGridView ( события манипуляций с таблицей)
     * @param selectMode тип выбора строк таблици
     * @param selectObjects выбранные объекты
     * @param selectRows выбранные строки
     * @return строка данных для таблици
     */
    public static TableRow getDataRow(final Object o,
                                      final TableLayout table,
                                      Context context,
                                      int width,
                                      View backgroundGrid,
                                      final SettingsTable settingsTable,
                                      final IDataGridEvents iDataGridEvents,
                                      final SelectMode selectMode, final List<Object> selectObjects, final List<TableRow> selectRows){
        final View[] selectCell = new View[1];
        final TableRow row =new TableRow(context);
        Class aClass=o.getClass();
        List<ColumnData> columnDatas=CacheData.getColumnData(aClass);

        Collections.sort(columnDatas, new Comparator<ColumnData>() {

            @Override
            public int compare(ColumnData o1, ColumnData o2) {
                if (o1 == null || o2 == null) return 0;
                if (o1.columnTable.index() < o2.columnTable.index()) return -1;
                if (o1.columnTable.index() == o2.columnTable.index()) return 0;
                return 1;
            }
        });


        int backgroundGr=Color.BLACK;

        int hrow=50;
        boolean isShowGrid=true;
        int sizeGrid=2;


        if(settingsTable !=null){

            sizeGrid=settingsTable.sizeGrid();
            isShowGrid= settingsTable.isShowGrid();
            hrow= settingsTable.rowHeightData();
            backgroundGr= settingsTable.colorGridData();

        }
        backgroundGrid.setBackgroundColor(backgroundGr);



        for (ColumnData cd : columnDatas) {

            Object value=null;
            if(cd.currentField==null&&cd.currentMethod==null) continue;

            if(cd.currentField!=null){
                try {
                    value=cd.currentField.get(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(cd.currentMethod!=null){
                try {
                    value=cd.currentMethod.invoke(o,null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            if(cd.getType()==boolean.class||cd.getType()==Boolean.class){
                final CheckBox b = new CheckBox(context);
                b.setGravity(Gravity.CENTER_VERTICAL | cd.columnTable.gravityItems());
                b.setChecked((boolean) value);
                b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if(iDataGridEvents!=null){
                                                         iDataGridEvents.onCheckBoxClick(b,o);
                                                     }
                                                 }
                                             }
                );

                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                params.gravity = cd.columnTable.gravityItems();
                b.setLayoutParams(params);

                LinearLayout tv=new LinearLayout(context);
                tv.addView(b);

                int dd=  width/100*cd.columnTable.width();
                TableRow.LayoutParams params1 = new TableRow.LayoutParams(dd,hrow);
                if(isShowGrid){
                    params1.setMargins(0, sizeGrid, sizeGrid, 0);
                }

                tv.setLayoutParams(params1);
                tv.setBackgroundColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER_VERTICAL | cd.columnTable.gravityItems());
                row.addView(tv);


            }
            else{

                TextView tv=new TextView(context);
                tv.setGravity(Gravity.CENTER_VERTICAL|cd.columnTable.gravityItems());
                tv.setPadding(cd.columnTable.leftPaddingItems(), 0, cd.columnTable.rightsPaddingItems(), 0);
                int dd=  width/100*cd.columnTable.width();
                TableRow.LayoutParams params = new TableRow.LayoutParams(dd,hrow);
                if(isShowGrid){
                    params.setMargins(0, sizeGrid, sizeGrid, 0);
                }
                tv.setLayoutParams(params);
                if(cd.columnTable.isImage()==true&&cd.getType()==int.class){
                    tv.setCompoundDrawablesWithIntrinsicBounds( (Integer) value, 0, 0, 0);

                }else if((cd.getType()==float.class||cd.getType()==double.class)&&!cd.columnTable.decimalFormat().equals("")){
                    DecimalFormat df = new DecimalFormat(cd.columnTable.decimalFormat());
                    String ss=df.format(value);
                    tv.setText(ss);

                }else if((cd.getType()==float.class||cd.getType()==double.class)&&!cd.columnTable.stringFormat().equals("")){
                    String str=String.format(cd.columnTable.stringFormat(), value);
                    tv.setText(str);
                } else{
                    tv.setText(String.valueOf(value));
                }


                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        selectCell[0] = null;
                        if (selectMode == SelectMode.SingleRow || selectMode == SelectMode.Cell) {
                            selectObjects.clear();
                            selectRows.clear();
                            selectObjects.add(o);
                            selectRows.add(row);
                        }
                        if (selectMode == SelectMode.MultiRows) {
                            if (!selectObjects.contains(o)) {
                                selectObjects.add(o);
                                selectRows.add(row);
                            } else {
                                selectObjects.remove(o);
                                selectRows.remove(row);
                            }
                        }
                        selectCell[0] = v;

                        if (iDataGridEvents != null) {
                            iDataGridEvents.onCellClick((TextView) v);
                            iDataGridEvents.onRowClick(row);
                        }

                        PaintGrid(settingsTable, selectMode, table, selectObjects,selectCell[0]);
                    }


                });

                if (cd.columnTable.styleData()!=0){
                    tv.setTextAppearance(context, cd.columnTable.styleData());
                }
                if (cd.columnTable.styleData() != 0) {
                    tv.setTextAppearance(context, cd.columnTable.styleData());
                }
                row.addView(tv);

            }

        }
        row.setTag(o);


        return row;

    }

    /**
     * Раскраска таблици
     * @param settingsTable настройки таблици
     * @param selectMode тип выбора строк
     * @param table раскрашиваемая таблица
     * @param selectObject выбранные объекты
     * @param selectView  Выбранный Мшуц, в роли Cell
     */
   public static void PaintGrid(SettingsTable settingsTable,SelectMode selectMode,TableLayout table,List<Object> selectObject,View selectView){
        int colorNegative=Color.WHITE;
        int colorPozitive=Color.WHITE;
       int backgrountSelectColor=Color.RED;
       int colorSelectText=Color.BLACK;
       int colorText=Color.BLACK;
        if(settingsTable !=null){
            colorNegative= settingsTable.colorRowNegative();
            colorPozitive= settingsTable.colorRowPozitive();
            selectMode= settingsTable.SELECT_MODE();
            backgrountSelectColor= settingsTable.colorBackgrountSelectRows();
            colorSelectText= settingsTable.colorTextSelectRows();
            colorText= settingsTable.colorTextDataRows();
        }
        for (int i=0;i<table.getChildCount();i++){
            TableRow row= (TableRow) table.getChildAt(i);
            Object o=row.getTag();
            if(o==null) continue;
            if(i%2==0){
                setColor(row, colorNegative,colorText);
            }else{
                setColor(row, colorPozitive, colorText);
            }
        }
       if(selectObject!=null&&selectObject.size()>0){
           for (int i=0;i<table.getChildCount();i++) {
               TableRow row = (TableRow) table.getChildAt(i);
               Object o = row.getTag();
               if (o == null) continue;

               if(selectObject.contains(o)){

                   if(selectMode!=SelectMode.Cell){
                       setColorSelect(row, backgrountSelectColor,colorSelectText);
                   }else{
                       setColorSelectCell(row,backgrountSelectColor,colorSelectText,selectView);
                   }
               }
           }
       }

    }

    /**
     * Раскраска выбранной ячейки
     * @param row выбранная строка
     * @param colorBack цвет фона элемента
     * @param colorText цвет текста
     * @param selectView выбранный элемент
     */
    private static void setColorSelectCell(TableRow row, int colorBack,int colorText, View selectView) {

        for (int i=0;i<row.getChildCount();i++){
            View v=row.getChildAt(i);
           if(v.equals(selectView)){
               v.setBackgroundColor(colorBack);
               if(v.getClass()==TextView.class){
                   ((TextView)v).setTextColor(colorText);
               }

           }
        }

    }

    /**
     * Раскраска выбранной строки
     * @param row строка таблици
     * @param colorBack цвет фона строки
     * @param colorText цвет текста выбранной строки
     */

    private static void setColorSelect(TableRow row, int colorBack,int colorText) {
        for (int i=0;i<row.getChildCount();i++){
            View v=row.getChildAt(i);
            v.setBackgroundColor(colorBack);
            if(v.getClass()==TextView.class){
                ((TextView)v).setTextColor(colorText);
            }

        }
    }

    /**
     * Раскраска строй в режиме покоя
     * @param row строка
     * @param colorBack цвет фона
     * @param colorText цвет текста
     */
    static private void setColor(TableRow row, int colorBack,int colorText) {

            for (int i=0;i<row.getChildCount();i++){
                View v=row.getChildAt(i);
                v.setBackgroundColor(colorBack);
                if(v.getClass()==TextView.class){
                    ((TextView)v).setTextColor(colorText);
                }

            }

    }

    public static int integerTryParse(String str){
        int res=0;
        if(str.trim().length()>0){
            try
            {
                res=  Integer.parseInt(str);
            }catch (Exception ex){

            }
        }
        return res;
    }

    public static double doubleTryParse(String str){
        double res=0;
        if(str.trim().length()>0){
            try
            {
                String str2=str.replace(",", ".");
                String str3=str2.replace(fchar,"");
                res=  Double.parseDouble(str3);
            }catch (Exception ex){

            }
        }
        return res;
    }
    public static double floatTryParse(String str){
        double res=0;
        if(str.trim().length()>0){
            try
            {
                String str2=str.replace(",", ".");
                String str3=str2.replace(fchar,"");
                res=  Float.parseFloat(str3);
            }catch (Exception ex){

            }
        }
        return res;
    }

}

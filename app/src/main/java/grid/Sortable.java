package grid;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by USER on 22.10.2015.
 */
public class Sortable {
    /**
     * Сщетировка таблици
     * @param table таблица с данными
     * @param columnData все данные по столбцу таблици,
     * @param index индекс итема по которому щелкнули ( заголовок таблици)
     */
    public static void SortableCore(TableLayout table,ColumnData columnData, final int index){

        if(columnData.currentMethod==null&&columnData.currentField==null) return;
        Type selType=null;
        if(columnData.currentField!=null){
            selType=columnData.currentField.getType();
        }
        if(columnData.currentMethod!=null){
            selType=columnData.currentMethod.getReturnType();
        }
        if(selType==null||selType==boolean.class||selType==Boolean.class){
            return;
        }

        List<TableRow> tableRows=new ArrayList<>();
        for (int i=1;i<table.getChildCount();i++){
            TableRow row= (TableRow) table.getChildAt(i);
            tableRows.add(row);
        }
        if(tableRows.size()==0) return;
        table.removeViews(1, tableRows.size());

        final Type finalSelType = selType;



        if(columnData.sortablePosition==0){
            Sortable_0_3(index, tableRows, finalSelType);
            columnData.sortablePosition=1;
        }else{
            Sortable_1_3(index, tableRows, finalSelType);
            columnData.sortablePosition=0;

        }


        for (TableRow tableRow : tableRows) {
                table.addView(tableRow);
        }

    }

    private static void Sortable_1_3(final int index, List<TableRow> tableRows, final Type finalSelType) {
        Collections.sort(tableRows, new Comparator<TableRow>() {
            @Override
            public int compare(TableRow t2, TableRow t1) {
                if (t1 == null || t2 == null) return 0;
                if (t1.equals(t2)) return 0;
                if (finalSelType == String.class) {
                    String o1 = ((TextView) t1.getChildAt(index)).getText().toString();
                    String o2 = ((TextView) t2.getChildAt(index)).getText().toString();
                    return (o1.compareTo(o2));
                }
                if (finalSelType == int.class || finalSelType == long.class || finalSelType == short.class || finalSelType == byte.class) {

                    int o1 = UtilsGrid.integerTryParse(((TextView) t1.getChildAt(index)).getText().toString());
                    int o2 = UtilsGrid.integerTryParse(((TextView) t2.getChildAt(index)).getText().toString());
                    if (o1 < o2) return -1;
                    if (o1 == o2) return 0;
                    return 1;
                }
                if (finalSelType == double.class) {
                    double o1 = UtilsGrid.doubleTryParse(((TextView) t1.getChildAt(index)).getText().toString());
                    double o2 = UtilsGrid.doubleTryParse(((TextView) t2.getChildAt(index)).getText().toString());
                    if (o1 < o2) return -1;
                    if (o1 == o2) return 0;
                    return 1;
                }
                if (finalSelType == float.class) {
                    double o1 = UtilsGrid.floatTryParse(((TextView) t1.getChildAt(index)).getText().toString());
                    double o2 = UtilsGrid.floatTryParse(((TextView) t2.getChildAt(index)).getText().toString());
                    if (o1 < o2) return -1;
                    if (o1 == o2) return 0;
                    return 1;
                }
                return 0;
            }
        });
    }

    private static void Sortable_0_3(final int index, List<TableRow> tableRows, final Type finalSelType) {
        Collections.sort(tableRows, new Comparator<TableRow>() {
            @Override
            public int compare(TableRow t1, TableRow t2) {
                if (t1 == null || t2 == null) return 0;
                if (t1.equals(t2)) return 0;
                if (finalSelType == String.class) {
                    String o1 = ((TextView) t1.getChildAt(index)).getText().toString();
                    String o2 = ((TextView) t2.getChildAt(index)).getText().toString();
                    return (o1.compareTo(o2));
                }
                if (finalSelType == int.class || finalSelType == long.class || finalSelType == short.class || finalSelType == byte.class) {

                    int o1 = UtilsGrid.integerTryParse(((TextView) t1.getChildAt(index)).getText().toString());
                    int o2 = UtilsGrid.integerTryParse(((TextView) t2.getChildAt(index)).getText().toString());
                    if (o1 < o2) return -1;
                    if (o1 == o2) return 0;
                    return 1;
                }
                if (finalSelType == double.class) {
                    double o1 = UtilsGrid.doubleTryParse(((TextView) t1.getChildAt(index)).getText().toString());
                    double o2 = UtilsGrid.doubleTryParse(((TextView) t2.getChildAt(index)).getText().toString());
                    if (o1 < o2) return -1;
                    if (o1 == o2) return 0;
                    return 1;
                }
                if (finalSelType == float.class) {
                    double o1 = UtilsGrid.floatTryParse(((TextView) t1.getChildAt(index)).getText().toString());
                    double o2 = UtilsGrid.floatTryParse(((TextView) t2.getChildAt(index)).getText().toString());
                    if (o1 < o2) return -1;
                    if (o1 == o2) return 0;
                    return 1;
                }
                return 0;
            }
        });
    }


}

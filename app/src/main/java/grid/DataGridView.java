package grid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;


public class DataGridView<T> extends LinearLayout {

    private static Map<String,Observer> observerMap=new HashMap<>();

    private SelectMode selectMode;
    private int widthGrid;




    private IDataGridEvents iDataGridEvents;
    private List<T> selectObjectList=new ArrayList<>();
    private List<TableRow> selectRowsList=new ArrayList<>();
    private Context context;
    private TableLayout tableLayoutHeader;
    TableLayout tableLayoutCore;

    private Observer observer;
    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    LinearLayout baseRow;
    TableRow baseRowData;
   private List<T> dataSource;

    public DataGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        horizontalScrollView=new HorizontalScrollView(this.context);
        LinearLayout baseLinearLayout=new LinearLayout(context);
        baseLinearLayout.setOrientation(VERTICAL);
        horizontalScrollView.addView(baseLinearLayout);

        baseRow=new LinearLayout(this.context);
        tableLayoutHeader=new TableLayout(context);
        baseRow.addView(tableLayoutHeader);

        baseLinearLayout.addView(baseRow);

        scrollView=new ScrollView(this.context);
        tableLayoutCore=new TableLayout(this.context);
        scrollView.addView(tableLayoutCore);
        baseLinearLayout.addView(scrollView);

        baseRow=new TableRow(this.context);
        this.addView(horizontalScrollView);



    }




    public DataGridView(Context context) {
        super(context);
        this.context=context;
    }

    public List<T> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<T> dataSource) {




        this.dataSource = dataSource;

            this.Activate(dataSource);


    }
    private void Activate(List<T> dataSource){





        if(selectMode==null){
            selectMode= SelectMode.SingleRow;
        }


            selectRowsList.clear();
            selectObjectList.clear();
            tableLayoutHeader.removeAllViews();
            tableLayoutCore.removeAllViews();


        if(dataSource==null||dataSource.size()==0){
            return;
        }











        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        if(widthGrid==0){
            widthGrid=width;
        }


        SettingsTable settingsTable = (SettingsTable) dataSource.get(0).getClass().getAnnotation(SettingsTable.class);
        if(settingsTable !=null){
            selectMode= settingsTable.SELECT_MODE();
        }


        TableRow headerRow1=UtilsGrid.getHeaderRow(
                dataSource.get(0).getClass(),
                tableLayoutCore,
                context,
                true,
                widthGrid,
                baseRow,
                settingsTable,
                iDataGridEvents,
                selectMode,
                (List<Object>) selectObjectList);
        tableLayoutHeader.addView(headerRow1);
        TableRow headerRow2=UtilsGrid.getHeaderRow(
                dataSource.get(0).getClass(),
                tableLayoutCore,
                context,
                false,
                widthGrid,
                baseRow,
                settingsTable,
                iDataGridEvents,
                selectMode,
                (List<Object>) selectObjectList);
        tableLayoutCore.addView(headerRow2);
        for (T t : dataSource) {
            TableRow rowData=UtilsGrid.getDataRow(
                    t,
                    tableLayoutCore,
                    context,
                    widthGrid,
                    scrollView,
                    settingsTable,
                    iDataGridEvents,
                    selectMode,
                    (List<Object>) selectObjectList,
                    selectRowsList);
            tableLayoutCore.addView(rowData);

        }
        UtilsGrid.PaintGrid(settingsTable,selectMode,tableLayoutCore,(List<Object>) selectObjectList,null);




    }



    public List<T> getSelectObjectsList() {
        return selectObjectList;
    }

    public List<TableRow> getSelectRowsList() {
        return selectRowsList;
    }

    public void setIDataGridEvents(IDataGridEvents iDataGridEvents) {
        this.iDataGridEvents=iDataGridEvents;
    }

    public SelectMode getSelectMode() {
        return selectMode;
    }

    public void setSelectMode(SelectMode selectMode) {
        this.selectMode = selectMode;
    }

    public int getWidthGrid() {
        return widthGrid;
    }

    public void setWidthGrid(int widthGrid) {
        this.widthGrid = widthGrid;
    }




    public Observer getObserver() {
        return observer;
    }
}


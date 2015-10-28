# Simple Grid for Android
======
**Usage grid.**

## Create class type:


```java
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
```

```java
@Target({ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
/**
 * @textColorRowHeader цвет текста строки заголовка
 *  @isSortable сортируется ли таблица ( ячейки которые содержат чекбокс или картинки - исключены из сортировки)
 *  @backgroundColorRowHeader цвет заливки строки заголовка
 *  @rowHeightHeader высота строки заголовка
 *  @rowHeightData высота строки данных
 *  @colorGridHeader цвет сетки строки заголовка таблици
 *  @colorGridData цвет сетки строки данных таблици
 *  @colorRowNegative цвет строки данных при расскраске зеброй
 *  @colorRowPozitive цвет строки данных при расскраске зеброй
 *  @SELECT_MODE тип выделения выбранной строки или ячейки
 *  @colorBackgrountSelectRows цвет заливки выбранной строки или ячейки
 *  @colorTextSelectRows цвет текста выбранной строки или ячейки
 *  @colorTextDataRows цвет текста строки данных
 *  @isShowGrid показывать ли сетку таблици
 *  @sizeGrid толщина сетки
 */
public @interface SettingsTable {
    int textColorRowHeader();
    boolean isSortable() default false;

    int backgroundColorRowHeader();
    int  rowHeightHeader() default 50;
    int  rowHeightData() default 50;
    int colorGridHeader() default Color.WHITE;
    int colorGridData() default Color.BLACK;
    int colorRowNegative() default Color.WHITE;
    int colorRowPozitive() default Color.WHITE;
    SelectMode SELECT_MODE() default SelectMode.SingleRow;
    int colorBackgrountSelectRows() default Color.RED;
    int colorTextSelectRows() default Color.BLACK;
    int colorTextDataRows() default Color.BLACK;
    boolean isShowGrid() default true;
    int sizeGrid()default 2;
}

```

```java
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
/**
 *@name название колонки таблици
 *@index очередность колонок в тадблице
 *@width ширина  колонки в процентах ко всей таблице
 *@gravityHeader гравтация элемента строки заголовка таблици
 *@gravityItems гравитация елемента данных таблици
 *@leftPaddingHeader левый отступ елемента строки заголовка
 *@leftPaddingItems левый отступ елемента строки данных
 *@rightsPaddingHeader правый отступ элемента строки заголовка
 *@rightsPaddingItems правый отступ элемента строки данных
 *@decimalFormat шаблон для DecimalFormat
 *@stringFormat шаблон для StringFormat
 *@styleHeader стиль для элемента заголовка
 *@styleData   стиль для элемента данных
 *@isImage в элементе находится картинка, тип элемента должен бвть int ( указатель на ресурс картинки)
 * styleHeader и styleData  перекрывают  настройки - кроме цветов строки.
 */
public @interface ColumnTable {
    int name();
    int index();
    int width();
    int  gravityHeader() default Gravity.LEFT;
    int  gravityItems() default Gravity.LEFT;
    int  leftPaddingHeader() default 0;
    int  leftPaddingItems() default 0;
    int  rightsPaddingHeader() default 0;
    int  rightsPaddingItems() default 0;
    String decimalFormat() default "";
    String stringFormat() default "";
    int styleHeader() default 0;
    int styleData() default 0;
    boolean isImage() default false;
}
```

**Resource view:**

```xml
   <grid.DataGridView
       android:id="@+id/grid1"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
   </grid.DataGridView>
```
**Initialization code:**

```java
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
            t.name="ssssss";
            test1s.add(t);
        }
        {
            Test1 t=new Test1();
            t.id=2;
            t.isHiden=false;
            t.name="ddd";
            test1s.add(t);
        }
        for (int i=0;i<100;i++){
            {
                Test1 t=new Test1();
                t.id=3+i;
                t.isHiden=true;
                t.name="ggg"+i;
                test1s.add(t);
            }
        }
        grid.setDataSource(test1s);
    }

    @Override
    public void onRowClick(TableRow v) {
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
```

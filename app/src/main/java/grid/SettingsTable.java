package grid;

import android.graphics.Color;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



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

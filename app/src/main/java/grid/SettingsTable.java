package grid;

import android.graphics.Color;
import android.view.Gravity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
/**
 *  @isSortable
 *  @backgroundColor
 *  @rowHeightHeader
 *  @rowHeightData
 *  @colorGridHeader
 *  @colorGridData
 *  @colorRowNegative
 *  @colorRowPozitive
 *  @SELECT_MODE
 *  @colorBackgrountSelectRows
 *  @colorTextSelectRows
 *  @colorTextDataRows
 *  @isShowGrid
 *  @sizeGrid
 */
public @interface SettingsTable {
    int foreColor();
    boolean isSortable() default false;
    int backgroundColor();
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

package grid;

import android.view.Gravity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


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

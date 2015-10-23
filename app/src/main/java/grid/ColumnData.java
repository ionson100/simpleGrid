package grid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by USER on 21.10.2015.
 */
public class ColumnData {

    public ColumnTable columnTable;
    public Field currentField;
    public Type getType(){
        if(currentField!=null){
            return currentField.getType();
        }
        if(currentMethod!=null){
            return currentMethod.getReturnType();
        }
        return null;
    }
    int sortablePosition=0;
    public Method currentMethod;
}

package grid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CacheData {
    private static Map<Class,List<ColumnData>> classColumnDataMap=new HashMap<>();

      public static List<ColumnData> getColumnData(Class aClass){
        if(classColumnDataMap.containsKey(aClass)){
            return classColumnDataMap.get(aClass);
        }else{
            classColumnDataMap.put(aClass,getListColumnDatas(aClass));
            return classColumnDataMap.get(aClass);
        }
    }


    private static List<ColumnData> getListColumnDatas(Class aClass){
      List<ColumnData> columnDatas=new ArrayList<>();




        for (Field field : aClass.getFields()) {
            ColumnTable dd= field.getAnnotation(ColumnTable.class);
            if(dd!=null){
                ColumnData cd=new ColumnData();
                cd.columnTable= dd;
                cd.currentField =field;

                columnDatas.add(cd);
            }

        }
        for (Method method : aClass.getMethods()) {
            ColumnTable dd= method.getAnnotation(ColumnTable.class);
            if(dd!=null){
                ColumnData cd=new ColumnData();
                cd.columnTable= dd;
                cd.currentMethod =method;

                columnDatas.add(cd);
            }
        }
        return  columnDatas;
    }
}



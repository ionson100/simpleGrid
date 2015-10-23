package grid;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by USER on 22.10.2015.
 */
public interface IDataGridEvents {
    void onRowClick(TableRow v);
    void onCheckBoxClick(CheckBox checkBox,Object o);
    void onCellClick(TextView view);
    void onSortableClick(TextView view);
}

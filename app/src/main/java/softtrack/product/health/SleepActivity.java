package softtrack.product.health;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SleepActivity extends AppCompatActivity {

    public ImageButton sleepActivityAuxBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        initialize();
    }

    public void initialize() {
        sleepActivityAuxBtn = findViewById(R.id.sleep_activity_aux_btn);
        sleepActivityAuxBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 701, Menu.NONE, "Установить норму");
                contextMenu.add(Menu.NONE, 702, Menu.NONE, "Аксессуары");
            }
        });
    }

}

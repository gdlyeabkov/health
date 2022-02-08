package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SleepActivity extends AppCompatActivity {

    public ImageButton sleepActivityAuxBtn;
    public ImageButton sleepActivityBackBtn;
    public Button sleepActivityBodyAddRecordBtn;

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
        sleepActivityBackBtn = findViewById(R.id.sleep_activity_back_btn);
        sleepActivityBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepActivity.this, MainActivity.class);
                SleepActivity.this.startActivity(intent);
            }
        });
        sleepActivityBodyAddRecordBtn = findViewById(R.id.sleep_activity_body_add_record_btn);
        sleepActivityBodyAddRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepActivity.this, RecordSleepDataActivity.class);
                SleepActivity.this.startActivity(intent);
            }
        });
    }

}

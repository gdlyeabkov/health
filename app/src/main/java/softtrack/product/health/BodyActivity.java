package softtrack.product.health;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BodyActivity extends AppCompatActivity {

    public ImageButton bodyActivityAuxBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);
        initialize();
    }

    public void initialize() {
        bodyActivityAuxBtn = findViewById(R.id.body_activity_aux_btn);
        bodyActivityAuxBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 601, Menu.NONE, "Установка целей");
                contextMenu.add(Menu.NONE, 602, Menu.NONE, "Выбор данных для просм.");
                contextMenu.add(Menu.NONE, 603, Menu.NONE, "Аксессуары");
                contextMenu.add(Menu.NONE, 604, Menu.NONE, "О составе тканей организма");
            }
        });
    }

}

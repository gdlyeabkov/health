package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsGeneralNotificationsActivity extends AppCompatActivity {

    public ImageButton settingsGeneralNotificationsActivityHeaderBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_general_notifications);
        initialize();
    }

    public void initialize() {
        settingsGeneralNotificationsActivityHeaderBackBtn = findViewById(R.id.settings_general_notifications_activity_header_back_btn);
        settingsGeneralNotificationsActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsGeneralNotificationsActivity.this, SettingsActivity.class);
                SettingsGeneralNotificationsActivity.this.startActivity(intent);
            }
        });
    }

}

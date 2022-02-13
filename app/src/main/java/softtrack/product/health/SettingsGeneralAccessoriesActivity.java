package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsGeneralAccessoriesActivity extends AppCompatActivity {

    public ImageButton settingsGeneralAccessoriesActivityHeaderBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_general_accessories);
        initialize();
    }

    public void initialize() {
        settingsGeneralAccessoriesActivityHeaderBackBtn = findViewById(R.id.settings_general_accessories_activity_header_back_btn);
        settingsGeneralAccessoriesActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsGeneralAccessoriesActivity.this, SettingsActivity.class);
                SettingsGeneralAccessoriesActivity.this.startActivity(intent);
            }
        });
    }

}

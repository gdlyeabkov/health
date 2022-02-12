package softtrack.product.health;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public ScrollView settingsActivityBodyScroll;
    public TextView settingsActivityMainHeader;
    public TextView settingsActivityBodyHeaderLabel;
    public TextView settingsActivityBodyPrivacyMenuPhone;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initialize() {
        settingsActivityBodyScroll = findViewById(R.id.settings_activity_body_scroll);
        settingsActivityMainHeader = findViewById(R.id.settings_activity_main_header);
        settingsActivityBodyHeaderLabel = findViewById(R.id.settings_activity_body_header_label);
        settingsActivityBodyPrivacyMenuPhone = findViewById(R.id.settings_activity_body_privacy_menu_phone);
        settingsActivityBodyScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 >= 150) {
                    settingsActivityMainHeader.setVisibility(View.GONE);
                    settingsActivityBodyHeaderLabel.setText("Настройки Softtrack\nЗдоровье");
                } else {
                    settingsActivityMainHeader.setVisibility(View.VISIBLE);
                    settingsActivityBodyHeaderLabel.setText("");
                }
            }
        });
        settingsActivityBodyPrivacyMenuPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsPrivacyPhoneActivity.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
    }

}

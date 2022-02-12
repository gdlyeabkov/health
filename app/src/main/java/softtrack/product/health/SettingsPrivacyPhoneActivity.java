package softtrack.product.health;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SettingsPrivacyPhoneActivity extends AppCompatActivity {

    public ScrollView settingsPrivacyPhoneActivityBodyScroll;
    public TextView settingsPrivacyPhoneActivityMainLabel;
    public TextView settingsPrivacyPhoneActivityHeaderLabel;
    public TextView settingsPrivacyPhoneActivityBodyBlockPhone;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_privacy_phone);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initialize() {
        settingsPrivacyPhoneActivityBodyScroll = findViewById(R.id.settings_privacy_phone_activity_body_scroll);
        settingsPrivacyPhoneActivityMainLabel = findViewById(R.id.settings_privacy_phone_activity_main_label);
        settingsPrivacyPhoneActivityHeaderLabel = findViewById(R.id.settings_privacy_phone_activity_header_label);
        settingsPrivacyPhoneActivityBodyBlockPhone = findViewById(R.id.settings_privacy_phone_activity_body_block_phone);
        settingsPrivacyPhoneActivityBodyScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 >= 150) {
                    settingsPrivacyPhoneActivityMainLabel.setVisibility(View.GONE);
                    settingsPrivacyPhoneActivityHeaderLabel.setText("Номер телефона");
                } else {
                    settingsPrivacyPhoneActivityMainLabel.setVisibility(View.VISIBLE);
                    settingsPrivacyPhoneActivityHeaderLabel.setText("");
                }
            }
        });
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_SMS }, 1);
        }
        String phoneNumber = telephonyManager.getLine1Number();
        settingsPrivacyPhoneActivityBodyBlockPhone.setText(phoneNumber);
    }

}

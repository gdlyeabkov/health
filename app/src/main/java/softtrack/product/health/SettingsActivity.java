package softtrack.product.health;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public ScrollView settingsActivityBodyScroll;
    public TextView settingsActivityMainHeader;
    public TextView settingsActivityBodyHeaderLabel;
    public TextView settingsActivityBodyPrivacyMenuPhone;
    public TextView settingsActivityBodyInfoMenuFeedback;
    public TextView settingsActivityBodyGeneralMenuMeasure;
    public TextView settingsActivityBodyPrivacyMenuNotifications;
    public TextView settingsActivityBodyGeneralMenuNotifications;
    public LinearLayout settingsActivityBodyGeneralMenuMarketNotifications;
    public Switch settingsActivityBodyGeneralMenuMarketNotificationsSwitch;
    public TextView settingsActivityBodyGeneralMenuAccessories;
    public ImageButton settingsActivityBodyHeaderIcon;

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
        settingsActivityBodyInfoMenuFeedback = findViewById(R.id.settings_activity_body_info_menu_feedback);
        settingsActivityBodyGeneralMenuMeasure = findViewById(R.id.settings_activity_body_general_menu_measure);
        settingsActivityBodyPrivacyMenuNotifications = findViewById(R.id.settings_activity_body_privacy_menu_notifications);
        settingsActivityBodyGeneralMenuNotifications = findViewById(R.id.settings_activity_body_general_menu_notifications);
        settingsActivityBodyGeneralMenuMarketNotifications = findViewById(R.id.settings_activity_body_general_menu_market_notifications);
        settingsActivityBodyGeneralMenuMarketNotificationsSwitch = findViewById(R.id.settings_activity_body_general_menu_market_notifications_switch);
        settingsActivityBodyGeneralMenuAccessories = findViewById(R.id.settings_activity_body_general_menu_accessories);
        settingsActivityBodyHeaderIcon = findViewById(R.id.settings_activity_body_header_icon);
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
        settingsActivityBodyInfoMenuFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setCancelable(true);
                builder.setMessage("Проверьте подключение к сети и\nповторите попытку");
                builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Ошибка сети");
                alert.show();
            }
        });
        settingsActivityBodyGeneralMenuMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsGeneralMeasureActivity.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
        settingsActivityBodyPrivacyMenuNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://samsunghealth.com/privacy?lc=ru&cc=RU&scv=6202017&platform=1&fqdn=samsunghealth.settings&source=2");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browserIntent);
            }
        });
        settingsActivityBodyGeneralMenuNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsGeneralNotificationsActivity.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
        settingsActivityBodyGeneralMenuMarketNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                boolean toggledValue = !settingsActivityBodyGeneralMenuMarketNotificationsSwitch.isChecked();
                settingsActivityBodyGeneralMenuMarketNotificationsSwitch.setChecked(toggledValue);
                boolean isEnabled = settingsActivityBodyGeneralMenuMarketNotificationsSwitch.isChecked();;
                if (isEnabled) {
                    builder.setMessage("Получайте маркетинговые уведомления от\nSofttrack Здоровье и ее партнеров.");
                } else {
                    builder.setMessage("Перестать получать маркетинговые\nуведомления от Softtrack Здоровье и ее\nпартнеров.");
                }

                AlertDialog alert = builder.create();
                alert.setTitle("Маркетинговая информация");
                alert.show();
            }
        });
        settingsActivityBodyGeneralMenuAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsGeneralAccessoriesActivity.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
        settingsActivityBodyHeaderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
    }

}

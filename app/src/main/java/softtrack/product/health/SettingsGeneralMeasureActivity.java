package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsGeneralMeasureActivity extends AppCompatActivity {

    public TextView settingsGeneralMeasureActivityBodyGrowthMeasure;
    public TextView settingsGeneralMeasureActivityBodyWeightMeasure;
    public TextView settingsGeneralMeasureActivityBodyTempMeasure;
    public TextView settingsGeneralMeasureActivityBodyDistanseMeasure;
    public TextView settingsGeneralMeasureActivityBodySugarMeasure;
    public TextView settingsGeneralMeasureActivityBodyPressureMeasure;
    public TextView settingsGeneralMeasureActivityBodyHba1cMeasure;
    public TextView settingsGeneralMeasureActivityBodyWaterMeasure;
    public LinearLayout settingsGeneralMeasureActivityBodyGrowth;
    public LinearLayout settingsGeneralMeasureActivityBodyWeight;
    public LinearLayout settingsGeneralMeasureActivityBodyTemp;
    public LinearLayout settingsGeneralMeasureActivityBodyDistanse;
    public LinearLayout settingsGeneralMeasureActivityBodySugar;
    public LinearLayout settingsGeneralMeasureActivityBodyPressure;
    public LinearLayout settingsGeneralMeasureActivityBodyHba1c;
    public LinearLayout settingsGeneralMeasureActivityBodyWater;
    public ImageView settingsGeneralMeasureActivityHeaderBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_general_measure);
        initialize();
    }

    public void initialize() {
        settingsGeneralMeasureActivityBodyGrowthMeasure = findViewById(R.id.settings_general_measure_activity_body_growth_measure);
        settingsGeneralMeasureActivityBodyWeightMeasure = findViewById(R.id.settings_general_measure_activity_body_weight_measure);
        settingsGeneralMeasureActivityBodyTempMeasure = findViewById(R.id.settings_general_measure_activity_body_temp_measure);
        settingsGeneralMeasureActivityBodyDistanseMeasure = findViewById(R.id.settings_general_measure_activity_body_distanse_measure);
        settingsGeneralMeasureActivityBodySugarMeasure = findViewById(R.id.settings_general_measure_activity_body_sugar_measure);
        settingsGeneralMeasureActivityBodyPressureMeasure = findViewById(R.id.settings_general_measure_activity_body_pressure_measure);
        settingsGeneralMeasureActivityBodyHba1cMeasure = findViewById(R.id.settings_general_measure_activity_body_hba1c_measure);
        settingsGeneralMeasureActivityBodyWaterMeasure = findViewById(R.id.settings_general_measure_activity_body_water_measure);
        settingsGeneralMeasureActivityBodyGrowth = findViewById(R.id.settings_general_measure_activity_body_growth);
        settingsGeneralMeasureActivityBodyWeight = findViewById(R.id.settings_general_measure_activity_body_weight);
        settingsGeneralMeasureActivityBodyTemp = findViewById(R.id.settings_general_measure_activity_body_temp);
        settingsGeneralMeasureActivityBodyDistanse = findViewById(R.id.settings_general_measure_activity_body_distanse);
        settingsGeneralMeasureActivityBodySugar = findViewById(R.id.settings_general_measure_activity_body_sugar);
        settingsGeneralMeasureActivityBodyPressure = findViewById(R.id.settings_general_measure_activity_body_pressure);
        settingsGeneralMeasureActivityBodyHba1c = findViewById(R.id.settings_general_measure_activity_body_hba1c);
        settingsGeneralMeasureActivityBodyWater = findViewById(R.id.settings_general_measure_activity_body_water);
        settingsGeneralMeasureActivityBodyGrowth.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyGrowthCentimetersMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "см");
                settingsGeneralMeasureActivityBodyGrowthCentimetersMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyGrowthMeasure.setText("см");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyGrowthInchsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "фт., дюйм");
                settingsGeneralMeasureActivityBodyGrowthInchsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyGrowthMeasure.setText("дюйм");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodyWeight.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyWeightKilogramsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "кг");
                settingsGeneralMeasureActivityBodyWeightKilogramsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyWeightMeasure.setText("кг");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyWeightPoundsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "фунт");
                settingsGeneralMeasureActivityBodyWeightPoundsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyWeightMeasure.setText("фунт");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodyTemp.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyTempCMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "℃");
                settingsGeneralMeasureActivityBodyTempCMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyTempMeasure.setText("℃");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyTempFMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "℉");
                settingsGeneralMeasureActivityBodyTempFMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyTempMeasure.setText("℉");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodyDistanse.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyDistanseKmsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "км");
                settingsGeneralMeasureActivityBodyDistanseKmsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyDistanseMeasure.setText("км");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyDistanseFtsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "ми/фт");
                settingsGeneralMeasureActivityBodyDistanseFtsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyWeightMeasure.setText("фт");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodySugar.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodySugarMgMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "мг/дл");
                settingsGeneralMeasureActivityBodySugarMgMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodySugarMeasure.setText("мг");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodySugarMoleMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "ммоль/л");
                settingsGeneralMeasureActivityBodySugarMoleMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodySugarMeasure.setText("ммоль/л");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodyPressure.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyPressureMlsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "мм рт. ст.");
                settingsGeneralMeasureActivityBodyPressureMlsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyPressureMeasure.setText("кг");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyPressurePascalsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "кПа");
                settingsGeneralMeasureActivityBodyPressurePascalsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyPressureMeasure.setText("фунт");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodyHba1c.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyHba1cPercentsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "%");
                settingsGeneralMeasureActivityBodyHba1cPercentsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyHba1cMeasure.setText("%");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyHba1cMoleMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "ммоль/моль");
                settingsGeneralMeasureActivityBodyHba1cMoleMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyWeightMeasure.setText("моль");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityBodyWater.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyWaterMlsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "мл");
                settingsGeneralMeasureActivityBodyWaterMlsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyWaterMeasure.setText("мл");
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyWaterOunceMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "жидк. унц.");
                settingsGeneralMeasureActivityBodyWaterOunceMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        settingsGeneralMeasureActivityBodyWaterMeasure.setText("унц.");
                        return false;
                    }
                });
            }
        });
        settingsGeneralMeasureActivityHeaderBackBtn = findViewById(R.id.settings_general_measure_activity_header_back_btn);
        settingsGeneralMeasureActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsGeneralMeasureActivity.this, SettingsActivity.class);
                SettingsGeneralMeasureActivity.this.startActivity(intent);
            }
        });
    }

}

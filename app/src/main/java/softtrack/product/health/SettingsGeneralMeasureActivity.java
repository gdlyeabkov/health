package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_general_measure);
        initialize();
    }

    @SuppressLint("WrongConstant")
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
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        settingsGeneralMeasureActivityBodyGrowth.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem settingsGeneralMeasureActivityBodyGrowthCentimetersMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "см");
                settingsGeneralMeasureActivityBodyGrowthCentimetersMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "см";
                        settingsGeneralMeasureActivityBodyGrowthMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 1", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyGrowthInchsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "фт., дюйм");
                settingsGeneralMeasureActivityBodyGrowthInchsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "фт., дюйм";
                        settingsGeneralMeasureActivityBodyGrowthMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 1", new String[] {  });
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
                        String value = "кг";
                        settingsGeneralMeasureActivityBodyWeightMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 2", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyWeightPoundsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "фунт");
                settingsGeneralMeasureActivityBodyWeightPoundsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "фунт";
                        settingsGeneralMeasureActivityBodyWeightMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 2", new String[] {  });
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
                        String value = "℃";
                        settingsGeneralMeasureActivityBodyTempMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 3", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyTempFMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "℉");
                settingsGeneralMeasureActivityBodyTempFMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "℉";
                        settingsGeneralMeasureActivityBodyTempMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 3", new String[] {  });
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
                        String value = "км";
                        settingsGeneralMeasureActivityBodyDistanseMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 4", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyDistanseFtsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "ми/фт");
                settingsGeneralMeasureActivityBodyDistanseFtsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "ми/фт";
                        settingsGeneralMeasureActivityBodyDistanseMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 4", new String[] {  });
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
                        String value = "мг/дл";
                        settingsGeneralMeasureActivityBodySugarMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 5", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodySugarMoleMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "ммоль/л");
                settingsGeneralMeasureActivityBodySugarMoleMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "ммоль/л";
                        settingsGeneralMeasureActivityBodySugarMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 5", new String[] {  });
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
                        String value = "мм рт. ст.";
                        settingsGeneralMeasureActivityBodyPressureMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 6", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyPressurePascalsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "кПа");
                settingsGeneralMeasureActivityBodyPressurePascalsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "кПа";
                        settingsGeneralMeasureActivityBodyPressureMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 6", new String[] {  });
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
                        String value = "%";
                        settingsGeneralMeasureActivityBodyHba1cMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 7", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyHba1cMoleMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "ммоль/моль");
                settingsGeneralMeasureActivityBodyHba1cMoleMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "ммоль/моль";
                        settingsGeneralMeasureActivityBodyHba1cMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 7", new String[] {  });
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
                        String value = "мл";
                        settingsGeneralMeasureActivityBodyWaterMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 8", new String[] {  });
                        return false;
                    }
                });
                MenuItem settingsGeneralMeasureActivityBodyWaterOunceMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "жидк. унц.");
                settingsGeneralMeasureActivityBodyWaterOunceMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String value = "жидк. унц.";
                        settingsGeneralMeasureActivityBodyWaterMeasure.setText(value);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("value", value);
                        db.update("measures", contentValues, "_id = 8", new String[] {  });
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
        Cursor measuresCursor = db.rawQuery("Select * from measures", null);
        measuresCursor.moveToFirst();
        String value = "";
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyGrowthMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyWeightMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyTempMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyDistanseMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodySugarMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyPressureMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyHba1cMeasure.setText(value);
        measuresCursor.moveToNext();
        value = measuresCursor.getString(2);
        settingsGeneralMeasureActivityBodyWaterMeasure.setText(value);

    }

}

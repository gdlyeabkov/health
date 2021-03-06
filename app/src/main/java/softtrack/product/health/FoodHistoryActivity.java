package softtrack.product.health;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodHistoryActivity extends AppCompatActivity {

    public ImageButton foodHistoryActivityHeaderBackBtn;
    public String foodType;
    public Button foodHistoryActivityNextBtn;
    public int foodsCallories;
    public TextView foodHistoryActivityFoodTypeLabel;
    public LinearLayout foodHistoryActivityFoodType;
    public Button foodHistoryActivityTimeBtn;
    public Button foodHistoryActivitySaveCustomFoodBtn;
    public ImageButton foodHistoryActivityInfoIcon;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_history);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        foodHistoryActivityHeaderBackBtn = findViewById(R.id.food_history_activity_header_back_btn);
        foodHistoryActivityNextBtn = findViewById(R.id.food_history_activity_next_btn);
        foodHistoryActivityFoodTypeLabel = findViewById(R.id.food_history_activity_food_type_label);
        foodHistoryActivityFoodType = findViewById(R.id.food_history_activity_food_type);
        foodHistoryActivityTimeBtn = findViewById(R.id.food_history_activity_time_btn);
        foodHistoryActivitySaveCustomFoodBtn = findViewById(R.id.food_history_activity_save_custom_food_btn);
        foodHistoryActivityInfoIcon = findViewById(R.id.food_history_activity_info_icon);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        foodType = extras.getString("foodType");
        foodsCallories = extras.getInt("foodsCallories");
        foodHistoryActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodHistoryActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                FoodHistoryActivity.this.startActivity(intent);
            }
        });
        foodHistoryActivityNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("food", foodsCallories);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });

                ArrayList<Integer> foodsRecordsIds = new ArrayList<Integer>();
                Cursor foodsCursor = db.rawQuery("Select * from food_records", null);
                long countFoods = DatabaseUtils.queryNumEntries(db, "food_records");
                foodsCursor.moveToFirst();

                db.execSQL("DELETE FROM food_records WHERE type=\"" + foodType + "\";");

                String selectedMealContent = foodType;
                db.execSQL("INSERT INTO \"food_records\"(type) VALUES (\"" + selectedMealContent + "\");");

                Intent intent = new Intent(FoodHistoryActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                FoodHistoryActivity.this.startActivity(intent);

            }
        });
        foodHistoryActivityFoodTypeLabel.setText(foodType);
        foodHistoryActivityFoodType.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem breakfastMenuItem = contextMenu.add(Menu.NONE, 1001, Menu.NONE, "??????????????");
                MenuItem lanchMenuItem = contextMenu.add(Menu.NONE, 1002, Menu.NONE, "????????");
                MenuItem dinnerMenuItem = contextMenu.add(Menu.NONE, 1003, Menu.NONE, "????????");
                MenuItem morningMealMenuItem = contextMenu.add(Menu.NONE, 1004, Menu.NONE, "???????????????? ??????????????");
                MenuItem dayMealMenuItem = contextMenu.add(Menu.NONE, 1005, Menu.NONE, "?????????????? ??????????????");
                MenuItem eveningMealMenuItem = contextMenu.add(Menu.NONE, 1006, Menu.NONE, "???????????????? ??????????????");
                breakfastMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        foodHistoryActivityFoodTypeLabel.setText("??????????????");
                        return false;
                    }
                });
                lanchMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        foodHistoryActivityFoodTypeLabel.setText("????????");
                        return false;
                    }
                });
                dinnerMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        foodHistoryActivityFoodTypeLabel.setText("????????");
                        return false;
                    }
                });
                morningMealMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        foodHistoryActivityFoodTypeLabel.setText("???????????????? ??????????????");
                        return false;
                    }
                });
                dayMealMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        foodHistoryActivityFoodTypeLabel.setText("?????????????? ??????????????");
                        return false;
                    }
                });
                eveningMealMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        foodHistoryActivityFoodTypeLabel.setText("???????????????? ??????????????");
                        return false;
                    }
                });
            }
        });
        foodHistoryActivityTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodHistoryActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_time_dialog, null);
                builder.setView(dialogView);
                builder.setCancelable(false);
                builder.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String foodTime = "00:00";
                        foodHistoryActivityTimeBtn.setText(foodTime);
                    }
                });
                builder.setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("???????????????????? ??????????");
                alert.show();
            }
        });
        foodHistoryActivitySaveCustomFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodHistoryActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_food_history_useful_dialog, null);
                builder.setView(dialogView);
                builder.setCancelable(false);
                builder.setPositiveButton("????????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("???????????????????? ???????????? ???????? ?? \"??????\n??????????????\"");
                alert.show();
                EditText activityFoodHistoryInputField = dialogView.findViewById(R.id.activity_food_history_input_field);
                activityFoodHistoryInputField.setText(foodType);
            }
        });
        foodHistoryActivityInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodHistoryActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_food_history_camera_dialog, null);
                builder.setView(dialogView);
                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.setTitle("???????????????????? ??????????????????????");
                alert.show();
                Button activityFoodHistoryCameraDialogPickImageBtn = dialogView.findViewById(R.id.activity_food_history_camera_dialog_pick_image_btn);
                Button activityFoodHistoryCameraDialogShotBtn = dialogView.findViewById(R.id.activity_food_history_camera_dialog_shot_btn);
                activityFoodHistoryCameraDialogPickImageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("debug", "?????????????? ??????????????????????");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        FoodHistoryActivity.this.startActivity(intent);
                    }
                });
                activityFoodHistoryCameraDialogShotBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("debug", "?????????????? ????????????");
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);
                    }
                });
            }
        });
    }

}

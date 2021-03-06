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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class EditFoodItemActivity extends AppCompatActivity {

    public Button editFoodItemActivityFooterCancelBtn;
    public Button editFoodItemActivityFooterNextBtn;
    public String foodType;
    public int foodId;
    public HashMap<String, Object> food;
    public TextView editFoodItemActivityBodyPortionsHheaderAsideHeader;
    public TextView editFoodItemActivityBodyPortionsHeaderAsideLabel;
    public TextView editFoodItemActivityPortionSizeLabel;
    public TextView editFoodItemActivityCalloriesLabel;
    public TextView editFoodItemActivityTotalFatsLabel;
    public TextView editFoodItemActivitySaturatedFatsLabel;
    public TextView editFoodItemActivityTransFatsLabel;
    public TextView editFoodItemActivityCholesterolLabel;
    public TextView editFoodItemActivitySodiumLabel;
    public TextView editFoodItemActivityTotalCarbsLabel;
    public TextView editFoodItemActivityCelluloseLabel;
    public TextView editFoodItemActivityProteinLabel;
    public TextView editFoodItemActivityALabel;
    public TextView editFoodItemActivityCLabel;
    public TextView editFoodItemActivityCalciumLabel;
    public TextView editFoodItemActivityIronLabel;
    public TextView editFoodItemActivityPotassiumLabel;
    public TextView editFoodItemActivitySugarLabel;
    public Button editFoodItemActivityBodyPortionsScaleValue;
    public SeekBar editFoodItemActivityBodyPortionsScaleTimeline;
    public LinearLayout editFoodItemActivityBodyPortionsFooter;
    public TextView editFoodItemActivityBodyPortionsFooterLabel;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_item);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        editFoodItemActivityFooterCancelBtn = findViewById(R.id.edit_food_item_activity_footer_cancel_btn);
        editFoodItemActivityFooterNextBtn = findViewById(R.id.edit_food_item_activity_footer_next_btn);
        editFoodItemActivityBodyPortionsHheaderAsideHeader = findViewById(R.id.edit_food_item_activity_body_portions_header_aside_header);
        editFoodItemActivityBodyPortionsHeaderAsideLabel = findViewById(R.id.edit_food_item_activity_body_portions_header_aside_label);

        editFoodItemActivitySugarLabel = findViewById(R.id.edit_food_item_activity_sugar_label);
        editFoodItemActivityPotassiumLabel = findViewById(R.id.edit_food_item_activity_potassium_label);
        editFoodItemActivityCLabel = findViewById(R.id.edit_food_item_activity_c_label);
        editFoodItemActivityALabel = findViewById(R.id.edit_food_item_activity_a_label);
        editFoodItemActivityCalciumLabel = findViewById(R.id.edit_food_item_activity_calcium_label);
        editFoodItemActivityTransFatsLabel = findViewById(R.id.edit_food_item_activity_trans_fats_label);
        editFoodItemActivityTotalFatsLabel = findViewById(R.id.edit_food_item_activity_total_fats_label);
        editFoodItemActivityTotalCarbsLabel = findViewById(R.id.edit_food_item_activity_total_carbs_label);
        editFoodItemActivitySaturatedFatsLabel = findViewById(R.id.edit_food_item_activity_saturated_fats_label);
        editFoodItemActivitySodiumLabel = findViewById(R.id.edit_food_item_activity_sodium_label);
        editFoodItemActivityCholesterolLabel = findViewById(R.id.edit_food_item_activity_cholesterol_label);
        editFoodItemActivityCelluloseLabel = findViewById(R.id.edit_food_item_activity_cellulose_label);
        editFoodItemActivityIronLabel = findViewById(R.id.edit_food_item_activity_iron_label);
        editFoodItemActivityPortionSizeLabel = findViewById(R.id.edit_food_item_activity_portion_size_label);
        editFoodItemActivityCalloriesLabel = findViewById(R.id.edit_food_item_activity_calories_label);
        editFoodItemActivityProteinLabel = findViewById(R.id.edit_food_item_activity_protein_label);
        editFoodItemActivityBodyPortionsScaleValue = findViewById(R.id.edit_food_item_activity_body_portions_scale_value);
        editFoodItemActivityBodyPortionsScaleTimeline = findViewById(R.id.edit_food_item_activity_body_portions_scale_timeline);
        editFoodItemActivityBodyPortionsFooter = findViewById(R.id.edit_food_item_activity_body_portions_footer);
        editFoodItemActivityBodyPortionsFooterLabel = findViewById(R.id.edit_food_item_activity_body_portions_footer_label);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            foodType = extras.getString("foodType");
            foodId = extras.getInt("id");
            Cursor foodsCursor = db.rawQuery("Select * from food_items where _id=" + Integer.toString(foodId), null);
            foodsCursor.moveToFirst();
            HashMap<String, Object> food = new HashMap<String, Object>();
            String foodName = foodsCursor.getString(1);
            int foodCallories = foodsCursor.getInt(2);
            int foodTotalCarbs = foodsCursor.getInt(3);
            int foodTotalFats = foodsCursor.getInt(4);
            int foodProtein = foodsCursor.getInt(5);
            int foodSaturatedFats = foodsCursor.getInt(6);
            int foodTransFats = foodsCursor.getInt(7);
            int foodCholesterol = foodsCursor.getInt(8);
            int foodSodium = foodsCursor.getInt(9);
            int foodPotassium = foodsCursor.getInt(10);
            int foodCellulose = foodsCursor.getInt(11);
            int foodSugar = foodsCursor.getInt(12);
            int foodA = foodsCursor.getInt(13);
            int foodC = foodsCursor.getInt(14);
            int foodCalcium = foodsCursor.getInt(15);
            int foodIron = foodsCursor.getInt(16);
            double foodPortions = foodsCursor.getDouble(0);
            editFoodItemActivityBodyPortionsScaleTimeline.setProgress(((int)(foodPortions)) * 10);
            editFoodItemActivityBodyPortionsScaleValue.setText(String.valueOf(foodPortions));
            String typeOfFood = foodsCursor.getString(17);
            food.put("name", foodName);
            food.put("callories", foodCallories);
            food.put("total_carbs", foodTotalCarbs);
            food.put("protein", foodProtein);
            food.put("saturated_fats", foodSaturatedFats);
            food.put("trans_fats", foodTransFats);
            food.put("cholesterol", foodCholesterol);
            food.put("sodium", foodSodium);
            food.put("potassium", foodPotassium);
            food.put("cellulose", foodCellulose);
            food.put("sugar", foodSugar);
            food.put("a", foodA);
            food.put("c", foodC);
            food.put("calcium", foodCalcium);
            food.put("iron", foodIron);
            food.put("portions", foodPortions);
            food.put("type", typeOfFood);
            editFoodItemActivityBodyPortionsHheaderAsideHeader.setText(foodName);
            String rawFoodCallories = String.valueOf(foodCallories);
            String parsedFoodCallories = rawFoodCallories + " ????????";
            editFoodItemActivityBodyPortionsHeaderAsideLabel.setText(parsedFoodCallories);
            editFoodItemActivityCalloriesLabel.setText(parsedFoodCallories);
            String parsedFoodPortions = String.valueOf(foodPortions);
            String foodPortionsMsg = parsedFoodPortions + " ????????????";
            editFoodItemActivityPortionSizeLabel.setText(foodPortionsMsg);
            String totalFatsMsg = "?????????? ?????????? " + foodTotalFats + " ??";
            editFoodItemActivityTotalFatsLabel.setText(totalFatsMsg);
            String saturatedFatsMsg = "???????????????????? ???????? " + foodSaturatedFats + " ??";
            editFoodItemActivitySaturatedFatsLabel.setText(saturatedFatsMsg);
            String transFatsMsg = "?????????????????? " + foodTransFats + " ??";
            editFoodItemActivityTransFatsLabel.setText(transFatsMsg);
            String cholesterolMsg = "???????????????????? " + foodCholesterol + " ????";
            editFoodItemActivityCholesterolLabel.setText(cholesterolMsg);
            String sodiumMsg = "???????????? " + foodSodium + " ????";
            editFoodItemActivitySodiumLabel.setText(sodiumMsg);
            String totalCarbsMsg = "?????????? ?????????????????? " + foodTotalCarbs + " ??";
            editFoodItemActivityTotalCarbsLabel.setText(totalCarbsMsg);
            String celluloseMsg = "?????????????????? " + foodCellulose + " ??";
            editFoodItemActivityCelluloseLabel.setText(celluloseMsg);
            String sugarMsg = "?????????? ???????????????????? ?????????????? " + foodSugar + " ??";
            editFoodItemActivitySugarLabel.setText(sugarMsg);
            String rawfoodProtein = String.valueOf(foodProtein);
            String proteinMsg = "?????????????? " + rawfoodProtein + " ??";
            // proteinMsg = "";
            editFoodItemActivityProteinLabel.setText(proteinMsg);
            String aMsg = "?????????????? A " + foodA + " ??????";
            editFoodItemActivityALabel.setText(aMsg);
            String cMsg = "?????????????? C " + foodC + " ????";
            editFoodItemActivityCLabel.setText(cMsg);
            String calciumMsg = "?????????????? " + foodCalcium + " ????";
            editFoodItemActivityCalciumLabel.setText(calciumMsg);
            String ironMsg = "???????????? " + foodIron + " ????";
            editFoodItemActivityIronLabel.setText(ironMsg);
            String potassiumMsg = "?????????? " + foodPotassium + " ????";
            editFoodItemActivityPotassiumLabel.setText(potassiumMsg);
        }
        editFoodItemActivityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditFoodItemActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                EditFoodItemActivity.this.startActivity(intent);
            }
        });
        editFoodItemActivityFooterNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO ???????????????? ???????????????????? food_items
                ContentValues contentValues = new ContentValues();
                int porionProgress = editFoodItemActivityBodyPortionsScaleTimeline.getProgress();
                float correctPorionProgress = porionProgress / 10;
                contentValues.put("portions", correctPorionProgress);
                db.update("food_items", contentValues, "_id = ?", new String[] { Integer.toString(foodId) });
                Intent intent = new Intent(EditFoodItemActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                EditFoodItemActivity.this.startActivity(intent);
            }
        });
        editFoodItemActivityBodyPortionsScaleTimeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int maxProgress = seekBar.getMax();
                boolean isMaxProgress = i == maxProgress;
                if (isMaxProgress) {
                    int newMaxProgress = maxProgress + 1;
                    seekBar.setMax(newMaxProgress);
                }
                float neededProgress = seekBar.getProgress() / 10;
                Log.d("debug", Float.toString(neededProgress));
//                String rawPortion = String.valueOf(i);
                String rawPortion = Float.toString(neededProgress);
                editFoodItemActivityBodyPortionsScaleValue.setText(rawPortion);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        editFoodItemActivityBodyPortionsFooter.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem portionMenuItem = contextMenu.add(Menu.NONE, 901, Menu.NONE, "????????????");
                MenuItem clMenuItem = contextMenu.add(Menu.NONE, 902, Menu.NONE, "????????");
                portionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        editFoodItemActivityBodyPortionsFooterLabel.setText("????????????");
                        return false;
                    }
                });
                clMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        editFoodItemActivityBodyPortionsFooterLabel.setText("????????");
                        return false;
                    }
                });
            }
        });
    }

}

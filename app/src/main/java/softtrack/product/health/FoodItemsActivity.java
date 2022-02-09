package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodItemsActivity extends AppCompatActivity {

    public Button foodItemsActivityHeaderNextBtn;
    public ImageButton foodItemsActivityHeaderBackBtn;
    public TextView foodItemsActivityHeaderLabel;
    public LinearLayout foodItemsActivityFoodsAddItem;
    public String foodType;
    public LinearLayout foodItemsActivityFoods;
    public ArrayList<HashMap<String, Object>> foods;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        foodItemsActivityHeaderNextBtn = findViewById(R.id.food_items_activity_header_next_btn);
        foodItemsActivityHeaderBackBtn = findViewById(R.id.food_items_activity_header_back_btn);
        foodItemsActivityHeaderLabel = findViewById(R.id.food_items_activity_header_label);
        foodItemsActivityFoodsAddItem = findViewById(R.id.food_items_activity_foods_add_item);
        foodItemsActivityFoods = findViewById(R.id.food_items_activity_foods);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        foodItemsActivityHeaderNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodItemsActivity.this, FoodHistoryActivity.class);
                intent.putExtra("foodType", foodType);
                FoodItemsActivity.this.startActivity(intent);
            }
        });
        foodItemsActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodItemsActivity.this, FoodActivity.class);
                intent.putExtra("isAddRecord", false);
                FoodItemsActivity.this.startActivity(intent);
            }
        });
        Bundle extras = getIntent().getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            foodType = extras.getString("foodType");
            foodItemsActivityHeaderLabel.setText(foodType);
        }
        foodItemsActivityFoodsAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodItemsActivity.this, AddFoodItemActivity.class);
                intent.putExtra("foodType", foodType);
                FoodItemsActivity.this.startActivity(intent);
            }
        });
        foods = new ArrayList<HashMap<String, Object>>();
        Cursor foodsCursor = db.rawQuery("Select * from food_items", null);
        long countFoods = DatabaseUtils.queryNumEntries(db, "food_items");
        foodsCursor.moveToFirst();
        for (int foodsCursorIndex = 0; foodsCursorIndex < countFoods; foodsCursorIndex++) {
            HashMap<String, Object> food = new HashMap<String, Object>();
            String foodName = foodsCursor.getString(1);
            int foodCallories = foodsCursor.getInt(2);
            int foodTotalCarbs = foodsCursor.getInt(3);
            int foodProtein = foodsCursor.getInt(4);
            int foodSaturatedFats = foodsCursor.getInt(5);
            int foodTransFats = foodsCursor.getInt(6);
            int foodCholesterol = foodsCursor.getInt(7);
            int foodSodium = foodsCursor.getInt(8);
            int foodPotassium = foodsCursor.getInt(9);
            int foodCellulose = foodsCursor.getInt(10);
            int foodSugar = foodsCursor.getInt(11);
            int foodA = foodsCursor.getInt(12);
            int foodC = foodsCursor.getInt(13);
            int foodCalcium = foodsCursor.getInt(14);
            int foodIron = foodsCursor.getInt(15);
            double foodPortions = foodsCursor.getDouble(0);
            int foodId = foodsCursor.getInt(0);
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
            food.put("_id", foodId);
            foods.add(food);
            foodsCursor.moveToNext();
        }
        for (HashMap<String, Object> food : foods) {
            Object rawFoodName = food.get("name");
            String foodName = rawFoodName.toString();
            View foodItemsActivityFoodsSplitter = new View(FoodItemsActivity.this);
            LinearLayout.LayoutParams foodItemsActivityFoodsSplitterLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
            foodItemsActivityFoodsSplitter.setLayoutParams(foodItemsActivityFoodsSplitterLayoutParams);
            foodItemsActivityFoodsSplitter.setBackgroundColor(Color.rgb(200, 200, 200));
            LinearLayout foodItemsActivityFood = new LinearLayout(FoodItemsActivity.this);
            Object rawFoodId = food.get("_id");
            String foodId = rawFoodId.toString();
            foodItemsActivityFood.setContentDescription(foodId);
            foodItemsActivityFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence rawFoodId = view.getContentDescription();
                    String foodId = rawFoodId.toString();
                    int parsedFoodId = Integer.valueOf(foodId);
                    Intent intent = new Intent(FoodItemsActivity.this, EditFoodItemActivity.class);
                    intent.putExtra("id", parsedFoodId);
                    FoodItemsActivity.this.startActivity(intent);
                }
            });
            foodItemsActivityFood.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout foodItemsActivityFoodAside = new LinearLayout(FoodItemsActivity.this);
            foodItemsActivityFoodAside.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams foodItemsActivityFoodAsideLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            foodItemsActivityFoodAsideLayoutParams.setMargins(150, 15, 0, 0);
            foodItemsActivityFoodAside.setLayoutParams(foodItemsActivityFoodAsideLayoutParams);
            TextView foodItemsActivityFoodAsideHeader = new TextView(FoodItemsActivity.this);
            foodItemsActivityFoodAsideHeader.setText(foodName);
            foodItemsActivityFoodAsideHeader.setTextSize(20);
            foodItemsActivityFoodAside.addView(foodItemsActivityFoodAsideHeader);
            TextView foodItemsActivityFoodAsideLabel = new TextView(FoodItemsActivity.this);
            Object rawFoodCallories = food.get("callories");
            String foodCallories = rawFoodCallories.toString();
            int parsedFoodCallories = Integer.valueOf(foodCallories);
            Object rawFoodPortions = food.get("portions");
            String foodPortions = rawFoodPortions.toString();
            double parsedFoodPortions = Float.valueOf(foodPortions);
            String foodInfo = parsedFoodCallories + " ккал, " + parsedFoodPortions + " порция";
            foodItemsActivityFoodAsideLabel.setText(foodInfo);
            foodItemsActivityFoodAside.addView(foodItemsActivityFoodAsideLabel);
            foodItemsActivityFood.addView(foodItemsActivityFoodAside);
            CheckBox foodItemsActivityFoodSelector = new CheckBox(FoodItemsActivity.this);
            LinearLayout.LayoutParams foodItemsActivityFoodSelectorLayoutParams = new LinearLayout.LayoutParams(250, ViewGroup.LayoutParams.MATCH_PARENT);
            foodItemsActivityFoodSelectorLayoutParams.setMargins(500, 15, 0, 0);
            foodItemsActivityFoodSelector.setLayoutParams(foodItemsActivityFoodSelectorLayoutParams);
            foodItemsActivityFood.addView(foodItemsActivityFoodSelector);
            foodItemsActivityFoods.addView(foodItemsActivityFoodsSplitter);
            foodItemsActivityFoods.addView(foodItemsActivityFood);
        }

    }

}

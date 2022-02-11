package softtrack.product.health;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodItemsActivity extends AppCompatActivity {

    public Button foodItemsActivityHeaderNextBtn;
    public ImageButton foodItemsActivityHeaderBackBtn;
    public TextView foodItemsActivityHeaderLabel;
    public LinearLayout foodItemsActivityFoodsAddItem;
    public String foodType;
    public LinearLayout foodItemsActivityFoods;
    public ArrayList<HashMap<String, Object>> foods;
    public ViewPager2 activityFoodItemsCurrentTab;
    public LinearLayout foodItemsActivityCustomMeal;
    public LinearLayout foodItemsActivitySearch;
    public LinearLayout foodItemsActivityFavorites;
    public TabLayout foodItemsMainTabs;
    public SearchView foodItemsActivitySearchField;
    public ArrayList<Integer> foodsCallories;
    public ArrayList<CheckBox> foodSelectors;
    public boolean isDirty = false;
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
        activityFoodItemsCurrentTab = findViewById(R.id.activity_food_items_current_tab);
        foodItemsActivitySearch = findViewById(R.id.food_items_activity_search);
        foodItemsActivityFavorites = findViewById(R.id.food_items_activity_favorites);
        foodItemsActivityCustomMeal = findViewById(R.id.food_items_activity_custom_meal);
        foodItemsMainTabs = findViewById(R.id.food_items_main_tabs);
        foodItemsActivitySearchField = findViewById(R.id.food_items_activity_search_field);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        foodItemsActivityHeaderNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFoodBlock();
            }
        });
        foodItemsActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDirty) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FoodItemsActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    builder.setCancelable(true);
                    builder.setPositiveButton("Отменить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            goToFoodActivity();
                        }
                    });
                    builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Сохранить изменения или удалить их");
                    alert.show();
                } else {
                    goToFoodActivity();
                }
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
        foodsCallories = new ArrayList<Integer>();
        foodSelectors = new ArrayList<CheckBox>();
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
//            foodItemsActivityFoodSelector.setContentDescription(foodCallories);
            foodItemsActivityFoodSelector.setContentDescription(String.valueOf(foodsCallories.size()));
            foodItemsActivityFoodSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    CharSequence rawData = compoundButton.getContentDescription();
                    String parsedRawData = rawData.toString();
                    int foodCalloriesIndex = Integer.valueOf(parsedRawData);
                    int ratio = 1;
                    if (!b) {
                        ratio = -1;
                    } else {
                        isDirty = true;
                    }
                    int foodsCalloriesItem = foodsCallories.get(foodCalloriesIndex);
                    foodsCallories.set(foodCalloriesIndex, foodsCalloriesItem * - ratio);
                    int countFoodsCallories = foodsCallories.size();
                    int foodsCalloriesCursor = 0;
                    for (CheckBox foodSelector : foodSelectors) {
                        if (foodSelector.isChecked()) {
                            foodsCalloriesCursor++;
                        }
                    }
                    if (foodsCalloriesCursor == 0) {
                        foodItemsActivityHeaderNextBtn.setText("Проп. еду");
                        foodItemsActivityHeaderNextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                goToFoodBlock();
                            }
                        });
                    } else if (foodsCalloriesCursor >= 1) {
                        foodItemsActivityHeaderNextBtn.setText("Далее");
                        foodItemsActivityHeaderNextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                goToFoodHistory();
                            }
                        });
                    }
                }
            });
            LinearLayout.LayoutParams foodItemsActivityFoodSelectorLayoutParams = new LinearLayout.LayoutParams(250, ViewGroup.LayoutParams.MATCH_PARENT);
            foodItemsActivityFoodSelectorLayoutParams.setMargins(500, 15, 0, 0);
            foodItemsActivityFoodSelector.setLayoutParams(foodItemsActivityFoodSelectorLayoutParams);
            foodItemsActivityFood.addView(foodItemsActivityFoodSelector);
            foodItemsActivityFoods.addView(foodItemsActivityFoodsSplitter);
            foodsCallories.add(parsedFoodCallories * - 1);
            foodSelectors.add(foodItemsActivityFoodSelector);
            foodItemsActivityFoods.addView(foodItemsActivityFood);
        }

        activityFoodItemsCurrentTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = activityFoodItemsCurrentTab.getCurrentItem();
                if (position == 0) {
                    foodItemsActivitySearch.setVisibility(View.VISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.INVISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.INVISIBLE);
                } else if (position == 1) {
                    foodItemsActivitySearch.setVisibility(View.INVISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.VISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.INVISIBLE);
                } else if (position == 2) {
                    foodItemsActivitySearch.setVisibility(View.INVISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.INVISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.VISIBLE);
                }
            }
        });
        activityFoodItemsCurrentTab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    foodItemsActivitySearch.setVisibility(View.VISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.INVISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.INVISIBLE);
                } else if (position == 1) {
                    foodItemsActivitySearch.setVisibility(View.INVISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.VISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.INVISIBLE);
                } else if (position == 2) {
                    foodItemsActivitySearch.setVisibility(View.INVISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.INVISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.VISIBLE);
                }
            }
        });
        foodItemsMainTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    foodItemsActivitySearch.setVisibility(View.VISIBLE);
                    foodItemsActivityFavorites.setVisibility(View.GONE);
                    foodItemsActivityCustomMeal.setVisibility(View.GONE);
                } else if (position == 1) {
                    foodItemsActivitySearch.setVisibility(View.GONE);
                    foodItemsActivityFavorites.setVisibility(View.VISIBLE);
                    foodItemsActivityCustomMeal.setVisibility(View.GONE);
                } else if (position == 2) {
                    foodItemsActivitySearch.setVisibility(View.GONE);
                    foodItemsActivityFavorites.setVisibility(View.GONE);
                    foodItemsActivityCustomMeal.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ArrayList<String> suggestions = new ArrayList<String>();
        suggestions.add("Apple");
        suggestions.add("Blueberry");
        suggestions.add("Carrot");
        suggestions.add("Daikon");
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.food_item_search_suggestion, null, new String[]{ "apple", "blueberry", "carrot", "daicon" }, new int[]{ R.id.search_suggestions_item, R.id.search_suggestions_item, R.id.search_suggestions_item, R.id.search_suggestions_item }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        foodItemsActivitySearchField.setSuggestionsAdapter(cursorAdapter);
        foodItemsActivitySearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    }

    public void goToFoodHistory() {
        int totalFoodCallories = 0;
        for (int foodsCalloriesItem : foodsCallories) {
            boolean isCalloriesExists = foodsCalloriesItem >= 0;
            if (isCalloriesExists) {
                totalFoodCallories += foodsCalloriesItem;
            }
        }
        Intent intent = new Intent(FoodItemsActivity.this, FoodHistoryActivity.class);
        intent.putExtra("foodsCallories", totalFoodCallories);
        intent.putExtra("foodType", foodType);
        FoodItemsActivity.this.startActivity(intent);
    }

    public void goToFoodBlock() {
        Intent intent = new Intent(FoodItemsActivity.this, FoodActivity.class);
        intent.putExtra("isAddRecord", false);
        FoodItemsActivity.this.startActivity(intent);
    }

    public void goToFoodActivity() {
        Intent intent = new Intent(FoodItemsActivity.this, FoodActivity.class);
        intent.putExtra("isAddRecord", false);
        FoodItemsActivity.this.startActivity(intent);
    }

}

package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddFoodItemActivity extends AppCompatActivity {

    public Button addFoodItemActivityFooterSaveBtn;
    public Button addFoodItemActivityFooterCancelBtn;
    public Button addFoodItemActivityExpandFoodBtn;
    public int isVisible;
    public int isUnVisible;
    public LinearLayout addFoodItemActivityBodyExpandMenu;
    public EditText addFoodItemActivityProductName;
    public EditText addFoodItemActivityCalloriesField;
    public EditText addFoodItemActivityTotalCarbsField;
    public EditText addFoodItemActivityTotalFatsField;
    public EditText addFoodItemActivityProteinField;
    public EditText addFoodItemActivitySaturatedFatsField;
    public EditText addFoodItemActivityTransFatsField;
    public EditText addFoodItemActivityCholesterolField;
    public EditText addFoodItemActivitySodiumField;
    public EditText addFoodItemActivityPotassiumField;
    public EditText addFoodItemActivityCelluloseField;
    public EditText addFoodItemActivitySugarField;
    public EditText addFoodItemActivityAField;
    public EditText addFoodItemActivityCField;
    public EditText addFoodItemActivityCalciumField;
    public EditText addFoodItemActivityIronField;
    public String foodType;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        addFoodItemActivityFooterSaveBtn = findViewById(R.id.add_food_item_activity_footer_save_btn);
        addFoodItemActivityFooterCancelBtn = findViewById(R.id.add_food_item_activity_footer_cancel_btn);
        addFoodItemActivityExpandFoodBtn = findViewById(R.id.add_food_item_activity_expand_food_btn);
        addFoodItemActivityBodyExpandMenu = findViewById(R.id.add_food_item_activity_body_expand_menu);
        addFoodItemActivityProductName = findViewById(R.id.add_food_item_activity_product_name);
        addFoodItemActivityCalloriesField = findViewById(R.id.add_food_item_activity_callories_field);
        addFoodItemActivityTotalCarbsField = findViewById(R.id.add_food_item_activity_product_carbs_field);
        addFoodItemActivityTotalFatsField = findViewById(R.id.add_food_item_activity_product_total_fats_field);
        addFoodItemActivityProteinField = findViewById(R.id.add_food_item_activity_product_protein_field);
        addFoodItemActivitySaturatedFatsField = findViewById(R.id.add_food_item_activity_product_saturated_fats_field);
        addFoodItemActivityTransFatsField = findViewById(R.id.add_food_item_activity_product_carbs_trans_fats_field);
        addFoodItemActivityCholesterolField = findViewById(R.id.add_food_item_activity_product_cholesterol_field);
        addFoodItemActivitySodiumField = findViewById(R.id.add_food_item_activity_product_sodium_field);
        addFoodItemActivityPotassiumField = findViewById(R.id.add_food_item_activity_potassium_field);
        addFoodItemActivityCelluloseField = findViewById(R.id.add_food_item_activity_cellulose_field);
        addFoodItemActivitySugarField = findViewById(R.id.add_food_item_activity_sugar_field);
        addFoodItemActivityAField = findViewById(R.id.add_food_item_activity_a_field);
        addFoodItemActivityCField = findViewById(R.id.add_food_item_activity_c_field);
        addFoodItemActivityCalciumField = findViewById(R.id.add_food_item_activity_calcium_field);
        addFoodItemActivityIronField = findViewById(R.id.add_food_item_activity_iron_field);

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        boolean isExtrasExists = extras != null;
        if (isExtrasExists) {
            foodType = extras.getString("foodType");
        }

        isVisible = View.VISIBLE;
        isUnVisible = View.INVISIBLE;
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        addFoodItemActivityFooterSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawAddFoodItemActivityProductNameContent = addFoodItemActivityProductName.getText();
                String addFoodItemActivityProductNameContent = rawAddFoodItemActivityProductNameContent.toString();
                CharSequence rawAddFoodItemActivityCalloriesFieldContent = addFoodItemActivityCalloriesField.getText();
                String addFoodItemActivityCalloriesFieldContent = rawAddFoodItemActivityCalloriesFieldContent.toString();
                int parsedAddFoodItemActivityCalloriesFieldContent = Integer.valueOf(addFoodItemActivityCalloriesFieldContent);
                CharSequence rawAddFoodItemActivityTotalCarbsFieldContent = addFoodItemActivityTotalCarbsField.getText();
                String addFoodItemActivityTotalCarbsFieldContent = rawAddFoodItemActivityTotalCarbsFieldContent.toString();
                int parsedAddFoodItemActivityTotalCarbsFieldContent = Integer.valueOf(addFoodItemActivityTotalCarbsFieldContent);
                CharSequence rawAddFoodItemActivityTotalFatsField = addFoodItemActivityTotalFatsField.getText();
                String addFoodItemActivityTotalFatsField = rawAddFoodItemActivityTotalFatsField.toString();
                int parsedAddFoodItemActivityTotalFatsField = Integer.valueOf(addFoodItemActivityTotalFatsField);
                CharSequence rawAddFoodItemActivityProteinField = addFoodItemActivityProteinField.getText();
                String addFoodItemActivityProteinField = rawAddFoodItemActivityProteinField.toString();
                int parsedAddFoodItemActivityProteinField = Integer.valueOf(addFoodItemActivityProteinField);
                CharSequence rawAddFoodItemActivitySaturatedFatsField = addFoodItemActivitySaturatedFatsField.getText();
                String addFoodItemActivitySaturatedFatsField = rawAddFoodItemActivitySaturatedFatsField.toString();
                int parsedAddFoodItemActivitySaturatedFatsField = Integer.valueOf(addFoodItemActivitySaturatedFatsField);
                CharSequence rawAddFoodItemActivityTransFatsField = addFoodItemActivityTransFatsField.getText();
                String addFoodItemActivityTransFatsField = rawAddFoodItemActivityTransFatsField.toString();
                int parsedAddFoodItemActivityTransFatsField = Integer.valueOf(addFoodItemActivityTransFatsField);
                CharSequence rawAddFoodItemActivityCholesterolField = addFoodItemActivityCholesterolField.getText();
                String addFoodItemActivityCholesterolField = rawAddFoodItemActivityCholesterolField.toString();
                int parsedAddFoodItemActivityCholesterolField = Integer.valueOf(addFoodItemActivityCholesterolField);
                CharSequence rawAddFoodItemActivitySodiumField = addFoodItemActivitySodiumField.getText();
                String addFoodItemActivitySodiumField = rawAddFoodItemActivitySodiumField.toString();
                int parsedAddFoodItemActivitySodiumField = Integer.valueOf(addFoodItemActivitySodiumField);
                CharSequence rawAddFoodItemActivityPotassiumField = addFoodItemActivityPotassiumField.getText();
                String addFoodItemActivityPotassiumField = rawAddFoodItemActivityPotassiumField.toString();
                int parsedAddFoodItemActivityPotassiumField = Integer.valueOf(addFoodItemActivityPotassiumField);
                CharSequence rawAddFoodItemActivityCelluloseField = addFoodItemActivityCelluloseField.getText();
                String addFoodItemActivityCelluloseField = rawAddFoodItemActivityCelluloseField.toString();
                int parsedAddFoodItemActivityCelluloseField = Integer.valueOf(addFoodItemActivityCelluloseField);
                CharSequence rawAddFoodItemActivitySugarField = addFoodItemActivitySugarField.getText();
                String addFoodItemActivitySugarField = rawAddFoodItemActivitySugarField.toString();
                int parsedAddFoodItemActivitySugarField = Integer.valueOf(addFoodItemActivitySugarField);
                CharSequence rawAddFoodItemActivityAField = addFoodItemActivityAField.getText();
                String addFoodItemActivityAField = rawAddFoodItemActivityAField.toString();
                int parsedAddFoodItemActivityAField = Integer.valueOf(addFoodItemActivityAField);
                CharSequence rawAddFoodItemActivityCField = addFoodItemActivityCField.getText();
                String addFoodItemActivityCField = rawAddFoodItemActivityCField.toString();
                int parsedAddFoodItemActivityCField = Integer.valueOf(addFoodItemActivityCField);
                CharSequence rawAddFoodItemActivityCalciumField = addFoodItemActivityCalciumField.getText();
                String addFoodItemActivityCalciumField = rawAddFoodItemActivityCalciumField.toString();
                int parsedAddFoodItemActivityCalciumField = Integer.valueOf(addFoodItemActivityCalciumField);
                CharSequence rawAddFoodItemActivityIronField = addFoodItemActivityIronField.getText();
                String addFoodItemActivityIronField = rawAddFoodItemActivityIronField.toString();
                int parsedAddFoodItemActivityIronField = Integer.valueOf(addFoodItemActivityIronField);

                db.execSQL("INSERT INTO \"food_items\"(name, callories, total_carbs, total_fats, protein, saturated_fats, trans_fats, cholesterol, sodium, potassium, cellulose, sugar, a, c, calcium, iron, portions, type) VALUES (\"" + addFoodItemActivityProductNameContent + "\", " + parsedAddFoodItemActivityCalloriesFieldContent + ", " + parsedAddFoodItemActivityTotalCarbsFieldContent + ", " + parsedAddFoodItemActivityTotalFatsField + ", " + parsedAddFoodItemActivityProteinField + ", " + parsedAddFoodItemActivitySaturatedFatsField + ", " + parsedAddFoodItemActivityTransFatsField + ", " + parsedAddFoodItemActivityCholesterolField + ", " + parsedAddFoodItemActivitySodiumField + ", " + parsedAddFoodItemActivityPotassiumField + ", " + parsedAddFoodItemActivityCelluloseField + ", " + parsedAddFoodItemActivitySugarField + ", " + parsedAddFoodItemActivityAField + ", " + parsedAddFoodItemActivityCField + ", " + parsedAddFoodItemActivityCalciumField + ", " + parsedAddFoodItemActivityIronField + ", " + 1 + ", \"" + foodType +  "\");");
                Intent intent = new Intent(AddFoodItemActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                AddFoodItemActivity.this.startActivity(intent);
            }
        });
        addFoodItemActivityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFoodItemActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                AddFoodItemActivity.this.startActivity(intent);
            }
        });
        addFoodItemActivityExpandFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(isUnVisible);
                addFoodItemActivityBodyExpandMenu.setVisibility(isVisible);
            }
        });
    }

}

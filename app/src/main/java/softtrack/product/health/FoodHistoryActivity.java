package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodHistoryActivity extends AppCompatActivity {

    public ImageButton foodHistoryActivityHeaderBackBtn;
    public String foodType;
    public Button foodHistoryActivityNextBtn;
    public int foodsCallories;
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

                String selectedMealContent = foodType;
                db.execSQL("INSERT INTO \"food_records\"(type) VALUES (\"" + selectedMealContent + "\");");

                Intent intent = new Intent(FoodHistoryActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                FoodHistoryActivity.this.startActivity(intent);

            }
        });
    }

}

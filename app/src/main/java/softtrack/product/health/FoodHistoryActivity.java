package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodHistoryActivity extends AppCompatActivity {

    public ImageButton foodHistoryActivityHeaderBackBtn;
    public String foodType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_history);
        initialize();
    }

    public void initialize() {
        foodHistoryActivityHeaderBackBtn = findViewById(R.id.food_history_activity_header_back_btn);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        foodType = extras.getString("foodType");
        foodHistoryActivityHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodHistoryActivity.this, FoodItemsActivity.class);
                intent.putExtra("foodType", foodType);
                FoodHistoryActivity.this.startActivity(intent);
            }
        });
    }

}

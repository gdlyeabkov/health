package softtrack.product.health;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity {

    public Button foodActivityAddRecordBtn;
    public ImageButton foodActivityHeaderAsideBackBtn;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;
    public ImageButton foodActivityHeaderArticleAuxBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        foodActivityAddRecordBtn = findViewById(R.id.food_activity_add_record_btn);
        foodActivityHeaderAsideBackBtn = findViewById(R.id.food_activity_header_aside_back_btn);
        foodActivityHeaderArticleAuxBtn = findViewById(R.id.food_activity_header_article_aux_btn);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Bundle extras = getIntent().getExtras();
        boolean isAddRecord = extras.getBoolean("isAddRecord");
        if (isAddRecord) {
            showFoodDialog();
        }
        foodActivityAddRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFoodDialog();
            }
        });
        foodActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, MainActivity.class);
                FoodActivity.this.startActivity(intent);
            }
        });
        foodActivityHeaderArticleAuxBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 801, Menu.NONE, "Установить целейнорму");
                contextMenu.add(Menu.NONE, 802, Menu.NONE, "Мое питание");
                contextMenu.add(Menu.NONE, 803, Menu.NONE, "О \"Питании и диете\"");
            }
        });
    }

    public void showFoodDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FoodActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_record_food_data, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RadioGroup mealsGroup = (RadioGroup) dialogView.findViewById(R.id.food_activity_meal);
                int selectedMealId = mealsGroup.getCheckedRadioButtonId();
                @SuppressLint("ResourceType") boolean isFoodTypeSelected = mealsGroup.getCheckedRadioButtonId() > 0;
                if (isFoodTypeSelected) {
                    RadioButton selectedMeal = dialogView.findViewById(selectedMealId);
                    CharSequence rawSelectedMealContent = selectedMeal.getText();
                    String selectedMealContent = rawSelectedMealContent.toString();
                    // db.execSQL("INSERT INTO \"food_records\"(type) VALUES (\"" + selectedMealContent + "\");");

                    // здесь
                    Intent intent = new Intent(FoodActivity.this, FoodItemsActivity.class);
                    intent.putExtra("foodType", selectedMealContent);
                    FoodActivity.this.startActivity(intent);

                }
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Прием пищи");
        alert.show();
    }

}

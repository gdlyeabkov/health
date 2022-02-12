package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddExerciseActivity extends AppCompatActivity {

    public ImageButton addExerciseActivityHeaderAsideBackBtn;
    public LinearLayout addExerciseActivityBody;
    public LinearLayout addExerciseActivityFooter;
    public ArrayList<CheckBox> addExerciseActivityBodyItemSelectors;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        addExerciseActivityHeaderAsideBackBtn = findViewById(R.id.add_exercise_activity_header_aside_back_btn);
        addExerciseActivityBody = findViewById(R.id.add_exercise_activity_body);
        addExerciseActivityFooter = findViewById(R.id.add_exercise_activity_footer);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        addExerciseActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddExerciseActivity.this, ExercisesListActivity.class);
                AddExerciseActivity.this.startActivity(intent);
            }
        });
        addExerciseActivityFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CheckBox addExerciseActivityBodyItemSelectorsItem : addExerciseActivityBodyItemSelectors) {
                    boolean isSelectorChecked = addExerciseActivityBodyItemSelectorsItem.isChecked();
                    if (isSelectorChecked) {
                        CharSequence rawAddExerciseActivityBodyItemSelectorsItemData = addExerciseActivityBodyItemSelectorsItem.getContentDescription();
                        String addExerciseActivityBodyItemSelectorsItemData = rawAddExerciseActivityBodyItemSelectorsItemData.toString();
                        int id = Integer.valueOf(addExerciseActivityBodyItemSelectorsItemData);
                        boolean isActivated = true;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("is_activated", isActivated);
                        db.update("exercises", contentValues, "_id = ?", new String[] { Integer.toString(id) });
                    }
                }
                Intent intent = new Intent(AddExerciseActivity.this, ExercisesListActivity.class);
                AddExerciseActivity.this.startActivity(intent);
            }
        });
        drawExercises();
    }

    public void drawExercises() {
        addExerciseActivityBody.removeAllViews();
        Cursor exercisesCursor = db.rawQuery("Select * from exercises", null);
        exercisesCursor.moveToFirst();
        long countExercises = DatabaseUtils.queryNumEntries(db, "exercises");
        addExerciseActivityBodyItemSelectors = new ArrayList<CheckBox>();
        for (int exercisesCursorIndex = 0; exercisesCursorIndex < countExercises; exercisesCursorIndex++) {
            int isRawActivated = exercisesCursor.getInt(1);
            boolean isActivated = isRawActivated == 1;
            boolean isNotActivated = !isActivated;
            if (isNotActivated) {
                int id = 1;
                id = exercisesCursor.getInt(0);
                String name = "Ходьба";
                name = exercisesCursor.getString(2);
                int logo = R.drawable.walk_logo;
                logo = exercisesCursor.getInt(3);
                LinearLayout addExerciseActivityBodyItem = new LinearLayout(AddExerciseActivity.this);
                addExerciseActivityBodyItem.setOrientation(LinearLayout.HORIZONTAL);
                CheckBox addExerciseActivityBodyItemSelector = new CheckBox(AddExerciseActivity.this);
                String rawId = String.valueOf(id);
                addExerciseActivityBodyItemSelector.setContentDescription(rawId);
                addExerciseActivityBodyItemSelectors.add(addExerciseActivityBodyItemSelector);
                addExerciseActivityBodyItemSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        int countCheckedSelectors = 0;
                        for (CheckBox addExerciseActivityBodyItemSelectorsItem : addExerciseActivityBodyItemSelectors) {
                            boolean isSelectorChecked = addExerciseActivityBodyItemSelectorsItem.isChecked();
                            if (isSelectorChecked) {
                                countCheckedSelectors++;
                            }
                        }
                        boolean isSelectionEmpty = countCheckedSelectors <= 0;
                        if (isSelectionEmpty) {
                            addExerciseActivityFooter.setVisibility(View.GONE);
                        } else {
                            addExerciseActivityFooter.setVisibility(View.VISIBLE);
                        }
                    }
                });
                LinearLayout.LayoutParams addExerciseActivityBodyItemSelectorLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                addExerciseActivityBodyItemSelectorLayoutParams.setMargins(25, 10, 250, 0);
                addExerciseActivityBodyItemSelector.setLayoutParams(addExerciseActivityBodyItemSelectorLayoutParams);
                addExerciseActivityBodyItem.addView(addExerciseActivityBodyItemSelector);
                LinearLayout addExerciseActivityBodyItemAside = new LinearLayout(AddExerciseActivity.this);
                ImageView addExerciseActivityBodyItemAsideIcon = new ImageView(AddExerciseActivity.this);
                LinearLayout.LayoutParams addExerciseActivityBodyItemAsideIconLayoutParams = new LinearLayout.LayoutParams(125, 125);
                addExerciseActivityBodyItemAsideIconLayoutParams.setMargins(0, 10, 0, 0);
                addExerciseActivityBodyItemAsideIcon.setLayoutParams(addExerciseActivityBodyItemAsideIconLayoutParams);
                addExerciseActivityBodyItemAsideIcon.setImageResource(logo);
                addExerciseActivityBodyItemAside.addView(addExerciseActivityBodyItemAsideIcon);
                TextView addExerciseActivityBodyItemAsideLabel = new TextView(AddExerciseActivity.this);
                addExerciseActivityBodyItemAsideLabel.setText(name);
                addExerciseActivityBodyItemAsideLabel.setTextSize(20);
                LinearLayout.LayoutParams addExerciseActivityBodyItemAsideLabelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                addExerciseActivityBodyItemAsideLabelLayoutParams.setMargins(25, 10, 0, 0);
                addExerciseActivityBodyItemAsideLabel.setLayoutParams(addExerciseActivityBodyItemAsideLabelLayoutParams);
                addExerciseActivityBodyItemAside.addView(addExerciseActivityBodyItemAsideLabel);
                addExerciseActivityBodyItem.addView(addExerciseActivityBodyItemAside);
                ImageButton addExerciseActivityBodyItemFavoriteBtn = new ImageButton(AddExerciseActivity.this);
                addExerciseActivityBodyItemFavoriteBtn.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams addExerciseActivityBodyItemFavoriteBtnLayoutParams = new LinearLayout.LayoutParams(175, 175);
                addExerciseActivityBodyItemFavoriteBtnLayoutParams.setMargins(500, 0, 0, 0);
                addExerciseActivityBodyItemFavoriteBtn.setLayoutParams(addExerciseActivityBodyItemFavoriteBtnLayoutParams);

                addExerciseActivityBodyItemFavoriteBtn.setContentDescription(rawId);
                addExerciseActivityBodyItemFavoriteBtn.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.Q)
                    @Override
                    public void onClick(View view) {
                        CharSequence rawId =  view.getContentDescription();
                        String parsedRawId = rawId.toString();
                        int id = Integer.valueOf(parsedRawId);
                        ImageButton favoriteBtn = (ImageButton) view;
                        View exercise = (View) favoriteBtn.getParent();
                        CharSequence rawExerciseData = exercise.getContentDescription();
                        String exerciseData = rawExerciseData.toString();
                        boolean isFavorite = !Boolean.valueOf(exerciseData);
                        ContentValues contentValues = new ContentValues();
                        Log.d("debug", Boolean.toString(isFavorite));
                        contentValues.put("is_favorite", isFavorite);
                        db.update("exercises", contentValues, "_id = ?", new String[] { Integer.toString(id) });
                        drawExercises();
                    }
                });
                addExerciseActivityBody.addView(addExerciseActivityBodyItem);
            }
            exercisesCursor.moveToNext();
        }
    }

}

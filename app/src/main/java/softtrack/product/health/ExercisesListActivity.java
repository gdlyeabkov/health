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
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ExercisesListActivity extends AppCompatActivity {

    public ImageButton exercisesListActivityHeaderAsideBackBtn;
    public Button exercisesListActivityAddExerciseBtn;
    public LinearLayout exercisesListActivityBody;
    public ArrayList<CheckBox> exercisesListActivityBodySelectors;
    public LinearLayout exercisesListActivityFooter;
    public ArrayList<ImageButton> exercisesListActivityBodyFavoriteBtns;
    public TextView exercisesListActivityHeaderLabel;
    public boolean isSelectionMode = false;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        exercisesListActivityHeaderAsideBackBtn = findViewById(R.id.exercises_list_activity_header_aside_back_btn);
        exercisesListActivityAddExerciseBtn = findViewById(R.id.exercises_list_activity_add_exercise_btn);
        exercisesListActivityBody = findViewById(R.id.exercises_list_activity_body);
        exercisesListActivityFooter = findViewById(R.id.exercises_list_activity_footer);
        exercisesListActivityHeaderLabel = findViewById(R.id.exercises_list_activity_header_label);
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        exercisesListActivityHeaderAsideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExercisesListActivity.this, MainActivity.class);
                ExercisesListActivity.this.startActivity(intent);
            }
        });
        exercisesListActivityAddExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExercisesListActivity.this, AddExerciseActivity.class);
                ExercisesListActivity.this.startActivity(intent);
            }
        });
        exercisesListActivityFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CheckBox addExerciseActivityBodyItemSelectorsItem : exercisesListActivityBodySelectors) {
                    boolean isSelectorChecked = addExerciseActivityBodyItemSelectorsItem.isChecked();
                    if (isSelectorChecked) {
                        CharSequence rawAddExerciseActivityBodyItemSelectorsItemData = addExerciseActivityBodyItemSelectorsItem.getContentDescription();
                        String addExerciseActivityBodyItemSelectorsItemData = rawAddExerciseActivityBodyItemSelectorsItemData.toString();
                        int id = Integer.valueOf(addExerciseActivityBodyItemSelectorsItemData);
                        boolean isActivated = false;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("is_activated", isActivated);
                        db.update("exercises", contentValues, "_id = ?", new String[] { Integer.toString(id) });
                    }
                    //addExerciseActivityBodyItemSelectorsItem.setVisibility(View.GONE);
                    drawExercises();
                }
                exercisesListActivityFooter.setVisibility(View.GONE);
                exercisesListActivityAddExerciseBtn.setVisibility(View.VISIBLE);
            }
        });
        drawExercises();
    }

    public void drawExercises() {
        exercisesListActivityBody.removeAllViews();
        Cursor exercisesCursor = db.rawQuery("Select * from exercises", null);
        exercisesCursor.moveToFirst();
        long countExercises = DatabaseUtils.queryNumEntries(db, "exercises");
        exercisesListActivityBodySelectors = new ArrayList<CheckBox>();
        exercisesListActivityBodyFavoriteBtns = new ArrayList<ImageButton>();
        for (int exercisesCursorIndex = 0; exercisesCursorIndex < countExercises; exercisesCursorIndex++) {
            int isRawActivated = exercisesCursor.getInt(1);
            boolean isActivated = isRawActivated == 1;
            if (isActivated) {
                int id = 1;
                id = exercisesCursor.getInt(0);
                String name = "Ходьба";
                name = exercisesCursor.getString(2);
                int logo = R.drawable.walk_logo;
                logo = exercisesCursor.getInt(3);
                int isRawFavorite = exercisesCursor.getInt(4);
                boolean isFavorite = isRawFavorite == 1;
                LinearLayout exercisesListActivityBodyItem = new LinearLayout(ExercisesListActivity.this);
                exercisesListActivityBodyItem.setContentDescription(String.valueOf(isFavorite));
                exercisesListActivityBodyItem.setOrientation(LinearLayout.HORIZONTAL);
                boolean isNotFavorite = !isFavorite;
                if (isNotFavorite) {
                    exercisesListActivityBodyItem.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            exercisesListActivityFooter.setVisibility(View.VISIBLE);
                            exercisesListActivityAddExerciseBtn.setVisibility(View.GONE);
                            for (CheckBox exercisesListActivityBodySelector : exercisesListActivityBodySelectors) {
                                exercisesListActivityBodySelector.setVisibility(View.VISIBLE);
                            }
                            for (ImageButton exercisesListActivityBodyFavoriteBtn : exercisesListActivityBodyFavoriteBtns) {
                                exercisesListActivityBodyFavoriteBtn.setVisibility(View.GONE);
                            }
                            LinearLayout currentExercise = (LinearLayout) view;
                            CheckBox currentSelector = ((CheckBox)(currentExercise.getChildAt(0)));
                            currentSelector.setChecked(true);
                        }
                    });
                }
                String rawId = String.valueOf(id);
                CheckBox exercisesListActivityBodyItemSelector = new CheckBox(ExercisesListActivity.this);
                exercisesListActivityBodyItemSelector.setVisibility(View.GONE);
                exercisesListActivityBodyItemSelector.setContentDescription(rawId);
                if (isFavorite) {
                    exercisesListActivityBodyItemSelector.setEnabled(false);
                }
                exercisesListActivityBodyItemSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        int countCheckedSelectors = 0;
                        for (CheckBox addExerciseActivityBodyItemSelectorsItem : exercisesListActivityBodySelectors) {
                            boolean isSelectorChecked = addExerciseActivityBodyItemSelectorsItem.isChecked();
                            if (isSelectorChecked) {
                                countCheckedSelectors++;
                            }
                        }
                        boolean isSelectionEmpty = countCheckedSelectors <= 0;
                        isSelectionMode = !isSelectionEmpty;
                        if (isSelectionEmpty) {
                            exercisesListActivityFooter.setVisibility(View.GONE);
                            exercisesListActivityHeaderLabel.setText("Скрыть");
                        } else {
                            exercisesListActivityFooter.setVisibility(View.VISIBLE);
                            String rawCountCheckedSelectors = String.valueOf(countCheckedSelectors);
                            exercisesListActivityHeaderLabel.setText("Выбрано: " + rawCountCheckedSelectors);
                        }
                    }
                });
                exercisesListActivityBodySelectors.add(exercisesListActivityBodyItemSelector);
                exercisesListActivityBodyItem.addView(exercisesListActivityBodyItemSelector);
                LinearLayout exercisesListActivityBodyItemAside = new LinearLayout(ExercisesListActivity.this);
                ImageView exercisesListActivityBodyItemAsideIcon = new ImageView(ExercisesListActivity.this);
                LinearLayout.LayoutParams exercisesListActivityBodyItemAsideIconLayoutParams = new LinearLayout.LayoutParams(125, 125);
                exercisesListActivityBodyItemAsideIconLayoutParams.setMargins(0, 10, 0, 0);
                exercisesListActivityBodyItemAsideIcon.setLayoutParams(exercisesListActivityBodyItemAsideIconLayoutParams);
                exercisesListActivityBodyItemAsideIcon.setImageResource(logo);
                exercisesListActivityBodyItemAside.addView(exercisesListActivityBodyItemAsideIcon);
                TextView exercisesListActivityBodyItemAsideLabel = new TextView(ExercisesListActivity.this);
                exercisesListActivityBodyItemAsideLabel.setText(name);
                exercisesListActivityBodyItemAsideLabel.setTextSize(20);
                LinearLayout.LayoutParams exercisesListActivityBodyItemAsideLabelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                exercisesListActivityBodyItemAsideLabelLayoutParams.setMargins(25, 10, 0, 0);
                exercisesListActivityBodyItemAsideLabel.setLayoutParams(exercisesListActivityBodyItemAsideLabelLayoutParams);
                exercisesListActivityBodyItemAside.addView(exercisesListActivityBodyItemAsideLabel);
                exercisesListActivityBodyItem.addView(exercisesListActivityBodyItemAside);
                // exercisesListActivityBodyItem.addView();
                ImageButton exercisesListActivityBodyItemFavoriteBtn = new ImageButton(ExercisesListActivity.this);
                exercisesListActivityBodyFavoriteBtns.add(exercisesListActivityBodyItemFavoriteBtn);
                if (isFavorite) {
                    exercisesListActivityBodyItemFavoriteBtn.setImageResource(R.drawable.favorite_fill_logo);
                } else {
                    exercisesListActivityBodyItemFavoriteBtn.setImageResource(R.drawable.favorite_logo);
                }
                exercisesListActivityBodyItemFavoriteBtn.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams exercisesListActivityBodyItemFavoriteBtnLayoutParams = new LinearLayout.LayoutParams(175, 175);
                exercisesListActivityBodyItemFavoriteBtnLayoutParams.setMargins(500, 0, 0, 0);
                exercisesListActivityBodyItemFavoriteBtn.setLayoutParams(exercisesListActivityBodyItemFavoriteBtnLayoutParams);
                exercisesListActivityBodyItemFavoriteBtn.setContentDescription(rawId);
                exercisesListActivityBodyItemFavoriteBtn.setOnClickListener(new View.OnClickListener() {
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
                exercisesListActivityBodyItem.addView(exercisesListActivityBodyItemFavoriteBtn);
                exercisesListActivityBody.addView(exercisesListActivityBodyItem);
            }
            exercisesCursor.moveToNext();
        }
    }

    @Override
    public void onBackPressed() {
        if (isSelectionMode) {
            exercisesListActivityFooter.setVisibility(View.GONE);
            exercisesListActivityHeaderLabel.setText("Упражнения");
            for (CheckBox exercisesListActivityBodySelector : exercisesListActivityBodySelectors) {
                exercisesListActivityBodySelector.setVisibility(View.GONE);
            }
            for (ImageButton exercisesListActivityBodyFavoriteBtn : exercisesListActivityBodyFavoriteBtns) {
                exercisesListActivityBodyFavoriteBtn.setVisibility(View.VISIBLE);
            }
        } else {
            super.onBackPressed();
        }
    }
}

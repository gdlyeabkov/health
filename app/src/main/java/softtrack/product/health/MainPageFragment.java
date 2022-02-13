package softtrack.product.health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainPageFragment extends Fragment implements SensorEventListener {

    public MainActivity parentActivity;
    public int waterControlBtnEnabledColor;
    public int waterControlBtnDisabledColor;
    public SensorManager sensorManager;
    public TextView mainPageWalkBlockDataAsideValue = null;
    boolean walkPhase = false;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;
    public HashMap indicators;
    public TextView mainPageFoodBlockCurrentValue;
    public ProgressBar mainPageWalkBlockDataAritcleProgress;
    public TextView mainPageWalkBlockDataAritcleProgressLabel;
    public int maxPercents = 100;
    public int goodWalkIndicatorValue = 6000;
    public Button mainPageBodyCompositionBlockWrapBodyAddBtn;
    public Button mainPageFoodBlockAddRecordBtn;
    public Button mainPageSleepBlockAddRecordBtn;
    public ImageButton mainPageExerciseBlockExercisesWalk;
    public ImageButton mainPageExerciseBlockExercisesRun;
    public ImageButton mainPageExerciseBlockExercisesBicycle;
    public TextView mainPageBodyCompositionBlockWrapFooterWeight;
    public LinearLayout mainPageBodyCompositionBlockWrapFooterData;
    public LinearLayout mainPageBodyCompositionBlockWrapFooter;
    public TextView mainPageBodyCompositionBlockWrapFooterDataWeightLabel;
    public TextView mainPageBodyCompositionBlockWrapFooterDataFatLabel;
    public TextView mainPageBodyCompositionBlockWrapFooterDataMusculatureLabel;
    public TextView mainPageBodyCompositionBlockWrapFooterWeightLabel;
    public TextView mainPageBodyCompositionBlockWrapFooterFatLabel;
    public TextView mainPageBodyCompositionBlockWrapFooterMusculatureLabel;
    public LinearLayout mainPageBodyCompositionBlockWrapFooterFat;
    public LinearLayout mainPageBodyCompositionBlockWrapFooterMusculature;
    public ImageView mainPageBodyCompositionBlockWrapBodyIconFat;
    public ImageView mainPageBodyCompositionBlockWrapBodyIconMusculature;
    public TextView mainPageActiveBlockAsideFooterWalkLabel;
    public ImageButton mainPageExerciseBlockExercisesList;
    public LinearLayout mainPageExerciseBlock;
    public LinearLayout mainPageExerciseBlockActivated;
    public TextView mainPageExerciseBlockActivatedHeaderStartTime;
    public TextView mainPageExerciseBlockActivatedHeaderType;
    public TextView mainPageExerciseBlockActivatedLabel;
    public String oneCharPrefix = "0";
    public String timePartsSeparator = ":";
    public int initialSeconds = 0;
    public int initialMinutes = 0;
    public int countSecondsInMinute = 60;
    public int countMinutesInHour = 60;
    public Timer timer;
    public LinearLayout mainPageExerciseBlockExercises;

    public MainPageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_page, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart() {
        super.onStart();
        try {
            initialize();
        } catch (Exception e) {
            String debugMsg = "Ошибка на уровне инициализации";
            Log.d("debug", debugMsg);
        }
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        parentActivity = (MainActivity) this.getActivity();
        db = parentActivity.openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        waterControlBtnDisabledColor = Color.rgb(150, 150, 150);
        waterControlBtnEnabledColor = Color.rgb(0, 0, 0);
        mainPageWalkBlockDataAritcleProgress = parentActivity.findViewById(R.id.main_page_walk_block_data_aritcle_progress);
        mainPageWalkBlockDataAritcleProgressLabel = parentActivity.findViewById(R.id.main_page_walk_block_data_aritcle_progress_label);
        TextView mainPageWaterBlockDrinkGlasses = parentActivity.findViewById(R.id.main_page_water_block_drink_glasses);
        Button mainPageWaterBlockDrinkGlassesDecreaseBtn = parentActivity.findViewById(R.id.main_page_water_block_drink_glasses_decrease_btn);
        mainPageBodyCompositionBlockWrapBodyAddBtn = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_body_add_btn);
        mainPageFoodBlockAddRecordBtn = parentActivity.findViewById(R.id.main_page_food_block_add_record_btn);
        mainPageSleepBlockAddRecordBtn = parentActivity.findViewById(R.id.main_page_sleep_block_add_record_btn);
        mainPageExerciseBlockExercisesWalk = parentActivity.findViewById(R.id.main_page_exercise_block_exercises_walk);
        mainPageExerciseBlockExercisesRun = parentActivity.findViewById(R.id.main_page_exercise_block_exercises_run);
        mainPageExerciseBlockExercisesBicycle = parentActivity.findViewById(R.id.main_page_exercise_block_exercises_bicycle);
        // mainPageBodyCompositionBlockWrapFooterWeight = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_weight_block);
        mainPageBodyCompositionBlockWrapFooterData = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_data);
        mainPageBodyCompositionBlockWrapFooter = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer);
        mainPageBodyCompositionBlockWrapFooterDataWeightLabel = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_data_weight_label);
        mainPageBodyCompositionBlockWrapFooterDataFatLabel = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_data_fat_label);
        mainPageBodyCompositionBlockWrapFooterDataMusculatureLabel = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_data_musculature_label);
        mainPageBodyCompositionBlockWrapFooterWeightLabel = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_weight_label);
        mainPageBodyCompositionBlockWrapFooterFatLabel = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_fat_label);
        mainPageBodyCompositionBlockWrapFooterMusculatureLabel = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_musculature_label);
        mainPageBodyCompositionBlockWrapFooterFat = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_fat);
        mainPageBodyCompositionBlockWrapFooterMusculature = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_footer_musculature);
        mainPageBodyCompositionBlockWrapBodyIconFat = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_body_icon_fat);
        mainPageBodyCompositionBlockWrapBodyIconMusculature = parentActivity.findViewById(R.id.main_page_body_composition_block_wrap_body_icon_musculature);
        mainPageActiveBlockAsideFooterWalkLabel = parentActivity.findViewById(R.id.main_page_active_block_aside_footer_walk_label);
        mainPageExerciseBlockExercisesList = parentActivity.findViewById(R.id.main_page_exercise_block_exercises_list);
        mainPageExerciseBlock = parentActivity.findViewById(R.id.main_page_exercise_block);
        mainPageExerciseBlockActivated = parentActivity.findViewById(R.id.main_page_exercise_block_activated);
        mainPageExerciseBlockActivatedHeaderStartTime = parentActivity.findViewById(R.id.main_page_exercise_block_activated_header_start_time);
        mainPageExerciseBlockActivatedHeaderType = parentActivity.findViewById(R.id.main_page_exercise_block_activated_header_type);
        mainPageExerciseBlockActivatedLabel = parentActivity.findViewById(R.id.main_page_exercise_block_activated_label);
        mainPageExerciseBlockExercises = parentActivity.findViewById(R.id.main_page_exercise_block_exercises);
        mainPageWaterBlockDrinkGlassesDecreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence rawGlassesCount = mainPageWaterBlockDrinkGlasses.getText();
                String parsedGlassesCount = rawGlassesCount.toString();
                int glassesCount = Integer.valueOf(parsedGlassesCount);
                boolean isCanDecrease = glassesCount >= 1;
                if (isCanDecrease) {
                    glassesCount -= 1;
                    String updatedRawGlassesCount = String.valueOf(glassesCount);
                    mainPageWaterBlockDrinkGlasses.setText(updatedRawGlassesCount);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("water", glassesCount);
                    db.update("indicators", contentValues, "_id = 1", new String[] {  });
                    boolean isCanNotDecrease = glassesCount == 0;
                    if (isCanNotDecrease) {
                        mainPageWaterBlockDrinkGlassesDecreaseBtn.setEnabled(false);
                        mainPageWaterBlockDrinkGlassesDecreaseBtn.setTextColor(waterControlBtnDisabledColor);
                    }
                }
            }
        });
        Button mainPageWaterBlockDrinkGlassesIncreaseBtn = parentActivity.findViewById(R.id.main_page_water_block_drink_glasses_increase_btn);
        mainPageWaterBlockDrinkGlassesIncreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDecreaseBtnEnabled = mainPageWaterBlockDrinkGlassesDecreaseBtn.isEnabled();
                boolean isDecreaseBtnDisabled = !isDecreaseBtnEnabled;
                if (isDecreaseBtnDisabled) {
                    mainPageWaterBlockDrinkGlassesDecreaseBtn.setEnabled(true);
                    mainPageWaterBlockDrinkGlassesDecreaseBtn.setTextColor(waterControlBtnEnabledColor);
                }
                CharSequence rawGlassesCount = mainPageWaterBlockDrinkGlasses.getText();
                String parsedGlassesCount = rawGlassesCount.toString();
                int glassesCount = Integer.valueOf(parsedGlassesCount);
                glassesCount += 1;
                String updatedRawGlassesCount = String.valueOf(glassesCount);
                mainPageWaterBlockDrinkGlasses.setText(updatedRawGlassesCount);
                ContentValues contentValues = new ContentValues();
                contentValues.put("water", glassesCount);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
            }
        });
        LinearLayout mainPageActiveBlock = parentActivity.findViewById(R.id.main_page_active_block);
        mainPageActiveBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, ActiveActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageWalkBlock = parentActivity.findViewById(R.id.main_page_walk_block);
        mainPageWalkBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, WalkActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageExerciseBlock = parentActivity.findViewById(R.id.main_page_exercise_block);
        mainPageExerciseBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, ExerciseActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageFoodBlock = parentActivity.findViewById(R.id.main_page_food_block);
        mainPageFoodBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, FoodActivity.class);
                intent.putExtra("isAddRecord", false);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageSleepBlock = parentActivity.findViewById(R.id.main_page_sleep_block);
        mainPageSleepBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, SleepActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageBodyCompositionBlock = parentActivity.findViewById(R.id.main_page_body_composition_block);
        mainPageBodyCompositionBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, BodyActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        LinearLayout mainPageWaterBlock = parentActivity.findViewById(R.id.main_page_water_block);
        mainPageWaterBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, WaterActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        mainPageWalkBlockDataAsideValue = parentActivity.findViewById(R.id.main_page_walk_block_data_aside_value);
        sensorManager = (SensorManager) parentActivity.getSystemService(Context.SENSOR_SERVICE);
        indicators = new HashMap<String, Object>();
        mainPageFoodBlockCurrentValue = parentActivity.findViewById(R.id.main_page_food_block_current_value);
        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        indicatorsCursor.moveToFirst();
        int water = indicatorsCursor.getInt(1);
        int walk = indicatorsCursor.getInt(2);
        int food = indicatorsCursor.getInt(3);
        int rawIsExerciseEnabled = indicatorsCursor.getInt(4);
        String exerciseStartTime = indicatorsCursor.getString(5);
        String exerciseType = indicatorsCursor.getString(6);
        indicators.put("water", water);
        indicators.put("walk", walk);
        indicators.put("food", food);
        String rawWalk = String.valueOf(walk);
        mainPageWalkBlockDataAsideValue.setText(rawWalk);
        int walkProgress = walk / (goodWalkIndicatorValue / maxPercents);
        mainPageWalkBlockDataAritcleProgress.setProgress(walkProgress);
        String rawWalkProgress = String.valueOf(walkProgress);
        String rawWalkProgressMsg = rawWalkProgress + "%";
        mainPageWalkBlockDataAritcleProgressLabel.setText(rawWalkProgressMsg);
        String rawFood = String.valueOf(food);
        mainPageFoodBlockCurrentValue.setText(rawFood);
        String rawWater = String.valueOf(water);
        mainPageWaterBlockDrinkGlasses.setText(rawWater);
        mainPageBodyCompositionBlockWrapBodyAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, RecordWeightDataActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        mainPageFoodBlockAddRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, FoodActivity.class);
                intent.putExtra("isAddRecord", true);
                parentActivity.startActivity(intent);
            }
        });
        mainPageSleepBlockAddRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, RecordSleepDataActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        mainPageExerciseBlockExercisesWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, RecordExerciseActivity.class);
                intent.putExtra("type", "Ходьба");
                parentActivity.startActivity(intent);
            }
        });
        mainPageExerciseBlockExercisesRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, RecordExerciseActivity.class);
                intent.putExtra("type", "Бег");
                parentActivity.startActivity(intent);
            }
        });
        mainPageExerciseBlockExercisesBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, RecordExerciseActivity.class);
                intent.putExtra("type", "Велоспорт");
                parentActivity.startActivity(intent);
            }
        });

        Cursor bodyRecordsCursor = db.rawQuery("Select * from body_records", null);
        bodyRecordsCursor.moveToLast();
        long bodyRecordsCount = DatabaseUtils.queryNumEntries(db, "body_records");
        boolean isHaveBodyData = bodyRecordsCount >= 1;
        if (isHaveBodyData) {
            int musculature = bodyRecordsCursor.getInt(2);
            int fat = bodyRecordsCursor.getInt(3);
            float weight = bodyRecordsCursor.getFloat(4);
            String rawWeight = String.valueOf(weight);
            String rawFat = String.valueOf(fat);
            String rawMusculature = String.valueOf(musculature);
            /*mainPageBodyCompositionBlockWrapFooterData.setVisibility(View.VISIBLE);
            mainPageBodyCompositionBlockWrapFooter.setVisibility(View.GONE);
            mainPageBodyCompositionBlockWrapFooterDataWeightLabel.setText(rawWeight);
            mainPageBodyCompositionBlockWrapFooterDataFatLabel.setText(rawFat);
            mainPageBodyCompositionBlockWrapFooterDataMusculatureLabel.setText(rawMusculature);*/
            mainPageBodyCompositionBlockWrapFooterWeightLabel.setText(rawWeight);
            boolean isHaveFat = fat >= 1;
            if (isHaveFat) {
                mainPageBodyCompositionBlockWrapFooterFat.setVisibility(View.VISIBLE);
                mainPageBodyCompositionBlockWrapFooterFatLabel.setText(rawFat);
                mainPageBodyCompositionBlockWrapBodyIconFat.setVisibility(View.VISIBLE);
            }
            boolean isHaveMusculature = musculature >= 1;
            if (isHaveMusculature) {
                mainPageBodyCompositionBlockWrapFooterMusculature.setVisibility(View.VISIBLE);
                mainPageBodyCompositionBlockWrapFooterMusculatureLabel.setText(rawMusculature);
                mainPageBodyCompositionBlockWrapBodyIconMusculature.setVisibility(View.VISIBLE);
            }
        }
        mainPageActiveBlockAsideFooterWalkLabel.setText(rawWalk);
        mainPageExerciseBlockExercisesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, ExercisesListActivity.class);
                parentActivity.startActivity(intent);
            }
        });

        boolean isExerciseEnabled = rawIsExerciseEnabled == 1;
        if (isExerciseEnabled) {
            mainPageExerciseBlock.setVisibility(View.GONE);
            mainPageExerciseBlockActivated.setVisibility(View.VISIBLE);
            mainPageExerciseBlockActivatedHeaderStartTime.setText(exerciseStartTime);
            mainPageExerciseBlockActivatedHeaderType.setText(exerciseType);
            detectExerciseDuration();
        }
        mainPageExerciseBlockActivated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(parentActivity, RecordStartedExerciseActivity.class);
                intent.putExtra("foodType", "Ходьба");
                parentActivity.startActivity(intent);
            }
        });
        Cursor exercisesCursor = db.rawQuery("Select * from exercises", null);
        exercisesCursor.moveToFirst();
        long countExercises = DatabaseUtils.queryNumEntries(db, "exercises");
        int favoritedExercisesCounter = 0;
        for (long exercisesCursorIndex = 0; exercisesCursorIndex < countExercises; exercisesCursorIndex++) {
            String name = exercisesCursor.getString(2);
            int isRawFavorite = exercisesCursor.getInt(4);
            boolean isFavorite = isRawFavorite == 1;
            boolean isCanCounter = favoritedExercisesCounter <= 2;
            boolean isAddExercise = isFavorite && isCanCounter;
            if (isAddExercise) {
                favoritedExercisesCounter++;
                ImageButton logo = new ImageButton(parentActivity);
                logo.setContentDescription(name);
                logo.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams logoLayoutParams = new LinearLayout.LayoutParams(225, 225);
                logoLayoutParams.setMargins(125, 0, 0, 0);
                logo.setLayoutParams(logoLayoutParams);
                //int logoSource = exercisesCursor.getInt(3);
                int logoSource = 0;
                if (name == "Ходьба") {
                    logoSource = R.drawable.walk_logo;
                } else if (name == "Бег") {
                    logoSource = R.drawable.run_logo;
                } else if (name == "Велоспорт") {
                    logoSource = R.drawable.bicycle_logo;
                } else if (name == "Поход") {
                    logoSource = R.drawable.camp_logo;
                } else if (name == "Йога") {
                    logoSource = R.drawable.yoga_logo;
                } else if (name == "Плавание") {
                    logoSource = R.drawable.swim_logo;
                }
                logo.setImageResource(logoSource);
                mainPageExerciseBlockExercises.addView(logo, 0);
                logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence rawLogoData = view.getContentDescription();
                        String logoData = rawLogoData.toString();
                        stopTimer();
                        Intent intent = new Intent(parentActivity, RecordExerciseActivity.class);
                        intent.putExtra("type", logoData);
                        parentActivity.startActivity(intent);
                    }
                });
            }
            exercisesCursor.moveToNext();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] offsets = sensorEvent.values;
        float verticalOffset = offsets[1];
        boolean isNotWalkPhase = !walkPhase;
        boolean isVerticalOffsetLess = verticalOffset < 4;
        boolean isFirstWalkPhase = isNotWalkPhase && isVerticalOffsetLess;
        boolean isWalkPhase = walkPhase;
        boolean isVerticalOffsetGreather = verticalOffset > 4;
        boolean isSecondWalkPhase = isWalkPhase && isVerticalOffsetGreather;
        if (isFirstWalkPhase) {
            walkPhase = true;
        } else if (isSecondWalkPhase) {
            walkPhase = false;
            detectWalk("sensorName", 0);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // проще сделать это в onSensorChanged
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            boolean isAccelerometerExists = accelerometer != null;
            if (isAccelerometerExists) {
                sensorManager.registerListener(this, accelerometer,
                        SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
            }
        } catch (Exception e) {
            String debugMsg = "Ошибка на уровне сенсора";
            Log.d("debug", debugMsg);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            sensorManager.unregisterListener(this);
        } catch (Exception e) {
            String debugMsg = "Ошибка на уровне сенсора";
            Log.d("debug", debugMsg);
        }
    }

    public void detectWalk(String sensorName, int offset) {
        // Log.d("debug", "Сенсор по имени " + sensorName + " сейчас в значении " + String.valueOf(offset));
        boolean isDetectOffset = offset <= 3;
        boolean isMock = true;
        boolean isDetectedWalk = isDetectOffset || isMock;
        boolean isMainPageWalkBlockDataAsideValueExists = mainPageWalkBlockDataAsideValue != null;
        boolean isAddWalk = isDetectedWalk && isMainPageWalkBlockDataAsideValueExists;
        if (isAddWalk) {
            CharSequence rawCurrentWalkData = mainPageWalkBlockDataAsideValue.getText();
            String currentWalkData = rawCurrentWalkData.toString();
            int parsedCurrentWalkData = Integer.parseInt(currentWalkData);
            parsedCurrentWalkData += 1;
            boolean isCanAddWalk = parsedCurrentWalkData < goodWalkIndicatorValue;
            if (isCanAddWalk) {
                String rawWalkData = String.valueOf(parsedCurrentWalkData);
                mainPageWalkBlockDataAsideValue.setText(rawWalkData);
                int walkProgress = parsedCurrentWalkData / (goodWalkIndicatorValue / maxPercents);
                mainPageWalkBlockDataAritcleProgress.setProgress(walkProgress);
                String rawWalkProgress = String.valueOf(walkProgress);
                String rawWalkProgressMsg = rawWalkProgress + "%";
                mainPageWalkBlockDataAritcleProgressLabel.setText(rawWalkProgressMsg);
                ContentValues contentValues = new ContentValues();
                contentValues.put("walk", parsedCurrentWalkData);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
                mainPageActiveBlockAsideFooterWalkLabel.setText(rawWalkData);
            }
        }
    }

    public void detectExerciseDuration() {
        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Cursor foodRecordsCursor = db.rawQuery("Select * from indicators", null);
                foodRecordsCursor.moveToFirst();
                String rawDuration = foodRecordsCursor.getString(7);
                mainPageExerciseBlockActivatedLabel.setText(rawDuration);
            }
        }, 0, 1000);*/
        Cursor foodRecordsCursor = db.rawQuery("Select * from indicators", null);
        foodRecordsCursor.moveToFirst();
        String rawDuration = foodRecordsCursor.getString(7);
        mainPageExerciseBlockActivatedLabel.setText(rawDuration);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                CharSequence rawSecondsText = mainPageExerciseBlockActivatedLabel.getText();
                String secondsText = rawSecondsText.toString();
                String[] timeParts = secondsText.split(timePartsSeparator);
                String rawHours = timeParts[0];
                String rawMinutes = timeParts[1];
                String rawSeconds = timeParts[2];
                int hours = Integer.valueOf(rawHours);
                int minutes = Integer.valueOf(rawMinutes);
                int seconds = Integer.valueOf(rawSeconds);
                seconds++;
                boolean isToggleSecond = seconds == countSecondsInMinute;
                if (isToggleSecond) {
                    seconds = initialSeconds;
                    minutes++;
                    boolean isToggleHour = minutes == countMinutesInHour;
                    if (isToggleHour) {
                        minutes = initialMinutes;
                        hours++;
                    }
                }
                String updatedHoursText = String.valueOf(hours);
                int countHoursChars = updatedHoursText.length();
                boolean isAddHoursPrefix = countHoursChars == 1;
                if (isAddHoursPrefix) {
                    updatedHoursText = oneCharPrefix + updatedHoursText;
                }
                String updatedMinutesText = String.valueOf(minutes);
                int countMinutesChars = updatedMinutesText.length();
                boolean isAddMinutesPrefix = countMinutesChars == 1;
                if (isAddMinutesPrefix) {
                    updatedMinutesText = oneCharPrefix + updatedMinutesText;
                }
                String updatedSecondsText = String.valueOf(seconds);
                int countSecondsChars = updatedSecondsText.length();
                boolean isAddSecondsPrefix = countSecondsChars == 1;
                if (isAddSecondsPrefix) {
                    updatedSecondsText = oneCharPrefix + updatedSecondsText;
                }
                String currentTime = updatedHoursText + ":" + updatedMinutesText + ":" + updatedSecondsText;
                mainPageExerciseBlockActivatedLabel.setText(currentTime);
                ContentValues contentValues = new ContentValues();
                contentValues.put("exercise_duration", currentTime);
                db.update("indicators", contentValues, "_id = 1", new String[] {  });
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

}

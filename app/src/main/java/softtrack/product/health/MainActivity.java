package softtrack.product.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public ViewPager2 currentTabBody;
    public TabLayout mainTabs;
    public ArrayList<String> tabsHeaders;
    public TextView mainHeaderLabel;
    public Button mainHeaderBtn;
    public boolean isSelectionMode = false;
    public Button elementsControlFooterCancelBtn;
    public Button elementsControlFooterSaveBtn;
    public LinearLayout elementsControlFooter;
    public ImageButton mainPageActiveBlockController;
    public ImageButton mainPageWalkBlockController;
    public ImageButton mainPageExerciseBlockController;
    public ImageButton mainPageFoodBlockController;
    public ImageButton mainPageSleepBlockController;
    public ImageButton mainPageBodyCompositionBlockController;
    public ImageButton mainPageWaterBlockController;
    public LinearLayout mainPageActiveBlock;
    public LinearLayout mainPageWalkBlock;
    public LinearLayout mainPageExerciseBlock;
    public LinearLayout mainPageFoodBlock;
    public LinearLayout mainPageSleepBlock;
    public LinearLayout mainPageBodyCompositionBlock;
    public LinearLayout mainPageWaterBlock;
    public static MainActivity gateway;
    public NotificationCompat.Builder builder;
    public int notificationId = 0;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        gateway = this;
        mainTabs = findViewById(R.id.main_tabs);
        currentTabBody = findViewById(R.id.current_tab_body);
        mainHeaderLabel = findViewById(R.id.main_header_label);
        mainHeaderBtn = findViewById(R.id.main_header_btn);
        elementsControlFooterSaveBtn = findViewById(R.id.elements_control_footer_save_btn);
        elementsControlFooterCancelBtn = findViewById(R.id.elements_control_footer_cancel_btn);
        elementsControlFooter = findViewById(R.id.elements_control_footer);
        initializeTabs();
        initDB();
        setContextMenuForMainPage();
        elementsControlFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideControllers();
                displayHealthItems();
            }
        });
        elementsControlFooterSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideControllers();
                ContentValues contentValues = new ContentValues();
                CharSequence rawControllerData = mainPageActiveBlockController.getContentDescription();
                String controllerData = rawControllerData.toString();
                boolean isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageActiveBlockController = findViewById(R.id.main_page_active_block);
                    mainPageActiveBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 1", new String[] {  });
                contentValues = new ContentValues();
                rawControllerData = mainPageWalkBlockController.getContentDescription();
                controllerData = rawControllerData.toString();
                isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageWalkBlockController = findViewById(R.id.main_page_walk_block);
                    mainPageWalkBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 2", new String[] {  });
                contentValues = new ContentValues();
                rawControllerData = mainPageExerciseBlockController.getContentDescription();
                controllerData = rawControllerData.toString();
                isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageExerciseBlockController = findViewById(R.id.main_page_exercise_block);
                    mainPageExerciseBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 3", new String[] {  });
                contentValues = new ContentValues();
                rawControllerData = mainPageFoodBlockController.getContentDescription();
                controllerData = rawControllerData.toString();
                isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageFoodBlockController = findViewById(R.id.main_page_food_block);
                    mainPageFoodBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 4", new String[] {  });
                contentValues = new ContentValues();
                rawControllerData = mainPageSleepBlockController.getContentDescription();
                controllerData = rawControllerData.toString();
                isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageSleepBlockController = findViewById(R.id.main_page_sleep_block);
                    mainPageSleepBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 5", new String[] {  });
                contentValues = new ContentValues();
                rawControllerData = mainPageBodyCompositionBlockController.getContentDescription();
                controllerData = rawControllerData.toString();
                isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageBodyCompositionBlockController = findViewById(R.id.main_page_body_composition_block);
                    mainPageBodyCompositionBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 6", new String[] {  });
                contentValues = new ContentValues();
                rawControllerData = mainPageWaterBlockController.getContentDescription();
                controllerData = rawControllerData.toString();
                isControllerActivated = true;
                if (controllerData == "minus") {
                    isControllerActivated = true;
                } else if (controllerData == "plus") {
                    isControllerActivated = false;
                    LinearLayout mainPageWaterBlockController = findViewById(R.id.main_page_water_block);
                    mainPageWaterBlockController.setVisibility(View.GONE);
                }
                contentValues.put("is_activated", isControllerActivated);
                db.update("controllers", contentValues, "_id = 7", new String[] {  });
            }
        });
        createNotification(0, 1);
    }

    public void initializeTabs() {
        FragmentManager fm = getSupportFragmentManager();
        ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
        currentTabBody.setAdapter(sa);
        mainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIndex = tab.getPosition();
                currentTabBody.setCurrentItem(tabIndex);
                String tabHeader = tabsHeaders.get(tabIndex);
                mainHeaderLabel.setText(tabHeader);
                boolean isMainPageTab = tabIndex == 0;
                boolean isTogetherTab = tabIndex == 1;
                boolean isFitnesTab = tabIndex == 2;
                boolean isMyPageTab = tabIndex == 3;
                if (isMainPageTab) {
                    setContextMenuForMainPage();
                } else if (isTogetherTab) {
                    mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            MenuItem forYouMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "Для вас");
                            forYouMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    Intent intent = new Intent(MainActivity.this, ForYouActivity.class);
                                    MainActivity.this.startActivity(intent);
                                    return false;
                                }
                            });
                            MenuItem eventsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "События");
                            forYouMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    Intent intent = new Intent(MainActivity.this, ForYouActivity.class);
                                    MainActivity.this.startActivity(intent);
                                    return false;
                                }
                            });
                            MenuItem notificationsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "Уведомления");
                            notificationsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                                    MainActivity.this.startActivity(intent);
                                    return false;
                                }
                            });
                            MenuItem settingsMenuItem = contextMenu.add(Menu.NONE, 201, Menu.NONE, "Настр.");
                            settingsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                    MainActivity.this.startActivity(intent);
                                    return false;
                                }
                            });
                        }
                    });
                } else if (isFitnesTab) {
                    mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            contextMenu.add(Menu.NONE, 301, Menu.NONE, "Направления фитнеса");
                            contextMenu.add(Menu.NONE, 302, Menu.NONE, "Журнал программы");
                            contextMenu.add(Menu.NONE, 303, Menu.NONE, "Для вас");
                            contextMenu.add(Menu.NONE, 304, Menu.NONE, "События");
                            contextMenu.add(Menu.NONE, 305, Menu.NONE, "Уведомления");
                            MenuItem settingsMenuItem = contextMenu.add(Menu.NONE, 306, Menu.NONE, "Настр.");
                            settingsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    goToSettings();
                                    return false;
                                }
                            });
                        }
                    });
                } else if (isMyPageTab) {
                    mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            contextMenu.add(Menu.NONE, 401, Menu.NONE, "Для вас");
                            contextMenu.add(Menu.NONE, 402, Menu.NONE, "События");
                            contextMenu.add(Menu.NONE, 403, Menu.NONE, "Уведомления");
                            MenuItem settingsMenuItem = contextMenu.add(Menu.NONE, 404, Menu.NONE, "Настр.");
                            settingsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    goToSettings();
                                    return false;
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        currentTabBody.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                boolean isMainPage = position == 0;
                if (isMainPage) {
                    mainTabs.selectTab(mainTabs.getTabAt(position));
                    // инициализация если выбрана вкладка MainPage
                }
            }
        });
        tabsHeaders = new ArrayList<String>();
        tabsHeaders.add("Softtrack Здоровье");
        tabsHeaders.add("Together");
        tabsHeaders.add("Фитнес");
        tabsHeaders.add("Моя стр.");
    }

    @SuppressLint("WrongConstant")
    public void initDB() {
        db = openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS indicators (_id INTEGER PRIMARY KEY AUTOINCREMENT, water INTEGER, walk INTEGER, food INTEGER, is_exercise_enabled BOOLEAN, exercise_start_time TEXT, exercise_type TEXT, exercise_duration TEXT, photo TEXT, name TEXT, gender TEXT, growth REAL, weight REAL, birthday TEXT, level TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS exercises (_id INTEGER PRIMARY KEY AUTOINCREMENT, is_activated BOOLEAN, name TEXT, logo INTEGER, is_favorite BOOLEAN);");
        db.execSQL("CREATE TABLE IF NOT EXISTS controllers (_id INTEGER PRIMARY KEY AUTOINCREMENT, is_activated BOOLEAN, name TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS measures (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, value TEXT);");
        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        long countIndicators = DatabaseUtils.queryNumEntries(db, "indicators");
        boolean isPreInstall = countIndicators <= 0;
        if (isPreInstall) {
            AccountManager accountManager = AccountManager.get(getApplicationContext());
            Account[] accounts = accountManager.getAccounts();
            boolean isAccountsDetected = accounts.length >= 1;
            String myAccountName = "";
            if (isAccountsDetected) {
                Account myAccount = accounts[0];
                myAccountName = myAccount.name;
            }
            db.execSQL("INSERT INTO \"indicators\"(water, walk, food, is_exercise_enabled, exercise_start_time, exercise_type, exercise_duration, photo, name, gender, growth, weight, birthday, level) VALUES (" + 0 + ", " + 0 + ", " + 0 + ", " + false + ", \"" + "00:00" + "\", \"" + "Ходьба" + "\", \"" + "00:00:00" + "\", \"" + "" + "\", \"" + myAccountName + "\", \"" + "" + "\", " + 0.0 + ", " + 0.0 + ", \"" + "22.11.2000" + "\", \"Сидячий образ жизни" + "" + "\");");

            db.execSQL("INSERT INTO \"exercises\"(is_activated, name, logo, is_favorite) VALUES (" + true + ", \"" + "Ходьба" + "\", " + R.drawable.walk_logo + ", " + true + ");");
            db.execSQL("INSERT INTO \"exercises\"(is_activated, name, logo, is_favorite) VALUES (" + true + ", \"" + "Бег" + "\", " + R.drawable.run_logo + ", " + true + ");");
            db.execSQL("INSERT INTO \"exercises\"(is_activated, name, logo, is_favorite) VALUES (" + true + ", \"" + "Велоспорт" + "\", " + R.drawable.bicycle_logo + ", " + true + ");");
            db.execSQL("INSERT INTO \"exercises\"(is_activated, name, logo, is_favorite) VALUES (" + false + ", \"" + "Поход" + "\", " + R.drawable.camp_logo + ", " + false + ");");
            db.execSQL("INSERT INTO \"exercises\"(is_activated, name, logo, is_favorite) VALUES (" + false + ", \"" + "Плавание" + "\", " + R.drawable.swim_logo + ", " + false + ");");
            db.execSQL("INSERT INTO \"exercises\"(is_activated, name, logo, is_favorite) VALUES (" + false + ", \"" + "Йога" + "\", " + R.drawable.yoga_logo + ", " + false  + ");");

            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"active\");");
            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"walk\");");
            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"exercise\");");
            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"food\");");
            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"sleep\");");
            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"body\");");
            db.execSQL("INSERT INTO \"controllers\"(is_activated, name) VALUES (true, \"water\");");

            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Рост\", \"см\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Вес\", \"кг\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Температура\", \"℃\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Расстояние\", \"км\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Сахар в крови\", \"мг/дл\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Давление\", \"мм рт. ст.\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"HbA1c\", \"%\");");
            db.execSQL("INSERT INTO \"measures\"(name, value) VALUES (\"Вода\", \"мл\");");

        }
        db.execSQL("CREATE TABLE IF NOT EXISTS body_records (_id INTEGER PRIMARY KEY AUTOINCREMENT, marks TEXT, musculature INTEGER, fat INTEGER, weight REAL, date TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS sleep_records (_id INTEGER PRIMARY KEY AUTOINCREMENT, hours TEXT, minutes TEXT, date TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS food_records (_id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS exercise_records (_id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, datetime TEXT, duration TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS food_items (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, callories INTEGER, total_carbs INTEGER, total_fats INTEGER, protein INTEGER, saturated_fats INTEGER, trans_fats INTEGER, cholesterol INTEGER, sodium INTEGER, potassium INTEGER, cellulose INTEGER, sugar INTEGER, a INTEGER, c INTEGER, calcium INTEGER, iron INTEGER, portions REAL, type TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS awards (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, type TEXT);");
    }

    public void setContextMenuForMainPage() {
        mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem elementsControlMenuItem = contextMenu.add(Menu.NONE, 101, Menu.NONE, "Управление элементами");
                MenuItem forYouMenuItem = contextMenu.add(Menu.NONE, 101, Menu.NONE, "Для вас");
                elementsControlMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mainTabs.setVisibility(View.GONE);
                        elementsControlFooter.setVisibility(View.VISIBLE);
                        isSelectionMode = true;
                        mainPageActiveBlockController = findViewById(R.id.main_page_active_block_controller);
                        mainPageWalkBlockController = findViewById(R.id.main_page_walk_block_controller);
                        mainPageExerciseBlockController = findViewById(R.id.main_page_exercise_block_controller);
                        mainPageFoodBlockController = findViewById(R.id.main_page_food_block_controller);
                        mainPageSleepBlockController = findViewById(R.id.main_page_sleep_block_controller);
                        mainPageBodyCompositionBlockController = findViewById(R.id.main_page_body_composition_block_controller);
                        mainPageWaterBlockController = findViewById(R.id.main_page_water_block_controller);
                        mainPageActiveBlockController.setVisibility(View.VISIBLE);
                        mainPageWalkBlockController.setVisibility(View.VISIBLE);
                        mainPageExerciseBlockController.setVisibility(View.VISIBLE);
                        mainPageFoodBlockController.setVisibility(View.VISIBLE);
                        mainPageSleepBlockController.setVisibility(View.VISIBLE);
                        mainPageBodyCompositionBlockController.setVisibility(View.VISIBLE);
                        mainPageWaterBlockController.setVisibility(View.VISIBLE);

                        mainPageActiveBlock = findViewById(R.id.main_page_active_block);
                        mainPageWalkBlock = findViewById(R.id.main_page_walk_block);
                        mainPageExerciseBlock = findViewById(R.id.main_page_exercise_block);
                        mainPageFoodBlock = findViewById(R.id.main_page_food_block);
                        mainPageSleepBlock = findViewById(R.id.main_page_sleep_block);
                        mainPageBodyCompositionBlock = findViewById(R.id.main_page_body_composition_block);
                        mainPageWaterBlock = findViewById(R.id.main_page_water_block);
                        mainPageActiveBlock.setVisibility(View.VISIBLE);
                        mainPageWalkBlock.setVisibility(View.VISIBLE);
                        mainPageExerciseBlock.setVisibility(View.VISIBLE);
                        mainPageFoodBlock.setVisibility(View.VISIBLE);
                        mainPageSleepBlock.setVisibility(View.VISIBLE);
                        mainPageBodyCompositionBlock.setVisibility(View.VISIBLE);
                        mainPageWaterBlock.setVisibility(View.VISIBLE);

                        return false;
                    }
                });
                forYouMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(MainActivity.this, ForYouActivity.class);
                        MainActivity.this.startActivity(intent);
                        return false;
                    }
                });
                MenuItem eventsMenuItem = contextMenu.add(Menu.NONE, 101, Menu.NONE, "События");
                forYouMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(MainActivity.this, ForYouActivity.class);
                        MainActivity.this.startActivity(intent);
                        return false;
                    }
                });
                MenuItem notificationsMenuItem = contextMenu.add(Menu.NONE, 101, Menu.NONE, "Уведомления");
                notificationsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                        MainActivity.this.startActivity(intent);
                        return false;
                    }
                });
                MenuItem settingsMenuItem = contextMenu.add(Menu.NONE, 101, Menu.NONE, "Настр.");
                settingsMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        goToSettings();
                        return false;
                    }
                });
            }
        });
    }

    public void hideControllers() {
        mainTabs.setVisibility(View.VISIBLE);
        elementsControlFooter.setVisibility(View.GONE);
        isSelectionMode = false;
        mainPageActiveBlockController.setVisibility(View.GONE);
        mainPageWalkBlockController.setVisibility(View.GONE);
        mainPageExerciseBlockController.setVisibility(View.GONE);
        mainPageFoodBlockController.setVisibility(View.GONE);
        mainPageSleepBlockController.setVisibility(View.GONE);
        mainPageBodyCompositionBlockController.setVisibility(View.GONE);
        mainPageWaterBlockController.setVisibility(View.GONE);
    }

    public void displayHealthItems() {
        Cursor controllersCursor = db.rawQuery("Select * from controllers", null);
        controllersCursor.moveToFirst();
        long countControllers = DatabaseUtils.queryNumEntries(db, "controllers");
        ArrayList<Boolean> controllersValues = new ArrayList<Boolean>();
        for (long controllersCursorIndex = 0; controllersCursorIndex < countControllers; controllersCursorIndex++) {
            String healthItemName = "";
            healthItemName = controllersCursor.getString(2);
            boolean isHealthItemActivated = true;
            int rawIsHealthItemActivated = controllersCursor.getInt(1);
            isHealthItemActivated = rawIsHealthItemActivated == 1;
            controllersValues.add(isHealthItemActivated);
            boolean isActiveItem = healthItemName == "active";
            boolean isWalkItem = healthItemName == "walk";
            boolean isExerciseItem = healthItemName == "exercise";
            boolean isFoodItem = healthItemName == "food";
            boolean isSleepItem = healthItemName == "sleep";
            boolean isBodyItem = healthItemName == "body";
            boolean isWaterItem = healthItemName == "water";
            if (isActiveItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            } else if (isWalkItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            } else if (isExerciseItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            } else if (isFoodItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            } else if (isSleepItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            } else if (isBodyItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            } else if (isWaterItem) {
                if (isHealthItemActivated) {
                    mainPageWalkBlockController.setContentDescription("minus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
                } else {
                    mainPageWalkBlockController.setContentDescription("plus");
                    mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
                    mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
                }
            }
        }
        if (controllersValues.get(0)) {
            mainPageActiveBlockController.setContentDescription("minus");
            mainPageActiveBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageActiveBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageActiveBlockController.setContentDescription("plus");
            mainPageActiveBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageActiveBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_active_block);
            block.setVisibility(View.GONE);
        }
        if (controllersValues.get(1)) {

            mainPageWalkBlockController.setContentDescription("minus");
            mainPageWalkBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageWalkBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageWalkBlockController.setContentDescription("plus");
            mainPageWalkBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageWalkBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_walk_block);
            block.setVisibility(View.GONE);
        }
        if (controllersValues.get(2)) {
            mainPageExerciseBlockController.setContentDescription("minus");
            mainPageExerciseBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageExerciseBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageExerciseBlockController.setContentDescription("plus");
            mainPageExerciseBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageExerciseBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_exercise_block);
            block.setVisibility(View.GONE);
        }
        if (controllersValues.get(3)) {
            mainPageFoodBlockController.setContentDescription("minus");
            mainPageFoodBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageFoodBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageFoodBlockController.setContentDescription("plus");
            mainPageFoodBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageFoodBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_food_block);
            block.setVisibility(View.GONE);
        }
        if (controllersValues.get(4)) {
            mainPageSleepBlockController.setContentDescription("minus");
            mainPageSleepBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageSleepBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageSleepBlockController.setContentDescription("plus");
            mainPageSleepBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageSleepBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_sleep_block);
            block.setVisibility(View.GONE);
        }
        if (controllersValues.get(5)) {
            mainPageBodyCompositionBlockController.setContentDescription("minus");
            mainPageBodyCompositionBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageBodyCompositionBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageBodyCompositionBlockController.setContentDescription("plus");
            mainPageBodyCompositionBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageBodyCompositionBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_body_composition_block);
            block.setVisibility(View.GONE);
        }
        if (controllersValues.get(6)) {
            mainPageWaterBlockController.setContentDescription("minus");
            mainPageWaterBlockController.setColorFilter(Color.rgb(255, 0, 0));
            mainPageWaterBlockController.setImageResource(R.drawable.minus_logo);
        } else {
            mainPageWaterBlockController.setContentDescription("plus");
            mainPageWaterBlockController.setColorFilter(Color.rgb(0, 150, 0));
            mainPageWaterBlockController.setImageResource(R.drawable.plus_logo);
            LinearLayout block = findViewById(R.id.main_page_water_block);
            block.setVisibility(View.GONE);
        }
    }

    public void createNotification(int walkInfo, int step) {
        String rawWalkInfo = Integer.toString(walkInfo);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        if (step == 1) {
            notificationId = (int) ((new Date(System.currentTimeMillis()).getTime() / 1000L) % Integer.MAX_VALUE);
            builder = new NotificationCompat.Builder(getApplicationContext(), "CONTACTIO_CHANNEL_ID")
                .setSmallIcon(R.drawable.walk_logo)
                .setContentTitle(rawWalkInfo + " шагов")
                .setContentText("Цель: 6000 шага (-ов).")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        } else {
            builder.setContentTitle(rawWalkInfo + " шагов");
        }
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("CONTACTIO_CHANNEL_ID", "channel for transfer messages between sockets", importance);
            channel.setDescription("transfer messages between sockets");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(notificationId /* ID of notification */, builder.build());
        }
    }

    public void goToSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (isSelectionMode) {
            hideControllers();
            displayHealthItems();
        } else {
            super.onBackPressed();
        }
    }
}
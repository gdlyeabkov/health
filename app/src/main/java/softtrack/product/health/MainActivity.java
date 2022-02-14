package softtrack.product.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
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
                            contextMenu.add(Menu.NONE, 306, Menu.NONE, "Настр.");
                        }
                    });
                } else if (isMyPageTab) {
                    mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            contextMenu.add(Menu.NONE, 401, Menu.NONE, "Для вас");
                            contextMenu.add(Menu.NONE, 402, Menu.NONE, "События");
                            contextMenu.add(Menu.NONE, 403, Menu.NONE, "Уведомления");
                            contextMenu.add(Menu.NONE, 404, Menu.NONE, "Настр.");
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
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        MainActivity.this.startActivity(intent);
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

}
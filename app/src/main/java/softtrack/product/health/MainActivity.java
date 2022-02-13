package softtrack.product.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
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
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        mainTabs = findViewById(R.id.main_tabs);
        currentTabBody = findViewById(R.id.current_tab_body);
        mainHeaderLabel = findViewById(R.id.main_header_label);
        mainHeaderBtn = findViewById(R.id.main_header_btn);
        initializeTabs();
        initDB();
        setContextMenuForMainPage();
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
                contextMenu.add(Menu.NONE, 101, Menu.NONE, "Управление элементами");
                MenuItem forYouMenuItem = contextMenu.add(Menu.NONE, 101, Menu.NONE, "Для вас");
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

}
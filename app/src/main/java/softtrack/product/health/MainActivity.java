package softtrack.product.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ViewPager2 currentTabBody;
    public TabLayout mainTabs;
    public ArrayList<String> tabsHeaders;
    public TextView mainHeaderLabel;
    public Button mainHeaderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTabs = findViewById(R.id.main_tabs);
        currentTabBody = findViewById(R.id.current_tab_body);
        mainHeaderLabel = findViewById(R.id.main_header_label);
        mainHeaderBtn = findViewById(R.id.main_header_btn);

        initializeTabs();

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
                    mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            contextMenu.add(Menu.NONE, 101, Menu.NONE, "Управление элементами");
                            contextMenu.add(Menu.NONE, 101, Menu.NONE, "Для вас");
                            contextMenu.add(Menu.NONE, 101, Menu.NONE, "События");
                            contextMenu.add(Menu.NONE, 101, Menu.NONE, "Уведомления");
                            contextMenu.add(Menu.NONE, 101, Menu.NONE, "Настр.");
                        }
                    });
                } else if (isTogetherTab) {
                    mainHeaderBtn.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                        @Override
                        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                            contextMenu.add(Menu.NONE, 201, Menu.NONE, "Для вас");
                            contextMenu.add(Menu.NONE, 201, Menu.NONE, "События");
                            contextMenu.add(Menu.NONE, 201, Menu.NONE, "Уведомления");
                            contextMenu.add(Menu.NONE, 201, Menu.NONE, "Настр.");
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

}
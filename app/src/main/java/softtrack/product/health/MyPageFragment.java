package softtrack.product.health;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyPageFragment extends Fragment {

    public MainActivity parentActivity;
    public TextView myPageActivityBodyHeaderLabel;
    public Button myPageActivityBodyHeaderEditBtn;
    public TextView myPageActivityBodyFooterNotFound;
    public LinearLayout myPageActivityBodyFooter;
    public ImageView myPageActivityBodyFooterIcon;
    public LinearLayout myPageActivityBodyFooterItems;
    @SuppressLint("WrongConstant") public SQLiteDatabase db;

    public MyPageFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_page, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }

    @SuppressLint("WrongConstant")
    public void initialize() {
        parentActivity = (MainActivity) this.getActivity();
        myPageActivityBodyHeaderLabel = parentActivity.findViewById(R.id.my_page_activity_body_header_label);
        myPageActivityBodyFooterNotFound = parentActivity.findViewById(R.id.my_page_activity_body_footer_not_found);
        myPageActivityBodyFooter = parentActivity.findViewById(R.id.my_page_activity_body_footer);
        myPageActivityBodyFooterIcon = parentActivity.findViewById(R.id.my_page_activity_body_footer_icon);
        myPageActivityBodyFooterItems = parentActivity.findViewById(R.id.my_page_activity_body_footer_items);
        db = parentActivity.openOrCreateDatabase("health-database.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        AccountManager accountManager = AccountManager.get(parentActivity.getApplicationContext());
        Account[] accounts = accountManager.getAccounts();
        boolean isAccountsDetected = accounts.length >= 1;
        if (isAccountsDetected) {
            Account myAccount = accounts[0];
            String myAccountName = myAccount.name;
            myPageActivityBodyHeaderLabel.setText(myAccountName);
        }
        myPageActivityBodyHeaderEditBtn = parentActivity.findViewById(R.id.my_page_activity_body_header_edit_btn);
        myPageActivityBodyHeaderEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, EditMyPageActivity.class);
                parentActivity.startActivity(intent);
            }
        });
        long countAwards = DatabaseUtils.queryNumEntries(db, "awards");
        boolean isAwardsFound = countAwards >= 1;
        if (isAwardsFound) {
            myPageActivityBodyFooterNotFound.setVisibility(View.GONE);
            myPageActivityBodyFooterIcon.setVisibility(View.VISIBLE);
            myPageActivityBodyFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(parentActivity, AwardsActivity.class);
                    parentActivity.startActivity(intent);
                }
            });

            Cursor foodsCursor = db.rawQuery("Select * from awards where type=\"Велоспорт\" OR type=\"Бег\" OR type=\"Ходьба\" OR type=\"Поход\" OR type=\"Плавание\" OR type=\"Йога\"", null);
            boolean isBigDurationAwardsExists = foodsCursor.getCount() >= 1;
            if (isBigDurationAwardsExists) {
                foodsCursor.moveToFirst();
                while (true) {
                    String awardName = foodsCursor.getString(1);
                    String awardRecord = foodsCursor.getString(2);
                    String[] durationParts = awardRecord.split(" ")[0].split(":");
                    String hours = durationParts[0];
                    String minutes = durationParts[1];
                    String seconds = durationParts[2];
                    int parsedHours = Integer.valueOf(hours);
                    int parsedMinutes = Integer.valueOf(minutes);
                    int parsedSeconds = Integer.valueOf(seconds);
                    int duration = parsedHours * 60 + parsedMinutes;
                    String rawDuration = String.valueOf(duration) + " мин";
                    String awardType = foodsCursor.getString(3);
                    LinearLayout myPageActivityBodyFooterItem = new LinearLayout(parentActivity);
                    myPageActivityBodyFooterItem.setLayoutParams(new LinearLayout.LayoutParams(500,450));
                    myPageActivityBodyFooterItem.setOrientation(LinearLayout.VERTICAL);
                    ImageView myPageActivityBodyFooterItemImg = new ImageView(parentActivity);
                    myPageActivityBodyFooterItemImg.setImageResource(R.drawable.award_logo);
                    myPageActivityBodyFooterItemImg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    myPageActivityBodyFooterItem.addView(myPageActivityBodyFooterItemImg);
                    TextView myPageActivityBodyFooterItemLabel = new TextView(parentActivity);
                    myPageActivityBodyFooterItemLabel.setText(awardName);
                    myPageActivityBodyFooterItemLabel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    myPageActivityBodyFooterItem.addView(myPageActivityBodyFooterItemLabel);
                    myPageActivityBodyFooterItem.setContentDescription(awardType + "@" + awardName + "@" + rawDuration);
                    myPageActivityBodyFooterItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(parentActivity, AwardActivity.class);
                            CharSequence rawAwardData = view.getContentDescription();
                            String awardData = rawAwardData.toString();
                            String[] awardDataItems = awardData.split("@");
                            String awardType = awardDataItems[0];
                            String awardName = awardDataItems[1];
                            String awardRecord = awardDataItems[2];
                            intent.putExtra("category", "Упражнение");
                            intent.putExtra("type", awardType);
                            intent.putExtra("name", awardName);
                            intent.putExtra("record", awardRecord);
                            parentActivity.startActivity(intent);
                        }
                    });
                    myPageActivityBodyFooterItems.addView(myPageActivityBodyFooterItem);
                    boolean isBreak = foodsCursor.moveToNext();
                    if (!isBreak) {
                        break;
                    }
                }
            }
        }
        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        indicatorsCursor.moveToFirst();
        String name = "";
        name = indicatorsCursor.getString(9);
        myPageActivityBodyHeaderLabel.setText(name);
    }

}

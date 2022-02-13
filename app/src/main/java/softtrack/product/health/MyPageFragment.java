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
        }
        Cursor indicatorsCursor = db.rawQuery("Select * from indicators", null);
        indicatorsCursor.moveToFirst();
        String name = "";
        name = indicatorsCursor.getString(9);
        myPageActivityBodyHeaderLabel.setText(name);
    }

}

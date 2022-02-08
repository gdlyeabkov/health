package softtrack.product.health;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyPageFragment extends Fragment {

    public MainActivity parentActivity;
    public TextView myPageActivityBodyHeaderLabel;

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

    public void initialize() {
        parentActivity = (MainActivity) this.getActivity();
        myPageActivityBodyHeaderLabel = parentActivity.findViewById(R.id.my_page_activity_body_header_label);
        AccountManager accountManager = AccountManager.get(parentActivity.getApplicationContext());
        Account[] accounts = accountManager.getAccounts();
        boolean isAccountsDetected = accounts.length >= 1;
        if (isAccountsDetected) {
            Account myAccount = accounts[0];
            String myAccountName = myAccount.name;
            myPageActivityBodyHeaderLabel.setText(myAccountName);
        }
    }

}

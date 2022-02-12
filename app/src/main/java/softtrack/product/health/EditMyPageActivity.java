package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditMyPageActivity extends AppCompatActivity {

    public Button editMyPageActivitityFooterCancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_page);
        initialize();
    }

    public void initialize() {
        editMyPageActivitityFooterCancelBtn = findViewById(R.id.edit_my_page_activitity_footer_cancel_btn);
        editMyPageActivitityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
                EditMyPageActivity.this.startActivity(intent);
            }
        });
    }

}

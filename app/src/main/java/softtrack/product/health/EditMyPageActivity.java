package softtrack.product.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditMyPageActivity extends AppCompatActivity {

    public Button editMyPageActivitityFooterCancelBtn;
    public Button editMyPageActivitityBodyGalleryBtn;
    public Button editMyPageActivitityBodyCameraBtn;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_page);
        initialize();
    }

    public void initialize() {
        editMyPageActivitityFooterCancelBtn = findViewById(R.id.edit_my_page_activitity_footer_cancel_btn);
        editMyPageActivitityBodyGalleryBtn = findViewById(R.id.edit_my_page_activitity_body_camera_btn);
        editMyPageActivitityBodyCameraBtn = findViewById(R.id.edit_my_page_activitity_body_camera_btn);
        editMyPageActivitityFooterCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
                EditMyPageActivity.this.startActivity(intent);
            }
        });
        editMyPageActivitityBodyGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
//                Intent intent = new Intent(EditMyPageActivity.this, Intent.ACTION_QUICK_CLOCK);
//                EditMyPageActivity.this.startActivity(Intent.createChooser(intent, ""));
//                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.gallery3d");
                openGallery();
            }
        });
        editMyPageActivitityBodyCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditMyPageActivity.this, MainActivity.class);
                EditMyPageActivity.this.startActivity(intent);
            }
        });
    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

}

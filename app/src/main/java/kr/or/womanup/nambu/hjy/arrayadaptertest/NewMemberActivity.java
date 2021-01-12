package kr.or.womanup.nambu.hjy.arrayadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewMemberActivity extends AppCompatActivity {
    EditText edtNewMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);
        edtNewMember = findViewById(R.id.edt_new_member);
        Button button = findViewById(R.id.btn_finish);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMember = edtNewMember.getText().toString();
                try {
                    Intent intent = new Intent();
                    intent.putExtra("new_member", newMember);
                    setResult(Activity.RESULT_OK, intent);
                }catch (Exception e) {
                    setResult(Activity.RESULT_CANCELED);
                }
                finish();
            }
        });

    }
}
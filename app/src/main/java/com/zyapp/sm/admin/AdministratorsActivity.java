package com.zyapp.sm.admin;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.zyapp.sm.R;

public class AdministratorsActivity extends Activity implements View.OnClickListener{
Button btn_teacher,btn_student,btn_information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrators);
        initStudent();
    }
    public void initStudent(){
        btn_teacher = findViewById(R.id.btn_teacher);
        btn_student = findViewById(R.id.btn_student);
        btn_information = findViewById(R.id.btn_information);

        btn_teacher.setOnClickListener(this);
        btn_student.setOnClickListener(this);
        btn_information.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_teacher:
                startActivity(new Intent(this, TeacherInformation.class));
                break;
            case R.id.btn_student:
                startActivity(new Intent(this, StudentInformation.class));
                break;
            case R.id.btn_information:
                startActivity(new Intent(this, Information.class));
                break;
        }
    }
}

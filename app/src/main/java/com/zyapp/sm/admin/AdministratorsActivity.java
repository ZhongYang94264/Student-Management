package com.zyapp.sm.admin;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.zyapp.sm.R;

public class AdministratorsActivity extends Activity implements View.OnClickListener{
    String ID;
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

        ID=getIntent().getStringExtra("id");
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_teacher:
                Intent TeacherInformation=new Intent(this, TeacherInformation.class);
                startActivity(TeacherInformation);
                break;
            case R.id.btn_student:
                Intent StudentInformation=new Intent(this, StudentInformation.class);
                startActivity(StudentInformation);
                break;
            case R.id.btn_information:
                Intent Information=new Intent(this, Information.class);
                Information.putExtra("id",ID);
                startActivity(Information);
                break;
        }
    }
}

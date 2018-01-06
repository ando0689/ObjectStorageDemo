package test.andranik.objectstoragedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import test.andranik.objectstoragedemo.pojos.User;

public class MainActivity extends AppCompatActivity {

    private EditText fNameEt;
    private EditText lNameEt;
    private EditText ageEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fNameEt = findViewById(R.id.activity_main_fname_et);
        lNameEt = findViewById(R.id.activity_main_lname_et);
        ageEt = findViewById(R.id.activity_main_age_et);
    }

    public void onSaveClick(View view) {
        User user = new User();

        user.setfName(fNameEt.getText().toString());
        user.setlName(lNameEt.getText().toString());
        user.setAge(ageEt.getText().length() > 0 ? Integer.parseInt(ageEt.getText().toString()) : 0);

        user.save();
    }

    public void onRestoreClick(View view) {
        User user = User.restore();

        if(user == null) return;

        fNameEt.setText(user.getfName() == null ? "" : user.getfName());
        lNameEt.setText(user.getlName() == null ? "" : user.getlName());
        ageEt.setText(user.getAge() <= 0 ? "" : String.valueOf(user.getAge()));
    }

}

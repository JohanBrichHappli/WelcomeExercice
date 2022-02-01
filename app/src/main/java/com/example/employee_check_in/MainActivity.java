package com.example.employee_check_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckIn = (Button) findViewById(R.id.btnCheckIn);
        Button btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        message = (TextView) findViewById(R.id.message);

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeList = new Intent(MainActivity.this, EmployeesListActivity.class);
                startActivityForResult(employeeList, 1);
            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeList = new Intent(MainActivity.this, EmployeesListActivity.class);
                startActivityForResult(employeeList, 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            message.setText("");
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String employeeName = data.getStringExtra("name");
                message.setText("L'employé " + employeeName + " a procédé à son check-in");
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String employeeName = data.getStringExtra("name");
                message.setText("L'employé " + employeeName + " a procédé à son check-out");
            }
        }
    }







}
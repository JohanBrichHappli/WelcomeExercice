package com.example.employee_check_in.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.employee_check_in.Model.Employee;
import com.example.employee_check_in.R;
import com.example.employee_check_in.ViewModel.RecyclerAdapter;

import java.util.ArrayList;

public class EmployeesListActivity extends AppCompatActivity {

    private ArrayList<Employee> employeesList;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        employeesList = new ArrayList<>();
        setEmployees();
        recyclerView = findViewById(R.id.recyclerViewEmployee);
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(employeesList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", employeesList.get(position).getName());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        };
    }

    private void setEmployees() {
        employeesList.add(new Employee("Johnny"));
        employeesList.add(new Employee("Bobby"));
        employeesList.add(new Employee("Michel"));
        employeesList.add(new Employee("Gontrand"));
        employeesList.add(new Employee("Robert"));

    }
}
package com.example.employee_check_in.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.employee_check_in.Model.Employee;
import com.example.employee_check_in.R;
import com.example.employee_check_in.ViewModel.RecyclerAdapter;
import com.example.employee_check_in.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeesListActivity extends AppCompatActivity {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getUsersFromApi();
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployeesList(employees);
            }
        });

        recyclerView = findViewById(R.id.recyclerViewEmployee);
        setAdapter();
    }

    public void setAdapter() {
        setOnClickListener();
        adapter = new RecyclerAdapter(listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", adapter.getEmployeesList().get(position).getName());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        };
    }

    /*public void setEmployees() {
        employeesList.add(new Employee("Johnny"));
        employeesList.add(new Employee("Bobby"));
        employeesList.add(new Employee("Michel"));
        employeesList.add(new Employee("Gontrand"));
        employeesList.add(new Employee("Robert"));
    }*/
}
package com.example.employee_check_in.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee_check_in.Model.Employee;
import com.example.employee_check_in.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Employee> employeesList;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(ArrayList<Employee> employeesList, RecyclerViewClickListener listener) {
        this.employeesList = employeesList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView employeeNameText;

        public MyViewHolder(final View view) {
            super(view);
            employeeNameText = view.findViewById(R.id.employeeNameText);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String employeeName = employeesList.get(position).getName();
        holder.employeeNameText.setText(employeeName);

    }

    @Override
    public int getItemCount() {
        return employeesList == null ? 0 : employeesList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}

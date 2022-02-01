package com.example.employee_check_in.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employee_check_in.Repository.RetrofitClient;
import com.example.employee_check_in.Repository.UserApiService;
import com.example.employee_check_in.Repository.UserDto;
import com.example.employee_check_in.Mapper.UserMapper;
import com.example.employee_check_in.Model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {

    private MutableLiveData<List<Employee>> employeesMutableLiveDate = new MutableLiveData<>();
    private LiveData<List<Employee>> employees = employeesMutableLiveDate;
    private UserApiService userApiService;
    private UserMapper userMapper;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userApiService = RetrofitClient.getRetrofitInstance().create(UserApiService.class);
        this.userMapper = UserMapper.getInstance();
    }

    public void getUsersFromApi() {
        userApiService.getUsers().enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserDto>> call,@NonNull Response<List<UserDto>> response) {
                if(response.isSuccessful()){
                    employeesMutableLiveDate.setValue(userMapper.mapFromUserToEmployee(response.body()));
                    Log.i("Test","userName : " + employeesMutableLiveDate.getValue().get(9).getName());
                } else {
                    Log.i("Zut","erreur");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserDto>> call,@NonNull Throwable t) {
                Log.i("erreur",t.getMessage());
            }
        });
    }
    public LiveData<List<Employee>> getEmployees() { return employees; }
}

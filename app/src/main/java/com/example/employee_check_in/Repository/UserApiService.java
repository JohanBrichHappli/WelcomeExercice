package com.example.employee_check_in.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiService {
    @GET("/users")
    Call<List<UserDto>> getUsers();
}

package com.example.employee_check_in.Mapper;

import com.example.employee_check_in.Repository.UserDto;
import com.example.employee_check_in.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private static UserMapper userMapper = null;

    public static UserMapper getInstance() {
        if(userMapper == null)
            userMapper = new UserMapper();
        return userMapper;
    }

    public ArrayList<Employee> mapFromUserToEmployee(List<UserDto> usersDto) {
        if(usersDto == null)
            return null;
        else {
            ArrayList<Employee> employees = new ArrayList<>();
            for (UserDto userDto: usersDto) {
                Employee employee = new Employee(userDto.getName());
                employees.add(employee);
            }
            return employees;
        }
    }
}

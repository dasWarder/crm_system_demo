package com.example.mapper.dto.user.superadmin;


import com.example.mapper.dto.user.admin.AdminDetailsUserDto;
import com.example.model.report.Report;
import com.example.model.todoList.TodoList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminUserDetailsDto extends AdminDetailsUserDto {

    private Long id;

    private List<Report> reports;
}

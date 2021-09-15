package com.example.service.dashboard;

import com.example.exception.UserNotFoundException;

public interface DashboardService {

  Long getNumberOfReports() throws UserNotFoundException;

  Long getNumberOfDaysInCompany() throws UserNotFoundException;

  Long getNumberOfActiveTasks() throws UserNotFoundException;
}

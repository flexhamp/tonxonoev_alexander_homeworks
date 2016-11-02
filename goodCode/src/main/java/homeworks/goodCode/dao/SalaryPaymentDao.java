package homeworks.goodCode.dao;


import homeworks.goodCode.model.SalaryPayment;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alexander on 24.10.2016.
 */
public interface SalaryPaymentDao {
    List<SalaryPayment> salaryPaymentGetByParam(String departmentId, LocalDate dateFrom, LocalDate dateTo);
}

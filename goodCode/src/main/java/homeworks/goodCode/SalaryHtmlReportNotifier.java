package homeworks.goodCode;




import homeworks.goodCode.builder.ReportBuilder;
import homeworks.goodCode.dao.SalaryPaymentDao;
import homeworks.goodCode.model.SalaryPayment;
import homeworks.goodCode.sender.Sender;

import java.time.LocalDate;
import java.util.List;

public class SalaryHtmlReportNotifier {

    private SalaryPaymentDao salaryPaymentDao;
    private ReportBuilder reportBuilder;
    private Sender sender;

    public SalaryHtmlReportNotifier(SalaryPaymentDao salaryPaymentDao, ReportBuilder reportBuilder, Sender sender) {

        this.salaryPaymentDao = salaryPaymentDao;
        this.reportBuilder = reportBuilder;
        this.sender = sender;
    }

    public void generateAndSendSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients){
        List<SalaryPayment> salaryPayments=salaryPaymentDao.salaryPaymentGetByParam(departmentId, dateFrom, dateTo);
        StringBuilder report=reportBuilder.build(salaryPayments);
        sender.send(recipients, report);
    }

}

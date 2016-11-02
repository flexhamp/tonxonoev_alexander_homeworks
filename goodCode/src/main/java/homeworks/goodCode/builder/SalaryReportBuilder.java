package homeworks.goodCode.builder;


import homeworks.goodCode.model.SalaryPayment;

import java.util.List;

/**
 * Created by Alexander on 27.10.2016.
 */
public class SalaryReportBuilder implements ReportBuilder {
    @Override
    public StringBuilder build(List<SalaryPayment> objects) {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;


        for (SalaryPayment salaryPayment : objects) {
            resultingHtml.append("<tr>"); // add row start tag
            resultingHtml.append("<td>").append(salaryPayment.getName()).append("</td>"); // appending employee name
            resultingHtml.append("<td>").append(salaryPayment.getSalary()).append("</td>"); // appending employee salary for period
            resultingHtml.append("</tr>"); // add row end tag
            totals += salaryPayment.getSalary();  // add salary to totals
        }


        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml;
    }
}

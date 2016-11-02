package homeworks.goodCode.builder;

import homeworks.goodCode.model.SalaryPayment;
import java.util.List;

/**
 * Created by Alexander on 24.10.2016.
 */
public interface ReportBuilder {
    StringBuilder build(List<SalaryPayment> objects);
}

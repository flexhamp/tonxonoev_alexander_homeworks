package homeworks.goodCode.model;

/**
 * Created by Alexander on 24.10.2016.
 */
public class SalaryPayment {
    private final Long id;
    private final String name;
    private final double salary;

    public SalaryPayment(Long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

package enpalmer.intellimem.employee;

public record AddEmployeeRequest(
    String name,
    String dept,
    long salary
) { }

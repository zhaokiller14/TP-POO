class AdministrationEmployee extends Person {
    int employeeId;

    public AdministrationEmployee(String name, int age, int employeeId) {
        super(name, age);
        this.employeeId = employeeId;
    }
}
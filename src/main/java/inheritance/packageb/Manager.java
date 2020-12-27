package inheritance.packageb;

import inheritance.packagea.Employee;

class Manager extends Employee {
    int age;

    public Manager(String name) {
        super(name);
//        super.name;  // not accessible, Employee.name is private
        super.salary = 10; // accessible
        this.salary = 10;  // accessible
//        super.age; // not accessible, age is not in super class
    }

    private void printinfo() {
        Employee em = new Manager("Jon");
        Manager m = new Manager("Pichi");

//		  System.out.println(em.salary);   // not accessible, Employee.salary field is protected
        System.out.println(((Manager) em).salary); // accessible

        System.out.println(super.salary); // accessible
        System.out.println(m.salary);     // accessible
        System.out.println(this.salary);  // accessible
        System.out.println(salary);       // accessible
    }
}

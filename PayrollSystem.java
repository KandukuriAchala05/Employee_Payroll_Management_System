package miniproject;

import java.util.*;
import java.io.*;

// ====================== EMPLOYEE CLASS ==========================
class Employee {
    int empId;
    String name;
    String dep;
    String bank;
    long mobileNo;
    double salary;
    long bankacco;
    String ifsc;
    int managementlevel;
    String Entity;
    String facility;

    Employee(int empId, String name, long mobileNo, double salary, String bank, String dep, String facility,
             long bankacco, String ifsc, int managementlevel, String Entity) {
        this.empId = empId;
        this.name = name;
        this.dep = dep;
        this.bank = bank;
        this.salary = salary;
        this.mobileNo = mobileNo;
        this.bankacco = bankacco;
        this.ifsc = ifsc;
        this.managementlevel = managementlevel;
        this.Entity = Entity;
        this.facility = facility;
    }

    void display() {
        System.out.println("----------------------------------");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Name          : " + name);
        System.out.println("Department    : " + dep);
        System.out.println("Bank          : " + bank);
        System.out.println("Mobile No     : " + mobileNo);
        System.out.println("Facility      : " + facility);
        System.out.println("Bank Acc No   : " + bankacco);
        System.out.println("IFSC Code     : " + ifsc);
        System.out.println("Management Lv : " + managementlevel);
        System.out.println("Entity        : " + Entity);
    }
}

// ====================== NEWEMPLOYEE CLASS ==========================
class NewEmployee extends Employee {
    double hra, da;
    double providentfund = 2500;
    double proffessionaltax = 200;
    double incometax = 1200;

    NewEmployee(int empId, String name, long mobileNo, double salary, String bank, String dep, String facility,
                long bankacco, String ifsc, int managementlevel, String Entity) {
        super(empId, name, mobileNo, salary, bank, dep, facility, bankacco, ifsc, managementlevel, Entity);
        recomputeSalary();
    }

    void recomputeSalary() {
        hra = 0.2 * salary;
        da = 0.1 * salary;
    }

    double totalSalary() {
        return salary + hra + da + providentfund - proffessionaltax - incometax;
    }
}

// ====================== PAYROLL SYSTEM MAIN CLASS ==========================
public class PayrollSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {

        addSampleEmployees(); // Add 5 sample employees

        int choice;
        do {
            System.out.println("\n===== EMPLOYEE PAYROLL SYSTEM =====");
            System.out.println("1. Display All Employees");
            System.out.println("2. Add New Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Generate Payslip");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> displayEmployees();
                case 2 -> addNewEmployee();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> searchEmployee();
                case 6 -> generatePayslip();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }

    // Add 5 sample employees
    static void addSampleEmployees() {
        employees.add(new NewEmployee(101, "Ravi Kumar", 9876543210L, 40000, "SBI", "IT", "Laptop", 1234567890L, "SBIN0001234", 2, "TCS"));
        employees.add(new NewEmployee(102, "Priya Sharma", 9876501234L, 35000, "HDFC", "HR", "Cab", 2234567890L, "HDFC0004321", 1, "Infosys"));
        employees.add(new NewEmployee(103, "Amit Verma", 9123456789L, 50000, "ICICI", "Finance", "Gym", 3234567890L, "ICIC0005678", 3, "Wipro"));
        employees.add(new NewEmployee(104, "Sneha Patil", 9988776655L, 45000, "Axis", "Admin", "Canteen", 4234567890L, "UTIB0007890", 2, "Capgemini"));
        employees.add(new NewEmployee(105, "Kiran Reddy", 9012345678L, 42000, "Union", "Marketing", "Bus", 5234567890L, "UBIN0006543", 1, "Tech Mahindra"));
    }

    // Display all employees
    static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display!");
            return;
        }
        for (Employee e : employees) {
            e.display();
        }
    }

    // Add new employee
    static void addNewEmployee() {
        System.out.print("Employee ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Mobile No: "); long mobile = sc.nextLong(); sc.nextLine();
        System.out.print("Salary: "); double salary = sc.nextDouble(); sc.nextLine();
        System.out.print("Bank: "); String bank = sc.nextLine();
        System.out.print("Department: "); String dep = sc.nextLine();
        System.out.print("Facility: "); String facility = sc.nextLine();
        System.out.print("Bank Acc: "); long acc = sc.nextLong(); sc.nextLine();
        System.out.print("IFSC: "); String ifsc = sc.nextLine();
        System.out.print("Management Level: "); int level = sc.nextInt(); sc.nextLine();
        System.out.print("Entity: "); String entity = sc.nextLine();

        employees.add(new NewEmployee(id, name, mobile, salary, bank, dep, facility, acc, ifsc, level, entity));
        System.out.println("✅ Employee added!");
    }

    // Update employee
    static void updateEmployee() {
        System.out.print("Enter Employee ID to update: "); int id = sc.nextInt(); sc.nextLine();
        Employee found = null;
        for (Employee e : employees) if (e.empId == id) found = e;

        if (found == null) { System.out.println("Employee not found!"); return; }

        System.out.print("New Name (" + found.name + "): "); String name = sc.nextLine(); if (!name.isEmpty()) found.name = name;
        System.out.print("New Department (" + found.dep + "): "); String dep = sc.nextLine(); if (!dep.isEmpty()) found.dep = dep;
        System.out.print("New Bank (" + found.bank + "): "); String bank = sc.nextLine(); if (!bank.isEmpty()) found.bank = bank;
        System.out.print("New Facility (" + found.facility + "): "); String facility = sc.nextLine(); if (!facility.isEmpty()) found.facility = facility;
        System.out.print("New Mobile (" + found.mobileNo + "): "); String mobile = sc.nextLine(); if (!mobile.isEmpty()) found.mobileNo = Long.parseLong(mobile);
        System.out.println("✅ Employee updated!");
    }

    // Delete employee
    static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: "); int id = sc.nextInt(); sc.nextLine();
        employees.removeIf(e -> e.empId == id);
        System.out.println("✅ Employee deleted (if existed)!");
    }

    // Search employee
    static void searchEmployee() {
        System.out.print("Enter Name or Department to search: "); String query = sc.nextLine().toLowerCase();
        for (Employee e : employees) {
            if (e.name.toLowerCase().contains(query) || e.dep.toLowerCase().contains(query)) {
                e.display();
            }
        }
    }

    // Generate payslip in box style
    static void generatePayslip() {
        System.out.print("Enter Employee ID for payslip: "); int id = sc.nextInt(); sc.nextLine();
        NewEmployee emp = null;
        for (Employee e : employees) if (e.empId == id && e instanceof NewEmployee) emp = (NewEmployee) e;

        if (emp == null) { System.out.println("Employee not found!"); return; }

        System.out.println("+---------------------------------------------+");
        System.out.println("|                   PAY SLIP                  |");
        System.out.println("+---------------------------------------------+");
        System.out.printf("| Employee ID   : %-30d |\n", emp.empId);
        System.out.printf("| Name          : %-30s |\n", emp.name);
        System.out.printf("| Department    : %-30s |\n", emp.dep);
        System.out.printf("| Bank          : %-30s |\n", emp.bank);
        System.out.println("+---------------------------------------------+");
        System.out.printf("| Basic Salary  : %-30.2f |\n", emp.salary);
        System.out.printf("| HRA (20%%)     : %-30.2f |\n", emp.hra);
        System.out.printf("| DA  (10%%)     : %-30.2f |\n", emp.da);
        System.out.printf("| ProvidentFund : %-30.2f |\n", emp.providentfund);
        System.out.printf("| ProfessionTax : %-30.2f |\n", emp.proffessionaltax);
        System.out.printf("| IncomeTax     : %-30.2f |\n", emp.incometax);
        System.out.println("+---------------------------------------------+");
        System.out.printf("| NET SALARY    : %-30.2f |\n", emp.totalSalary());
        System.out.println("+---------------------------------------------+");
    }
}
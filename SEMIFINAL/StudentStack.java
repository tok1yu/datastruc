import java.util.Scanner;

class Student {
    int id;
    String name;
    String course;

    Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Course: " + course;
    }
}

public class StudentStack {
    static Student[] students = new Student[100];
    static int top = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        int idCounter = 1;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Last Inserted Student");
            System.out.println("3. Remove Last Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    students[++top] = new Student(idCounter++, name, course);
                    System.out.println("Student added!");
                    break;

                case 2:
                    if (top >= 0) {
                        System.out.println("Last Student:");
                        System.out.println(students[top]);
                    } else {
                        System.out.println("No records found.");
                    }
                    break;

                case 3:
                    if (top >= 0) {
                        System.out.println("Removed Student:");
                        System.out.println(students[top]);
                        students[top] = null;
                        top--;
                    } else {
                        System.out.println("No records found.");
                    }
                    break;

                case 4:
                    if (top >= 0) {
                        System.out.println("All Students:");
                        for (int i = 0; i <= top; i++) {
                            System.out.println(students[i]);
                        }
                    } else {
                        System.out.println("No records found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
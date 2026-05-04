import java.util.Scanner;

class Student {
    int idNumber;
    String name;
    String course;

    public Student(int idNumber, String name, String course) {
        this.idNumber = idNumber;
        this.name = name;
        this.course = course;
    }

    public void displayRecord() {
        System.out.println("Student ID: " + idNumber);
        System.out.println("Student Name: " + name);
        System.out.println("Course: " + course);
        System.out.println();
    }
}

public class StudentRecordQueue {

    static Student[] queue = new Student[5];
    static int front = 0;
    static int rear = -1;
    static int maxSize = 5;

    public static void add(Student s) {
        if (rear == maxSize - 1) {
            System.out.println("Queue is Full");
            return;
        }
        rear++;
        queue[rear] = s;
    }

    public static Student remove() {
        if (front > rear) {
            System.out.println("Queue is Empty");
            return null;
        }
        Student temp = queue[front];
        front++;
        return temp;
    }

    public static Student peek() {
        if (front > rear) {
            return null;
        }
        return queue[front];
    }

    public static void display() {
        if (front > rear) {
            System.out.println("Queue is Empty");
            return;
        }
        for (int i = front; i <= rear; i++) {
            queue[i].displayRecord();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("-----STUDENT RECORD SYSTEM (QUEUE)-----");
            System.out.println("1. Add a Student");
            System.out.println("2. View First Inserted Student");
            System.out.println("3. Remove a Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            System.out.println();

            switch (choice) {

                case 1:
                    System.out.print("Enter Student ID: ");
                    int idNumber = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Student Course: ");
                    String course = input.nextLine();

                    add(new Student(idNumber, name, course));
                    System.out.println("\nStudent Added Successfully");
                    break;

                case 2:
                    Student first = peek();
                    if (first != null) {
                        System.out.println("First Inserted Student:");
                        first.displayRecord();
                    } else {
                        System.out.println("Queue is Empty");
                    }
                    break;

                case 3:
                    Student removed = remove();
                    if (removed != null) {
                        System.out.println("Student Removed Successfully");
                        removed.displayRecord();
                    }
                    break;

                case 4:
                    System.out.println("Student Record:");
                    display();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 5);

        input.close();
    }
}

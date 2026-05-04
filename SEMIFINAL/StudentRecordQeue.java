import java.util.Scanner;

class Student {
    private int idNumber;
    private String name;
    private String course;

    public Student(int idNumber, String name, String course) {
        this.idNumber = idNumber;
        this.name = name;
        this.course = course;
    }

    public int getIdNumber() { return idNumber; }
    public String getName()  { return name; }
    public String getCourse(){ return course; }

    public void displayRecord() {
        System.out.println("  Student ID : " + idNumber);
        System.out.println("  Name       : " + name);
        System.out.println("  Course     : " + course);
    }
}

class StudentQueue {
    private static final int MAX_SIZE = 5;
    private Student[] queue = new Student[MAX_SIZE];
    private int front = 0;
    private int rear  = -1;
    private int count = 0;

    public boolean isFull()  { return count == MAX_SIZE; }
    public boolean isEmpty() { return count == 0; }
    public int     size()    { return count; }

    public boolean enqueue(Student student) {
        if (isFull()) return false;
        rear = (rear + 1) % MAX_SIZE;
        queue[rear] = student;
        count++;
        return true;
    }

    public Student dequeue() {
        if (isEmpty()) return null;
        Student removed = queue[front];
        queue[front] = null;
        front = (front + 1) % MAX_SIZE;
        count--;
        return removed;
    }

    public Student peek() {
        return isEmpty() ? null : queue[front];
    }

    public void displayAll() {
        if (isEmpty()) {
            printDivider();
            System.out.println("  No student records found.");
            printDivider();
            return;
        }
        printDivider();
        System.out.printf("  %-6s  %-25s  %-20s%n", "ID", "Name", "Course");
        printDivider();
        for (int i = 0; i < count; i++) {
            Student s = queue[(front + i) % MAX_SIZE];
            System.out.printf("  %-6d  %-25s  %-20s%n",
                s.getIdNumber(), s.getName(), s.getCourse());
        }
        printDivider();
    }

    private void printDivider() {
        System.out.println("  " + "-".repeat(56));
    }
}
 class StudentRecordQueue {

    private static final Scanner input = new Scanner(System.in);
    private static final StudentQueue queue = new StudentQueue();

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("  Enter your choice: ");

            System.out.println();
            switch (choice) {
                case 1: handleAddStudent();    break;
                case 2: handleViewFirst();     break;
                case 3: handleRemoveStudent(); break;
                case 4: handleDisplayAll();    break;
                case 5: System.out.println("  Exiting system. Goodbye!\n"); break;
                default: System.out.println("  [!] Invalid choice. Please enter 1 - 5.\n");
            }

        } while (choice != 5);

        input.close();
    }

    // ─── Menu ───────────────────────────────────────────────────────────────────

    private static void printMenu() {
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║    STUDENT RECORD SYSTEM (QUEUE)     ║");
        System.out.println("  ╠══════════════════════════════════════╣");
        System.out.println("  ║  1. Add Student                      ║");
        System.out.println("  ║  2. View First Inserted Student       ║");
        System.out.println("  ║  3. Remove First Student              ║");
        System.out.println("  ║  4. Display All Students              ║");
        System.out.println("  ║  5. Exit                              ║");
        System.out.println("  ╚══════════════════════════════════════╝");
    }

    // ─── Handlers ───────────────────────────────────────────────────────────────

    private static void handleAddStudent() {
        if (queue.isFull()) {
            System.out.println("  [!] Queue is full. Cannot add more students.\n");
            return;
        }

        System.out.println("  [ ADD STUDENT ]");
        int id = readInt("  Student ID   : ");
        System.out.print("  Name         : ");
        String name = input.nextLine().trim();
        System.out.print("  Course       : ");
        String course = input.nextLine().trim();

        if (name.isEmpty() || course.isEmpty()) {
            System.out.println("  [!] Name and course cannot be blank.\n");
            return;
        }

        queue.enqueue(new Student(id, name, course));
        System.out.println("  [✓] Student record added successfully. "
            + "(" + queue.size() + " / 5 slots used)\n");
    }

    private static void handleViewFirst() {
        System.out.println("  [ FIRST STUDENT IN QUEUE ]");
        Student first = queue.peek();
        if (first != null) {
            System.out.println("  " + "-".repeat(34));
            first.displayRecord();
            System.out.println("  " + "-".repeat(34));
        } else {
            System.out.println("  [!] Queue is empty.\n");
        }
        System.out.println();
    }

    private static void handleRemoveStudent() {
        System.out.println("  [ REMOVE STUDENT ]");
        Student removed = queue.dequeue();
        if (removed != null) {
            System.out.println("  [✓] The following record has been removed:");
            System.out.println("  " + "-".repeat(34));
            removed.displayRecord();
            System.out.println("  " + "-".repeat(34));
        } else {
            System.out.println("  [!] Queue is empty. No record to remove.");
        }
        System.out.println();
    }

    private static void handleDisplayAll() {
        System.out.println("  [ ALL STUDENT RECORDS ]");
        queue.displayAll();
        System.out.println();
    }

    // ─── Utility ────────────────────────────────────────────────────────────────

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a number.");
            }
        }
    }
}
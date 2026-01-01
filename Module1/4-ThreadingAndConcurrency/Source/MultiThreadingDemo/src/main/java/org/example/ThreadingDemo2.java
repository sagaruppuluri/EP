package org.example;

class Task1 implements Runnable {
    // Thread execution begins here.
    public void run() {
        // performs the task i.e. prints 1500 T's
        doTask();
    }

    public void doTask() {
        for(int i=1; i <= 1500; i++) {
            System.out.print("T");
        }
    }
}

public class ThreadingDemo2 {
    // Runs with in the Main thread started by JVM.
    public static void main(String[] args) {

        Task1 task1 = new Task1();

        // Creating a Thread and passing the
        // Runnable object to it.
        Thread t1 = new Thread(task1);

        // Starts a separate Thread using the
        // the start method of the Thread class.
        t1.start();

        // runs in the Main thread and prints 1500 M's
        for(int i=1; i <= 1500; i++) {
            System.out.print("M");
        }
    }
}

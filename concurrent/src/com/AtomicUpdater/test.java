package com.AtomicUpdater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

// keep object element update (atomic)
public class test {
    public static void main(String[] args) {
        Student stu = new Student();

        AtomicReferenceFieldUpdater updater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");

        updater.compareAndSet(stu, null, "John");
        System.out.println(stu.toString());
    }
}

class Student {
    volatile String name;   //need volatile

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}

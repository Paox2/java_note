package com.testUnsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(null);

        System.out.println(unsafe);

        //Gets the offset address of the field
        long idOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));

        Student stu = new Student();
        unsafe.compareAndSwapInt(stu, idOffset, 0, 1);
        unsafe.compareAndSwapObject(stu, nameOffset, null, "John");

        System.out.println(stu.toString());
    }
}


class Student {
    volatile int id;
    volatile String name;   //need volatile

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
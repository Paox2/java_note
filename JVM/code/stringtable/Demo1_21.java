package cn.itcast.jvm.t1.stringtable;

/**
 * 演示字符串相关面试题
 */
public class Demo1_21 {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b"; // ab
        String s4 = s1 + s2;   // new String("ab")
        String s5 = "ab";
        String s6 = s4.intern();

// question
        System.out.println(s3 == s4); // false
        System.out.println(s3 == s5); // true
        System.out.println(s3 == s6); // true

        String x2 = new String("c") + new String("d"); // new String("cd")
        x2.intern();
        String x1 = "cd";

// queston，change position of 22,23?，if use jdk1.6?
        System.out.println(x1 == x2); // false, true, false
    }
}

package com.post;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Mailboxes {
    private static Map<Integer, GuardObject> boxes = new Hashtable<>();

    private static int id = 1;

    private static synchronized int generateID() {
        return id++;
    }

    public static GuardObject createGuardObject() {
        GuardObject go = new GuardObject(generateID());
        boxes.put(go.getId(), go);
        return go;
    }

    public static GuardObject getGuardObject(int id) {
        return boxes.remove(id);
    }

    public static Set<Integer> getIDs() {
        return boxes.keySet();
    }
}
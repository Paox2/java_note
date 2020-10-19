package com.cp;

final class Message {
    private int id;
    private Object content;

    public Message(int id, Object content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content=" + content +
                '}';
    }

    public int getId() {
        return id;
    }

    public Object getContent() {
        return content;
    }
}

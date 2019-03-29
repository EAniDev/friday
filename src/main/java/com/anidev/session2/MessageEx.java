package com.anidev.session2;

public class MessageEx {

    public static void main(String[] args) {
        Message m1 = Message.createOrGet(2, "I");
        Message m2 = Message.createOrGet(Integer.valueOf(2), "I");
        Message m3 = Message.createOrGet(new Integer(2), "I");

        System.out.printf("m1 == m2 : %b\n", m1 == m2);
        System.out.printf("m1 == m3 : %b\n", m1 == m3);

        Message hb1 = Message.heartbeat();
        Message hb2 = Message.heartbeat();

        System.out.printf("hb1 == hb2 : %b\n", hb1 == hb2);
        System.out.printf("hb1.equals(hb2) : %b\n", hb1.equals(hb2));

        Message ack1 = Message.acknowledgement("OK");
        Message ack2 = Message.acknowledgement("OK");
        System.out.printf("ack1 == ack2 :%b\n", ack1 == ack2);
        System.out.printf("ack1 == ack2 :%b\n", ack1.equals(ack2));
    }

}

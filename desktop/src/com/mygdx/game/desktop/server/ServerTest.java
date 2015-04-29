package com.mygdx.game.desktop.server;

import client.Client;
import interfaces.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by software on 29.04.15.
 */
public class ServerTest implements Listener {

    public static void main(String... args) throws IOException {
        new ServerTest().run();
    }
    private void run() throws IOException {
        Client client = new Client("localhost" , 5555);

        client.start();
        client.getClientHandler().addListener(this);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Input message: ");
            client.sendMessage(reader.readLine() + "\r\n");
            System.out.println();
        }
    }

    @Override
    public void update(String message) {
        System.out.println();
        System.out.println("received " + message);
    }
}

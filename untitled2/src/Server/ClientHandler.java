package Server;

import com.red1_torabi.mojtabat.objectsrepos.Message;
import com.red1_torabi.mojtabat.objectsrepos.Todo;
import com.red1_torabi.mojtabat.objectsrepos.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ClientHandler implements Runnable {

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    ObjectOutputStream fileObjectOutputStream;
    ObjectInputStream fileObjectInputStream;
    public static void saveData() throws IOException {
        File file = new File("message.ser");
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        messages = new ArrayList<>();
        objectOutputStream.writeObject(Server.messages);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public ClientHandler(Socket socket) throws IOException {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }


    @Override
    public void run() {
        Message order = null;
        try {
            order = (Message) objectInputStream.readObject();
            System.out.println(order.getUser().getEmail());
            System.out.println(order.getMessageType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("inter to switch");
        switch (order.getMessageType()){
            case 0:
                try {
                    logIn(order);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    register(order);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    toDo(order);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    System.out.println("default case-switch running.");
                    dataOutputStream.writeUTF("Command not recognized!");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void logIn(Message info) throws IOException, ClassNotFoundException {
       // Server.messages = (ArrayList<Message>) fileObjectInputStream.readObject();
        for(int i = 0; i < Server.messages.size(); i++){
            if(Server.messages.get(i).getUser().getUserName().equals(info.getUser().getUserName()) || Server.messages.get(i).getUser().getEmail().equals(info.getUser().getEmail()) ){
                if(Server.messages.get(i).getUser().getPassword().equals(info.getUser().getPassword())){
                    System.out.println("User information confirmed.");
                    objectOutputStream.writeObject(Server.messages.get(i));
                    objectOutputStream.flush();
                    return;
                }else{
                    info.setMessageType(4);
                    System.out.println("User password not correct.");
                    objectOutputStream.writeObject(info);
                    objectOutputStream.flush();
                    return;
                }
            }
        }
        info.setMessageType(5);
        System.out.println("User not found.");
        objectOutputStream.writeObject(info);
        objectOutputStream.flush();
    }

    private void register(Message info) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject(isUserNew(info));
        objectOutputStream.flush();
    }

    private void toDo(Message info) throws IOException, ClassNotFoundException {
        //Server.messages = (ArrayList<Message>) fileObjectInputStream.readObject();
        for(int i = 0; i < Server.messages.size(); i++){
            if(Server.messages.get(i).getUser() == info.getUser()){
                Server.messages.remove(i);
            }
        }
        System.out.println("User new tasks saved.");
        Server.messages.add(info);
        saveData();
    }

    private Message isUserNew(Message info) throws IOException, ClassNotFoundException {
//        Server.messages = (ArrayList<Message>) fileObjectInputStream.readObject();
        for(int i = 0; i < Server.messages.size(); i++){
            if(Server.messages.get(i).getUser().getEmail().equals(info.getUser().getEmail())){
                System.out.println("Email already exists.");
                return new Message(null, null, 0, 5);
            }
            if(Server.messages.get(i).getUser().getUserName().equals(info.getUser().getUserName())){
                System.out.println("Username already exists.");
                return new Message(null, null, 0, 4);
            }
        }
        System.out.println("Register successful.");
        Server.messages.add(info);
        saveData();
        return info;
    }
}

package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.red1_torabi.mojtabat.objectsrepos.Message;
import com.red1_torabi.mojtabat.objectsrepos.User;

public class Server {
    public static final int port = 8090;
    private ServerSocket ss = null;
    static ArrayList<Message> messages;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        Socket clientSocket;
        File file = new File("message.ser");
        if(!file.exists()){
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            messages = new ArrayList<>();
            objectOutputStream.writeObject(messages);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        }else {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                messages = (ArrayList<Message>) objectInputStream.readObject();
                objectInputStream.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

}

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("введите фигуру или 'Q' чтобы выйти:");
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("Q")) {
                    output.writeObject("Q");
                    break;
                } else if (command.equalsIgnoreCase("круг")) {
                    System.out.println("введите радиус:");
                    double radius = scanner.nextDouble();
                    scanner.nextLine();
                    output.writeObject(new Circle(radius));
                } else if (command.equalsIgnoreCase("прямоугольник")) {
                    System.out.println("введите ширину и высоту:");
                    double width = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    scanner.nextLine();
                    output.writeObject(new Rectangle(width, height));
                }

                String response = (String) input.readObject();
                System.out.println(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


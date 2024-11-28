import java.io.*;
import java.net.*;

abstract class Shape implements Serializable {
    public abstract double calculateArea();

}
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

public class Server {
    public static void main(String[] args) {
        try (ServerSocket Socket = new ServerSocket(4900)) {
            System.out.println("сервер запущен...");
            try (Socket socket = Socket.accept();
                 ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                while (true) {
                    Object obj = input.readObject();
                    if (obj instanceof Shape) {
                        Shape shape = (Shape) obj;
                        double area = shape.calculateArea();
                        output.writeObject("Ответ будет:\n " + area + " вот!");
                    } else if (obj.equals("Q")) {
                        System.out.println("соединение завершено...");
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        CarService carService = context.getBean(CarService.class);
        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Tuareg", 3);
        Car car2 = new Car("BMW X", 3);
        Car car3 = new Car("BMW X", 5);
        Car car4 = new Car("BMW X", 7);

        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        carService.addCar(car4);

        userService.addUser(new User("Sergey", "Fedorov", "user1@mail.ru", car1));
        userService.addUser(new User("Lena", "Levitskikh", "user2@mail.ru", car2));
        userService.addUser(new User("Mentor", "Mentorov", "user3@mail.ru", car3));
        userService.addUser(new User("Ben", "Ben", "user4@mail.ru", car4));

        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        User user = userService.getUserByCar("BMW X", 5);
        System.out.println(user);
        context.close();
    }
}

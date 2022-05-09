package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            UserService userService = context.getBean(UserService.class);

      userService.add(new User("Sergey", "Fedorov", "user1@mail.ru",
              new Car("Tuareg", 3)));
      userService.add(new User("Lena", "Levitskikh", "user2@mail.ru",
              new Car("BMW X", 3)));
      userService.add(new User("Mentor", "Mentorov", "user3@mail.ru",
              new Car("BMW X", 5)));
      userService.add(new User("Ben", "Ben", "user4@mail.ru",
              new Car("BMW X", 7)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

            User user = userService.getUserByCar("BMW X", 5);
            if (user != null) {
                System.out.println(user);
            }
        }
    }
}

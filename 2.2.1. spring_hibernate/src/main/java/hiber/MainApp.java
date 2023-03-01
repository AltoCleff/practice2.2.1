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
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Egor", "Paramonov", "egorp@mail.ru",new Car("Lada", 1));
      User user2 = new User("Pawel", "Ryazancev", "pawapudge@mail.ru", new Car("Jeep", 3));
      User user3 = new User("Ruslan", "Guliev", "GulievRus@mail.ru", new Car("Rolls-Royce", 5));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().toString());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Rolls-Royce", 5).toString());

      context.close();
   }
}

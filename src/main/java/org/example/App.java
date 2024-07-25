package org.example;

import org.example.config.DatabaseConfig;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        UserService userService=new UserServiceImpl();

        //Тфблицаны тузуу
//        userService.createUserTable();

        //Колдонуучуну сактоо
//        System.out.println(userService.saveUser(new User("Datka","Mamatjanova","datka@gmail.com")));

        //Колдонуучуну ID боюнча алуу
        System.out.println(userService.getUserById(1L));

        //Бардык колдонуучуларды алуу
        List<User> users=userService.getAllUsers();
        for (User user:users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getFirstName()+" "+user.getLastName()+ ", Email" + user.getEmail());
        }

        //Колдонуучуну жаныртуу
        User updateUser=new User();
        updateUser.setFirstName("Omurzak");
        updateUser.setLastName("Soorbaev");
        updateUser.setEmail("omurzak@gmail.com");
        userService.updateUser(1L,updateUser);

        //Колдонуучуну ID боюнча очуруу
        userService.deleteUserById(1l);
        System.out.println("User with ID 1 deleted");

        //Очурулгондон кийин бардык колдонуучуларды алуу
        users=userService.getAllUsers();
        for (User user:users) {
            System.out.println("ID: " + user.getId() + ", Name" + user.getFirstName()+" "+user.getLastName()+ ", Email" + user.getEmail());
        }

    }
}

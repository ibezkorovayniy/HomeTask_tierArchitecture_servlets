package com.MyApp;

import com.MyApp.controller.UserController;
import com.MyApp.dbConnect.Factory;
import com.MyApp.model.User;

public class Main {

    public static void main(String[] args) {
        UserController controller = Factory.getUserController(
                Factory.getUserService(
                        Factory.getUserDao(
                                Factory.getConnection())));

//        User user = new User("FirstUser","HisPassword","FirstUser@mail.com");
//         controller.create(user);
//          //controller.delete(user);
//
//
//         User user2 = controller.findById((long) 15);
//         user2.setEmail("NewMail@mail.com");
//         user2.setPassword("newPassword");
//         //user2.setId((long)17);
//         controller.update(user2);
//
//         System.out.println(user2);
//

    }
}
package com.onur.spring.cli;

import com.onur.spring.common.Common;
import com.onur.spring.domain.User;
import com.onur.spring.App;

import java.time.LocalDate;
import java.util.Scanner;

public class UserOptions {

    public static void userMenuOperations(Scanner in){
        printUserOptions();
        String userInput = in.nextLine();
        switch (userInput){
            case "1":
                System.out.println(App.userService.getAll());
                break;
            case "2":
                System.out.println("Enter User Email address: ");
                String userEmail = in.nextLine();
                User user = App.userService.getUserByEmail(userEmail);
                if (user != null){
                    System.out.println(user);
                } else {
                    System.out.println("User is not registered...");
                }
                break;
            case "3":
                System.out.println("Enter User ID: ");
                String userID = in.nextLine();
                if (userID.matches("[0-9]+")){
                    user = App.userService.getById(Long.parseLong(userID));
                    if (user != null){
                        System.out.println(user);
                    } else {
                        System.out.println("User is not registered...");
                    }
                }else {
                    System.out.println("User ID must be Numeric...");
                }
                break;
            case "4":
                System.out.println("Removing User. Enter User ID: ");
                String removeUserID = in.nextLine();
                if (removeUserID.matches("[0-9]+")){
                    user = App.userService.getById(Long.parseLong(removeUserID));
                    if (user != null){
                        if (App.userService.removeUser(user)){
                            System.out.println("User Removed Successfully : " + user);
                        } else {
                            System.out.println("Failure on User removing process..");
                        }
                    } else {
                        System.out.println("User is not registered...");
                    }
                }else {
                    System.out.println("User ID must be Numeric...");
                }
                break;
            case "5":
                System.out.println("Saving New User:");
                System.out.println("Enter new User ID: ");
                String newUserID = in.nextLine();
                if (newUserID.matches("[0-9]+")){
                    System.out.println("Enter User Name,LastName,EMail Address(Comma Seperated Ex: Kim,Li,kim@kim.com): ");
                    String newUserInfo = in.nextLine();
                    String[] newUserInfoArr = newUserInfo.split(",");

                    if (newUserInfoArr.length < 3){
                        System.out.println("Please Enter All User Information correctly...");
                    } else {
                        System.out.println("Enter new User Birthday on format YYYY-MM-DD: ");
                        String newUserBirthday = in.nextLine();

                        if (Common.isDateValidFormat(newUserBirthday)) {
                            LocalDate localDateBirthday = LocalDate.parse(newUserBirthday);

                            User newUser = new User();
                            newUser.setId(Long.parseLong(newUserID));
                            newUser.setFirstName(newUserInfoArr[0]);
                            newUser.setLastName(newUserInfoArr[1]);
                            newUser.setEmail(newUserInfoArr[2]);
                            newUser.setBirthday(localDateBirthday);

                            if (App.userService.saveUser(newUser)){
                                System.out.println("New User Saved Successfully: " + newUser);
                            } else {
                                System.out.println("Failure on User saving process..");
                            }
                        } else {
                            System.out.println("Enter new User Birthday on CORRECT format: YYYY-MM-DD");
                        }
                    }
                }else {
                    System.out.println("User ID must be Numeric...");
                }
                break;
        }
    }

    private static void printUserOptions(){
        System.out.println("===============");
        System.out.println(" Please Select User Options:");
        System.out.println("1 - Get All Users");
        System.out.println("2 - Get User By Email Address");
        System.out.println("3 - Get User By User ID");
        System.out.println("4 - Remove User");
        System.out.println("5 - Save User");
        System.out.println("9 - To Upper Menu");
    }


}

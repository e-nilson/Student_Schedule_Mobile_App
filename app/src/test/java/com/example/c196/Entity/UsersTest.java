package com.example.c196.Entity;

import junit.framework.TestCase;

import org.junit.Test;

public class UsersTest extends TestCase {

    public static final int userID = 0;
    public static final String username = "test";
    public static final String usernameEmpty = "";
    public static final String password = "test";
    public static final String passwordEmpty = "";

    @Test
    public void testValidUserInput() {
        Users user = new Users(userID, username, password);
        assertTrue(user.validUserInput(userID, username, password));
        System.out.println("Test 1: testInvalidUsernameInput() ---> Asserts TRUE that user input are valid.");
    }

    @Test
    public void testInvalidUsernameInput() {
        Users user = new Users(userID, usernameEmpty, password);
        assertFalse(user.validUserInput(userID, usernameEmpty, password));
        System.out.println("Test 2: testInvalidUsernameInput() ---> Asserts FALSE that user input is valid.");
    }

    @Test
    public void testInvalidPasswordInput() {
        Users user = new Users(userID, username, passwordEmpty);
        assertFalse(user.validUserInput(userID, username, passwordEmpty));
        System.out.println("Test 3: testInvalidPasswordInput() ---> Asserts FALSE that user input is valid.");
    }
}
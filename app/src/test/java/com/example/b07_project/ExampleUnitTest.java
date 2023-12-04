package com.example.b07_project;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginModel model;
    @Mock
    LoginView view;

    @Test
    public void checkDB_EmptyName(){
        when(view.getUsername()).thenReturn("");
        when(view.getUserEmail()).thenReturn("1");
        when(view.getUserPassword()).thenReturn("1");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(view).ToastMsg("Enter Name");
    }
    @Test
    public void checkDB_EmptyEmail(){
        when(view.getUsername()).thenReturn("1");
        when(view.getUserEmail()).thenReturn("");
        when(view.getUserPassword()).thenReturn("1");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(view).ToastMsg("Enter Email");
    }
    @Test
    public void checkDB_EmptyPassword(){
        when(view.getUsername()).thenReturn("1");
        when(view.getUserEmail()).thenReturn("1");
        when(view.getUserPassword()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(view).ToastMsg("Enter Password");
    }
    @Test
    public void testQueryDB_Student() {
        // Setup
        String name = "1";
        String email = "1";
        String password = "1";
        String identity = "Student";
        when(view.getUsername()).thenReturn(name);
        when(view.getUserEmail()).thenReturn(email);
        when(view.getUserPassword()).thenReturn(password);

        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();

        verify(model).queryDB("1", "1", "1", presenter);
        presenter.indicateJump("Student");
        verify(view).jump("Student");
    }

    @Test
    public void testQueryDB_Admin() {
        // Setup

        String name = "2";
        String email = "2";
        String password = "2";
        String identity = "Admin";
        when(view.getUsername()).thenReturn(name);
        when(view.getUserEmail()).thenReturn(email);
        when(view.getUserPassword()).thenReturn(password);

        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();

        verify(model).queryDB("2", "2", "2", presenter);
        presenter.indicateJump("Admin");
        verify(view).jump("Admin");
    }

    @Test
    public void testQueryDB_unSuccess() {
        // Setup

        String name = "123";
        String email = "155";
        String password = "144";
        String identity = "";
        when(view.getUsername()).thenReturn(name);
        when(view.getUserEmail()).thenReturn(email);
        when(view.getUserPassword()).thenReturn(password);

        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();

        verify(model).queryDB("123", "155", "144", presenter);
        presenter.Toast("Email or Password or Name Wrong");
        verify(view).ToastMsg("Email or Password or Name Wrong");
    }
}
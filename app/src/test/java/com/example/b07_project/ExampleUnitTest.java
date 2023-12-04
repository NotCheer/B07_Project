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
    public void checkDB_Student(){
        when(view.getUsername()).thenReturn("1");
        when(view.getUserEmail()).thenReturn("1");
        when(view.getUserPassword()).thenReturn("1");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(model).queryDB("1","1","1",view);
    }
    @Test
    public void checkDB_Admin(){
        when(view.getUsername()).thenReturn("2");
        when(view.getUserEmail()).thenReturn("2");
        when(view.getUserPassword()).thenReturn("2");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(model).queryDB("2","2","2",view);
    }

    @Test
    public void checkDB_Unsuccessful(){
        when(view.getUsername()).thenReturn("2");
        when(view.getUserEmail()).thenReturn("2");
        when(view.getUserPassword()).thenReturn("66659215");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(model).queryDB("2","2","66659215",view);
    }



}
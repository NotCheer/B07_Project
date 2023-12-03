package com.example.b07_project;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.text.Editable;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
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

//    @Mock
//    EditText editText_email,editText_password,editText_name;
//
//    @Mock
//    Editable email, password, name;

    @Test
    public void checkDB_EmptyName(){
        when(view.getUsername()).thenReturn("");
        when(view.getUserEmail()).thenReturn("1");
        when(view.getUserPassword()).thenReturn("1");
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.checkDB();
        verify(view).ToastMsg("Enter Name");
    }

}
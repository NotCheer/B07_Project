package com.example.b07_project;

public class LoginPresenter {
    private LoginModel model;
    private LoginView view;
    public LoginPresenter(LoginModel model, LoginView view){
        this.model = model;
        this.view = view;
    }

    public void checkDB(){
        String name = view.getUsername();
        String email = view.getUserEmail();
        String password = view.getUserPassword();
        if(name.isEmpty()){
            Toast("Enter Name");
        } else if (email.isEmpty()) {
            Toast("Enter Email");
        } else if (password.isEmpty()) {
            Toast("Enter Password");
        }else{
            model.queryDB(name,email,password,this);
        }
    }

    public void userCorrect(String identity){
        if(identity.equals("Admin")) {
            view.jumpAdmin();
        }else{
            view.jumpStudent();
        }
    }
    public void Toast(String msg) {
        view.ToastMsg(msg);
    }
}

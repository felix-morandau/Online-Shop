package controller;

import model.*;
import repo.LoginRepo;
import view.Login;

import javax.swing.JOptionPane;


public class LoginController {
    private Login view = new Login();

    public LoginController(){
        loginFunctionality();
    }

    public void loginFunctionality(){
        DatabaseInfo.clients = LoginRepo.getAllClients();
        view.getSignUpButton().addActionListener(e -> changeToSignUpView());
        view.getLoginButton().addActionListener(e -> loginButtonFunctionality());
    }

    private void changeToSignUpView(){
        view.setVisible(false);
        SignUpController signUpController = new SignUpController();
    }

    private void changeToMainMenuView(){
        view.setVisible(false);
        MainMenuController mainMenuController = new MainMenuController();
    }

    private void loginButtonFunctionality(){
        Client currentUser = LoginRepo.validateClient(view.getUsernameField().getText(),
                String.valueOf(view.getPasswordField().getPassword()));

        DatabaseInfo.setCurrentClient(currentUser);
        if(currentUser != null){
            changeToMainMenuView();
        }
        else{
            JOptionPane.showMessageDialog(null, "Wrong credentials", "Error", JOptionPane.ERROR_MESSAGE);
            clearFields();
        }

    }


    private void clearFields() {
        view.getEmailField().setText("");
        view.getUsernameField().setText("");
        view.getPasswordField().setText("");
    }

}

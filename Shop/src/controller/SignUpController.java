package controller;

import model.*;
import repo.SignUpRepo;
import view.SignUp;

import java.util.Objects;

public class SignUpController {
    private SignUp view = new SignUp();

    public SignUpController(){
        signUpFunctionality();
    }

    public void signUpFunctionality(){
        view.setVisible(true);
        view.getSignUpButtonRight().addActionListener(e -> saveData());
    }

    public void saveData(){

        if(Objects.equals(view.getUserTypeDropdown().getSelectedItem().toString(), "Client")){
            Client client = new Client(view.getUsernameField().getText(),
                    String.valueOf(view.getPasswordField().getPassword()),
                    view.getEmailField().getText(),
                    view.getNameField().getText(),
                    Integer.parseInt(view.getAgeField().getText()),
                    view.getPhoneNumberField().getText());

            boolean saved = SignUpRepo.saveClient(client);

            if(saved){
                DatabaseInfo.addClient(client);
                changeToLogin();
            }
            else{
                System.out.println("Fail.");
            }
        }
        else{
            Admin admin = new Admin(view.getUsernameField().getText(),
                    String.valueOf(view.getPasswordField().getPassword()),
                    view.getEmailField().getText());

            boolean saved = SignUpRepo.saveAdmin(admin);

            if(saved){
                DatabaseInfo.addAdmin(admin);
                changeToLogin();
            }
            else{
                clearFields();
                System.out.println("Fail.");
            }
        }
    }

    private void changeToLogin(){
        clearFields();
        view.setVisible(false);
        LoginController loginController = new LoginController();

    }

    private void clearFields() {
        view.getNameField().setText("");
        view.getAgeField().setText("");
        view.getPhoneNumberField().setText("");
        view.getEmailField().setText("");
        view.getUsernameField().setText("");
        view.getPasswordField().setText("");
    }


}

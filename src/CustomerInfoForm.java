import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

public class CustomerInfoForm extends Application {

    private int count = 0;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {



        // -----------------------------------------------------------------------------------------------------------------//
                                                    // ******** FORM *******//

        Label fName = new Label("First Name : ");
        TextField fNameText = new TextField();

        Label lName = new Label("Last Name : ");
        TextField lNameText = new TextField();

        Label address = new Label("Address : ");
        TextField addressText = new TextField();

        Label city = new Label("City : ");
        city.setMinWidth(30);
        TextField cityText = new TextField();

        Label province = new Label("Province : ");
        ComboBox<String> provinceCombo = new ComboBox<>();
        ObservableList<String> provList = FXCollections.observableArrayList(provinceList());
        provinceCombo.getItems().addAll(provList);

        Label postal = new Label("Postal : ");
        TextField postalText = new TextField();
        postalText.setPrefWidth(60);

        Label email = new Label("Email : ");
        TextField emailText = new TextField();

        Label phone = new Label("Phone : ");
        TextField phoneText = new TextField();



        VBox formVbox = new VBox();
        formVbox.setPadding(new Insets(20,55,20, 55));
        formVbox.setAlignment(Pos.CENTER);
        formVbox.setSpacing(15);

        HBox formLine1 = new HBox();
        formLine1.setSpacing(10);
        formLine1.setAlignment(Pos.CENTER);

        HBox formLine2 = new HBox();
        formLine2.setSpacing(10);
        formLine2.setAlignment(Pos.CENTER);

        HBox formLine3 = new HBox();
        formLine3.setSpacing(10);
        formLine3.setAlignment(Pos.CENTER);

        HBox formLine4 = new HBox();
        formLine4.setSpacing(10);
        formLine4.setAlignment(Pos.CENTER);

        HBox.setHgrow(fName, Priority.ALWAYS);
        HBox.setHgrow(fNameText, Priority.ALWAYS);

        HBox.setHgrow(lName, Priority.ALWAYS);
        HBox.setHgrow(lNameText, Priority.ALWAYS);

        HBox.setHgrow(address, Priority.ALWAYS);
        HBox.setHgrow(addressText, Priority.ALWAYS);

        HBox.setHgrow(city, Priority.ALWAYS);
        HBox.setHgrow(cityText, Priority.ALWAYS);

        HBox.setHgrow(province, Priority.ALWAYS);
        HBox.setHgrow(provinceCombo, Priority.ALWAYS);

        HBox.setHgrow(postal, Priority.ALWAYS);
        HBox.setHgrow(postalText, Priority.ALWAYS);

        HBox.setHgrow(email, Priority.ALWAYS);
        HBox.setHgrow(emailText, Priority.ALWAYS);

        HBox.setHgrow(phone, Priority.ALWAYS);
        HBox.setHgrow(phoneText, Priority.ALWAYS);

        formLine1.getChildren().addAll(fName, fNameText, lName, lNameText);
        formLine2.getChildren().addAll(address, addressText);
        formLine3.getChildren().addAll(city, cityText, province, provinceCombo, postal, postalText);
        formLine4.getChildren().addAll(email, emailText, phone, phoneText);

        formVbox.getChildren().addAll(formLine1,formLine2, formLine3, formLine4);


        // -----------------------------------------------------------------------------------------------------------------//
                                            // ******** BUTTONS *******//


        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(15);

        Button loadData = new Button("Load Data");
        Button save = new Button("Save");
        loadData.setPrefSize(100.0,25.0);
        save.setPrefSize(100.0, 25.0);

        RadioButton BinSave = new RadioButton("Bin Save");
        RadioButton RAFSave = new RadioButton("RAF Save");
        ToggleGroup group = new ToggleGroup();
        BinSave.setToggleGroup(group);
        RAFSave.setToggleGroup(group);

        buttonBox.getChildren().addAll(loadData, BinSave, RAFSave, save);

        // -----------------------------------------------------------------------------------------------------------------//
                                            // ******** LOAD BUTTON ACTION *******//


        String[] customerList = createInput();
        save.setDisable(true);

        loadData.setOnAction(e ->{
            String customerData = customerList[count];
            String[] custDataArr = customerData.split(",");
            fNameText.setText(custDataArr[0]);
            lNameText.setText(custDataArr[1]);
            addressText.setText(custDataArr[4]);
            cityText.setText(custDataArr[5]);
            provinceCombo.setValue(custDataArr[6]);
            postalText.setText(custDataArr[7]);
            emailText.setText(custDataArr[2]);
            phoneText.setText(custDataArr[3]);
            count++;
            save.setDisable(false);
            if(count >= (customerList.length)){
                loadData.setDisable(true);
            }
        });

        // -----------------------------------------------------------------------------------------------------------------//
                                             // ******** SAVE BUTTON ACTION *******//
        save.setOnAction(e->{
            if(BinSave.isSelected()){
                try (DataOutputStream output = new DataOutputStream(new FileOutputStream("customerBin.dat", true))){

                    output.writeUTF(fNameText.getText());
                    output.writeUTF(lNameText.getText());
                    output.writeUTF(emailText.getText());
                    output.writeUTF(phoneText.getText());
                    output.writeUTF(addressText.getText());
                    output.writeUTF(cityText.getText());
                    output.writeUTF(provinceCombo.getValue());
                    output.writeUTF(postalText.getText());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if (RAFSave.isSelected()){
                try(RandomAccessFile rafCustomer = new RandomAccessFile("customerRaf.dat", "rw")){
                    rafCustomer.seek(rafCustomer.length());
                    rafCustomer.writeUTF(fNameText.getText());
                    rafCustomer.writeUTF(lNameText.getText());
                    rafCustomer.writeUTF(emailText.getText());
                    rafCustomer.writeUTF(phoneText.getText());
                    rafCustomer.writeUTF(addressText.getText());
                    rafCustomer.writeUTF(cityText.getText());
                    rafCustomer.writeUTF(provinceCombo.getValue());
                    rafCustomer.writeUTF(postalText.getText());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        // -----------------------------------------------------------------------------------------------------------------//
                                            // ******** OUTER BORDER PANE *******//

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(heading("Customer Information"));
        borderPane.setCenter(formVbox);
        borderPane.setBottom(buttonBox);


        // -----------------------------------------------------------------------------------------------------------------//
                                                // ******** SCENE *******//

        Scene scene = new Scene(borderPane, 600, 400);

        primaryStage.setTitle("Customer Information Form");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // -----------------------------------------------------------------------------------------------------------------//
                                    // ******** CREATE CUSTOMER INPUT *******//
    private String[] createInput() throws IOException {
        String cust1= "Paul,Newton,paulN@email.org,123-456-7890,1 house street,Saskatoon,SK,S7K-3W6";
        String cust2 = "Chris,Everest,C_Ever@email.org,234-567-8901,2 house street,Iqaluit,NU,X7h-3W4";
        String cust3 = "Owen,Park,OP@email.org,345-678-9012,3 house street,Victoria,BC,V7h-3W4";
        String cust4 = "Judy,Hobbs,JB@email.org,456-789-0123,4 house street,Toronto,ON,M7h-3W4";
        String cust5 = "Kristy,Sugar,sugary@email.org,567-890-1234,5 house street,St. John's,NL,A7h-3W4";
        String cust6 = "James,Sona,SonaJay@email.org,678-901-2345,6 house street,Winnipeg,MB,R7h-3W4";
        String cust7 = "Jane,Mariam,JMa@email.org,789-012-3456,7 house street,Quebec City,QC,J7h-3W4";
        String cust8 = "Timi,Faro,FaroT@email.org,890-123-4567,8 house street,White Horse,YT,Y7h-3W4";
        String cust9 = "Pete,Jones,pete@email.org,901-234-5678,9 house street,YellowKnife,NT,X7h-3W4";
        String cust10 = "Sandy,Rivers,rivera@email.org,012-345-6789,10 house street,Edmonton,AB,T5K-3J4";

        try(DataOutputStream output = new DataOutputStream(new FileOutputStream("customerInput.dat"))){
            output.writeUTF(cust1);
            output.writeUTF(cust2);
            output.writeUTF(cust3);
            output.writeUTF(cust4);
            output.writeUTF(cust5);
            output.writeUTF(cust6);
            output.writeUTF(cust7);
            output.writeUTF(cust8);
            output.writeUTF(cust9);
            output.writeUTF(cust10);
        }

        DataInputStream input = new DataInputStream(new FileInputStream("customerInput.dat"));

        String[] customerList = new String[10];
        try{
            int index = 0;
            String value;
            while (!((value = input.readUTF()).isEmpty())){
                customerList[index] = value;
                index++;
            }
        }catch (EOFException e){

        }

        return customerList;
    }

    // -----------------------------------------------------------------------------------------------------------------//
                                        // ******** HEADING PANE *******//
    private StackPane heading(String headerText){
        Text headingText = new Text(headerText);
        headingText.setFont(Font.font(27));

        StackPane headingPane = new StackPane(headingText);
        headingPane.setPadding(new Insets(20));

        return headingPane;
    }

    // -----------------------------------------------------------------------------------------------------------------//
                                        // ******** PROVINCE LIST *******//
    private String[] provinceList() throws IOException, ClassNotFoundException {
        File file = new File("src/prov.dat");

        String[] provList;

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))){
            provList = (String[]) input.readObject();
        }
        return provList;
    }
}

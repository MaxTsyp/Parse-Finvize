package com.company;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable {
    public static String globalUrl;

    @FXML
    private ImageView img;

    @FXML
    private ProgressBar jprogressBar;

    @FXML
    private Label lbl;

    @FXML
    private TextField heigthField;

    @FXML
    private Button btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


       btn.setOnAction(event -> {TextSerch(heigthField.getText());



           ParserFinviz parse = new ParserFinviz();
           try {
               parse.ParsNumberPage();

               jprogressBar.setProgress(30);

           } catch (IOException e) {
               e.printStackTrace();
           }

           try {
               parse.ParsFirstPage();

               jprogressBar.setProgress(60);

           } catch (IOException e) {
               e.printStackTrace();
           }

           try {
               parse.Pars();
               jprogressBar.setProgress(100);
           } catch (IOException e) {
               e.printStackTrace();
           }

           lbl.setText("Готово!");
       });



    }
    public void TextSerch(String text){
        globalUrl = text;
    }
}

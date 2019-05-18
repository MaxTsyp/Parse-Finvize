package com.company;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.geometry.Orientation;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;




public class Main extends Application{

    private final StringProperty heigth = new SimpleStringProperty();
    public static String globalUrl;

    public static void main(String[] args) {

        Application.launch(args);
        System.out.println(globalUrl);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Label lblTitle = new Label();
        lblTitle.setText("Open Finviz.com on you browser, change option on screener, copy URL browser, and past Application");


        Label lbl = new Label();
        lbl.setTextFill(Color.RED);
        TextField heigthField = new TextField();
        heigthField.setPrefColumnCount(80);
        Button btn = new Button("Parse");

        btn.setOnAction(event -> {TextSerch(heigthField.getText());

            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Application.xml");
            ParserFinviz parserFinviz = context.getBean("ParseFin",ParserFinviz.class);


            try {
                parserFinviz.ParsNumberPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                parserFinviz.ParsFirstPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                parserFinviz.Pars();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lbl.setText("Done");

        });


        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, lblTitle, heigthField, btn, lbl);
        root.setPadding(new Insets(20,20,20,20));
        Scene scene = new Scene(root, 1100, 150);

        stage.setScene(scene);
        stage.setTitle("TextField in JavaFX");
        stage.show();
        System.out.println(globalUrl);
    }
    public void TextSerch(String text){
        globalUrl = text;
    }


    public void setParseFin(ParserFinviz parseFin) {
    }
}
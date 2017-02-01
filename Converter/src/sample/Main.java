package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.omg.CORBA.FREE_MEM;

public class Main extends Application {
    String  indexFrom=" ";
    String   indexTo=" ";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CONVERTER");
        Group root=new Group();
        Scene scene =new Scene (root, 640,230, Color.BEIGE);
        ObservableList<String> valute= FXCollections.observableArrayList("","AUD", "AZN", "GBP", "AMD", "BYN", "BGN", "BRL", "HUF", "DKK",
                "USD", "EUR", "INR", "KZT", "CAD", "KGS", "CNY", "MDL", "NOK", "PLN", "RON", "XDR", "SGD", "TJS", "TRY", "TMT", "UZS",
                "UAH", "CZK", "SEK", "CHF", "ZAR", "KRW", "JPY");

        Label label1=new Label ("  Select a currency");
        label1.setLayoutX(50);
        label1.setLayoutY(20);
        label1.setStyle("-fx-font:bold italic 10pt Arial");
        Label label2=new Label ("  Select a currency");
        label2.setLayoutX(440);
        label2.setLayoutY(20);
        label2.setStyle("-fx-font:bold italic 10pt Arial");

        ChoiceBox<String> from=new ChoiceBox<String>(valute);
        from.setLayoutX(50);
        from.setLayoutY(40);
        from.setBlendMode(BlendMode.HARD_LIGHT);
        from.setCursor(Cursor.CLOSED_HAND);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(2);
        effect.setOffsetY(2);
        from.setEffect(effect);
        from.setStyle("-fx-text-fill:#00000000;-fx-border-width:2pt;-fx-border-color:#000033;-fx-font:bold italic 12pt Arial;");
        from.setPrefSize(150,30);
        from.getSelectionModel().selectFirst();
        from.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                indexFrom = newValue;
            }
        });
        ChoiceBox<String> to=new ChoiceBox<String>(valute);
        to.setLayoutX(440);
        to.setLayoutY(40);
        to.setBlendMode(BlendMode.HARD_LIGHT);
        to.setCursor(Cursor.CLOSED_HAND);
        DropShadow effect2=new DropShadow();
        effect2.setOffsetX(2);
        effect2.setOffsetY(2);
        to.setEffect(effect2);
        to.setStyle("-fx-text-fill:#00000000;-fx-border-width:2pt;-fx-border-color:#000033;-fx-font:bold italic 12pt Arial;");
        to.setPrefSize(150,30);
        to.getSelectionModel().selectFirst();
        to.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                indexTo = newValue;
            }
        });

        TextField textFrom=new TextField();
        textFrom.setLayoutX(10);
        textFrom.setLayoutY(150);
        textFrom.setCursor(Cursor.TEXT);
        DropShadow effectText=new DropShadow();
        effectText.setOffsetX(2);
        effectText.setOffsetY(2);
        textFrom.setEffect(effectText);
        textFrom.setStyle("-fx-background-color:#FFFFFF;-fx-border-width:1pt;" +
                "-fx-border-color:#000033;-fx-font-weight:bold;-fx-font-size:12pt;-fx-font-family:Arial;-fx-font-style:italic");
        textFrom.setPrefSize(230,40);
        textFrom.setEditable(true);
        textFrom.setPromptText("0.00");
        textFrom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sumFrom=textFrom.getText();
            }
        });
        TextField textTo=new TextField();
        textTo.setLayoutX(400);
        textTo.setLayoutY(150);
        textTo.setCursor(Cursor.TEXT);

        effectText.setOffsetX(2);
        effectText.setOffsetY(2);
        textTo.setEffect(effectText);
        textTo.setStyle("-fx-background-color:#FFFFFF;-fx-border-width:1pt;" +
                "-fx-border-color:#000033;-fx-font-weight:bold;-fx-font-size:12pt;-fx-font-family:Arial;-fx-font-style:italic");
        textTo.setPrefSize(230,40);
        textTo.setEditable(true);
        textTo.setPromptText("0.00");
        textTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        Button convert;
        convert= ButtonBuilder.create().build();
        convert.setLayoutX(260);
        convert.setLayoutY(85);
        convert.setText("CONVERT");
        DropShadow effect3=new DropShadow();
        effect3.setOffsetX(3);
        effect3.setOffsetY(3);
        convert.setEffect(effect3);
        convert.setStyle("-fx-font: bold italic 12pt Arial;-fx-text-fill: #000033;-fx-background-color: #999999; " +
                "-fx-border-width:2px;-fx-border-radius:5;-fx-background-radius:5;-fx-border-color: #000033;");
        convert.setPrefSize(120,50);
        convert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sumFrom=textFrom.getText();
                Double sum = new Double(sumFrom);
                String date=""; // оставляем, чтоб в дальнейшем расширить возможности конвертера добавив конвертацию на заданную дату
                double  result;
                ValCurs valCurs=null;
                valCurs=valCurs.getQuotes(date);
                Valute [] val=valCurs.getValutes();
                result=sum*valCurs.getRate(val,indexFrom,indexTo);
                textTo.setText(Double.toString(result));
            }
        });
        root.getChildren().add(convert);
        root.getChildren().add(label1);
        root.getChildren().add(label2);
        root.getChildren().add(from);
        root.getChildren().add(to);
        root.getChildren().add(textFrom);
        root.getChildren().add(textTo);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

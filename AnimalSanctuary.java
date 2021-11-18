import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
/** Sanctuary which can add, display, and remove animals.
 * @author Dennis Tsui
 * @version 1.0
 */
public class AnimalSanctuary extends Application {
    private Animal animalType;
    private String name;
    private String health;
    /** Sets up screen and event handlers.
     * @param primaryStage Stage which GUI is on.
     */
    public void start(Stage primaryStage) {
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);
        primaryStage.setTitle("My Animal Sanctuary");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        Pane leftPane = new Pane();
        leftPane.setPrefSize(100, 100);
        root.setLeft(leftPane);

        Pane rightPane = new Pane();
        rightPane.setPrefSize(100, 100);
        root.setRight(rightPane);

        Image image = new Image("animalImage.jpg", 700, 500, false, true);
        ImageView view = new ImageView();
        view.setImage(image);
        root.getChildren().add(view);
        //Bottom User input
        GridPane bottomPane = new GridPane();
        bottomPane.setHgap(10);
        bottomPane.setVgap(10);
        bottomPane.setPrefSize(100, 100);

        TextField nameTextField = new TextField();
        bottomPane.add(nameTextField, 0, 1);
        Text nameText = new Text("Name:");
        nameText.setFill(Color.WHITE);
        bottomPane.add(nameText, 0, 0);

        TextField healthTextField = new TextField();
        bottomPane.add(healthTextField, 1, 1);
        Text healthText = new Text("Health:");
        healthText.setFill(Color.WHITE);
        bottomPane.add(healthText, 1, 0);

        MenuBar typeMenuBar = new MenuBar();
        Menu typeMenu = new Menu("Animal Type:");

        MenuItem dogItem = new MenuItem("Dog");
        MenuItem catItem = new MenuItem("Cat");
        MenuItem squirrelItem = new MenuItem("Squirrel");
        MenuItem birdItem = new MenuItem("Bird");
        typeMenu.getItems().add(dogItem);
        typeMenu.getItems().add(catItem);
        typeMenu.getItems().add(squirrelItem);
        typeMenu.getItems().add(birdItem);

        typeMenuBar.getMenus().addAll(typeMenu);
        bottomPane.add(typeMenuBar, 2, 1);

        Button addButton = new Button("Enter");
        bottomPane.add(addButton, 3, 0);

        bottomPane.setStyle("-fx-background-color:black");
        root.setBottom(bottomPane);
        //Top title
        Pane topPane = new Pane();
        topPane.setPrefSize(100, 100);
        BorderPane.setMargin(topPane, new Insets(30, 0, 0, 215));
        Text topText = new Text("Animal Sanctuary");
        topText.setFill(Color.RED);
        topText.setTextAlignment(TextAlignment.CENTER);
        topText.setFont(Font.font(30.0));
        topPane.getChildren().add(topText);
        root.setTop(topPane);

        //Middle
        GridPane middlePane = new GridPane();
        middlePane.setHgap(10);
        middlePane.setVgap(10);
        middlePane.setPrefSize(50, 100);
        BorderPane.setMargin(middlePane, new Insets(0, 75, 0, 75));

        ArrayList<VBox> middlePaneList = new ArrayList<>(6);
        ArrayList<Button> middlePaneButtonList = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            VBox box = new VBox();
            box.setStyle("-fx-background-color: gray");
            box.setPrefSize(100, 200);
            Text empty = new Text("Empty");
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(empty);
            middlePaneList.add(box);

            Button button = new Button();
            middlePaneButtonList.add(button);
            button.setStyle("-fx-background-color: transparent");
            button.setPickOnBounds(true);
            button.setPrefSize(100, 200);
        }
        middlePane.add(middlePaneList.get(0), 0, 0);
        middlePane.add(middlePaneList.get(1), 1, 0);
        middlePane.add(middlePaneList.get(2), 2, 0);
        middlePane.add(middlePaneList.get(3), 0, 1);
        middlePane.add(middlePaneList.get(4), 1, 1);
        middlePane.add(middlePaneList.get(5), 2, 1);

        middlePane.add(middlePaneButtonList.get(0), 0, 0);
        middlePane.add(middlePaneButtonList.get(1), 1, 0);
        middlePane.add(middlePaneButtonList.get(2), 2, 0);
        middlePane.add(middlePaneButtonList.get(3), 0, 1);
        middlePane.add(middlePaneButtonList.get(4), 1, 1);
        middlePane.add(middlePaneButtonList.get(5), 2, 1);

        BorderPane.setMargin(bottomPane, new Insets(0, 100, 0, 100));
        root.setCenter(middlePane);

        primaryStage.setScene(scene);
        primaryStage.show();

        //Events
        dogItem.setOnAction(e -> animalType = Animal.DOG);
        catItem.setOnAction(e -> animalType = Animal.CAT);
        squirrelItem.setOnAction(e -> animalType = Animal.SQUIRREL);
        birdItem.setOnAction(e -> animalType = Animal.BIRD);
        addButton.setOnAction(e -> {
            System.out.println("Button Pressed.");
            name = (nameTextField.getText().equals("")) ? ("No Name Yet") : (nameTextField.getText());
            try {
                health = (Integer.parseInt(healthTextField.getText()) >= 1
                                    && Integer.parseInt(healthTextField.getText()) <= 5)
                                    ? healthTextField.getText()
                                    : "5";
            } catch (Exception ex) {
                health = "5";
            }

            boolean isFull = true;
            for (int i = 0; i < 6; i++) {
                VBox textBox = (VBox) (middlePane.getChildren().get(i));
                System.out.println(textBox.getChildren().get(0));
                if (((Text) (textBox.getChildren().get(0))).getText().equals("Empty")
                        && textBox.getChildren().size() == 1) {
                    textBox.getChildren().remove(0);
                    textBox.getChildren().add(new Text(name));
                    textBox.getChildren().add(new Text(health));
                    textBox.getChildren().add(new Text(animalType.name()));
                    textBox.setStyle("-fx-background-color: yellow");
                    isFull = false;
                    break;
                }
            }
            if (isFull) {
                Alert a = new Alert(AlertType.NONE, "There is no more room!", ButtonType.CANCEL);
                a.show();
            }
            nameTextField.setText("");
            healthTextField.setText("");
            animalType = null;
            name = null;
            health = null;
        });

        middlePaneButtonList.get(0).setOnAction(e -> AnimalSanctuary.resetText(0, middlePane));
        middlePaneButtonList.get(1).setOnAction(e -> AnimalSanctuary.resetText(1, middlePane));
        middlePaneButtonList.get(2).setOnAction(e -> AnimalSanctuary.resetText(2, middlePane));
        middlePaneButtonList.get(3).setOnAction(e -> AnimalSanctuary.resetText(3, middlePane));
        middlePaneButtonList.get(4).setOnAction(e -> AnimalSanctuary.resetText(4, middlePane));
        middlePaneButtonList.get(5).setOnAction(e -> AnimalSanctuary.resetText(5, middlePane));

    }

    /** Resets the text box with animals.
     * @param i Index of text box removed.
     * @param middlePane Pane where text box lies.
     */
    private static void resetText(int i, GridPane middlePane) {
        System.out.println("Button " + i + " pressed!");
        VBox textBox = (VBox) (middlePane.getChildren().get(i));
        while (textBox.getChildren().size() > 0) {
            textBox.getChildren().remove(0);
        }
        textBox.getChildren().add(new Text("Empty"));
        textBox.setStyle("-fx-background-color: gray");
    }
}
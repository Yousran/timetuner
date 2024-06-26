package timetuner.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timetuner.App;

public class SceneMain {
    private Stage primaryStage;
    private double screenWidth;
    private double screenHeight;
    public Scene scene;
    public static VBox content;

    public SceneMain(Stage primaryStage, double screenWidth, double screenHeight) {
        this.primaryStage = primaryStage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        content = new VBox();

        ComponentSidebar sidebar = new ComponentSidebar(this);
        ComponentNavbar navbar = new ComponentNavbar(this);
        VBox containerVBox = new VBox(navbar, content);

        HBox containerHBox = new HBox(sidebar, containerVBox);

        StackPane root = new StackPane(containerHBox);
        root.getStyleClass().add("background");
        StackPane.setAlignment(containerHBox, Pos.CENTER);

        scene = new Scene(root, screenWidth, screenHeight);
        scene.getStylesheets().add(getClass().getResource(App.style).toExternalForm());
        sidebar.prefWidthProperty().bind(scene.widthProperty().multiply(0.2));
        content.prefWidthProperty().bind(scene.widthProperty().multiply(0.8));
        navbar.prefHeightProperty().bind(scene.heightProperty().multiply(0.1));
        content.prefHeightProperty().bind(scene.heightProperty().multiply(0.9));
    }

    public void show() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updatePage(String page) {
        content.getChildren().clear();

        switch (page) {
            case "Dashboard":
                content.getChildren().add(new PageDashboard());
                break;
            case "Add New Project":
                content.getChildren().add(new PageAddProject());
                break;
            case "Settings":
                content.getChildren().add(new PageSettings(this));
                break;
            case "Logout":
                App.loggedUser = null;
                SceneLogin loginScene = new SceneLogin(primaryStage, screenWidth, screenHeight);
                loginScene.show();
                break;
        }
    }
}
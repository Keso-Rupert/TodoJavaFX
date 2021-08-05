package dev.kesorupert;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import dev.kesorupert.view.TodoView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TodoApplication extends MobileApplication {

    @Override
    public void init() {
        addViewFactory(HOME_VIEW, () -> TodoView.getView());
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        ((Stage) scene.getWindow()).getIcons().add(new Image(TodoApplication.class.getResourceAsStream("/easy.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}

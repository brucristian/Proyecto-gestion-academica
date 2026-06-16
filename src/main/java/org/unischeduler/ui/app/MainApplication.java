package org.unischeduler.ui.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataWriter;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppNavigator.setPrimaryStage(stage);

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/ui/fxml/pages/auth/LoginView.fxml"
                )
        );

        Scene scene = new Scene(root, 1400, 800);

        stage.setTitle("UniScheduler");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        ExcelDataWriter writer = new ExcelDataWriter();
        writer.save(AppContext.FILE_PATH, AppContext.DATA_STORE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
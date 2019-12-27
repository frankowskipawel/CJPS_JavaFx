package stages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OknoDialogoweController {

    @FXML
    private Label message;

    @FXML
    private Button okButton;

    @FXML
    private Button anulujButton;

    @FXML
    public void initialize() {

    }

    @FXML
    void anulujClick(ActionEvent event) {

    }

    @FXML
    public boolean okClick() {
        if (okButton.isPressed()) {
            return true;
        }
        return false;
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }


}

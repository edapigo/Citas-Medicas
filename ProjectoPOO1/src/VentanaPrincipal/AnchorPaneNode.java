
package VentanaPrincipal;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

/**
 * Una clase que crea un archor pane.
 */
public class AnchorPaneNode extends AnchorPane {

    // Una fecha asociada al pane
    private LocalDate date;

    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> System.out.println("This pane's date is: " + date));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

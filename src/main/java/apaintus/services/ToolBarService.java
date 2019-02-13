package apaintus.services;

import javafx.scene.control.ToggleButton;

import java.util.List;

public class ToolBarService {
    public void toggle(ToggleButton button, List<ToggleButton> toggleButtonList) {
        button.setSelected(true);

        toggleButtonList.stream().filter(toggleButton -> toggleButton != button).forEach(toggleButton ->
            toggleButton.setSelected(false));
    }
}

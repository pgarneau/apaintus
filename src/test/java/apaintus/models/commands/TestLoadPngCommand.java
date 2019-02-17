package apaintus.models.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestLoadPngCommand {
    @Test
    public void testCreateLoadPngCommand(){
        LoadPngCommand test = new LoadPngCommand();
        assertNotNull(test);
    }

}
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;

public class Main {
    public static void main (String[] args){
        GUIScreen gui = TerminalFacade.createGUIScreen();
        gui.getScreen().startScreen();
        MyWindow w = new MyWindow();
        gui.showWindow(w, GUIScreen.Position.NEW_CORNER_WINDOW);
        gui.getScreen().stopScreen();
    }
}
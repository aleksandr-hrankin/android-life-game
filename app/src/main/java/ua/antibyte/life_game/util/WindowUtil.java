package ua.antibyte.life_game.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class WindowUtil {
    public static Point getWindowSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point sizeWindow = new Point();
        display.getSize(sizeWindow);
        return sizeWindow;
    }
}

package helpers;

public class GenerationHelpers {
    public static String ProcessOrbitVisibility(boolean vis)
    {
        if(vis)
        {
            return "REDRAW_AND_RECALCULATE";
        }
        else
        {
            return "OFF";
        }
    }
}

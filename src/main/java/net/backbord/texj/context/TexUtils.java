package net.backbord.texj.context;

public class TexUtils {
    public static boolean isWindowsSystem() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static String getTerminalExecutable() {
        if(TexUtils.isWindowsSystem())
            return "powershell.exe";
        return "bash";
    }
}
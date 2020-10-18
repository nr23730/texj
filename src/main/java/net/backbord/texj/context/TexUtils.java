package net.backbord.texj.context;

/**
 * Utilities that help handling TeX files.
 */
public final class TexUtils {

    private TexUtils() {
    }

    public static boolean isWindowsSystem() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    /**
     * Windows and Linux have different terminals.
     * @return The terminal executable for the corresponding operating system.
     */
    public static String getTerminalExecutable() {
        if (TexUtils.isWindowsSystem()) {
            return "powershell.exe";
        }
        return "bash -c";
    }
}

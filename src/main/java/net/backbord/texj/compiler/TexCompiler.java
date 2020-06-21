package net.backbord.texj.compiler;

/**
 * List available TeX compilers and their executables.
 */
public enum TexCompiler {
    /**
     * pdfTeX (default).
    */
    PDFTEX("pdflatex"),
    /**
     * XeTeX compiler.
     */
    XETEX("xelatex"),
    /**
     * LuaTeX compiler.
     */
    LUATEX("lualatex");

    private final String executable;

    TexCompiler(String executable) {
        this.executable = executable;
    }

    public String getExecutable() {
        return this.executable;
    }
}

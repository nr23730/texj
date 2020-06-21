package net.backbord.texj.compiler;

public enum TexCompiler {
    PDFLATEX("pdflatex"),
    XELATEX("xelatex"),
    LUATEX("lualatex");

    public final String executable;

    private TexCompiler(String executable) {
        this.executable = executable;
    }
}
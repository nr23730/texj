package net.backbord.texj.context;

import java.io.File;
import java.io.InputStream;

import net.backbord.texj.compiler.TexCompiler;

public interface TexContext {
    public void setTexCompiler(TexCompiler compiler);

    public TexCompiler getTexComiler();

    public void setLatexmk(boolean isLatexmk);

    public File compile(File file);

    public File compile(String texString);

    public byte[] compileToByteArray(File file);

    public byte[] compileToByteArray(String texString);
}
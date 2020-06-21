package net.backbord.texj.context;

import java.io.File;
import net.backbord.texj.compiler.TexCompiler;

/**
 * Interface for TexContext. Handles all the compilation of TeX files.
 */
public interface TexContext {
    void setTexCompiler(TexCompiler compiler);

    TexCompiler getTexComiler();

    void setLatexmk(boolean isLatexmk);

    File compile(File file);

    File compile(String texString);

    byte[] compileToByteArray(File file);

    byte[] compileToByteArray(String texString);
}

package net.backbord.texj.context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import net.backbord.texj.compiler.TexCompiler;
import net.backbord.texj.document.TexDocument;

/**
 * Default way to handle TeX files.
 */
public class DefaultTexContext implements TexContext {

    private TexCompiler compiler;
    private boolean isLatexmk;

    public DefaultTexContext() {
        this.compiler = TexCompiler.PDFTEX;
    }

    public DefaultTexContext(TexCompiler compiler, boolean isLatexmk) {
        this.compiler = compiler;
        this.isLatexmk = isLatexmk;
    }

    public DefaultTexContext(TexCompiler compiler) {
        this.compiler = compiler;
    }

    public DefaultTexContext(boolean isLatexmk) {
        this.isLatexmk = isLatexmk;
    }

    @Override
    public void setTexCompiler(TexCompiler newCompiler) {
        this.compiler = newCompiler;
    }

    @Override
    public TexCompiler getTexComiler() {
        return this.compiler;
    }

    @Override
    public void setLatexmk(boolean newIsLatexmk) {
        this.isLatexmk = newIsLatexmk;
    }

    @Override
    public File compile(TexDocument texDocument) {
        return compile(texDocument.getTexCode());
    }

    @Override
    public File compile(File file) {
        try {
            Path tmp = Files.createTempDirectory("texj");
            ProcessBuilder builder = new ProcessBuilder(TexUtils.getTerminalExecutable(),
                    compiler.getExecutable() + " " + file.getAbsolutePath());
            builder.directory(tmp.toFile());
            Process p = builder.start();
            p.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String s = null;
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            return new File(tmp + "/texput.pdf");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File compile(String texString) {
        try {
            Path tmp = Files.createTempDirectory("texj");
            ProcessBuilder builder = new ProcessBuilder(TexUtils.getTerminalExecutable(),
                    "echo '" + texString + "' | " + compiler.getExecutable());
            builder.directory(tmp.toFile());
            Process p = builder.start();
            p.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String s = null;
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            return new File(tmp + "/texput.pdf");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] compileToByteArray(TexDocument texDocument) {
        return compileToByteArray(texDocument.getTexCode());
    }

    @Override
    public byte[] compileToByteArray(File file) {
        try {
            return Files.readAllBytes(compile(file).toPath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] compileToByteArray(String in) {
        try {
            return Files.readAllBytes(compile(in).toPath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}

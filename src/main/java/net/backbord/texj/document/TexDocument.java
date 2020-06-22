package net.backbord.texj.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 * Loads TeX documents and handles all variables.
 */
public class TexDocument {
    private String texCode;
    private String[] escapeSequence = {"<texj>", "</texj>"};
    private Map<String, String> variables = new LinkedHashMap<>();

    /**
     * Creates a new TeX document.
     *
     * @param texFile           InputStream of a TeX file.
     * @param newEscapeSequence Sequence to escape variables in your TeX code.
     * @param charset           Charset of file.
     * @throws IOException
     */
    public TexDocument(InputStream texFile, String[] newEscapeSequence, String charset) throws IOException {
        escapeSequence = newEscapeSequence;
        convertInputStream(texFile, charset);
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile           InputStream of a TeX file.
     * @param newEscapeSequence Sequence to escape variables in your TeX code.
     * @throws IOException
     */
    public TexDocument(InputStream texFile, String[] newEscapeSequence) throws IOException {
        escapeSequence = newEscapeSequence;
        convertInputStream(texFile, StandardCharsets.UTF_8.name());
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile InputStream of a TeX file.
     * @param charset Charset of file.
     * @throws IOException
     */
    public TexDocument(InputStream texFile, String charset) throws IOException {
        convertInputStream(texFile, charset);
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile InputStream of a TeX file.
     * @throws IOException
     */
    public TexDocument(InputStream texFile) throws IOException {
        convertInputStream(texFile, StandardCharsets.UTF_8.name());
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile           InputStream of a TeX file.
     * @param newEscapeSequence Sequence to escape variables in your TeX code.
     * @param charset           Charset of file.
     * @throws IOException
     */
    public TexDocument(File texFile, String[] newEscapeSequence, String charset) throws IOException {
        escapeSequence = newEscapeSequence;
        convertFile(texFile, StandardCharsets.UTF_8.name());
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile           InputStream of a TeX file.
     * @param newEscapeSequence Sequence to escape variables in your TeX code.
     * @throws IOException
     */
    public TexDocument(File texFile, String[] newEscapeSequence) throws IOException {
        escapeSequence = newEscapeSequence;
        convertFile(texFile, StandardCharsets.UTF_8.name());
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile InputStream of a TeX file.
     * @param charset Charset of file.
     * @throws IOException
     */
    public TexDocument(File texFile, String charset) throws IOException {
        convertFile(texFile, charset);
        initalize();
    }

    /**
     * Creates a new TeX document.
     *
     * @param texFile InputStream of a TeX file.
     * @throws IOException
     */
    public TexDocument(File texFile) throws IOException {
        convertFile(texFile, StandardCharsets.UTF_8.name());
        initalize();
    }

    private void convertInputStream(InputStream texFile, String charset) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(texFile, charset))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            texCode = sb.toString();
        }
    }

    private void convertFile(File texFile, String charset) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(texFile.toPath(), Charset.forName(charset))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        texCode = contentBuilder.toString();
    }

    private void initalize() {
        assert escapeSequence.length == 2;

        for (String variable : StringUtils.substringsBetween(texCode, escapeSequence[0], escapeSequence[1])) {
            variables.put(variable, variable);
        }
    }

    public void setVariable(String variable, String value) {
        variables.put(escapeSequence[0] + variable + escapeSequence[1], value);
    }

    public String getVariable(String variable) {
        return variables.get(escapeSequence[0] + variable + escapeSequence[1]);
    }

    public String getTexCode() {
        return StringUtils.replaceEach(texCode, variables.keySet().toArray(new String[variables.keySet().size()]),
                variables.values().toArray(new String[variables.values().size()]));
    }
}

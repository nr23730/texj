# TexJ
TexJ - Use TeX Documents as templates in Java!

## Prerequisites

1. Java 8+ (8 an 11 tested)
2. LaTeX distribution installed and executable from your `PATH`

## Minimal example

### Java code

```
TexContext tex = new DefaultTexContext();
TexDocument doc = new TexDocument(new File("sometexfile.tex"));
File pdf = tex.compile(doc);
```

## Example with template

### Java code
```
TexContext tex = new DefaultTexContext();
TexDocument doc = new TexDocument(new File("sometexfile.tex"));
doc.setVariable("somevariable, "somevalue");
File pdf = tex.compile(doc);
```

### TeX code
```
\documentclass{article}
\begin{document}
Value of variable: <texj>somevariable</texj>
\end{document}
```
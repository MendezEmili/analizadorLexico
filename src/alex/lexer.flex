package alex;
import static alex.Token.*;
%%
%class Lexer
%type Token

L=[a-zA-Z_]
D=[0-9]
S=[ ( ) { } [ ] ; : ]
M=[ / * - + = ]
B=[< > ]
WHITE=[ \t\r\n]

%{
public String lexeme;
%}
%%
{WHITE} {/* ignore */}
(("//".*)|("/*"(.|\n)*"*/")) {/* ignore */}
((("+"|"-")?{D}+"."{D}+("+"|"-")("E"|"e"){D}+) | (("+"|"-")?{D}+"."{D}+) | (("+"|"-")?{D}+)) {lexeme=yytext();return NUMERICO;}
({S}|"."| "[" | "]") {lexeme=yytext(); return SIGNO;}
("&&" | "||" | "!" | "&" | "|" )  {lexeme=yytext(); return LOGICO;}
{M} {lexeme=yytext(); return MATEMATICO;}
( "!=" | ">=" | "<=" |"=="| {B}) {lexeme=yytext(); return RELACIONAL;}
("private" | "protected" | "public" | "print" | "println" | "abstract" | "class" | "extends" | "final" | "implements" | "interface" | "void" | "main" | "native" | "new" | "static" | "strictfp" | "synchronized" | "transient" | "volatile" | "break" | "case" | "continue" | "default" | "do" | "else" | "for" | "if" | "instanceof" | "return" | "switch" | "while" | "assert" | "catch" | "finally" | "throw" | "throws" | "try" | "import" | "package" | "long" | "super" | "this" | "void" | "const" | "goto" | "null" | "String" | "System" | "out" ) {lexeme = yytext(); return PALABRA;}
("int" | "char" | "boolean" | "double" | "byte" | "float" | "short" ) {lexeme=yytext(); return VAR;}
({L}|"_"|"$")({D}|"_"|"$"|{L})* {lexeme=yytext(); return ID;}
. {return ERROR;}
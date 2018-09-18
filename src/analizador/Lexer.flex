package analizador;
import jdk.nashorn.internal.ir.LiteralNode;import static analizador.Token.*;
%%
%class Lexer
%type Token
%ignorecase
%{
public String lexeme;
public int line;
%}
%line
LENGUAGE = [ABC]
LETRA = [a-zA-Z_]
DIGITO = [0-9]
ESPACIO = [ \t \r \n \f \r\n]
WHITE_SPACE_CHAR=[\ \n\r\t\f]
SIMBOLO = "*"|"+"|"-"|"/"|"#"|"!"|"#"|"$"|"%"|"&"|"/"|"("|")"|"="|"?"|"¡"|"{"|"}"|","|"."|"-"|";"|":"|"_"|"["|"]"|"<"|">"
%%

{WHITE_SPACE_CHAR} {/*No se procesa*/} // espacio en blanco
"//".* {/*No se procesa*/} // dos slash de comentario
("\(\*" [^*] ~"\*\)" | "\(\*" "\*"+ "\)") {/*No se procesa*/} // comentario multilínea
("{" [^*] ~"}" | "{" "}") {/*No se procesa*/} // comentario multilínea
"<<EOF>>" {/*No se procesa*/}
"," {lexeme=yytext(); line=yyline; return OPERADOR;}
";" {lexeme=yytext(); line=yyline; return OPERADOR;}
"++" {lexeme=yytext(); line=yyline; return OPERADOR_INCREMENTO;}
"--" {lexeme=yytext(); line=yyline; return OPERADOR_DISMINUCION;}
">=" {lexeme=yytext(); line=yyline; return OPERADOR_MAYOR_IGUAL_QUE;}
">" {lexeme=yytext(); line=yyline; return OPERADOR_MAYOR_QUE;}
"<=" {lexeme=yytext(); line=yyline; return OPERADOR_MENOR_IGUAL_QUE;}
"<" {lexeme=yytext(); line=yyline; return OPERADOR_MENOR_QUE;}
"<>" {lexeme=yytext(); line=yyline; return OPERADOR;}
"=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION;}
"+" {lexeme=yytext(); line=yyline; return OPERADOR_ADICION;}
"-" {lexeme=yytext(); line=yyline; return OPERADOR_SUSTRACCION;}
"*" {lexeme=yytext(); line=yyline; return OPERADOR_MULTIPLICACION;}
"/" {lexeme=yytext(); line=yyline; return OPERADOR_DIVISION;}
"(" {lexeme=yytext(); line=yyline; return OPERADOR_PARENTESIS_ABRIR;}
")" {lexeme=yytext(); line=yyline; return OPERADOR_PARENTESIS_CERRAR;}
"[" {lexeme=yytext(); line=yyline; return OPERADOR_CORCHETE_ABRIR;}
"]" {lexeme=yytext(); line=yyline; return OPERADOR_CORCHETE_CERRAR;}
":=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION;}
"." {lexeme=yytext(); line=yyline; return OPERADOR;}
":" {lexeme=yytext(); line=yyline; return OPERADOR;}
"+=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION_ADICION;}
"-=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION_SUSTRACCION;}
"*=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION_MULTIPLICACION;}
"/=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION_DIVISION;}
">>" {lexeme=yytext(); line=yyline; return OPERADOR_DESPLAZAMIENTO_DERECHA;}
"<<" {lexeme=yytext(); line=yyline; return OPERADOR_DESPLAZAMIENTO_IZQUIERDA;}
"<<=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION_DESPLAZAMIENTO_DERECHA;}
">>=" {lexeme=yytext(); line=yyline; return OPERADOR_ASIGNACION_DESPLAZAMIENTO_IZQUIERDA;}

"AND" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"ARRAY" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"BEGIN" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"BOOLEAN" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"BYTE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"CASE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"CHAR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"CONST" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"DIV" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;} // operador
"DO" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"DOWNTO" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"ELSE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"END" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"FALSE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"FILE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"FOR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"FORWARD" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"FUNCTION" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"GOTO" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"IF" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"IN" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"INLINE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"INT" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"LABEL" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"LONGINT" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"MOD" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;} // operador
"NIL" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"NOT" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;} // operador
"OF" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"OR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;} // operador
"PACKED" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"PROCEDURE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"PROGRAM" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"READ" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"REAL" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"RECORD" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"REPEAT" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"SET" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"SHORTINT" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"STRING" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"THEN" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"TO" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"TRUE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"TYPE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"UNTIL" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"VAR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"WHILE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"WITH" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"WRITE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"XOR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;} // operador



// |-------------------- RECONOCER EXPRESIONES --------------------| //
// Identificadores
({LETRA}({LETRA}|{DIGITO})*){1, 127} {lexeme=yytext(); line=yyline; return IDENTIFICADOR;}

// Flotantes
(({DIGITO}+"."{DIGITO}+)) |
    (({DIGITO}"."{DIGITO}+)([eE][-]?{DIGITO}+)) {lexeme=yytext(); line=yyline; return LITERAL_NUM_FLOTANTE;}

// Literales
\"({LETRA}|{DIGITO}|{ESPACIO}|{SIMBOLO})*+\" | ("#"{DIGITO}{DIGITO}) {lexeme=yytext(); line=yyline; return LITERAL_STRING;}
("(-"{DIGITO}+")")|{DIGITO}+ {lexeme=yytext(); line=yyline; return LITERAL_NUM_ENTERO;} // Un numero entero


// // |-------------------- RECONOCER ERRORES --------------------| //
// Identificadores
// no funca ({LETRA}|{DIGITO}|(.))({LETRA}|{DIGITO}|(.))* {lexeme=yytext(); line=yyline; return ERROR;}

// Flotantes


// Literales
"#"{DIGITO}{DIGITO}{DIGITO}+ {lexeme=yytext(); line=yyline; return ERROR_LITERAL;}


. {lexeme=yytext(); line=yyline; return ERROR;}
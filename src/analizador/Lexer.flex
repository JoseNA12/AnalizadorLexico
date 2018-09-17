package analizador;
import static analizador.Token.*;
%%
%class Lexer
%type Token
LENGUAGE = [ABC]
LETRA = [a-zA-Z_]
DIGITO = [0-9]
ESPACIO =[ \t\r\n]
SIMBOLO = "*"|"+"|"-"|"/"|"#"|"!"|"#"|"$"|"%"|"&"|"/"|"("|")"|"="|"?"|"¡"|"{"|"}"|","|"."|"-"|";"|":"|"_"|"["|"]"|"<"|">"
%{
public String lexeme;
public int line;
%}
%line
%%
{ESPACIO} {/*Ignore*/} // espacio en blanco
"//".* {/*Ignore*/} // dos slash de comentario
("\(\*" [^*] ~"\*\)" | "\(\*" "\*"+ "\)") {/*Ignore*/} // comentario multilínea
("{" [^*] ~"}" | "{" "}") {/*Ignore*/} // comentario multilínea
"<<EOF>>" {/*Ignore*/}
"," {lexeme=yytext(); line=yyline; return OPERADOR;}
";" {lexeme=yytext(); line=yyline; return OPERADOR;}
"++" {lexeme=yytext(); line=yyline; return OPERADOR;}
"--" {lexeme=yytext(); line=yyline; return OPERADOR;}
">=" {lexeme=yytext(); line=yyline; return OPERADOR;}
">" {lexeme=yytext(); line=yyline; return OPERADOR;}
"<=" {lexeme=yytext(); line=yyline; return OPERADOR;}
"<" {lexeme=yytext(); line=yyline; return OPERADOR;}
"<>" {lexeme=yytext(); line=yyline; return OPERADOR;}
"=" {lexeme=yytext(); line=yyline; return OPERADOR;}
"+" {lexeme=yytext(); line=yyline; return OPERADOR;}
"-" {lexeme=yytext(); line=yyline; return OPERADOR;}
"*" {lexeme=yytext(); line=yyline; return OPERADOR;}
"/" {lexeme=yytext(); line=yyline; return OPERADOR;}
"(" {lexeme=yytext(); line=yyline; return OPERADOR;}
")" {lexeme=yytext(); line=yyline; return OPERADOR;}
"[" {lexeme=yytext(); line=yyline; return OPERADOR;}
"]" {lexeme=yytext(); line=yyline; return OPERADOR;}
":=" {lexeme=yytext(); line=yyline; return OPERADOR;}
"." {lexeme=yytext(); line=yyline; return OPERADOR;}
":" {lexeme=yytext(); line=yyline; return OPERADOR;}
"+=" {lexeme=yytext(); line=yyline; return OPERADOR;}
"-=" {lexeme=yytext(); line=yyline; return OPERADOR;}
"*=" {lexeme=yytext(); line=yyline; return OPERADOR;}
"/=" {lexeme=yytext(); line=yyline; return OPERADOR;}
">>" {lexeme=yytext(); line=yyline; return OPERADOR;}
"<<" {lexeme=yytext(); line=yyline; return OPERADOR;}
"<<=" {lexeme=yytext(); line=yyline; return OPERADOR;}
">>=" {lexeme=yytext(); line=yyline; return OPERADOR;}
%caseless
"AND" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"ARRAY" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"BEGIN" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"BOOLEAN" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"BYTE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"CASE" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"CHAR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"CONST" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"DIV" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
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
"MOD" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"NIL" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"NOT" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"OF" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
"OR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}
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
"XOR" {lexeme=yytext(); line=yyline; return PALABRA_RESERVADA;}


{LETRA}({LETRA}|{DIGITO})* {lexeme=yytext(); line=yyline; return IDENTIFICADOR;} // Identificadores
({DIGITO}+"."{DIGITO}+)|({DIGITO}"."{DIGITO}+)("E-"{DIGITO}+|"E"{DIGITO}+) {lexeme=yytext(); line=yyline; return LITERAL;}
(\"({LETRA}|{DIGITO}|{ESPACIO}|{SIMBOLO})*+\" | ("#"{DIGITO}{DIGITO})) {lexeme=yytext(); line=yyline; return LITERAL;} // falta el caso de #65

("(-"{DIGITO}+")")|{DIGITO}+ {lexeme=yytext(); line=yyline; return INT;} // Un numero entero

(("#"{DIGITO}{DIGITO}{DIGITO}+)) {lexeme=yytext(); line=yyline; return ERROR;}
. {lexeme=yytext(); line=yyline; return ERROR;}
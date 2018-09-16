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
%}
%%
{ESPACIO} {/*Ignore*/} // espacio en blanco
// "//".* {/*Ignore*/} // dos slash de comentario
"<<EOF>>" {/*Ignore*/}
"," {return OPERADOR;}
";" {return OPERADOR;}
"++" {return OPERADOR;}
"--" {return OPERADOR;}
">=" {return OPERADOR;}
">" {return OPERADOR;}
"<=" {return OPERADOR;}
"<" {return OPERADOR;}
"<>" {return OPERADOR;}
"=" {return OPERADOR;}
"+" {return OPERADOR;}
"-" {return OPERADOR;}
"*" {return OPERADOR;}
"/" {return OPERADOR;}
"(" {return OPERADOR;}
")" {return OPERADOR;}
"[" {return OPERADOR;}
"]" {return OPERADOR;}
":=" {return OPERADOR;}
"." {return OPERADOR;}
":" {return OPERADOR;}
"+=" {return OPERADOR;}
"-=" {return OPERADOR;}
"*=" {return OPERADOR;}
"/=" {return OPERADOR;}
">>" {return OPERADOR;}
"<<" {return OPERADOR;}
"<<=" {return OPERADOR;}
"+" {return OPERADOR;}
"-" {return OPERADOR;}
"*" {return OPERADOR;}
"/" {return OPERADOR;}
"AND" {return PALABRA_RESERVADA;}
"ARRAY" {return PALABRA_RESERVADA;}
"BEGIN" {return PALABRA_RESERVADA;}
"BOOLEAN" {return PALABRA_RESERVADA;}
"BYTE" {return PALABRA_RESERVADA;}
"CASE" {return PALABRA_RESERVADA;}
"CHAR" {return PALABRA_RESERVADA;}
"CONST" {return PALABRA_RESERVADA;}
"DIV" {return PALABRA_RESERVADA;}
"DO" {return PALABRA_RESERVADA;}
"DOWNTO" {return PALABRA_RESERVADA;}
"ELSE" {return PALABRA_RESERVADA;}
"END" {return PALABRA_RESERVADA;}
"FALSE" {return PALABRA_RESERVADA;}
"FILE" {return PALABRA_RESERVADA;}
"FOR" {return PALABRA_RESERVADA;}
"FORWARD" {return PALABRA_RESERVADA;}
"FUNCTION" {return PALABRA_RESERVADA;}
"GOTO" {return PALABRA_RESERVADA;}
"IF" {return PALABRA_RESERVADA;}
"IN" {return PALABRA_RESERVADA;}
"INLINE" {return PALABRA_RESERVADA;}
"INT" {return PALABRA_RESERVADA;}
"LABEL" {return PALABRA_RESERVADA;}
"LONGINT" {return PALABRA_RESERVADA;}
"MOD" {return PALABRA_RESERVADA;}
"NIL" {return PALABRA_RESERVADA;}
"NOT" {return PALABRA_RESERVADA;}
"OF" {return PALABRA_RESERVADA;}
"OR" {return PALABRA_RESERVADA;}
"PACKED" {return PALABRA_RESERVADA;}
"PROCEDU" {return PALABRA_RESERVADA;}
"RE" {return PALABRA_RESERVADA;}
"PROGRAM" {return PALABRA_RESERVADA;}
"READ" {return PALABRA_RESERVADA;}
"REAL" {return PALABRA_RESERVADA;}
"RECORD" {return PALABRA_RESERVADA;}
"REPEAT" {return PALABRA_RESERVADA;}
"SET" {return PALABRA_RESERVADA;}
"SHORTINT" {return PALABRA_RESERVADA;}
"STRING" {return PALABRA_RESERVADA;}
"THEN" {return PALABRA_RESERVADA;}
"TO" {return PALABRA_RESERVADA;}
"TRUE" {return PALABRA_RESERVADA;}
"TYPE" {return PALABRA_RESERVADA;}
"UNTIL" {return PALABRA_RESERVADA;}
"VAR" {return PALABRA_RESERVADA;}
"WHILE" {return PALABRA_RESERVADA;}
"WITH" {return PALABRA_RESERVADA;}
"WRITE" {return PALABRA_RESERVADA;}
"XOR" {return PALABRA_RESERVADA;}

{LETRA}({LETRA}|{DIGITO})* {lexeme=yytext(); return IDENTIFICADOR;} // Identificadores
({DIGITO}+"."{DIGITO}+)|({DIGITO}"."{DIGITO}+)("E-"{DIGITO}+|"E"{DIGITO}+) {lexeme=yytext(); return LITERAL;}
("'"({LETRA}|{DIGITO}|{ESPACIO}|{SIMBOLO})+"'") {lexeme=yytext(); return LITERAL;} // falta el caso de #65

("(-"{DIGITO}+")")|{DIGITO}+ {lexeme=yytext(); return INT;} // Un numero entero
. {return ERROR;}
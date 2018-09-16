package analizador;

public enum Token {

    // |------------------- IDENTIFICADORES -------------------|
    IDENTIFICADOR("Identificador"),

    // |------------------- OPERADORES -------------------|
    OPERADOR("OPERADOR"),
    // Aritmeticos : XOR DIV MOD
    OPERADOR_ADICION("Operador de adicción"),
    OPERADOR_SUSTRACCION("Operador de sustracción"),
    OPERADOR_MULTIPLICACION("Operador de multiplicación"),
    OPERADOR_DIVISION("Operador de división"),
    OPERADOR_SIGNO("Operador de signo"),

    // Monarios
    OPERADOR_INCREMENTO("Operador de incremento"),
    OPERADOR_DISMINUCION("Operador de disminución"),

    // Lógicos : NOT OR AND
    OPERADOR_MAYOR_QUE("Operador mayor que"),
    OPERADOR_MAYOR_IGUAL_QUE("Operador mayor o igual que"),
    OPERADOR_MENOR_QUE("Operador menor que"),
    OPERADOR_MENOR_IGUAL_QUE("Operador menor o igual que"),
    OPERADOR_IGUAL("Operador de igualdad"),
    OPERADOR_DISTINTO("Operador distinto que"),

    // Asignación
    OPERADOR_ASIGNACION("Operador de asignación"),

    // Bits
    OPERADOR_DESPLAZAMIENTO_DERECHA("Operador desplazamiento derecha"),
    OPERADOR_DESPLAZAMIENTO_IZQUIERDA("Operador desplazamiento izquierda"),

    // Corchetes
    OPERADOR_CORCHETE_CIR_ABRIR("Operador corchete abierto"), // CIR: ( )
    OPERADOR_CORCHETE_CIR_CERRAR("Operador corchete cerrado"),
    OPERADOR_CORCHETE_CUA_ABRIR("Operador corchete abierto"), // CUA: [ ]
    OPERADOR_CORCHETE_CUA_CERRAR("Operador corchete cerrado"),

    // |------------------- PALABRAS RESERVADA -------------------|
    PALABRA_RESERVADA("Palabra reservada"),

    // |------------------- LITERALES -------------------|
    LITERAL("Literal"),
    LITERAL_NUM_ENTERO("Literal número entero"),
    LITERAL_NUM_FLOTANTE("Literal número floatante"),
    LITERAL_CARACTER("Literal caracter"),
    LIERAL_STRING("Literal strings"),

    ERROR("Error"),

    INT("");

    String nombre;
    Token(String pNombre) { nombre = pNombre; }

    public String getNombre() { return nombre; }
}

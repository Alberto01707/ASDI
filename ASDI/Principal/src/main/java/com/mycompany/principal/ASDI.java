/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principal;

/**
 *
 * @author alber
 */
import java.util.List;
import java.util.Stack;

public class ASDI implements Parser{
    
    private int i = 0;
    private boolean hayErrores = false;
    private Token a;
    private final List<Token> tokens;
    
    public ASDI(List<Token> tokens){
        this.tokens = tokens;
        a = this.tokens.get(i);
    }
    
    @Override
    public boolean parse() {
        bucle();

        if(a.tipo == TipoToken.EOF && !hayErrores){
            System.out.println("Consulta correcta");
            return  true;
        }else {
            System.out.println("Se encontraron errores");
        }
        return false;
    }
    
    private void bucle(){
         Stack<String> pila = new Stack<>();
         pila.push(""); 
         pila.push("Q");
         while(!pila.isEmpty()){
             String valorTope = pila.peek();
             if(valorTope.equals(a.lexema)){
                 pila.pop();
                 if(!pila.isEmpty()){
                     i++;
                     a = this.tokens.get(i);
                 }
             }
             else if(valorTope.equals("id") && a.tipo == TipoToken.IDENTIFICADOR){
                 pila.pop();
                 if(!pila.isEmpty()){
                     i++;
                     a = this.tokens.get(i);
                 }
             }
             else{
                 if(a.tipo == TipoToken.SELECT && valorTope.equals("Q")){
                    pila.pop();
                    pila.push("T");
                    pila.push("from");
                    pila.push("D");
                    pila.push("select");
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("D")){
                    pila.pop();
                    pila.push("P"); 
                }
                else if(a.tipo == TipoToken.DISTINCT && valorTope.equals("D")){
                    pila.pop();
                    pila.push("P");
                    pila.push("distinct"); 
                }
                else if(a.tipo == TipoToken.ASTERISCO && valorTope.equals("D")){
                    pila.pop();
                    pila.push("P"); 
                }
                else if(a.tipo == TipoToken.ASTERISCO && valorTope.equals("P")){
                    pila.pop();
                    pila.push("*"); 
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("P")){
                    pila.pop();
                    pila.push("A"); 
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("A")){
                    pila.pop();
                    pila.push("A1");
                    pila.push("A2"); 
                }
                else if(a.tipo == TipoToken.COMA && valorTope.equals("A1")){
                    pila.pop();
                    pila.push("A");
                    pila.push(","); 
                }
                else if(a.tipo == TipoToken.FROM && valorTope.equals("A1")){
                    pila.pop();
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("A2")){
                    pila.pop();
                    pila.push("A3");
                    pila.push("id");
                }
                else if(a.tipo == TipoToken.PUNTO && valorTope.equals("A3")){
                    pila.pop();
                    pila.push("id");
                    pila.push(".");
                }
                else if(a.tipo == TipoToken.COMA && valorTope.equals("A3")){
                    pila.pop();
                }
                else if(a.tipo == TipoToken.FROM && valorTope.equals("A3")){
                    pila.pop();
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("T")){
                    pila.pop();
                    pila.push("T1");
                    pila.push("T2");
                }
                else if(a.tipo == TipoToken.COMA && valorTope.equals("T1")){
                    pila.pop();
                    pila.push("T");
                    pila.push(",");
                }
                else if(a.tipo == TipoToken.EOF && valorTope.equals("T1")){
                    pila.pop();
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("T2")){
                    pila.pop();
                    pila.push("T3");
                    pila.push("id");
                }
                else if(a.tipo == TipoToken.IDENTIFICADOR && valorTope.equals("T3")){
                    pila.pop();
                    pila.push("id");
                }
                else if(a.tipo == TipoToken.COMA && valorTope.equals("T3")){
                    pila.pop();
                }
                else if(a.tipo == TipoToken.EOF && valorTope.equals("T3")){
                    pila.pop();
                }
                else{
                    hayErrores = true;
                    //i++;
                    //System.out.println("Error en el "+i+" token tipo "+a.tipo+" Valor del Tope "+valorTope);
                    pila.clear();
                }
             }
         }
    }
    
}

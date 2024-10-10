
package caritocastro;


public class PrimeraClase {
    public static void main(String[] args) {
     String nombrePersona = " Paola";
     int edad = 11;
        System.out.println("Bienvenida!" + nombrePersona);
        System.out.println("Verificaremos si usted es mayor de edad");
        System.out.println("Su edad es " + edad +" anios");
        if(edad >= 18) {
            System.out.println(nombrePersona + "es mayor de edad");
        }else {
            System.out.println(nombrePersona +" es menor de edad");
        }
        int edadFaltante = 18 - edad;
        System.out.println("te faltan:" + edadFaltante + " años para los 18");
      
     }      
 }

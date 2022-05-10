package es.ujaen.dagil;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void calcularX( ) {
        // Valores negativos de t deben lanzar una excepción
        try {
            App.calcularX(0,0,0, -1);
        } catch(Exception e) {
            System.err.println("Excepción: "+ e.getMessage());
        }
        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.calcularX(0,0,Math.PI, 0);
        } catch(Exception e) {
            System.err.println("Excepción: "+ e.getMessage());
        }
            // Valor de t igual a 0, debe devolver el mismo valor que x_ini
        // hacemos un par de comprobaciones
        assertEquals( 10, App.calcularX(10, 29, 0, 0), 0);
        assertEquals( 4, App.calcularX(4, 12, 0, 0), 0);

        // Valor de velocidad negativa, debe devolver un valor x menor que x_ini
        assertTrue( 20>App.calcularX(20, -12, 0, 5));
        assertTrue( 0>App.calcularX(0, -10, 0, 4));

        // Según https://www.areaciencias.com/fisica/tiro-parabolico-formulas/ 

        // Para una velocidad de 26m/s, un ángulo de 40º, y 3.41 segundos, X debe devolver 67.86m
        assertEquals(67.86, App.calcularX(0, 26, Math.toRadians(40), 3.41), 0.1);

        // Si en vez de partir desde X=0, damos otro valor, debe incrementarse en dicho valor
        assertEquals(67.86+10, App.calcularX(10, 26, Math.toRadians(40), 3.41), 0.1);
        assertEquals(67.86-10, App.calcularX(-10, 26, Math.toRadians(40), 3.41), 0.1);
    }

    @Test
    public void calcularTParaX( ) {
       // Si x_ini == x_fin, t debe ser 0
       assertEquals(0, App.calcularTParaX(-10, 26, Math.toRadians(40), -10), 0.1);

       // Para una velocidad de 26m/s, un ángulo de 40º, y x final de 67.86m, debe devolver 3.41 segundos
       assertEquals(3.41, App.calcularTParaX(0, 26, Math.toRadians(40), 67.86), 0.1);

       // Si parte de un x_ini distinto, y se incrementa en el mismo valor el x_fin, el tiempo debe seguir siendo el mismo
       assertEquals(3.41, App.calcularTParaX(-13, 26, Math.toRadians(40), 67.86-13), 0.1);
       assertEquals(3.41, App.calcularTParaX(22, 26, Math.toRadians(40), 67.86+22), 0.1);


    }
}
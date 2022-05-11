package es.ujaen.dagil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void calcular_X_dado_T() {
        // Valores negativos de t deben lanzar una excepción
        try {
            App.calcular_X_dado_T(0, 0, 0, -1);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_X_dado_T: " + e.getMessage());
        }
        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.calcular_X_dado_T(0, 0, Math.PI, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_X_dado_T: " + e.getMessage());
        }
        // Valor de t igual a 0, debe devolver el mismo valor que x_ini
        // hacemos un par de comprobaciones
        assertEquals(10, App.calcular_X_dado_T(10, 29, 0, 0), 0);
        assertEquals(4, App.calcular_X_dado_T(4, 12, 0, 0), 0);

        // Valor de velocidad negativa, debe devolver un valor x menor que x_ini
        assertTrue(20 > App.calcular_X_dado_T(20, -12, 0, 5));
        assertTrue(0 > App.calcular_X_dado_T(0, -10, 0, 4));

        // Según https://www.areaciencias.com/fisica/tiro-parabolico-formulas/

        // Para una velocidad de 26m/s, un ángulo de 40º, y 3.41 segundos, X debe
        // devolver 67.86m
        assertEquals(67.86, App.calcular_X_dado_T(0, 26, Math.toRadians(40), 3.41), 0.1);

        // Si en vez de partir desde X=0, damos otro valor, debe incrementarse en dicho
        // valor
        assertEquals(67.86 + 10, App.calcular_X_dado_T(10, 26, Math.toRadians(40), 3.41), 0.1);
        assertEquals(67.86 - 10, App.calcular_X_dado_T(-10, 26, Math.toRadians(40), 3.41), 0.1);
    }

    @Test
    public void calcular_T_dado_X() {
        // Si x_ini == x_fin, t debe ser 0
        assertEquals(0, App.calcular_T_dado_X(-10, 26, Math.toRadians(40), -10), 0.1);

        // Para una velocidad de 26m/s, un ángulo de 40º, y x final de 67.86m, debe
        // devolver 3.41 segundos
        assertEquals(3.41, App.calcular_T_dado_X(0, 26, Math.toRadians(40), 67.86), 0.1);

        // Si parte de un x_ini distinto, y se incrementa en el mismo valor el x_fin, el
        // tiempo debe seguir siendo el mismo
        assertEquals(3.41, App.calcular_T_dado_X(-13, 26, Math.toRadians(40), 67.86 - 13), 0.1);
        assertEquals(3.41, App.calcular_T_dado_X(22, 26, Math.toRadians(40), 67.86 + 22), 0.1);

        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.calcular_T_dado_X(0, 0, Math.PI, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_T_dado_X: " + e.getMessage());
        }
        
        // Valor PI/2 para el ángulo debe lanzar una excepción
        try {
            App.calcular_T_dado_X(0, 0, Math.PI/2, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_T_dado_X: " + e.getMessage());
        }        
    }


    @Test
    public void calcular_Y_dado_T() {
        // Valores negativos de t deben lanzar una excepción
        try {
            App.calcular_Y_dado_T(0, 0, 0, -9.8, -1);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_Y_dado_T: " + e.getMessage());
        }
        
        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.calcular_Y_dado_T(0, 0, Math.PI,-9.8, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_Y_dado_T: " + e.getMessage());
        }
        // Valor de t igual a 0, debe devolver el mismo valor que x_ini
        // hacemos un par de comprobaciones
        assertEquals(10, App.calcular_Y_dado_T(10, 29, 0, -9.8, 0), 0);
        assertEquals(4, App.calcular_Y_dado_T(4, 12, 0, -9.8, 0), 0);


        // Según https://www.areaciencias.com/fisica/tiro-parabolico-formulas/

        // Para una velocidad de 26m/s, un ángulo de 40º, y 3.41/2 segundos, Y debe
        // devolver 14.23m
        assertEquals(14.23, App.calcular_Y_dado_T(0, 26, Math.toRadians(40), -9.8, 3.41/2), 0.1);

        // Si en vez de partir desde Y=0, damos otro valor, debe incrementarse en dicho
        // valor
        assertEquals(14.23+10, App.calcular_Y_dado_T(0+10, 26, Math.toRadians(40), -9.8, 3.41/2), 0.1);
        assertEquals(14.23-10, App.calcular_Y_dado_T(0-10, 26, Math.toRadians(40), -9.8, 3.41/2), 0.1);
    }

    @Test
    public void calcular_Y_dado_X() {
        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.calcular_Y_dado_T(0, 0, Math.PI,-9.8, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcular_Y_dado_X: " + e.getMessage());
        }
        // Valor de x_fin igual a x, debe devolver el mismo valor que y_ini
        // hacemos un par de comprobaciones
        assertEquals(10, App.calcular_Y_dado_X(22, 10, 29, 0, -9.8, 22), 0);
        assertEquals(4, App.calcular_Y_dado_X(-5, 4, 12, 0, -9.8, -5), 0);


        // Según https://www.areaciencias.com/fisica/tiro-parabolico-formulas/

        // Para una velocidad de 26m/s, un ángulo de 40º, y x igual a 67.73/2, Y debe
        // devolver 14.23m
        assertEquals(14.23, App.calcular_Y_dado_X(0,0, 26, Math.toRadians(40), -9.8, 67.73/2), 0.1);
 
        // Para una velocidad de 26m/s, un ángulo de 40º, y x igual a 67.73, Y debe
        // devolver 0m
        assertEquals(0, App.calcular_Y_dado_X(0,0, 26, Math.toRadians(40), -9.8, 0), 0.1);

        // Si en vez de partir desde Y=0, damos otro valor, debe incrementarse en dicho
        // valor
        assertEquals(14.23+5, App.calcular_Y_dado_X(0,0+5, 26, Math.toRadians(40), -9.8, 67.73/2), 0.1);
        assertEquals(14.23-5, App.calcular_Y_dado_X(0,0-5, 26, Math.toRadians(40), -9.8, 67.73/2), 0.1);
    }


    @Test
    public void impacta_en_muro() {
        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.impacta_en_muro(0, 0, 12, Math.PI,-9.8, 0,0);
            fail();
        } catch (Exception e) {
            System.err.println("impacta_en_muro: " + e.getMessage());
        }
   
        // Según https://www.areaciencias.com/fisica/tiro-parabolico-formulas/

        // Para una velocidad de 26m/s, un ángulo de 40º, y x igual a 67.73/2, Y debe devolver 14.23m. 
        // Por tanto valores mayores o iguales que 14.23 deben devolver true y valores fuera de ese rango deben devolver false
        assertTrue(App.impacta_en_muro(0, 0, 26, Math.toRadians(40), -9.8, 67.73/2, 15));
        assertTrue(App.impacta_en_muro(0, 0, 26, Math.toRadians(40), -9.8, 67.73/2, 22));

        assertFalse(App.impacta_en_muro(0, 0, 26, Math.toRadians(40), -9.8, 67.73/2, 10));
        assertFalse(App.impacta_en_muro(0, 0, 26, Math.toRadians(40), -9.8, 67.73/2, 14));
    }
}
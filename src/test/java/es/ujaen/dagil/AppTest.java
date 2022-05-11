package es.ujaen.dagil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void calcularX() {
        // Valores negativos de t deben lanzar una excepción
        try {
            App.calcular_X_dado_T(0, 0, 0, -1);
            fail();
        } catch (Exception e) {
            System.err.println("calcularX: " + e.getMessage());
        }
        // Valores del angulo que no estén entre 0 y PI/2 deben lanzar una excepción
        try {
            App.calcular_X_dado_T(0, 0, Math.PI, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcularX: " + e.getMessage());
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
    public void calcularTParaX() {
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
            System.err.println("calcularTParaX: " + e.getMessage());
        }
        
        // Valor PI/2 para el ángulo debe lanzar una excepción
        try {
            App.calcular_T_dado_X(0, 0, 0, 0);
            fail();
        } catch (Exception e) {
            System.err.println("calcularTParaX: " + e.getMessage());
        }
        

    }
}
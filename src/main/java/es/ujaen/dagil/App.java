package es.ujaen.dagil;

/**
 * Cálculo de fórmulas para tiro parabólico SIN rozamiento, CON gravedad.
 * Calculadas según:
 * https://www.areaciencias.com/fisica/tiro-parabolico-formulas/
 *
 */
public class App {
    /**
     * Calcula el desplazamiento en el eje X durante un tiempo t.
     * Consideramos el rozamiento nulo.
     * 
     * @param x_ini  Posición X inicial
     * @param v_ini  Velocidad inicial
     * @param angulo Ángulo (expresado en radianes) con el que se lanza el
     *               proyectil.
     * @param t      Tiempo (medido en segundos) para el que se quiere calcular X
     * @post Si t<0, lanza una excepción de tipo ArithmeticException
     * @post Si angulo<0 o angulo>pi/2, lanza una excepción de tipo
     *       ArithmeticException
     * 
     */

    public static double calcular_X_dado_T(final double x_ini, final double v_ini, final double angulo,
            final double t) {
        if (t < 0)
            throw (new ArithmeticException("El tiempo no puede ser negativo."));
        if (angulo < 0 || angulo > Math.PI / 2)
            throw (new ArithmeticException("El ángulo debe estar entre 0 y PI/2"));
        if (t == 0)
            return x_ini;
        double x = x_ini + v_ini * Math.cos(angulo) * t;
        return x;
    }

    /**
     * Calcula el tiempo que tarda el proyectil en alcanzar un determinado punto X
     * Consideramos el rozamiento nulo.
     * 
     * @param x_ini  Posición X inicial
     * @param v_ini  Velocidad inicial
     * @param angulo Ángulo (expresado en radianes) con el que se lanza el
     *               proyectil.
     * @param x_fin  Posición final X para la que se quiere saber el tiempo en que
     *               la alcanzará.
     * @post Si el coseno del angulo es 0, lanza una excepción de tipo
     *       ArithmeticException
     * 
     */

    public static double calcular_T_dado_X(final double x_ini, final double v_ini, final double angulo,
            final double x_fin) {

        // double x = x_ini + v_ini * Math.cos(angulo) * t;
        if (angulo < 0 || angulo > Math.PI / 2)
            throw (new ArithmeticException("El ángulo debe estar entre 0 y PI/2"));
        if (angulo == Math.PI / 2)
            throw (new ArithmeticException("El ángulo no puede ser PI/2"));
        double t = (x_fin - x_ini) / (v_ini * Math.cos(angulo));
        return t;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    /**
     * Calcula el desplazamiento en el eje Y durante un tiempo t.
     * Consideramos el rozamiento nulo
     * 
     * @param y_ini Posición Y inicial
     * @param v_ini Velocidad inicial
     * @param angulo Ángulo (expresado en radianes) con el que se lanza el proyectil.
     * @param g Valor para la gravedad (normalmente -9.8)
     * @param t Tiempo (medido en segundos) para el que se quiere calcular Y
     *
     * @post Si t<0, lanza una excepción de tipo ArithmeticException
     * @post Si angulo<0 o angulo>pi/2, lanza una excepción de tipo ArithmeticException
     * 
     */

    public static double calcular_Y_dado_T(final double y_ini, final double v_ini, final double angulo, final double g,
            final double t) {
        if (t < 0)
            throw (new ArithmeticException("El tiempo no puede ser negativo."));
        if (angulo < 0 || angulo > Math.PI / 2)
            throw (new ArithmeticException("El ángulo debe estar entre 0 y PI/2"));
        if (t == 0)
            return y_ini;
        double v_0y = v_ini * Math.sin(angulo);
        double y = y_ini + v_0y * t + (1 / 2.0) * g * t * t;
        return y;
    }
    /**
     * Calcula el valor que tendra Y para un determinado valor de X
     * Consideramos el rozamiento nulo
     * 
     * @param X_ini Posición X inicial
     * @param y_ini Posición Y inicial
     * @param v_ini Velocidad inicial
     * @param angulo Ángulo (expresado en radianes) con el que se lanza el proyectil.
     * @param g Valor para la gravedad (normalmente -9.8)
     * @param x_fin Valor de X para el que se quiere calcular Y
     *
     * @post Si angulo<0 o angulo>pi/2, lanza una excepción de tipo ArithmeticException
     * 
     */

    public static double calcular_Y_dado_X(final double x_ini, final double y_ini, final double v_ini, final double angulo, final double g,
            final double x_fin) {
        if (angulo < 0 || angulo > Math.PI / 2)
            throw (new ArithmeticException("El ángulo debe estar entre 0 y PI/2"));
        if( x_fin==x_ini ) 
                return y_ini;
        double t = calcular_T_dado_X(x_ini, v_ini, angulo, x_fin);
        double y = calcular_Y_dado_T(y_ini, v_ini, angulo, g, t);
        return y;
    }


}
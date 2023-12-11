import java.util.ArrayList;
public class Parqueadero {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    public static final int TAMANO = 40;

    public static final int NO_HAY_PUESTO = -1;

    public static final int PARQUEADERO_CERRADO = -2;

    public static final int CARRO_NO_EXISTE = -3;

    public static final int CARRO_YA_EXISTE = -4;

    public static final int HORA_INICIAL = 6;

    public static final int HORA_CIERRE = 20;

    public static final int TARIFA_INICIAL = 1200;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    private Puesto puestos[];

    private int tarifa;

    private int caja;

    private int horaActual;
    private boolean abierto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    public Parqueadero( )
    {
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;
        // Crea el arreglo de puestos e inicializa cada uno de ellos
        puestos = new Puesto[TAMANO];
        for( int i = 0; i < TAMANO; i++ )
            puestos[ i ] = new Puesto( i );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    public String darPlacaCarro( int pPosicion )
    {
        String respuesta = "";
        if( estaOcupado( pPosicion ) )
        {
            respuesta = "Placa: " + puestos[ pPosicion ].darCarro( ).darPlaca( );
        }
        else
        {
            respuesta = "No hay un carro en esta posici�n";
        }

        return respuesta;
    }

    public int entrarCarro( String pPlaca )
    {
        int resultado = 0;
        if( !abierto )
        {
            resultado = PARQUEADERO_CERRADO;
        }
        else
        {
            // Buscar en el parqueadero un carro con la placa indicada
            int numPuestoCarro = buscarPuestoCarro( pPlaca.toUpperCase( ) );
            if( numPuestoCarro != CARRO_NO_EXISTE )
            {
                resultado = CARRO_YA_EXISTE;
            }

            // Buscar un puesto libre para el carro y agregarlo
            resultado = buscarPuestoLibre( );
            if( resultado != NO_HAY_PUESTO )
            {
                Carro carroEntrando = new Carro( pPlaca, horaActual );
                puestos[ resultado ].parquearCarro( carroEntrando );
            }
        }

        return resultado;
    }

    public int sacarCarro( String pPlaca )
    {
        int resultado = 0;
        if( !abierto )
        {
            resultado = PARQUEADERO_CERRADO;
        }
        else
        {
            int numPuesto = buscarPuestoCarro( pPlaca.toUpperCase( ) );
            if( numPuesto == CARRO_NO_EXISTE )
            {
                resultado = CARRO_NO_EXISTE;
            }
            else
            {
                Carro carro = puestos[ numPuesto ].darCarro( );
                int nHoras = carro.darTiempoEnParqueadero( horaActual );
                int porPagar = nHoras * tarifa;
                caja = caja + porPagar;
                puestos[ numPuesto ].sacarCarro( );
                resultado = porPagar;
            }
        }

        return resultado;
    }

    public int darMontoCaja( )
    {
        return caja;
    }

    public int calcularPuestosLibres( )
    {
        int puestosLibres = 0;
        for( Puesto puesto : puestos )
        {
            if( !puesto.estaOcupado( ) )
            {
                puestosLibres = puestosLibres + 1;
            }
        }
        return puestosLibres;
    }

    public void cambiarTarifa( int pTarifa )
    {
        tarifa = pTarifa;
    }

    private int buscarPuestoLibre( )
    {
        int puesto = NO_HAY_PUESTO;
        for( int i = 0; i < TAMANO && puesto == NO_HAY_PUESTO; i++ )
        {
            if( !puestos[ i ].estaOcupado( ) )
            {
                puesto = i;
            }
        }
        return puesto;
    }

    private int buscarPuestoCarro( String pPlaca )
    {
        int puesto = CARRO_NO_EXISTE;
        for( int i = 0; i < TAMANO && puesto == CARRO_NO_EXISTE; i++ )
        {
            if( puestos[ i ].tieneCarroConPlaca( pPlaca ) )
            {
                puesto = i;
            }
        }
        return puesto;
    }

    public void avanzarHora( )
    {
        if( horaActual <= HORA_CIERRE )
        {
            horaActual = ( horaActual + 1 );
        }
        if( horaActual == HORA_CIERRE )
        {
            abierto = false;
        }
    }

    public int darHoraActual( )
    {
        return horaActual;
    }

    public boolean estaAbierto( )
    {
        return abierto;
    }

    public int darTarifa( )
    {
        return tarifa;
    }

    public boolean estaOcupado( int pPuesto )
    {
        boolean ocupado = puestos[ pPuesto ].estaOcupado( );
        return ocupado;
    }

    // -----------------------------------------------------------------
    // Metodos parte 2 y 3
    // -----------------------------------------------------------------

    public double darTiempoPromedio(){
        int tiempoTotal = 0;
        int carros = 0;
        int tiempoProm = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                Carro carro = puesto.darCarro();
                tiempoTotal = tiempoTotal + carro.darTiempoEnParqueadero(horaActual);
                carros ++;
            }
        }
        tiempoProm = tiempoTotal/carros;
        return tiempoProm;
    }
    public Carro carroMasOchoHoras(){
        int tiempoCarro = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                Carro carro = puesto.darCarro();
                tiempoCarro = carro.darTiempoEnParqueadero(horaActual);
                if(tiempoCarro > 8){
                    return carro;
                }
            }
        }
        return null;
    }
    public Boolean hayMasOchoHoras(){
        int tiempoCarro = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                Carro carro = puesto.darCarro();
                tiempoCarro = carro.darTiempoEnParqueadero(horaActual);
                if(tiempoCarro > 8){
                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Carro> darCarrosMasDeTresHoras(){
        ArrayList<Carro> carros = new ArrayList<>();
        int tiempoCarro = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                Carro carro = puesto.darCarro();
                tiempoCarro = carro.darTiempoEnParqueadero(horaActual);
                if(tiempoCarro > 3){
                    carros.add(carro);
                }
            }
        }
        return carros;
    }
    public boolean hayCarrosPlacaIgual(){
        String placa1 = "";
        String placa2 = "";
        for(int i = 0; i < puestos.length; i++){
            if(puestos[i].estaOcupado()){
                Carro carro1 = puestos[i].darCarro();
                placa1 = carro1.darPlaca();
            }
            for(int j = 0; j < puestos.length; j++){
                if(puestos[j].estaOcupado()){
                    Carro carro2 = puestos[j].darCarro();
                    placa2 = carro2.darPlaca();
                }
                if(placa1.equals(placa2)){
                    return true;
                }

            }
        }
        return false;
    }
    public Boolean hayCarroCon24Horas(){
        int tiempoCarro = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                Carro carro = puesto.darCarro();
                tiempoCarro = carro.darTiempoEnParqueadero(horaActual);
                if(tiempoCarro >= 24){
                    return true;
                }
            }
        }
        return false;
    }
    public void carrosQueComienzanConPlacaPB(){
        int cont = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                Carro carro = puesto.darCarro();
                String placa = carro.darPlaca();
                if(placa.startsWith("PB")){
                    cont++;
                }
            }
        }
        if(hayCarroCon24Horas()){
            System.out.println("Cantidad de carros con placa PB: " + cont + "\nHay carro parqueado por 24 o mas horas: si");
        } else{
            System.out.println("Cantidad de carros con placa PB: " + cont + "\nHay carro parqueado por 24 o mas horas: no");
        }
    }
    public void desocuparParqueadero(){
        int cont = 0;
        for(Puesto puesto : puestos){
            if(puesto.estaOcupado()){
                puesto.sacarCarro();
                cont++;
            }
        }
        System.out.println("Cantidad de carros sacados: " + cont);
    }
}

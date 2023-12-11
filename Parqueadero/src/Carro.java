public class Carro {
// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    private String placa;

    private int horaLlegada;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    public Carro( String pPlaca, int pHora )
    {
        placa = pPlaca;
        horaLlegada = pHora;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    public String darPlaca( )
    {
        return placa;
    }

    public int darHoraLlegada( )
    {
        return horaLlegada;
    }

    public boolean tienePlaca( String pPlaca )
    {
        boolean tienePlaca = false;
        if( placa.equalsIgnoreCase( pPlaca ) )
        {
            tienePlaca = true;
        }
        else
        {
            tienePlaca = false;
        }
        return tienePlaca;
    }

    public int darTiempoEnParqueadero( int pHoraSalida )
    {
        int tiempoParqueadero = pHoraSalida - horaLlegada + 1;
        return tiempoParqueadero;
    }
}

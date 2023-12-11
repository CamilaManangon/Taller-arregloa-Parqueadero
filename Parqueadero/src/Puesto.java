public class Puesto {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    private Carro carro;
    private int numeroPuesto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    public Puesto( int pPuesto )
    {
        carro = null;
        numeroPuesto = pPuesto;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    public Carro darCarro( )
    {
        return carro;
    }

    public boolean estaOcupado( )
    {
        boolean ocupado = carro != null;
        return ocupado;
    }

    public void parquearCarro( Carro pCarro )
    {
        carro = pCarro;
    }

    public void sacarCarro( )
    {
        carro = null;
    }

    public int darNumeroPuesto( )
    {
        return numeroPuesto;
    }

    public boolean tieneCarroConPlaca(String pPlaca )
    {
        boolean tieneCarro = true;

        if( carro == null )
        {
            tieneCarro = false;
        }
        else if( carro.tienePlaca( pPlaca ) )
        {
            tieneCarro = true;
        }
        else
        {
            tieneCarro = false;
        }

        return tieneCarro;
    }

}
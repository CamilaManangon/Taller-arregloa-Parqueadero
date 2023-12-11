public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        parqueadero.entrarCarro("ABH2364");
        parqueadero.entrarCarro("PBA5489");
        parqueadero.entrarCarro("MBA7894");
        parqueadero.entrarCarro("PBI0147");
        parqueadero.entrarCarro("PAC8012");
        parqueadero.entrarCarro("AAE1254");
        parqueadero.entrarCarro("ABH2364");
        System.out.println("Puestos libres: " + parqueadero.calcularPuestosLibres());
        parqueadero.sacarCarro("AAE1254");
        System.out.println("Puestos libres luego de sacar carro: " + parqueadero.calcularPuestosLibres());
        System.out.println("Tarifa: " + parqueadero.darTarifa());
        System.out.println("Caja: " + parqueadero.darMontoCaja());
        parqueadero.avanzarHora();
        parqueadero.sacarCarro("PAC8012");
        System.out.println("Caja final: " + parqueadero.darMontoCaja());
        parqueadero.cambiarTarifa(1000);
        System.out.println("Tarifa final: " + parqueadero.darTarifa());
        System.out.println("\n------------------Metodos implementados parte 2 y 3------------------\n");
        System.out.println("Tiempo promedio en el que los carros están en el parqueadero: " + parqueadero.darTiempoPromedio());
        System.out.println("Hay carros mas de 8 horas: " + parqueadero.hayMasOchoHoras());
        System.out.println("Dar carros mas de tres horas: " + parqueadero.darCarrosMasDeTresHoras());
        System.out.println("Hay carros con placas iguales: " + parqueadero.hayCarrosPlacaIgual());
        parqueadero.carrosQueComienzanConPlacaPB();
        parqueadero.desocuparParqueadero();
        System.out.println("Puestos libres luego de desocupar parqueadero: " + parqueadero.calcularPuestosLibres());
    }
}

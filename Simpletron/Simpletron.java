import java.util.Scanner;

public class Simpletron{

	private int acumulador;
	private int [] memoria;
	private int instruccionRegistro;
	private int instruccionContador;
	private int codigoOp;
	private int operador;
	
	public Simpletron ( ){

		mensajeInicial ();
		IniciarVariables ();
		runSimulador ();
	}

	public void mensajeInicial ( ){

		System.out.printf ("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s %s\n%s %s\n", 
			"*** ¡Bienvenido a Simpletron! ***",
			"*** Por favor ingrese su programa una instrucción ***",
			"*** (o palabra de datos) a la vez en la entrada   ***",
			"*** campo de texto. Voy a mostrar la ubicación   ***",
			"*** número y un signo de interrogación (?). Entonces tú  ***",
			"*** escriba la palabra para esa ubicación. presione el ***",
			"*** Botón Listo para dejar de ingresar a su programa ***",
			" Loc", " Inst", "****", "*****");
	}


	public void runSimulador (){

		int instruccionEnviada = 0;
		int punteroMemoria = 0;

		Scanner input = new Scanner ( System.in );
		
		do{

			System.out.printf ("%d %s  ", punteroMemoria, "?" );
			instruccionEnviada = input.nextInt ();
			if ( instruccionEnviada != -99999 ) 
				memoria [ punteroMemoria ] = instruccionEnviada;
			punteroMemoria++;
			
		} while ( instruccionEnviada != -99999 );
		
	        System.out.printf ("\n%s%s", "*** Carga del programa completada ***\n", 
				"*** Comienza la ejecución del programa ***\n");	
		
		for ( int code : memoria ){


			if ( code != 0 ){

				load ();
				execute ( operador, codigoOp );
			}
		}

	}
	
	public void IniciarVariables ( ){

		memoria = new int [100];
		instruccionContador = 0;

	}

	public void load ( ){
		
		codigoOp = memoria [ instruccionContador ] / 100;
		operador = memoria [ instruccionContador ] % 100;

	}


	public void execute (int operador, int operacion ){

		switch ( operacion ){

			case 10:
				Scanner input = new Scanner ( System.in );
				System.out.print ( "Ingrese un número entero (positivo o negativo)): " );
				memoria [ operador ] = input.nextInt ();
				break;
			case 11:
				System.out.println ("El resultado de la operación es " + memoria [ operador] );
				break;
			case 20:
				acumulador = memoria [ operador ];
				break;
			case 21:
				memoria [ operador ] = acumulador;
				break;
			case 30:

				acumulador += memoria [ operador ];
				break;
			case 31:

				acumulador -= memoria [ operador ];
				break;
			case 32:

				acumulador /=  memoria [ operador ];
				break;
			case 33:

				acumulador *= memoria [ operador ];
				break;
			case 40:
				instruccionContador = operador;
				break;
			case 41:
				if ( acumulador < 0 )
					instruccionContador = operador;
				break;
			case 42:
				if ( acumulador == 0 )
					instruccionContador = operador;
				break;
			case 43:
				dumpTheCore ();
				System.out.printf ("\n%s\n", "El programa ha terminado...");
				System.exit ( 0 );
				break;

		}

		instruccionContador++;

	}


	public void dumpTheCore ( ){

		System.out.printf ("\n%30s\n%30s\t%s%4d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n\n%30s\n", "Registros:", 
				"acumulador", "+", acumulador, "instruccion contador", instruccionContador, "instruccion registro",
			       	instruccionContador, "codigo operacion", codigoOp, "operador", operador, "Memoria:" );

		for ( int i = 0; i < 10; i++ ){
			System.out.printf ( "%6d", i);
		}

		System.out.println ();
		int contador = 0;

		for (int i = 0; i < 10; i++ ) {

			if ( contador %10 == 0 )
				System.out.printf ("%2d ", contador);
			for (int j = 0; j < 10; j++) {

				if ( memoria [ contador ] == 0 )
					System.out.printf ( "%s%s", "+", "0000 ");
				else 
					System.out.printf ("%s%4d ", "+", memoria [contador]);
				contador++;

			}
		System.out.println ();	
		}
	}
}

package view;

import java.util.concurrent.Semaphore;

import controller.Cozinha;

public class Restaurante {

	public static void main(String[] args)
	{
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int idThread = 1; idThread < 6; idThread++)
		{
			Thread t = new Cozinha(idThread, semaforo);
			t.start();
		}

	}

}

package controller;

import java.util.concurrent.Semaphore;

public class Cozinha extends Thread
{
	private int idPrato;
	private Semaphore semaforo;
	private int tempoPrep;
	private int porcentagem;
	private final int passo = 100;
	
	public Cozinha(int idPrato, Semaphore semaforo)
	{
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run()
	{
		Preparo();
		
		try
		{
			semaforo.acquire();
			Entrega();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			semaforo.release();
		}
	}
	
	private boolean ePar()
	{
		if (idPrato % 2 == 0)
		{
			return true;
		}
		return false;
	}

	private void Preparo()
	{
		if(ePar())
		{
			tempoPrep = (int) ((Math.random() * 601) + 600);
			System.out.println("Pedido #" + idPrato + ": Lasanha a Bolonhesa, tempo de preparo: " + tempoPrep);
			
			while (porcentagem < 100)
			{
				try
				{
					sleep(passo);
					System.out.println("Pedido #" + idPrato + ", " + porcentagem + "% concluído.");
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				porcentagem += tempoPrep / passo;
			}
			
		}
		else
		{
			tempoPrep = (int) ((Math.random() * 301) + 500);
			System.out.println("Pedido #" + idPrato + ": Sopa de Cebola, tempo de preparo: " + tempoPrep);
			
			while (porcentagem < 100)
			{
				try
				{
					sleep(passo);
					System.out.println("Pedido #" + idPrato + ", " + porcentagem + "% concluído.");
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				porcentagem += tempoPrep / passo;
			}
			
		}
	}
	
	private void Entrega()
	{
		System.out.println("Entregando pedido #" + idPrato + "...");
		try
		{
			sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Pedido #"+idPrato+" entregue.");
	}
}

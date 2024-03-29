package cursojava.thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread {

	private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha_fila = new ConcurrentLinkedQueue<ObjetoFilaThread>();

	public static void add(ObjetoFilaThread objetoFilaThread) {
		pilha_fila.add(objetoFilaThread);
	}

	@Override
	public void run() {
		System.out.println("Fila iniciada...");
		
		while(true) {

		synchronized (pilha_fila) { /*Bloqueia o acesso a esta lista por outros processos*/
		Iterator iteracao = pilha_fila.iterator();
		while (iteracao.hasNext()) { /* Enquanto conter dados na lista ir� processar */

			ObjetoFilaThread processar = (ObjetoFilaThread) iteracao.next(); /* Pega o objeto atual */

			/* Exemplo de processo 1: Processar 10 mil notas fiscais */
			/* Exemplo de processo 2: Gerar uma lista enorme de pdf */
			/* Exemplo um envio em massa de email */
			
			System.out.println("-------------------------------");
			System.out.println(processar.getEmail());
			System.out.println(processar.getNome());
			

			iteracao.remove(); /* Remove para o pr�ximo objeto */
			try {
				Thread.sleep(1000); /* Dar um tempo para descarga de mem�ria */
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}		
		try {
			Thread.sleep(1000); /*Processou toda lista de objetos, d� um tempo para a limpeza de mem�ria*/
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	
		
	}
	


}

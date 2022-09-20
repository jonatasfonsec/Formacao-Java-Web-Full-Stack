package modulo_datas;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;

public class DatasEmJava8 {

	public static void main(String[] args) throws ParseException, InterruptedException {
		
		Instant inicio = Instant.now();
		
		Thread.sleep(2000);
		
		Instant ifinal = Instant.now();
		
		Duration duracao = Duration.between(inicio, ifinal);
		
		System.out.println(" Dura��o em nano segundos : " + duracao.toNanos());
		
		System.out.println(" Dura��o em minutos : " + duracao.toMinutes());
		
		
		
		
		
	}

}

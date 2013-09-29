package gerenciamentodefrota.scheduled;

import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;

@ApplicationScoped
@Scheduled(fixedRate = 60 * 60 * 1000)
public class TesteTask implements Task {
	
	// TODO - Aprender como configurar o cron
	// http://quartz-scheduler.org/documentation/quartz-2.1.x/tutorials/crontrigger
	
	@Override
	public void execute() {
		System.out.println("Contagem:" + LocalDateTime.now());
	}
	
}

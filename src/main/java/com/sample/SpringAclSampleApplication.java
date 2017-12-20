package com.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sample.document.domain.Document;
import com.sample.document.domain.DocumentRepository;
import com.sample.workflow.domain.WorkFlow;
import com.sample.workflow.domain.WorkFlowRepository;

@SpringBootApplication
@EnableJpaRepositories
public class SpringAclSampleApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringAclSampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner createDocuments(final DocumentRepository repository) {
		
		return args -> {

			repository.save(new Document("Livro sobre Java", "Tudo o que você precisa saber sobre Java"));
			repository.save(new Document("Livro sobre PHP", "Tudo o que você precisa saber sobre PHP"));
			repository.save(new Document("Livro sobre Javascript", "Tudo o que você precisa saber sobre Javascript"));
			repository.save(new Document("Livro sobre Spring", "Tudo o que você precisa saber sobre Spring"));
			repository.save(new Document("Livro sobre React", "Tudo o que você precisa saber sobre React"));
			repository.save(new Document("Livro sobre Katana", "Tudo o que você precisa saber sobre Katana"));

			//createFakeDocuments(repository);
		};
	}
	
	@Bean
	public CommandLineRunner createWorkflow(final WorkFlowRepository repository) {
		
		return args -> {

			repository.save(new WorkFlow("Preciso de um novo monitor", "Preciso de um novo monitor, pois quebrei o meu na marretada"));
			repository.save(new WorkFlow("Liberar acesso ao Youtube", "Preciso ver uma zueira para me desestressar"));
			repository.save(new WorkFlow("Acesso de administrador na minha maquina", "Preciso instalar uns paranaues sem licenca"));
		};
	}

/*	private void createFakeDocuments(DocumentRepository repository) {

		for (int i = 0; i < 1000; i++) {
			
			repository.save(new Document("Fake " + i, "Fakezao " + i));
		}
	}*/
}

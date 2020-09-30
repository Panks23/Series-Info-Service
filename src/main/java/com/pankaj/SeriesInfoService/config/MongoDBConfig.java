package com.pankaj.SeriesInfoService.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.pankaj.SeriesInfoService.model.User;
import com.pankaj.SeriesInfoService.mongo.repository.UsersRepository;


//@EnableMongoRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class MongoDBConfig {

//	@Bean
//	CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				usersRepository.save(new User("1", "Pankaj", 1000L));
//				usersRepository.save(new User("2", "Singh", 2000L));
//
//			}
//		};
//	}
	

}

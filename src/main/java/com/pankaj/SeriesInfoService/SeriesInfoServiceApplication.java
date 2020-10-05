package com.pankaj.SeriesInfoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.pankaj.SeriesInfoService.model.Series;

@SpringBootApplication
@EnableCaching
public class SeriesInfoServiceApplication {


	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, Series> redisTemplateSeries(){
		final RedisTemplate<String, Series> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Series>(Series.class));
		return redisTemplate;
		
	}
	public static void main(String[] args) {
		SpringApplication.run(SeriesInfoServiceApplication.class, args);
	}

}

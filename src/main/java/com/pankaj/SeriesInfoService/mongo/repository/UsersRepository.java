package com.pankaj.SeriesInfoService.mongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.pankaj.SeriesInfoService.model.User;

public interface UsersRepository extends MongoRepository<User, Integer >{


}

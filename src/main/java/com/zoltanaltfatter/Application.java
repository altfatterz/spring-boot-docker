package com.zoltanaltfatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@SpringBootApplication
public class Application {

	@Autowired
	SchoolRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Document(collection = "schools")
class School {

	@Id
	private String id;

	private final String name;
	private final String city;

	@JsonCreator
	public School(@JsonProperty("name") String name, @JsonProperty("city") String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
}

@RepositoryRestResource(collectionResourceRel = "schools", path = "/schools")
interface SchoolRepository extends MongoRepository<School, String> {

	List<School> findByCityIgnoreCase(@Param("city") String city);

}

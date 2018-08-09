package com.example.server;

import com.example.server.conf.SecurityConfiguration;
import com.example.server.conf.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SecurityConfiguration.class, WebConfiguration.class })
public class ServerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}

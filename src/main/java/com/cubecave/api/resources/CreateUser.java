package com.cubecave.api.resources;

import com.cubecave.api.database.LoadDatabase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "create-new-user-account")
public class CreateUser {
    private final LoadDatabase loadDatabase;

    private final GenerateUuid generateUuid;

    public CreateUser(LoadDatabase loadDatabase, GenerateUuid generateUuid) {
        this.loadDatabase = loadDatabase;
        this.generateUuid = generateUuid;}

    @PostMapping
    ResponseEntity<?> user(@RequestBody Map<Object, String> payload) throws SQLException {
        String uuid = generateUuid.uuid().toString();
        String username = payload.get("username");
        String email = payload.get("email");
        String discord = payload.get("discord");
        String password = payload.get("password");

        if (Objects.equals(username, "") || Objects.equals(email, "") || Objects.equals(discord, "") || Objects.equals(password, "")) {
            return ResponseEntity.status(200).body("Não deixe campos vázios!");
        }

        Connection conn = loadDatabase.connect();

        String SQL_CREATE_USER = "INSERT INTO users (uuid, username, email, discord, password) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(SQL_CREATE_USER);
        statement.setObject(1, uuid, Types.OTHER);
        statement.setString(2, username);
        statement.setString(3, email);
        statement.setString(4, discord);
        statement.setString(5, password);

        statement.execute();

        return ResponseEntity.status(202).body("Conta criada com sucesso!");
    }
}

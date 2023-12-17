package ru.netology.netologydiplombackend.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@EnableAutoConfiguration
public class CloudStoreRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CloudStoreRepository.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_PASSWORD = "select password from users where login = ?";

    private static final String SQL_INSERT_TOKEN = "insert into tokens (token) values (?)";

    private static final String SQL_DELETE_TOKEN = "delete from tokens where token = ?";

    private static final String SQL_COUNT_TOKEN = "select count(*) from tokens where token = ?";


    public CloudStoreRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getPassword(String login) {
        LOG.debug("!!!getPassword for login: {}", login);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(SQL_SELECT_PASSWORD, login);
        if (result.size() == 1) {
            //TODO лучше хранить пароль в зашифрованном виде
            return result.get(0).containsKey("password") ? result.get(0).get("password").toString() : null;
        }
        else return null;
    }

    public void addToken(String token) {
        jdbcTemplate.update(SQL_INSERT_TOKEN, token);
    }

    public void deleteToken(String token) {
        jdbcTemplate.update(SQL_DELETE_TOKEN, token);
    }

    public boolean findToken(String token) {
        int count = jdbcTemplate.queryForObject(SQL_COUNT_TOKEN, Integer.class, token);
        return count > 0;
    }
}

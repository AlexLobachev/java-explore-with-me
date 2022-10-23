package ru.practicum.ewm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс статистики со свойствами <b>id</b>, <b>app</b>, <b>uri</b>, <b>api</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "hit")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hit {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле идентификатор сервиса
     */
    private String app;
    /**
     * Поле URI
     */
    private String uri;
    /**
     * Поле ip-адрес пользователя
     */
    private String ip;
    /**
     * Поле дата просмотра события
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit that = (Hit) o;
        return id == that.id && app.equals(that.app) && uri.equals(that.uri) && ip.equals(that.ip) && timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, app, uri, ip, timestamp);
    }
}

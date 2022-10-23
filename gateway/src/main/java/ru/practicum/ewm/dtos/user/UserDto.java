package ru.practicum.ewm.dtos.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Класс ДТО пользователей со свойствами:
 * <b>id</b>, <b>name</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class UserDto {
    /**
     * Поле идентификатор
     */
    private Long id;
    /**
     * Поле имя
     */
    @NotBlank
    @NotNull
    private String name;
    /**
     * Поле email
     */
    @NotBlank
    @Email
    private String email;
}

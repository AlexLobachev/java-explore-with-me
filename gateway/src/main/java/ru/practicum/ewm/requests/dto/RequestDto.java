package ru.practicum.ewm.requests.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestDto {
    private long id;
    private LocalDateTime created = LocalDateTime.now();
    private StatusRequest status = StatusRequest.PENDING;
}

package com.fivengers.blooming.project.adapter.in.web;


import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertListResponse;
import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertUseCase concertUseCase;

    @GetMapping
    public ApiResponse<List<ConcertListResponse>> concertList() {

        return new ApiResponse<>(HttpStatus.OK.value(),
                concertUseCase.searchAll().stream()
                        .map(ConcertListResponse::from)
                        .toList());
    }
}

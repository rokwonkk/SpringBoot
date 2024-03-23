package com.springboot.rest.service;

import com.springboot.rest.dto.MemberDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 실제 운영환경에 적용되는 버전들은 스프링 부트 버전보다 낮은 경우가 많다.
 * 그래서 RestTemplate을 많이 사용하고 있지만 최신 버전에서는 RestTemplate이 지원 중단이 되어서
 * WebClient를 사용할 것을 권고하고 있다.
 * 그래서 WebClient도 알아보겠다.
 * <p>
 * 논블로킹 I/O를 지원
 * 리액티브 스트림의 백 프레셔를 지원
 * 적은 하드웨어 리소스로 동시성 제어
 * 함수형 API 지원
 * 동기,비동기 상호작용 지원
 * 스트리밍 지원
 * <p>
 * defaultHeader() - 기본 헤더 설정
 * defaultCookie() - 기본 쿠기 설정
 * defaultVariable() - 기본 uri 확장값 설정
 * defaultfilter() - 발생하는 요처에 대한 필터 설정
 */
@Service
public class WebClientService {

    public String getName() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri("/api/v1/crud-api")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getNameWithPathVariable() {
        WebClient webClient = WebClient.create("http://localhost:9090");

        ResponseEntity<String> responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api/{name}")
                        .build("flature"))
                .retrieve().toEntity(String.class).block();

        //간략 작성
        ResponseEntity<String> responseEntity1 = webClient.get()
                .uri("/api/v1/crud-api/{name}", "flature")
                .retrieve()
                .toEntity(String.class)
                .block();

        return Objects.requireNonNull(responseEntity).getBody();
    }

    public String getNameWithParameter() {

        WebClient webClient = WebClient.create("http://localhost:9090");

        return webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api")
                        .queryParam("name", "flature")
                        .build())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    public String cloneWebClient() {
//        WebClient webClient = WebClient.create("http://localhost:9090");
//        webClien
//        ]t clone = webClient.mutate().build();
        return "ok";
    }

    public ResponseEntity<MemberDto> postWithParamAAndBody() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDto dto = new MemberDto();
        dto.setName("flature!!");
        dto.setEmail("rokwonkk@gmail.com");
        dto.setOrganization("Around Hub Studio");

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api")
                        .queryParam("name", "flature")
                        .queryParam("email", "rokwonkk@rokwonkk.io")
                        .queryParam("organization", "wikiboos")
                        .build())
                .bodyValue(dto)
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

    public ResponseEntity<MemberDto> postWithHeader() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDto dto = new MemberDto();
        dto.setName("flature!!");
        dto.setEmail("rokwonkk@gmail.com");
        dto.setOrganization("Around Hub Studio");

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api/add-header")
                        .build())
                .bodyValue(dto)
                .header("my-header", "rokwonkk API")
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }
}

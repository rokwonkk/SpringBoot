package com.manning.sbip.ch04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 부트는 어플리케이션 운영에 필요한 기능도 제공.
 * 상용 환경에 배포되고 실재 고객과 사용자가 사용하고 있으면 어플리케이션은 운영 중이라 할 수 있다.
 * 문제 없이 매끄럽게 서비스를 제공하려면 어플리케이션을 지속적으로 모니터링하고 관리해야함.
 * 모니터링과 관리에는 어플리케이션 상태 점검, 성능, 트래픽, 감사, 각종 측정지표, 재시작, 로그 레벨 변경 등
 * 다양한 작업이 포함된다.
 * 어플리케이션의 동작을 분석하고 필요한 조치를 취하려면 다양한 모니터링 정보와 상세한 측정 지표가 필요.
 *
 * 액추에이터는 부트 애플리케이션 모니터링과 관리에 필요한 기능을 제공한다.
 * 주요 장점 중 하나는 운영에 필요한 광범위한 기능을 직접 구현할 필요 없이 쉽게 사용할 수 있다는 점이다.
 *
 * application.properties파일의 menagement.endpoints.web.exposure.include 프로퍼티에 *를 값으로
 * 지정하면 액추에이터가 제공하는 모든 엔드포인트를 웹으로 노출함
 *
 * 필요한 엔드포인트만 노출하고 싶다면 * 대신 엔드포인트 이름을 쉼표로 연결해서 include=info,health와 같이 지정하면 된다.
 *
 * health 엔드포인트는 status: UP을 반환한다.
 * UP은 어플리케이션의 전반적인 상태가 정상이고 어플리케이션의 모든 구성 요소가 접속 가능한 상태라는 것을 의미한다.
 * 물론 커스텀도 가능하다.
 */
@SpringBootApplication
public class SpringBootAppDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppDemoApplication.class, args);
	}

}

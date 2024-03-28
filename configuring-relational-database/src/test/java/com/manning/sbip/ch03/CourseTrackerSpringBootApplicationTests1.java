package com.manning.sbip.ch03;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class CourseTrackerSpringBootApplicationTests1 {

    /**
     * MognoTemplate 주입
     * 몽고DB 연산을 수행할 수 있도록 도와주는 헬퍼 클래스.
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void givenObjectAvailableWhenSaveToCollectionThenExpectValue() throws Exception{

        /**
         * Manning = key / Spring Boot In Practice = value -> 이름이 collection인 몽고DB collection에 저장
         * collection 에서 Manning으로 조회해 값이 Spring Boot in Practice가 맞는지 검증
         */
        //given
        DBObject object = BasicDBObjectBuilder.start()
                .add("Manning", "Spring Boot In Practice").get();

        //when
        mongoTemplate.save(object, "collection");

        //then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
                .extracting("Manning")
                .containsOnly("Spring Boot In Practice");
    }
}

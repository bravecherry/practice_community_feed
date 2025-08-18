package org.fastcampus.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    // 쿼리문을 직접 쓰거나 persist를 활용하고자 할 때 호출
    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNameList;
    private List<String> notGeneratedTableNameList;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 엔티티 중 테이블인 클래스들 이름 조회
        tableNameList = entityManager.getMetamodel().getEntities().stream()
                .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
                .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
                .toList();

        // id가 자동생성되지 않는 테이블 관리
        notGeneratedTableNameList = List.of("user_community_relation", "content_like");
    }

    @Transactional
    public void execute() {
        // 혹시 모를 수행이 아직 안된 쿼리문을 모두 반영
        entityManager.flush();
        // 테이블 삭제 전에 F키와 같은 설정 값들이 있을 때 삭제 제한이 걸리지 않도록 변경하는 옵션
        entityManager.createNativeQuery("SET REFERENCIAL_INTEGRITY FALSE").executeUpdate();
        for (String tableName : tableNameList) {
            // truncate table
            entityManager.createNativeQuery("truncate table " + tableName).executeUpdate();

            // set table id as 1
            if (!notGeneratedTableNameList.contains(tableName)) {
                entityManager.createNativeQuery("alter table " + tableName + " alter column id restart with 1").executeUpdate();
            }
        }
        // 위의 수정 사항 반영 (truncate 하고, id: 1로 맞추기)
        entityManager.createNativeQuery("SET REFERENCIAL_INTEGRITY TRUE").executeUpdate();
    }
}

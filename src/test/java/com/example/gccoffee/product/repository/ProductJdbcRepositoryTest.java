package com.example.gccoffee.product.repository;

import com.example.gccoffee.product.domain.Category;
import com.example.gccoffee.product.domain.Product;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.distribution.Version;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import static com.wix.mysql.config.MysqldConfig.*;

@ActiveProfiles("test")
@SpringBootTest
class ProductJdbcRepositoryTest {

    static EmbeddedMysql embeddedMysql;

    @Autowired
    ProductJdbcRepository productJdbcRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeAll
    static void setup() {
        var config = aMysqldConfig(Version.v8_0_11)
                .withCharset(Charset.UTF8MB4)
                .withPort(2215)
                .withUser("test", "test1234")
                .withTimeZone("Asia/Seoul")
                .build();
        embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema("test-order_mgmt", ScriptResolver.classPathScripts("schema.sql")).start();
    }

    private static Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN, 1000L);

    @AfterEach
    void initDb() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "products");
    }

    @Test
    void 생성_테스트() {
        productJdbcRepository.insert(newProduct);

        List<Product> products = productJdbcRepository.findAll();

        assertThat(products).hasSize(1);
    }

    @Test
    void 제품명_조회_테스트() {
        productJdbcRepository.insert(newProduct);

        Optional<Product> product = productJdbcRepository.findById(newProduct.getProductId());

        assertThat(product.get().getProductId()).isEqualTo(newProduct.getProductId());
    }

    @Test
    void 제품_삭제_테스트() {
        productJdbcRepository.deleteAll();

        List<Product> products = productJdbcRepository.findAll();
        assertThat(products.isEmpty()).isTrue();
    }

    @Test
    void 제품_업데이트_테스트() {
        Product inserProduct = productJdbcRepository.insert(newProduct);
        inserProduct.setProductName("newnew");

        System.out.println(newProduct.getProductId());

        productJdbcRepository.update(inserProduct);
        Optional<Product> lastProduct = productJdbcRepository.findById(inserProduct.getProductId());

        assertThat(lastProduct.get().getProductName()).isEqualTo("newnew");

    }
}
package com.auc.auction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = AuctionApplication.class)
@TestPropertySource(properties = "couchbase.bucket.allowances.name=test-bucket")
class AuctionApplicationTests {
	//@Test
	void contextLoads() {
	}

}

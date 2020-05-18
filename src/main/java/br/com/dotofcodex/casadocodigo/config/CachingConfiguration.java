package br.com.dotofcodex.casadocodigo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

// https://stackoverflow.com/questions/21944202/using-ehcache-in-spring-4-without-xml

@EnableCaching
@Configuration
public class CachingConfiguration {

	@Bean
	public net.sf.ehcache.config.Configuration configuration() {
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(bookCache());
		return config;
	}

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager(net.sf.ehcache.config.Configuration configuration) {
		return net.sf.ehcache.CacheManager.newInstance(configuration);
	}

	@Bean
	public EhCacheCacheManager cacheManager(net.sf.ehcache.CacheManager ehCacheManager) {
		return new EhCacheCacheManager(ehCacheManager);
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Bean
	public CacheResolver cacheResolver(CacheManager cacheManager) {
		return new SimpleCacheResolver(cacheManager);
	}

	@Bean
	public CacheErrorHandler errorHandler() {
		return new SimpleCacheErrorHandler();
	}

	private CacheConfiguration bookCache() {
		CacheConfiguration cc = new CacheConfiguration();
		cc.setName("books");
		cc.setMemoryStoreEvictionPolicy("LRU");
		cc.setMaxEntriesLocalHeap(100);
		return cc;
	}

}
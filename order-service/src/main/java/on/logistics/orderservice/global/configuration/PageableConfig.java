package on.logistics.orderservice.global.configuration;

import java.util.List;
import lombok.RequiredArgsConstructor;
import on.logistics.orderservice.global.enums.PageNumber;
import on.logistics.orderservice.global.enums.PageSize;
import on.logistics.orderservice.global.enums.PageSortBy;
import on.logistics.orderservice.global.resolver.PageableVerificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class PageableConfig implements WebMvcConfigurer {

  private final PageableVerificationArgumentResolver pageableVerificationArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(pageableVerificationArgumentResolver);
  }
}

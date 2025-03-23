# Makefile

# Step 1: 필수 인프라만 실행
infra:
	docker-compose up --build -d redis eureka-server-db eureka-server

# Step 2: 모니터링 서비스 실행
monitor:
	docker-compose up --build -d zipkin grafana prometheus

# Step 3: 핵심 서비스 빌드 및 실행
core:
	docker-compose up --build -d auth-service user-service api-gateway

# Step 4: 나머지 서비스 순차 실행
rest:
	docker-compose up --build -d company-service order-service hub-transit-service
	docker-compose up --build -d delivery-service hub-service map-service
	docker-compose up --build -d product-service ai-service delivery-manager-service

# 전체 빌드 & 실행 (비추천: 너무 무거움)
all:
	docker-compose up --build -d

# 특정 서비스만 실행 (예: make run SERVICE=auth-service)
run:
	docker-compose up --build -d $(SERVICE)

# 특정 서비스만 빌드
build:
	docker-compose build $(SERVICE)

# 전체 종료 (볼륨 보존)
down:
	docker-compose down

# 전체 종료 + 볼륨 제거
down-v:
	docker-compose down -v

# 로그 보기 (예: make logs SERVICE=auth-service)
logs:
	docker-compose logs -f $(SERVICE)
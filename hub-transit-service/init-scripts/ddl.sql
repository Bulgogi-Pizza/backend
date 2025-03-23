CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- p_route 테이블
CREATE TABLE p_route (
                         route_id UUID PRIMARY KEY DEFAULT (uuid_generate_v4()),
                         start_hub_name VARCHAR(255) NOT NULL,
                         end_hub_name VARCHAR(255) NOT NULL,
                         path_json jsonb NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         created_by UUID NULL,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         updated_by UUID NULL,
                         deleted_at TIMESTAMP NULL,
                         deleted_by UUID NULL,
                         is_deleted BOOLEAN DEFAULT FALSE
);

-- p_hub_transit 테이블
CREATE TABLE p_hub_transit (
                               id UUID PRIMARY KEY DEFAULT (uuid_generate_v4()),
                               delivery_id UUID NOT NULL,
                               delivery_record_id UUID NOT NULL,
                               current_hub_id UUID NOT NULL,
                               current_hub_name VARCHAR(255) NOT NULL,
                               next_hub_id UUID NOT NULL,
                               next_hub_name VARCHAR(255) NOT NULL,
                               next_delivery_type VARCHAR(50) NOT NULL,
                               user_id UUID,
                               route_snapshot jsonb NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               created_by UUID NOT NULL,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               updated_by UUID NOT NULL,
                               is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                               deleted_at TIMESTAMP NULL,
                               deleted_by UUID NULL
);
#!/bin/bash

echo "Step 1: Starting infrastructure containers..."
make infra

echo "Step 2: Starting core services..."
make core

echo "Step 3: Starting remaining services..."
make rest

echo "All services are up and running!"
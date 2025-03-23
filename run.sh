#!/bin/bash

echo "Step 1: Starting infrastructure containers..."
make infra

echo "Step 2: Starting monitoring services..."
make monitor

echo "Step 3: Starting core services..."
make core

echo "Step 4: Starting remaining services..."
make rest

echo "All services are up and running!"